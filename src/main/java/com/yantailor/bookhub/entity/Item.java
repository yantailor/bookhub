package com.yantailor.bookhub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by yantailor
 * on 2022/7/3 15:02 @Version 1.0
 */

@Data
public class Item {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private int itemId;

    //标签
    private String[] tagsVo;

//    @ApiModelProperty(hidden = true)
//    private String tags;



    //发布日期
    //前台传后台
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    //后台传前台
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    //图书类别
    @NotNull(message = "类别不为空")
    private String classify;

    //是否免费
    private boolean state = true;

    @Valid
    @NotNull(message = "book实体类不能为空")
    private Book book;
}
