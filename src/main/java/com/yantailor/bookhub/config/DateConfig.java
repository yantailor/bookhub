package com.yantailor.bookhub.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by yantailor
 * on 2022/7/16 11:11 @Version 1.0
 */
@Component
public class DateConfig implements MetaObjectHandler {
    /**
     * 使用mp做添加操作时候，这个方法执行
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //设置属性值
        this.setFieldValByName("uploadDate",new Date(),metaObject);
//        this.setFieldValByName("updateTime",new Date(),metaObject);
    }

    /**
     * 使用mp做修改操作时候，这个方法执行
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
//        this.setFieldValByName("updateTime",new Date(),metaObject);
    }

}
