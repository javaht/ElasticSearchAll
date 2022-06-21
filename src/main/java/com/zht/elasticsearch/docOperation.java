package com.zht.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;


public class docOperation {

    public static void main(String[] args) throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.20.62",9200,"http")));

         //===================文档操作===============================
     /*   IndexRequest IndexRequest = new IndexRequest();
        IndexRequest.index("user").id("1001");
        User user = new User();
        user.setName("zhangsan");
        user.setAge(30);
        user.setSex("男");
        IndexRequest.source(JSON.toJSONString(user), XContentType.JSON);
        IndexResponse response = client.index(IndexRequest, RequestOptions.DEFAULT);
        System.out.println("_index:" + response.getIndex()+"_id:" + response.getId()+"_result:" + response.getResult());*/

        //=====================修改文档==========================
    /*    UpdateRequest updateRequest = new UpdateRequest();

        updateRequest.index("user").id("1001");
        updateRequest.doc(XContentType.JSON, "sex", "女");
        UpdateResponse updateResponse = client.update(updateRequest,RequestOptions.DEFAULT);
        System.out.println("_index:" + updateResponse.getIndex()+"_id:" + updateResponse.getId()+"_result:" + updateResponse.getResult());*/

   // =====================================查询文档=================================================================
    /*    GetRequest getRequest = new GetRequest().index("user").id("1001");
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println("_index:" + getResponse.getIndex());
        System.out.println("_type:" + getResponse.getType());
        System.out.println("_id:" + getResponse.getId());
        System.out.println("source:" + getResponse.getSourceAsString());*/

        //======================删除文档==================================
   /*     DeleteRequest deleteRequest = new DeleteRequest().index("user").id("1001");
        DeleteResponse deleteResponse = client.delete(deleteRequest,RequestOptions.DEFAULT);
        System.out.println(deleteResponse.toString());*/

        //=====================批量操作====================================
      /*  BulkRequest request = new BulkRequest();
        request.add(new IndexRequest().index("user").id("1001").source(XContentType.JSON, "name", "zhangsan"));
        request.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON, "name", "lisi"));
        request.add(new IndexRequest().index("user").id("1003").source(XContentType.JSON, "name", "wangwu"));
        request.add(new IndexRequest().index("user").id("1004").source(XContentType.JSON, "name", "zhaoliu"));
        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println("took:" + bulkResponse.getTook());
        System.out.println("items:" + bulkResponse.getItems());*/

        //=====================批量删除=============================================
        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest().index("user").id("1001"));
        request.add(new DeleteRequest().index("user").id("1002"));
        request.add(new DeleteRequest().index("user").id("1003"));
        request.add(new DeleteRequest().index("user").id("1004"));
        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println("took:" + bulkResponse.getTook());
        System.out.println("items:" + bulkResponse.getItems());



        client.close();

    }
}
