package com.yantailor.bookhub.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created by yantailor
 * on 2022/7/5 19:49 @Version 1.0
 */
@Data
@NoArgsConstructor
@TableName("book")
public class BookPo {
    private int bookId;

    private String bookName;

    private String bookAuthor;

    //译者
    private String bookTranslator;

    //简介
    private String bookBriefIntroduction;

    //本书特色
    private String bookCharacteristic;

    //封面图片URL
//    @NotNull(message = "封面图片需要添加")
    private String bookCoverUrl;

    //图书资源URL
    private String bookSourceUrl;
}
