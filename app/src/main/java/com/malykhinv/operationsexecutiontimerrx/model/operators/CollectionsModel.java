package com.malykhinv.operationsexecutiontimerrx.model.operators;

import android.util.Log;
import com.malykhinv.operationsexecutiontimerrx.FragmentContract;
import com.malykhinv.operationsexecutiontimerrx.IndexedData;
import com.malykhinv.operationsexecutiontimerrx.OperationResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import dagger.Module;
import static android.content.ContentValues.TAG;

@Module
public class CollectionsModel implements FragmentContract.Model {

    private static final String PREFERENCE_PREFIX = "C";            // Prefix for SharedPreferences data ("C0"..."C20")
    private static final int NUMBER_OF_COLLECTIONS = 3;
    private static final int NUMBER_OF_OPERATIONS_WITH_COLLECTIONS = 7;

    @Override
    public List<IndexedData> getIndexedData() {
        List<IndexedData> indexedCollections = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_COLLECTIONS; i++) {
            for (int k = 0; k < NUMBER_OF_OPERATIONS_WITH_COLLECTIONS; k++) {
                int index = i * NUMBER_OF_OPERATIONS_WITH_COLLECTIONS + k;
                switch (i) {
                    case 0:
                        indexedCollections.add(new IndexedData (index, new ArrayList<>())); break;
                    case 1:
                        indexedCollections.add(new IndexedData (index, new LinkedList<>())); break;
                    case 2:
                        indexedCollections.add(new IndexedData (index, new CopyOnWriteArrayList<>())); break;
                }
            }
        }
        return indexedCollections;
    }

    @Override
    public IndexedData fillIndexedData(IndexedData indexedCollection, int size) {
        Log.d(TAG, "fill indexed collection: " + Thread.currentThread().getName());
        List<Byte> collection = indexedCollection.getCollection();
        collection.addAll(Collections.nCopies(size, (byte) 1));
        return indexedCollection;
    }

    @Override
    public OperationResult operateWithIndexedData(IndexedData indexedCollection) {
        int index = indexedCollection.getIndex();
        List<Byte> collection = indexedCollection.getCollection();
        long timeStart = System.currentTimeMillis();

        if (collection != null) {
            switch (index % NUMBER_OF_OPERATIONS_WITH_COLLECTIONS) {
                case 0:
                    if (collection instanceof LinkedList) ((LinkedList<Byte>) collection).addFirst((byte) 1);
                    else collection.add(0, (byte) 1);
                    break;
                case 1:
                    collection.add(collection.size()/2, (byte) 1); break;
                case 2:
                    if (collection instanceof LinkedList) ((LinkedList<Byte>) collection).addLast((byte) 1);
                    else collection.add((byte) 1);
                    break;
                case 3:
                    collection.indexOf((byte) 1); break;
                case 4:
                    collection.remove(0); break;
                case 5:
                    collection.remove(collection.size()/2); break;
                case 6:
                    collection.remove(collection.size()-1); break;
            }
        }
        if (collection != null) collection.clear();
        return new OperationResult(index, System.currentTimeMillis() - timeStart);
    }
}

