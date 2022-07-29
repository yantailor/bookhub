package com.yantailor.bookhub.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by yantailor
 * on 2022/7/24 11:59 @Version 1.0
 */
@Data
public class ItemShowWithHits{
    public volatile static ItemShowWithHits INSTANCE ;

    private ItemShowWithHits(){

    }

    public static ItemShowWithHits getINSTANCE(){
        if(INSTANCE == null){
            synchronized (ItemShowWithHits.class){
                if(INSTANCE == null){
                    INSTANCE =new ItemShowWithHits();
                }
            }
        }
        return INSTANCE;
    }

    private List<ItemShow> itemShowList;

    private Long hitsTotal;
}
