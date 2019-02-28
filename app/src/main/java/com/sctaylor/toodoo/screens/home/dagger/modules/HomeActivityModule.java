package com.sctaylor.toodoo.screens.home.dagger.modules;

import com.sctaylor.toodoo.screens.home.HomeActivity;
import com.sctaylor.toodoo.screens.home.dagger.scopes.HomeActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by simon on 6/27/2018.
 */

@Module
public class HomeActivityModule {
    private final HomeActivity homeActivity;

    public HomeActivityModule(HomeActivity userActivity){
        this.homeActivity= userActivity;
    }

    @Provides
    @HomeActivityScope
    public HomeActivity getHomeActivity() {
        return this.homeActivity;
    }
}
