package com.sctaylor.toodoo.application.dagger.components;


import com.sctaylor.toodoo.application.dagger.modules.PicassoModule;
import com.sctaylor.toodoo.application.dagger.modules.ToodooServiceModule;
import com.sctaylor.toodoo.application.network.ToodooService;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * Created by simon on 6/27/2018.
 */

@com.sctaylor.example.application.dagger.scopes.ToodooApplicationScope
@Component(modules = {ToodooServiceModule.class, PicassoModule.class})
public interface ToodooApplicationComponent {

    Picasso getPicasso();

    ToodooService getToodooService();
}
