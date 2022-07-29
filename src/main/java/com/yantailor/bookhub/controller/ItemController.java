package com.yantailor.bookhub.controller;

import com.yantailor.bookhub.bean.R;
import com.yantailor.bookhub.entity.Item;
import com.yantailor.bookhub.entity.vo.ItemShow;
import com.yantailor.bookhub.service.ItemService;
import com.yantailor.bookhub.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * Created by yantailor
 * on 2022/7/3 23:03 @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping("/add")
    public R itemAdd(@Valid Item item , BindingResult bindingResult,
                     @RequestPart(name = "bookCover" ,required = true) MultipartFile[] bookCover,
                     @RequestPart(name = "bookSource" ,required = true) MultipartFile[] bookSource) throws IOException {
        R resultCheck = ValidationUtil.bindingResultCheck(bindingResult);
        if(resultCheck !=null){
            return resultCheck;
        }
        itemService.addItem(item,bookCover,bookSource);

        return R.ok();
    }

    @GetMapping("/getFrontItem")
    public R getFrontItem(int page){
        List<ItemShow> frontItem = itemService.getFrontItem(page);
        return R.ok().data("frontItem",frontItem);
    }


    @GetMapping("/getSingleBook")
    public R getSingleBook(int BookId) throws IOException, InstantiationException, IllegalAccessException, ParseException {
        Item singleBook = itemService.getSingleBook(BookId);
        return R.ok().data("singleBook",singleBook);
    }

}
