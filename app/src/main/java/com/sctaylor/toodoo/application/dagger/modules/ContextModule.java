package com.sctaylor.toodoo.application.dagger.modules;

import android.content.Context;

import com.sctaylor.toodoo.application.dagger.scopes.ToodooApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by simon on 6/27/2018.
 */

@Module
public class ContextModule {
    private final Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @Provides
    @ToodooApplicationScope
    public Context context(){
        return this.context;
    }
}
