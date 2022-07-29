package com.yantailor.bookhub.service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yantailor.bookhub.dao.BookDao;
import com.yantailor.bookhub.dao.ItemDao;
import com.yantailor.bookhub.entity.Book;
import com.yantailor.bookhub.entity.Item;
import com.yantailor.bookhub.entity.po.ItemPo;
import com.yantailor.bookhub.entity.vo.ItemShow;
import com.yantailor.bookhub.service.ItemService;
import com.yantailor.bookhub.utils.TransferUtil;
import com.yantailor.bookhub.utils.mapToObjectUtil;
import com.yantailor.bookhub.utils.transfer.ToItemPo;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by yantailor
 * on 2022/7/3 23:00 @Version 1.0
 */
@Service
@Transactional
public class ItemServiceImpl extends ServiceImpl<ItemDao , ItemPo> implements ItemService {

    RestHighLevelClient client;
    @Autowired
    public void RestHighLevelClient(RestHighLevelClient restHighLevelClient){
        this.client = restHighLevelClient;
    }

    @Autowired
    TransferUtil transferUtil;
    @Autowired
    BookDao bookDao;

    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    @Value("${bookhub.bookCoverFile}")
    private String bookCoverPath;

    @Value("${bookhub.bookSourceFile}")
    private String bookSourcePath;

//    @Value("${bookhub.desPath}")
    private String desPath = "mvc\\bookhubFile";
    @Override
    public void addItem(Item item, MultipartFile bookCover[], MultipartFile[] bookSource) throws IOException {
        ItemPo itemPo = ToItemPo.INSTANCE.toItemPo(item);
        itemPo.setBookName(item.getBook().getBookName());
        StringBuilder stringBuilder = new StringBuilder();
        if(item.getTagsVo()!=null){
            for(String tag : item.getTagsVo()){
                stringBuilder.append(tag+",");
            }
            itemPo.setTags(stringBuilder.toString());
        }

        //SQL传入-----
        //itemPO传入
        baseMapper.insert(itemPo);

        //bookPO传入
        Book book = item.getBook();
        book.setBookId(baseMapper.getLastItemId());
        book.setBookCoverUrl(desPath+bookCoverPath+bookCover[0].getOriginalFilename());
        book.setBookSourceUrl(desPath+bookSourcePath+bookSource[0].getOriginalFilename());
        bookDao.insert(book);

        //ES传入-----
        try {
            //item索引
            IndexRequest requestItem = new IndexRequest("item");
            itemPo.setItemId(book.getBookId());
            // 将我们的数据放入请求 json
            System.out.println(gson.toJson(itemPo));
            requestItem
                    .source(gson.toJson(itemPo), XContentType.JSON);
            // 客户端发送请求 , 获取响应的结果
            IndexResponse ResponseItem = client.index(requestItem, RequestOptions.DEFAULT);
            System.out.println(ResponseItem.toString()); //
            System.out.println(ResponseItem.status()); // 对应我们命令返回的状态CREATED

            //book索引
            IndexRequest requestBook = new IndexRequest("book");
            requestBook.source(gson.toJson(book), XContentType.JSON);
            IndexResponse ResponseBook = client.index(requestBook, RequestOptions.DEFAULT);
            System.out.println(ResponseBook.toString()); //
            System.out.println(ResponseBook.status()); // 对应我们命令返回的状态CREATED
        }catch (Exception e){
            System.out.println("es数据存储失败");
            e.printStackTrace();
            throw e;
        }

        //文件传入
        transferUtil.TransferFile(bookCover[0],bookCoverPath);
        transferUtil.TransferFile(bookSource[0],bookSourcePath);
    }

    //获取前端展示书籍
    @Override
    public List<ItemShow> getFrontItem(int page) {
        int offset = page*8;
        List<ItemShow> res ;
        try {
            res = baseMapper.getTagsBook(null,offset,8);
        } catch (Exception e) {
            log.error("获取前端书籍数据错误");
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public Item getSingleBook(int bookId) throws IOException, InstantiationException, IllegalAccessException, ParseException {
        //先查item索引上的信息
        SearchRequest itemRequest = new SearchRequest("item");
        SearchSourceBuilder itemSourceBuilder = new SearchSourceBuilder();
        TermQueryBuilder itemQueryBuilder = QueryBuilders.termQuery("itemId",bookId);
        itemSourceBuilder.query(itemQueryBuilder);
        itemRequest.source(itemSourceBuilder);
        SearchResponse itemResponse = client.search(itemRequest, RequestOptions.DEFAULT);
        SearchHit[] itemhits = itemResponse.getHits().getHits();
        Item res = mapToObjectUtil.mapToObject(itemhits[0].getSourceAsMap(),Item.class);

        //再查书本索引上的信息
        SearchRequest bookRequest = new SearchRequest("book");
        SearchSourceBuilder bookSourceBuilder = new SearchSourceBuilder();
        TermQueryBuilder bookQueryBuilder = QueryBuilders.termQuery("bookId", bookId);
        bookSourceBuilder.query(bookQueryBuilder);
        bookRequest.source(bookSourceBuilder);
        SearchResponse bookResponse = client.search(bookRequest, RequestOptions.DEFAULT);
        SearchHit[] bookhits = bookResponse.getHits().getHits();
        Book book = mapToObjectUtil.mapToObject(bookhits[0].getSourceAsMap(),Book.class);
        res.setBook(book);

        System.out.println(res);


        //

        return res;
    }
}
