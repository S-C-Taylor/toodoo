package com.sctaylor.toodoo.screens.tododetails.dagger.components;

import com.sctaylor.toodoo.application.dagger.components.ToodooApplicationComponent;
import com.sctaylor.toodoo.screens.tododetails.TodoDetailsActivity;
import com.sctaylor.toodoo.screens.tododetails.dagger.modules.TodoDetailsActivityModule;
import com.sctaylor.toodoo.screens.tododetails.dagger.modules.TodoDetailsModule;
import com.sctaylor.toodoo.screens.tododetails.dagger.scopes.TodoDetailsActivityScope;

import dagger.Component;

@TodoDetailsActivityScope
@Component(modules = {TodoDetailsActivityModule.class, TodoDetailsModule.class}, dependencies = {ToodooApplicationComponent.class})
public interface TodoDetailsActivityComponent {
    void injectTodoDetailsActivity(TodoDetailsActivity activity);
}
