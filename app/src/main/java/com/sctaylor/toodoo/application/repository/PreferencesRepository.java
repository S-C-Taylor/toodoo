package com.sctaylor.toodoo.application.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;

import com.google.gson.Gson;
import com.sctaylor.toodoo.R;
import com.sctaylor.toodoo.models.TodoItem;


public class PreferencesRepository {

    private SharedPreferences preferences;
    private Context context;
    private Gson gson;

    public PreferencesRepository(Context context, SharedPreferences preferences, Gson gson) {
        this.preferences = preferences;
        this.context = context;
        this.gson = gson;
    }

    public void storeCurrentItem(TodoItem item) {
        String jsonUser = gson.toJson(item);

        preferences.edit().putString(context.getString(R.string.current_item), jsonUser).apply();
    }

    public TodoItem getCurrentItem() {
        return gson.fromJson(preferences.getString(context.getString(R.string.current_item), null), TodoItem.class);
    }

    public void clearCurrentItem() {
        preferences.edit().remove(context.getString(R.string.current_item)).apply();
    }

    public void clearAll(){
        preferences.edit().clear().apply();
    }
}
