package com.malykhinv.operationsexecutiontimerrx.di;

import android.app.Application;
import android.content.Context;

import com.malykhinv.operationsexecutiontimerrx.mvp.model.CollectionsModel;
import com.malykhinv.operationsexecutiontimerrx.mvp.model.MapsModel;
import com.malykhinv.operationsexecutiontimerrx.mvp.presenter.CollectionsPresenter;
import com.malykhinv.operationsexecutiontimerrx.mvp.view.fragments.CollectionsFragment;
import com.malykhinv.operationsexecutiontimerrx.storage.SharedPreferencesStorage;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    public SharedPreferencesStorage provideStorage() {
        return new SharedPreferencesStorage();
    }

    @Provides
    @Singleton
    public CollectionsModel provideCollectionsModel() {
        return new CollectionsModel();
    }

    @Provides
    @Singleton
    public MapsModel provideMapsModel() {
        return new MapsModel();
    }
}
