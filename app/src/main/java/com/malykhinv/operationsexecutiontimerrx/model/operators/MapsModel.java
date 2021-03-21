package com.malykhinv.operationsexecutiontimerrx.model.operators;

import android.util.Log;
import com.malykhinv.operationsexecutiontimerrx.FragmentContract;
import com.malykhinv.operationsexecutiontimerrx.IndexedData;
import com.malykhinv.operationsexecutiontimerrx.OperationResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import static android.content.ContentValues.TAG;

public class MapsModel implements FragmentContract.Model {

    private static final String PREFERENCE_PREFIX = "M";                        // Prefix for SharedPreferences data ("M0"..."M5")
    private static final int NUMBER_OF_MAPS = 2;
    private static final int NUMBER_OF_OPERATIONS = 3;

    private final CompositeDisposable disposables = new CompositeDisposable();
    private FragmentContract.Model.Callback callback;

    public void registerCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public List<IndexedData> getIndexedData() {
        Log.d(TAG, "create indexed maps: " + Thread.currentThread().getName());
        List<IndexedData> indexedMaps = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_MAPS; i++) {
            for (int k = 0; k < NUMBER_OF_OPERATIONS; k++) {
                int index = i * NUMBER_OF_OPERATIONS + k;
                switch (i) {
                    case 0:
                        indexedMaps.add(new IndexedData(index, new TreeMap<>()));
                        break;
                    case 1:
                        indexedMaps.add(new IndexedData(index, new HashMap<>()));
                        break;
                }
            }
        }
        return indexedMaps;
    }

    @Override
    public IndexedData fillIndexedData(IndexedData indexedMap, int size) {
        Log.d(TAG, "fill indexed map: " + Thread.currentThread().getName());
        Map<Integer, Byte> map = indexedMap.getMap();
        for (int s = 0; s < size; s++) map.put(s, (byte) 0);
        return indexedMap;
    }

    @Override
    public OperationResult operateWithIndexedData(IndexedData indexedMap) {
        int index = indexedMap.getIndex();
        Map<Integer, Byte> map = indexedMap.getMap();
        long timeStart = System.currentTimeMillis();

        if (map != null) {
            switch (NUMBER_OF_OPERATIONS) {
                case 0:
                    map.put(-1, (byte) 0);
                    break;
                case 1:
                    map.get(0);
                    break;
                case 2:
                    map.remove(0);
                    break;
            }
        }
        if (map != null) map.clear();
        return new OperationResult(index, System.currentTimeMillis() - timeStart);
    }

    @Override
    public void execute(String size) {
        @NonNull Observable<OperationResult> observable = Observable
                .fromIterable(getIndexedData())
                .flatMap(indexedData -> Observable
                        .just(indexedData)
                        .subscribeOn(Schedulers.computation())
                        .map(map -> fillIndexedData(map, Integer.parseInt(size)))
                        .map(this::operateWithIndexedData)
                        .observeOn(AndroidSchedulers.mainThread())
                );

        observable.subscribe(new Observer<OperationResult>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe: " + Thread.currentThread().getName());
                disposables.add(d);
            }

            @Override
            public void onNext(@NonNull OperationResult operationResult) {
                Log.d(TAG, "onNext: " + Thread.currentThread().getName());
                callback.onSingleCalculationComplete(operationResult.getIndex(), String.valueOf(operationResult.getExecutionTime()));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: " + Thread.currentThread().getName());
                callback.onError(e);
                disposables.dispose();
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: " + Thread.currentThread().getName());
                disposables.dispose();
            }
        });
    }
}
