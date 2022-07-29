package com.yantailor.bookhub.utils.transfer;

import com.yantailor.bookhub.entity.Item;
import com.yantailor.bookhub.entity.po.ItemPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by yantailor
 * on 2022/7/5 19:55 @Version 1.0
 */

@Mapper
public interface ToItemPo {
    ToItemPo INSTANCE = Mappers.getMapper(ToItemPo.class);

    ItemPo toItemPo(Item item);
}
