package LojaExample.Elastic;

import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;

import java.io.IOException;

public class ClienteDao {
    private static RestHighLevelClient getConnection(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));
        return client;
    }
    private static ActionListener listener = new ActionListener<IndexResponse>() {
        @Override
        public void onResponse(IndexResponse indexResponse) {
            System.out.println(indexResponse.status());
        }

        @Override
        public void onFailure(Exception e) {
            System.out.println(e);
        }
    };

    public static void Put(String json,String index){
        IndexRequest request = new IndexRequest(index);
//        request.id();
        request.source(json, XContentType.JSON);
        getConnection().indexAsync(request, RequestOptions.DEFAULT, listener);

    }
}
