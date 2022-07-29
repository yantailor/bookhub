package com.yantailor.bookhub;

import com.yantailor.bookhub.dao.ItemDao;
import com.yantailor.bookhub.entity.vo.ItemShow;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class BookhubApplicationTests {

    @Autowired
    ItemDao itemDao;

    @Autowired
    RestHighLevelClient client;
    @Test
    void contextLoads() throws IOException {
//        GetRequest getRequest = new GetRequest("item", "DgHo_4EB_7H26PsNmkjc");
//        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
//        System.out.println(getResponse.getSourceAsString()); // 打印文档的内容
//        System.out.println(getResponse); // 返回的全部内容和命令式一样的
        System.out.println(ClassLoader.getSystemResource(""));
    }

}
