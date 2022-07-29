package com.yantailor.bookhub.controller;

import com.yantailor.bookhub.bean.R;
import com.yantailor.bookhub.entity.BookSearch;
import com.yantailor.bookhub.entity.vo.ItemShow;
import com.yantailor.bookhub.entity.vo.ItemShowWithHits;
import com.yantailor.bookhub.service.BookSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by yantailor
 * on 2022/7/16 12:51 @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/search")
public class BookSearchController {

    @Autowired
    private BookSearchService bookSearchService;

    @PostMapping("/mixed")
    private R mixedSearch(BookSearch content,int page) throws IOException, InstantiationException, IllegalAccessException, ParseException {

        ItemShowWithHits itemShowWithHits = bookSearchService.bookSearch(content, page);
        return R.ok().data("mixedItem",itemShowWithHits.getItemShowList()).data("hitsTotal",itemShowWithHits.getHitsTotal());
    }


}
