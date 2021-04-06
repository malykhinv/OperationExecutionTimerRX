package com.malykhinv.operationsexecutiontimerrx.di;

import android.app.Application;
import android.content.Context;
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
}
