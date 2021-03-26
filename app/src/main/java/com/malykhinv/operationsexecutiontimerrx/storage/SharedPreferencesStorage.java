package com.malykhinv.operationsexecutiontimerrx.storage;

import android.content.Context;
import com.malykhinv.operationsexecutiontimerrx.R;
import com.malykhinv.operationsexecutiontimerrx.di.App;

public class SharedPreferencesStorage implements Storage {

    private final String SHARED_PREFERENCES_NAME = "saved";
    private final Context context = App.getAppComponent().getContext();

    @Override
    public void write(String prefix, int index, String text) {
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(prefix + index, text)
                .apply();
    }

    @Override
    public String read(String prefix, int index) {
        if (this.contains(prefix + index)) {
            return context
                    .getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                    .getString(prefix + index, context.getString(R.string.default_cell_text));
        } else return null;
    }

    @Override
    public Boolean contains(String key) {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).contains(key);
    }
}
