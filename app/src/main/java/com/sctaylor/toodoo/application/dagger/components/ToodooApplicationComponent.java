package com.sctaylor.toodoo.application.dagger.components;


import com.sctaylor.toodoo.application.central.RxBus;
import com.sctaylor.toodoo.application.dagger.modules.BusModule;
import com.sctaylor.toodoo.application.dagger.modules.PicassoModule;
import com.sctaylor.toodoo.application.dagger.modules.PreferencesModule;
import com.sctaylor.toodoo.application.dagger.modules.ToodooServiceModule;
import com.sctaylor.toodoo.application.dagger.scopes.ToodooApplicationScope;
import com.sctaylor.toodoo.application.network.ToodooService;
import com.sctaylor.toodoo.application.repository.PreferencesRepository;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * Created by simon on 6/27/2018.
 */

@ToodooApplicationScope
@Component(modules = {ToodooServiceModule.class, PicassoModule.class, PreferencesModule.class, BusModule.class})
public interface ToodooApplicationComponent {

    Picasso getPicasso();

    ToodooService getToodooService();

    PreferencesRepository getPreferences();

    RxBus getRxBus();
}
