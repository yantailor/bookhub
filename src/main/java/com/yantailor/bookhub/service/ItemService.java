package com.yantailor.bookhub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yantailor.bookhub.dao.ItemDao;
import com.yantailor.bookhub.entity.Item;
import com.yantailor.bookhub.entity.po.ItemPo;
import com.yantailor.bookhub.entity.vo.ItemShow;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by yantailor
 * on 2022/7/3 15:08 @Version 1.0
 */
@Service
public interface ItemService extends IService<ItemPo> {

    //添加展示的图书
    void addItem(Item item, MultipartFile[] bookCover , MultipartFile[] bookSource) throws IOException;

    //获取前端展示的图书
    List<ItemShow> getFrontItem(int page);

    //获取单个图书要展示的数据
    Item getSingleBook(int bookId) throws IOException, InstantiationException, IllegalAccessException, ParseException;
}
