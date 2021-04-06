package com.malykhinv.operationsexecutiontimerrx.di;

import android.content.Context;
import com.malykhinv.operationsexecutiontimerrx.storage.SharedPreferencesStorage;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    SharedPreferencesStorage getSharedPreferencesStorage();
    Context getContext();
}
