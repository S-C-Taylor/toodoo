package com.sctaylor.toodoo.application.dagger.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.sctaylor.toodoo.application.network.ToodooService;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by simon on 6/27/2018.
 */

@Module(includes = {NetworkModule.class})
public class ToodooServiceModule {
    @Provides
    @com.sctaylor.example.application.dagger.scopes.ToodooApplicationScope
    public ToodooService toodooService(Retrofit retrofit){
        return retrofit.create(ToodooService.class);
    }

    @Provides
    @com.sctaylor.example.application.dagger.scopes.ToodooApplicationScope
    public Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @com.sctaylor.example.application.dagger.scopes.ToodooApplicationScope
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl("https://toodoo.azurewebsites.net/api/")
                .build();
    }
}
