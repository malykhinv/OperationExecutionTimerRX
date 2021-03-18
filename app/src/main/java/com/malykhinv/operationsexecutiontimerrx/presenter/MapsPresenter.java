package com.malykhinv.operationsexecutiontimerrx.presenter;

import android.util.Log;

import com.malykhinv.operationsexecutiontimerrx.FragmentContract;
import com.malykhinv.operationsexecutiontimerrx.OperationResult;
import com.malykhinv.operationsexecutiontimerrx.model.operators.CollectionsModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

public class MapsPresenter implements FragmentContract.Presenter {

    private final FragmentContract.View view;
    private final FragmentContract.Model model;
    private final CompositeDisposable disposables = new CompositeDisposable();

    public MapsPresenter(FragmentContract.View view) {
        this.view = view;
        this.model = new CollectionsModel();
    }

    @Override
    public void start(String size) {
        view.showProgress();
        view.resetCellText();
        execute(size);
    }

    @Override
    public void execute(String size) {
        ExecutorService executor = Executors.newFixedThreadPool(21);
        Scheduler scheduler = Schedulers.from(executor);
        @NonNull Observable<OperationResult> observable = Observable
                .fromIterable(model.getIndexedData())
                .flatMap(indexedData -> Observable
                        .just(indexedData)
                        .subscribeOn(scheduler)
                        .map(collection -> model.fillIndexedData(collection, Integer.parseInt(size)))
                        .map(model::operateWithIndexedData)
                        .observeOn(AndroidSchedulers.mainThread())
                )
                .doFinally(executor::shutdown);

        observable.subscribe(new Observer<OperationResult>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe: " + Thread.currentThread().getName());
                disposables.add(d);
            }

            @Override
            public void onNext(@NonNull OperationResult operationResult) {
                Log.d(TAG, "onNext: " + Thread.currentThread().getName());
                view.hideProgress(operationResult.getIndex());
                view.updateTime(operationResult.getIndex(), String.valueOf(operationResult.getExecutionTime()));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: " + Thread.currentThread().getName());
                view.hideProgress();
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: " + Thread.currentThread().getName());
            }
        });
    }

    @Override
    public void detachView() {
        disposables.dispose();
    }
}
