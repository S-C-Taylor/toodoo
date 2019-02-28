package com.sctaylor.toodoo.screens.home.dagger.components;

import com.sctaylor.toodoo.application.dagger.components.ToodooApplicationComponent;
import com.sctaylor.toodoo.screens.home.HomeActivity;
import com.sctaylor.toodoo.screens.home.dagger.modules.HomeActivityModule;
import com.sctaylor.toodoo.screens.home.dagger.modules.HomeModule;
import com.sctaylor.toodoo.screens.home.dagger.scopes.HomeActivityScope;

import dagger.Component;

/**
 * Created by simon on 6/27/2018.
 */

@HomeActivityScope
@Component(modules = {HomeActivityModule.class, HomeModule.class}, dependencies = {ToodooApplicationComponent.class})
public interface HomeActivityComponent {

    void injectUserActivity(HomeActivity userActivity);

}