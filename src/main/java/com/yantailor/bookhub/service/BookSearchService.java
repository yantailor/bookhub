package com.yantailor.bookhub.service;

import com.yantailor.bookhub.entity.BookSearch;
import com.yantailor.bookhub.entity.vo.ItemShow;
import com.yantailor.bookhub.entity.vo.ItemShowWithHits;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by yantailor
 * on 2022/7/16 11:33 @Version 1.0
 */
public interface BookSearchService {
    ItemShowWithHits bookSearch(BookSearch bookSearch, int page) throws IOException, InstantiationException, IllegalAccessException, ParseException;
}
