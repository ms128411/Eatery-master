package com.eatery.cd.controller;

import com.eatery.cd.pojo.Product;
import com.eatery.cd.pojo.ProductKind;
import com.eatery.cd.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    //第一次直接跳转到页面
    @RequestMapping("/products")
    public String products(Model model) {
        //查询所有的商品信息列表
        List<Product> productList = productService.findAllProductList();
        model.addAttribute("productList", productList);
        return "/admin/Products_List";
    }

    //创建新对象
    @RequestMapping("/createOne")
    public String createOne() {

        return "/admin/picture-add";
    }


    //批量删除
    @RequestMapping("/deleteAllProduct")
    public String deleteAll(@RequestParam("productId") String[] productIds) {//
        productService.deleteAllProduct(productIds);
        return "redirect:/products";
    }

    //单体修改数据member-add.jsp,不需要@ReqeustMapping
    @RequestMapping("/updateOne")
    public String updateById(String productId, Model model) {
        Product product = productService.findProductById(productId);
        List<ProductKind> pk = productService.findAllKind();
        model.addAttribute("list", pk);
        model.addAttribute("product", product);
        return "/admin/picture-update";
    }

    @RequestMapping("/toInsert")//修改数据
    public String toupdate(Product p) {

        productService.updateOne(p);
        return "redirect:/products";
    }

    @RequestMapping("/deleteById")
    public String deleteById(String thisId) {

        productService.deleteById(thisId);
        System.out.print(thisId);
        return "redirect:/products";
    }

    @RequestMapping("/toAdd")
    public String toAdd(Model model) {
        List<ProductKind> pk = productService.findAllKind();
        model.addAttribute("list", pk);
        //单纯跳转
        return "/admin/picture-add";
    }
    @RequestMapping("/CreateOne")
    public String CreateOne(Product p, MultipartFile picture, HttpServletRequest request) throws IOException {
        String picName = picture.getOriginalFilename();
        //System.out.println("原始文件名:"+picName);
        int productKindId = p.getProductKind().getProductKindId();
        String newPicName = UUID.randomUUID()+picName;
//        System.out.println("新文件名:"+newPicName);
       //获取项目路径
        ServletContext sc = request.getSession().getServletContext();
        String localPath = "";
        if (productKindId == 1){
            localPath = "/static/pro/upload/jcc/";
        }else if(productKindId == 2){
            localPath = "/static/pro/upload/dfts/";
        } else if(productKindId == 3){
            localPath = "/static/pro/upload/qlyx/";
        } else {
            localPath = "/static/pro/upload/xcyl/";
        }
        //上传全路径
        String path = sc.getRealPath(localPath);
        if (!picture.isEmpty()){
            try{
                FileOutputStream fos = new FileOutputStream(path+newPicName);
                InputStream in = picture.getInputStream();
                int b = 0;
                while((b = in.read()) != -1){
                    fos.write(b);
                }
                fos.close();
                in.close();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.print(p);
        p.setPic(localPath+newPicName);
        productService.addOne(p);
        return "redirect:/products";
    }

    //按条件查找数据--模糊查询
    @RequestMapping("/toSelect")
    public String toSelect(@RequestParam("Pname") String Pname, Model model) {
        List<Product> list = productService.findAllProductCondition(Pname);
        model.addAttribute("productList", list);
        return "/admin/Products_List";
    }

    /**
     * picture-add.jsp
     * 查询商品种类名信息;查询热门商品信息;
     * 获取数据，然后跳转到list.jsp页面并回显数据
     */

    @RequestMapping("/toList")
    public String toList(@RequestParam(required = true, defaultValue = "1") Integer page, Model model) {
        PageHelper.startPage(page, 4);
        List<Product> productList = productService.findAllProductList();
        PageInfo<Product> p = new PageInfo<Product>(productList);
        //查询热门商品信息列表
        List<Product> hotProductList = productService.findHotProductList();
        model.addAttribute("page", p);
        model.addAttribute("productList", productList);
        model.addAttribute("hotProductList", hotProductList);
        return "/pages/list/list1";

    }

    /**
     * 查询商品种类名信息;查询热门商品信息;
     * 获取数据，然后跳转到list.jsp页面并回显数据
     * 根据输入的查询条件查询商品，然后跳转到list.jsp页面并回显数据
     * 获取查询条件(商品的名称(product_name), 商品的种类(name))
     * 商品的最低价格(minprice)及最高价格(maxprice)
     */
    @RequestMapping("/findProductByCondition")
    public String findProductByCondition(@RequestParam(required = true, defaultValue = "1") Integer page, String product_name, String name, Double minprice, Double maxprice, Model model) {
        PageHelper.startPage(page, 4);
        List<Product> productConditionList = productService.findProductByCondition(product_name,name,minprice,maxprice);
        List<Product> HotProductList = productService.findHotProductList();
        PageInfo<Product> p = new PageInfo<Product>(productConditionList);
        model.addAttribute("productList", productConditionList);
        model.addAttribute("HotProductList", HotProductList);
        model.addAttribute("pages", p);
        return "/pages/list/list";
    }

    /*食物查询*/
    @RequestMapping("/searchByConditition")
    public String searchByConditition(@RequestParam(required = true, defaultValue = "1") Integer page,String keyword, Model model){
        PageHelper.startPage(page, 4);
        List<Product> productConditionList = productService.searchByConditition(keyword);
        List<Product> HotProductList = productService.findHotProductList();
        PageInfo<Product> p = new PageInfo<Product>(productConditionList);
        model.addAttribute("productList", productConditionList);
        model.addAttribute("HotProductList", HotProductList);
        model.addAttribute("keyword",keyword);
        model.addAttribute("pages", p);
        return "/pages/list/list3";
    }
    /**
     * 查询商品种类名信息;查询热门商品信息;
     * 获取数据，然后跳转到list.jsp页面并回显数据
     * 根据输入的查询条件查询商品，然后跳转到list.jsp页面并回显数据
     * 获取查询条件(商品的名称(product_name), 商品的种类(name))
     * 商品的最低价格(minprice)及最高价格(maxprice)
     */
    @RequestMapping("/findProductByCondition2")
    public String findProductByCondition2(@RequestParam(required = true, defaultValue = "1") Integer page, String productKindId, String product_name, String name, Double minprice, Double maxprice, Model model) {
        PageHelper.startPage(page, 4);
        List<Product> productConditionList = productService.findProductByCondition(product_name, name, minprice, maxprice);
        List<Product> HotProductList = productService.findHotProductList();
        PageInfo<Product> p = new PageInfo<Product>(productConditionList);
        model.addAttribute("productList", productConditionList);
        model.addAttribute("productKindId", productKindId);
        model.addAttribute("HotProductList", HotProductList);
        model.addAttribute("pages", p);
        return "/pages/list/list2";
    }
    @RequestMapping("/findProductByCategory")
    public String findProductByCategory(@RequestParam(required = true, defaultValue = "1") Integer page,String productKindId,Model model){
        PageHelper.startPage(page, 4);
        List<Product> productList = productService.findProductByCategory(productKindId);
        List<Product> HotProductList = productService.findHotProductList();
        PageInfo<Product> p = new PageInfo<Product>(productList);
        model.addAttribute("productList", productList);
        model.addAttribute("productKindId", productKindId);
        model.addAttribute("HotProductList", HotProductList);
        model.addAttribute("pages", p);
        return "/pages/list/list2";
    }
}
