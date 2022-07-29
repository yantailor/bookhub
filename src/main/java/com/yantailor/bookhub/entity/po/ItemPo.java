package com.yantailor.bookhub.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by yantailor
 * on 2022/7/5 19:47 @Version 1.0
 */
@Data
@TableName("item")
public class ItemPo {

    private int itemId;


    private String tags;



    //发布日期
    //前台传后台
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    //后台传前台
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    //图书类别
    private String classify;

    //是否免费
    private boolean state = true;

    //书籍上传日期
    @TableField(fill = FieldFill.INSERT) //创建时自动填充
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date uploadDate;

    //书籍名称
    private String bookName;




}
