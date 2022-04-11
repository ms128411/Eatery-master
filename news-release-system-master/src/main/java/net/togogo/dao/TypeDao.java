package net.togogo.dao;
import net.togogo.domain.Type;

import java.util.List;

/*类型服务实现接口 增删查改*/
public interface TypeDao {
    /*分页查询所有类别*/
    public List<Type> getAllType();
    //查询某个类别
    public Type getTypeByNy(String newsType);
    /*修改密码*/

    public int updateNt(int id, String newNt);

    public int addType(String newType);

    public int deleteType(List<Integer> ids);

    public List<Type> searchType(String newsType);

    public List<String> getAllType2();

    public Type searchType1(String newsType);

}

