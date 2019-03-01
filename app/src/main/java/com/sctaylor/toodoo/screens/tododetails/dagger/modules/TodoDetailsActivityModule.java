package com.sctaylor.toodoo.screens.tododetails.dagger.modules;

import com.sctaylor.toodoo.screens.tododetails.TodoDetailsActivity;
import com.sctaylor.toodoo.screens.tododetails.dagger.scopes.TodoDetailsActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class TodoDetailsActivityModule {
    private final TodoDetailsActivity activity;

    public TodoDetailsActivityModule(TodoDetailsActivity activity) {
        this.activity = activity;
    }

    @Provides
    @TodoDetailsActivityScope
    public TodoDetailsActivity getTodoDetailsActivity() {
        return this.activity;
    }
}