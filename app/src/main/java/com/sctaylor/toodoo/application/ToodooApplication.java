package com.sctaylor.toodoo.application;

import android.app.Activity;
import android.app.Application;

import com.sctaylor.toodoo.application.dagger.components.DaggerToodooApplicationComponent;
import com.sctaylor.toodoo.application.dagger.components.ToodooApplicationComponent;
import com.sctaylor.toodoo.application.dagger.modules.ContextModule;
import com.sctaylor.toodoo.application.network.ToodooService;
import com.squareup.picasso.Picasso;

import timber.log.Timber;

/**
 * Created by simon on 7/11/2018.
 */

public class ToodooApplication extends Application {

    private ToodooService roomService;
    private Picasso picasso;
    private ToodooApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());


        component = DaggerToodooApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

        roomService = component.getToodooService();
        picasso = component.getPicasso();
    }

    public ToodooApplicationComponent getComponent(){
        return this.component;
    }

    public static ToodooApplication get(Activity activity) {
        return (ToodooApplication) activity.getApplication();
    }


}
