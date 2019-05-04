package com.example.giantviewsample.networkLayer;

import com.example.giantviewsample.model.NewsDataModel;
import com.example.giantviewsample.utils.Constants;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import static com.example.giantviewsample.utils.Constants.Params.*;

public interface ApiInterface  {

    @GET("v2/top-headlines")
    Observable<NewsDataModel> getNewsHeadlines(@Header(AUTHORIZATION) String authKey, @Query(COUNTRY) String country, @Query(LANGUAGE) String language,
                                           @Query(PAGE_SIZE) int pageSize, @Query(PAGE) int pageNumber);


    @GET("v2/everything")
    Observable<NewsDataModel> getNewsByTopics(@Header(AUTHORIZATION) String authKey, @Query(TOPIC) String topic, @Query(LANGUAGE) String language,
                                               @Query(PAGE_SIZE) int pageSize, @Query(PAGE) int pageNumber);

}
