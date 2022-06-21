package com.zht.elasticsearch;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;

public class highLevelSearch {
    public static void main(String[] args) throws IOException {

        //初始化ES操作客户端
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "a123456"));  //es账号密码

       RestHighLevelClient    esClient =new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.20.62",9200)).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
        @Override
        public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
            httpClientBuilder.disableAuthCaching();
            return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
        }
                })
        );

        SearchRequest searchRequest = new SearchRequest().indices("idx_api_test");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
       //============================查询所有的索引============================================
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = esClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        //=======================精确查询===========================================
       /* searchSourceBuilder.query(QueryBuilders.termQuery("name","百"));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = esClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();*/

        //===================分页查询=============================================
        /* searchSourceBuilder.query(QueryBuilders.matchAllQuery());
         searchSourceBuilder.from(0);
         searchSourceBuilder.size(3);
         searchRequest.source(searchSourceBuilder);*/

         //===================数据排序==========================================
        /* searchSourceBuilder.query(QueryBuilders.matchAllQuery());
         searchSourceBuilder.sort("age", SortOrder.ASC);
         searchRequest.source(searchSourceBuilder);
*/

        //=======================过滤字段================================================
    /*     searchSourceBuilder.query(QueryBuilders.matchAllQuery());
         String[] excludes = {};
         String[] includes = {"age"};
         searchSourceBuilder.fetchSource(includes,excludes);
         searchRequest.source(searchSourceBuilder);*/


 /*       //=====================bool查询==================================================
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //boolQueryBuilder.must(QueryBuilders.matchQuery("age",90));
        boolQueryBuilder.mustNot(QueryBuilders.matchQuery("name","虎"));
        boolQueryBuilder.should(QueryBuilders.matchQuery("age",95));
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);*/


/*        //========================范围搜索========================================
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");
        rangeQuery.gte("25");
        rangeQuery.lte("95");
        searchSourceBuilder.query(rangeQuery);
        searchRequest.source(searchSourceBuilder);*/

     /*   //===========================模糊查询======================================
        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("name", "张三");
        fuzzyQueryBuilder.fuzziness(Fuzziness.ONE);
        searchSourceBuilder.query(fuzzyQueryBuilder);
        searchRequest.source(searchSourceBuilder);
*/

    /*     //=====================高亮查询====================================================
        searchSourceBuilder.query(QueryBuilders.termsQuery("name", "张"));
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font color='red'>");//设置标签前缀
        highlightBuilder.postTags("</font>");//设置标签后缀
        highlightBuilder.field("name");//设置高亮字段
        searchSourceBuilder.highlighter(highlightBuilder);
        searchRequest.source(searchSourceBuilder);
*/
        /*  //========================聚合查询，就是一些聚合函数 比如max min等====================================
         searchSourceBuilder.aggregation(AggregationBuilders.max("maxAge").field("age"));
         searchRequest.source(searchSourceBuilder);*/


        //====================================分组统计=======================================================

 /*       searchSourceBuilder.aggregation(AggregationBuilders.terms("age_groupby").field("age"));
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = esClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        System.out.println("took:" + searchResponse.getTook()+"==============="+
                "timeout:" + searchResponse.isTimedOut()+"==============="+
                "total:" + hits.getTotalHits()+"==============="+
                "MaxScore:" + hits.getMaxScore());*/
        for (SearchHit hit : hits) {
            //输出每条查询的结果信息
            System.out.println(hit.getSourceAsString());
        }

        esClient.close();

    }

}
