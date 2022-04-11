package com.eatery.cd.service;

import com.eatery.cd.mapper.EvaMapper;
import com.eatery.cd.mapper.OrderMapper;
import com.eatery.cd.pojo.Evaluate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 评论管理
 */
@Service
public class EvaServiceImpl implements EvaService {

    @Autowired
    private EvaMapper evaMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void saveEva(String orderId, String productName, String evaContent, String status) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Evaluate evaluate = new Evaluate();
        evaluate.setOrderId(orderId);
        evaluate.setProductName(productName);
        evaluate.setEvaContent(evaContent);
        evaluate.setEvaDate(sdf.format(date));
        orderMapper.updateOrderStatus(status, orderId);
        evaMapper.saveEva(evaluate);
    }

    @Override
    public List<Evaluate> findAllEvaList() {
        return evaMapper.findAllEvaList();
    }

    @Override
    public Evaluate findEvaListByOrderId(String orderId) {
        return evaMapper.findEvaListByOrderId(orderId);
    }

    @Override
    public void saveAnsEva(String orderId, String ansContent) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
       String ansDate = sdf.format(date);
        evaMapper.saveAnsEva(ansDate,ansContent,orderId);

    }

    @Override
    public List<Evaluate> find2Eva() {
        return evaMapper.find2Eva();
    }
}
