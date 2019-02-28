package com.sctaylor.toodoo.screens.home.core;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sctaylor.toodoo.R;

/**
 * Created by simon on 7/24/2018.
 */

public class TodoItemAdapter extends RecyclerView.Adapter<TodoItemHolder> {

    private HomePresenter presenter;
    private TodoItemListener listener;

    public TodoItemAdapter(HomePresenter presenter, TodoItemListener listener) {
        this.presenter = presenter;
        this.listener = listener;
    }

    @Override
    public TodoItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_todo, parent, false);

        return new TodoItemHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(TodoItemHolder holder, int position) {
        presenter.setTodoItem(holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getTodoCount();
    }
}
