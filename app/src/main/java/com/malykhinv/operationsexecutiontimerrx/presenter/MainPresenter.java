package com.malykhinv.operationsexecutiontimerrx.presenter;

import android.content.Context;
import android.util.Log;
import com.malykhinv.operationsexecutiontimerrx.IndexedData;
import com.malykhinv.operationsexecutiontimerrx.model.MainModel;
import com.malykhinv.operationsexecutiontimerrx.OperationResult;
import com.malykhinv.operationsexecutiontimerrx.R;
import com.malykhinv.operationsexecutiontimerrx.view.CalculationView;
import com.malykhinv.operationsexecutiontimerrx.view.fragments.AbstractFragment;
import java.util.List;
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

public class MainPresenter implements CalculationPresenter {

    private CalculationView view;
    private final MainModel model;
    private final Context context;
    private final CompositeDisposable disposables = new CompositeDisposable();

    public MainPresenter(MainModel model, Context context) {
        this.model = model;
        this.context = context;
    }

    @Override
    public void attachView(CalculationView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        disposables.dispose();
        view = null;
    }

    @Override
    public void start(AbstractFragment fragment, String size) {
        if (!size.isEmpty() && Integer.parseInt(size) > 0) {
            view.showProgress(fragment);
            view.resetCellText(fragment);

            ExecutorService executor = Executors.newFixedThreadPool(21);
            Scheduler scheduler = Schedulers.from(executor);
                @NonNull Observable<OperationResult> observable = Observable
                        .fromIterable(model.getIndexedData(fragment))
                        .flatMap(indexedData -> Observable
                                .just(indexedData)
                                .subscribeOn(scheduler)
                                .map(indexedDataItem -> model.fillIndexedData((IndexedData<Integer, List<Byte>>) indexedDataItem, Integer.parseInt(size)))
                                .map(model::operateWithData)
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
                        view.hideProgress(fragment, operationResult.getIndex());
                        view.updateTime(fragment, operationResult.getIndex(), String.valueOf(operationResult.getExecutionTime()));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + Thread.currentThread().getName());
                        view.hideProgress(fragment);
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: " + Thread.currentThread().getName());
                    }
                });
        } else view.showError(context.getString(R.string.error_wrong_size));
    }
}