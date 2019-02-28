package com.sctaylor.toodoo.screens.home.dagger.modules;

import com.sctaylor.toodoo.application.network.ToodooService;
import com.sctaylor.toodoo.screens.home.HomeActivity;
import com.sctaylor.toodoo.screens.home.core.HomeContract;
import com.sctaylor.toodoo.screens.home.core.TodoItemAdapter;
import com.sctaylor.toodoo.screens.home.core.TodoItemListener;
import com.sctaylor.toodoo.screens.home.core.HomePresenter;
import com.sctaylor.toodoo.screens.home.dagger.scopes.HomeActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by simon on 4/27/2018.
 */

@Module
public class HomeModule {

    @HomeActivityScope
    @Provides
    public HomePresenter provideHomePresenter(HomeContract.HomeView view, ToodooService service) {
        return new HomePresenter(view, service);
    }

    @HomeActivityScope
    @Provides
    public HomeContract.HomeView provideHomeView(HomeActivity homeActivity) {
        return homeActivity;
    }

    @HomeActivityScope
    @Provides
    public TodoItemAdapter provideTodoItemAdapter(HomePresenter presenter, TodoItemListener listener) {
        return new TodoItemAdapter(presenter, listener);
    }

    @HomeActivityScope
    @Provides
    public TodoItemListener provideTodoItemListener(HomeActivity view) {
        return (TodoItemListener) view;
    }

}