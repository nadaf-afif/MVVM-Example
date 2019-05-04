package com.example.giantviewsample.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.giantviewsample.model.NewsDataModel;
import com.example.giantviewsample.networkLayer.ApiClient;
import com.example.giantviewsample.networkLayer.ApiInterface;
import com.example.giantviewsample.utils.AppConfig;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityViewModel extends ViewModel {


    public MutableLiveData<NewsDataModel> mNewsModelMutableData = new MutableLiveData<>();

    public int mPageNumber = 1;

    public String mNewsTopic = "general";

    public void getNewsHeadlines(){

        ApiInterface apiInterface = ApiClient.getApiInterface();

        apiInterface.getNewsHeadlines(AppConfig.API_KEY, AppConfig.COUNTRY, AppConfig.LANGUAGE, AppConfig.PAGE_SIZE, mPageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsDataModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("MainActivityViewModel", "onSubscribe");
                    }

                    @Override
                    public void onNext(NewsDataModel newsDataModel) {
                        mNewsModelMutableData.postValue(newsDataModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("MainActivityViewModel", "onError");

                    }

                    @Override
                    public void onComplete() {
                        Log.d("MainActivityViewModel", "onComplete");

                    }
                });

    }


    public void getNewsByTopics( ){

        ApiInterface apiInterface = ApiClient.getApiInterface();

        apiInterface.getNewsByTopics(AppConfig.API_KEY, mNewsTopic , AppConfig.LANGUAGE, AppConfig.PAGE_SIZE, mPageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsDataModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("MainActivityViewModel", "onSubscribe");
                    }

                    @Override
                    public void onNext(NewsDataModel newsDataModel) {
                        mNewsModelMutableData.postValue(newsDataModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("MainActivityViewModel", "onError");

                    }

                    @Override
                    public void onComplete() {
                        Log.d("MainActivityViewModel", "onComplete");

                    }
                });

    }


}
