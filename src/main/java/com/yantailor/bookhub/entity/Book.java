package com.yantailor.bookhub.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.NotNull;

/**
 * Created by yantailor
 * on 2022/6/30 14:31 @Version 1.0
 */

@Data
@NoArgsConstructor
@TableName("book")
public class Book {

    @ApiModelProperty(hidden = true)
    private int bookId;

    @NotNull(message = "书名不为空")
    @Length(max = 50 , message = "书名长度过长,50字以内")
    private String bookName;

    @NotNull(message = "作者不为空,可填未知")
    @Length(max = 30 , message = "作者名字过长,30字以内")
    private String bookAuthor;

    //译者
    @Length(max = 30 , message = "译者名字过长,30字以内")
    private String bookTranslator;

    //简介
    @Length(max = 500, message = "简介过长,500字以内")
    private String bookBriefIntroduction;

    //本书特色
    @Length(max = 500, message = "特色介绍过长,500字以内")
    private String bookCharacteristic;

    //封面图片URL
//    @NotNull(message = "封面图片需要添加")
    @ApiModelProperty(hidden = true)
    private String bookCoverUrl;

    //图书资源URL
//    @NotNull(message = "需要上传图书资源")
    @ApiModelProperty(hidden = true)
    private String bookSourceUrl;
}
