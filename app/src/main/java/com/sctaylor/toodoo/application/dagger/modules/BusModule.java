package com.sctaylor.toodoo.application.dagger.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.sctaylor.toodoo.R;
import com.sctaylor.toodoo.application.central.RxBus;
import com.sctaylor.toodoo.application.dagger.scopes.ToodooApplicationScope;
import com.sctaylor.toodoo.application.repository.PreferencesRepository;

import dagger.Module;
import dagger.Provides;


@Module
public class BusModule {

    @Provides
    @ToodooApplicationScope
    RxBus rxBus(Context context) {
        return new RxBus();
    }
}
