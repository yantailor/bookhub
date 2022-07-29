package com.yantailor.bookhub.utils.esUtil;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yantailor
 * on 2022/7/15 23:09 @Version 1.0
 */
@Component
public class RestHighLevelClientUtil {
    RestHighLevelClient client;
    @Autowired
    public void RestHighLevelClient(RestHighLevelClient restHighLevelClient){
        this.client = restHighLevelClient;
    }


}
