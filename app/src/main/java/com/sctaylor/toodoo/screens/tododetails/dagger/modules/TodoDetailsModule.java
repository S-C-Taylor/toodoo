package com.sctaylor.toodoo.screens.tododetails.dagger.modules;

import com.sctaylor.toodoo.application.central.RxBus;
import com.sctaylor.toodoo.application.network.ToodooService;
import com.sctaylor.toodoo.application.repository.PreferencesRepository;
import com.sctaylor.toodoo.screens.tododetails.TodoDetailsActivity;
import com.sctaylor.toodoo.screens.tododetails.core.TodoDetailsContract;
import com.sctaylor.toodoo.screens.tododetails.core.TodoDetailsPresenter;
import com.sctaylor.toodoo.screens.tododetails.dagger.scopes.TodoDetailsActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class TodoDetailsModule {

    @TodoDetailsActivityScope
    @Provides
    public TodoDetailsPresenter provideTodoDetailsPresenter(TodoDetailsContract.View view, ToodooService service, PreferencesRepository repository, RxBus rxBus) {
        return new TodoDetailsPresenter(view, service, repository, rxBus);
    }

    @TodoDetailsActivityScope
    @Provides
    public TodoDetailsContract.View providesTodoDetailsView(TodoDetailsActivity activity) {
        return activity;
    }
}
