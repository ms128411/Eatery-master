package net.togogo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class NewsInfo {

    private int newsID;//新闻id
    private String photoUrl;//封面url地址
    private String title;//新闻标题
    private int authorID;//作者编号
//    private String authorName;
    private int newsTypeID;//新闻类别id
    private String content;//新闻内容 String类型可以接收数据库的text
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //出参格式化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")   //入参格式化，具体说明可见https://blog.csdn.net/zhou520yue520/article/details/81348926
    private Date date;//新闻发布时间

    private String userName;
    private String newsType;

}
