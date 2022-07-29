package com.yantailor.bookhub.service.impl;

import com.google.gson.Gson;
import com.yantailor.bookhub.entity.BookSearch;
import com.yantailor.bookhub.entity.vo.ItemShow;
import com.yantailor.bookhub.entity.vo.ItemShowWithHits;
import com.yantailor.bookhub.service.BookSearchService;
import com.yantailor.bookhub.utils.mapToObjectUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by yantailor
 * on 2022/7/16 11:33 @Version 1.0
 */
@Service
public class BookSearchServiceImpl implements BookSearchService {

    RestHighLevelClient client;
    @Autowired
    public void RestHighLevelClient(RestHighLevelClient restHighLevelClient){
        this.client = restHighLevelClient;
    }

    @Override
    public ItemShowWithHits bookSearch(BookSearch bookSearch , int page) throws IOException, InstantiationException, IllegalAccessException, ParseException {
        long start = System.currentTimeMillis();
        int offset = (page-1)*9;
        //处理字符串空串
        if(bookSearch.getBookClassify().equals("null")){
            bookSearch.setBookClassify(null);
        }
        if(bookSearch.getTag().equals("null")){
            bookSearch.setTag(null);
        }
        if(bookSearch.getSearchContent().equals("null") || bookSearch.getSearchContent().isBlank()){
            bookSearch.setSearchContent(null);
        }
        SearchRequest searchRequest = new SearchRequest("item");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // bool类型查询构造器
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        //匹配图书分类
        if(bookSearch.getBookClassify() != null) {
            TermQueryBuilder termQueryBuilder1 = QueryBuilders.termQuery("classify", bookSearch.getBookClassify());
            boolBuilder.must(termQueryBuilder1);
        }
        //匹配书籍状态
        TermQueryBuilder termQueryBuilder2 = QueryBuilders.termQuery("state", bookSearch.isState());
        boolBuilder.must(termQueryBuilder2);
        //匹配标签
        if(bookSearch.getTag()!=null) {
            TermQueryBuilder termQueryBuilder3 = QueryBuilders.termQuery("tags", bookSearch.getTag());
            boolBuilder.must(termQueryBuilder3);
        }
        //匹配搜索内容
        if(bookSearch.getSearchContent()!=null) {
            TermQueryBuilder termQueryBuilder4 = QueryBuilders.termQuery("bookName", bookSearch.getSearchContent().toLowerCase(Locale.ROOT));
            boolBuilder.must(termQueryBuilder4);
        }

        sourceBuilder.query(boolBuilder)
                .from(offset)
                .size(9);
        searchRequest.source(sourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println("总条数：" + response.getHits().getTotalHits().value);
        System.out.println("获取文档最大得分：" + response.getHits().getMaxScore());

        SearchHit[] hits = response.getHits().getHits();
        List<ItemShow> itemShowList = new ArrayList<>();
        int[] idList = new int[hits.length];
        int temp = 0;
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsMap());
            Integer itemId = (Integer)hit.getSourceAsMap().get("itemId");
            idList[temp] = itemId;
            temp++;
        }
        SearchRequest searchRequest1 = new SearchRequest("book");
        SearchSourceBuilder sourceBuilder1 = new SearchSourceBuilder();
        TermsQueryBuilder builder1 = QueryBuilders.termsQuery("bookId", idList);
        sourceBuilder1.query(builder1);
        searchRequest1.source(sourceBuilder1);
        SearchResponse response1 = client.search(searchRequest1, RequestOptions.DEFAULT);
        SearchHit[] hits1 = response1.getHits().getHits();
        for(SearchHit hit:hits1){
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            ItemShow itemShow = mapToObjectUtil.mapToObject(sourceAsMap, ItemShow.class);
            itemShowList.add(itemShow);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        ItemShowWithHits instance = ItemShowWithHits.getINSTANCE();
        instance.setItemShowList(itemShowList);
        instance.setHitsTotal(response.getHits().getTotalHits().value);
        return instance;
    }



}
