package com.zht.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;

public class baseOperation {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.20.62", 9200, "http")));

        // 创建索引 - 请求对象
        CreateIndexRequest request = new CreateIndexRequest("user");
        CreateIndexResponse response = client.indices().create(request,RequestOptions.DEFAULT);
        boolean acknowledged = response.isAcknowledged();
        System.out.println("操作状态 = " + acknowledged);

        //================================这个是查看=================================================

        GetIndexRequest getIndexRequest = new GetIndexRequest("user");
        GetIndexResponse getIndexResponse = client.indices().get(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println("aliases:"+getIndexResponse.getAliases());
        System.out.println("mappings:"+getIndexResponse.getMappings());
        System.out.println("settings:"+getIndexResponse.getSettings());

        //==============================删除索引============================================================
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("user");
        AcknowledgedResponse deleteResponse = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        System.out.println("这个是结果"+deleteResponse);





        // 关闭客户端连接
        client.close();
    }

}
