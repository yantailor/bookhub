package com.yantailor.bookhub.controller;

import com.yantailor.bookhub.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yantailor
 * on 2022/7/3 15:09 @Version 1.0
 */
@RestController
public class testController {
    @Autowired
    ItemService itemService;

    @RequestMapping("/test")
    public void tt202273(){
//        itemService.addItem();
    }
}
