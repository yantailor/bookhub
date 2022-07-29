package com.yantailor.bookhub.utils;

import com.yantailor.bookhub.bean.R;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * Created by yantailor
 * on 2022/2/12 18:18 @Version 1.0
 */
public class ValidationUtil {

    public static R bindingResultCheck(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            R r = R.error();
            int errorCounter = 1;
            for(ObjectError error : bindingResult.getAllErrors()){
                r.data("error"+errorCounter, error.getDefaultMessage());
                errorCounter++;
            }
            return r;
        }
        return null;
    }
}
