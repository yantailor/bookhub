package com.yantailor.bookhub.utils.transfer;

import com.yantailor.bookhub.entity.Book;
import com.yantailor.bookhub.entity.po.BookPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by yantailor
 * on 2022/7/5 19:53 @Version 1.0
 */
@Mapper
public interface ToBookPo {
    ToBookPo INSTANCE = Mappers.getMapper(ToBookPo.class);

    BookPo toBookPo(Book book);
}
