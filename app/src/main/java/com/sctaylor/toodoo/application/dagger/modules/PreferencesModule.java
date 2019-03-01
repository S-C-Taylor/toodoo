package com.sctaylor.toodoo.application.dagger.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.sctaylor.toodoo.R;
import com.sctaylor.toodoo.application.dagger.scopes.ToodooApplicationScope;
import com.sctaylor.toodoo.application.repository.PreferencesRepository;

import dagger.Module;
import dagger.Provides;


@Module(includes = {ContextModule.class})
public class PreferencesModule {
    @Provides
    @ToodooApplicationScope
    SharedPreferences sharedPreferences(Context context) {
        return context.getSharedPreferences(context.getString(R.string.PREFS_ID), Context.MODE_PRIVATE);
    }

    @Provides
    @ToodooApplicationScope
    PreferencesRepository preferencesRepository(Context context, SharedPreferences sharedPreferences, Gson gson) {
        return new PreferencesRepository(context, sharedPreferences, gson);
    }
}
