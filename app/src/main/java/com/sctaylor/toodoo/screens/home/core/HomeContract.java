package com.sctaylor.toodoo.screens.home.core;

import com.sctaylor.example.models.Email;
import com.sctaylor.toodoo.models.TodoItem;

/**
 * Created by simon on 7/11/2018.
 */

public interface HomeContract {

    interface UserPresenter {
        int getTodoCount();
        void setTodoItem(HomeContract.TodoItemHolder holder, int position);
        TodoItem getTodoItem(int position);
        void loadTodoItems();
        void completeItem(int position, boolean completed);
    }

    interface HomeView {
        void showProgress();
        void hideProgress();
        void updateTodoList();
    }

    interface TodoItemHolder {
        void setTitle(String title);
        void setDescription(String description);
        void setCompleted(Boolean checked);
    }
}
