package com.yantailor.bookhub.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yantailor.bookhub.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by yantailor
 * on 2022/7/5 12:57 @Version 1.0
 */
@Mapper
@Repository
public interface BookDao extends BaseMapper<Book> {

}
