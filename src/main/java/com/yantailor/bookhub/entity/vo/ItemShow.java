package com.yantailor.bookhub.entity.vo;

import lombok.Data;

/**
 * Created by yantailor
 * on 2022/7/6 0:44 @Version 1.0
 */
@Data
public class ItemShow {
    private int bookId;
    private String bookName;
    private String bookAuthor;
    private String bookTranslator;
    private String bookCoverUrl;


}
