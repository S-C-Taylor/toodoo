package com.sctaylor.toodoo.application.dagger.modules;

import android.content.Context;

import com.sctaylor.toodoo.application.dagger.scopes.ToodooApplicationScope;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

/**
 * Created by simon on 6/27/2018.
 */

@Module(includes = {ContextModule.class})
public class NetworkModule {

    @Provides
    @ToodooApplicationScope
    public OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor, Cache cache, Interceptor networkInterceptor){

        return new OkHttpClient.Builder()
                .addNetworkInterceptor(networkInterceptor)
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();
    }

    @Provides
    @ToodooApplicationScope
    public HttpLoggingInterceptor loggingInterceptor(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.tag("OkHttp").i(message);
            }
        });

        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        return interceptor;
    }

    @Provides
    @ToodooApplicationScope
    public Interceptor networkInterceptor(){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed( chain.request() );
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge( 0, TimeUnit.SECONDS )
                        .build();

                long tx = response.sentRequestAtMillis();
                long rx = response.receivedResponseAtMillis();
                Timber.tag("Retrofit").i("response time : "+(rx - tx)+" ms");

                return response.newBuilder()
                        .header("Cache-Control", cacheControl.toString() )
                        .build();
            }
        };
    }

    @Provides
    @ToodooApplicationScope
    public Cache cache(File cacheFile){
        return new Cache(cacheFile, 10 * 1000 * 1000); //10mb cache
    }

    @Provides
    @ToodooApplicationScope
    public File cacheFile(Context context){
        return new File(context.getCacheDir(), "okhttp_cache");
    }
}
