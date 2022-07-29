package com.yantailor.bookhub.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yantailor.bookhub.entity.Item;
import com.yantailor.bookhub.entity.po.ItemPo;
import com.yantailor.bookhub.entity.vo.ItemShow;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yantailor
 * on 2022/7/3 15:07 @Version 1.0
 */
@Mapper
@Repository
public interface ItemDao extends BaseMapper<ItemPo> {
    @Select("SELECT LAST_INSERT_ID()")
    public int getLastItemId();

    //获取 单tag 展示书籍 每页展 示8本
    @Select("SELECT book_id , item.book_name , book_author , book_translator , book_cover_url FROM book INNER JOIN item ON book.`book_id` = item.`item_id` WHERE item.`tags` LIKE concat('%',ifnull(#{tag},''),'%') limit #{offset},#{count}")
    public List<ItemShow> getTagsBook(String tag,int offset, int count);
}
