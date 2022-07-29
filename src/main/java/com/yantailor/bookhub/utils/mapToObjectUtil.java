package com.yantailor.bookhub.utils;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by yantailor
 * on 2022/7/26 11:52 @Version 1.0
 */
public class mapToObjectUtil {
    public static <T> T mapToObject(Map source , Class<T> target) throws InstantiationException, IllegalAccessException, ParseException {
        Field[] declaredFields = target.getDeclaredFields();
        T instance = target.newInstance();
        for(Field field : declaredFields){
            if(source.get(field.getName()) != null){
                System.out.println(field.getName());
                field.setAccessible(true);
                if(field.getName().equals("date")){
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = simpleDateFormat.parse(source.get(field.getName()).toString());
                    field.set(instance,date);
                    continue;
                }
                field.set(instance,source.get(field.getName()));
            }
        }

        return instance;
    }
}
