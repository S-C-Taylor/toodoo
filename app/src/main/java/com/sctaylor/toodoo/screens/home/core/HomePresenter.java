package com.sctaylor.toodoo.screens.home.core;

import com.sctaylor.toodoo.application.network.ToodooService;
import com.sctaylor.toodoo.models.TodoItem;
import com.sctaylor.toodoo.models.TodoItemUpdate;
import com.sctaylor.toodoo.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by simon on 7/11/2018.
 */

public class HomePresenter extends BasePresenter<HomeContract.HomeView> implements HomeContract.HomePresenter {

    private ToodooService service;
    private List<TodoItem> todoList = new ArrayList<TodoItem>();


    public HomePresenter(HomeContract.HomeView view, ToodooService service) {
        super(view);

        this.service = service;
    }

    @Override
    public TodoItem getTodoItem(int position) {
        return todoList.get(position);
    }

    @Override
    public void loadTodoItems() {
        view.showProgress();

        addDisposable(service.getTodos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<TodoItem>>() {
                    @Override
                    public void accept(List<TodoItem> todoItems) throws Exception {
                        todoList = todoItems;
                        view.hideProgress();
                        view.updateTodoList();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.hideProgress();
                    }
                }));
    }

    @Override
    public void completeItem(int position, final boolean completed) {
        final TodoItem item = todoList.get(position);

        item.setCompleted(completed);
        addDisposable(service.updateTodoItem(item.getId(), new TodoItemUpdate(item))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {

                        view.updateTodoList();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        item.setCompleted(!completed);
                        view.updateTodoList();
                    }
                }));
    }

    @Override
    public void deleteItem(final int position) {
        TodoItem item = todoList.get(position);

        addDisposable(service.deleteTodoItem(item.getId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        todoList.remove(position);
                        view.updateTodoList();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.updateTodoList();
                    }
                }));

    }

    @Override
    public void setTodoItem(HomeContract.TodoItemHolder holder, int position) {
        TodoItem item = todoList.get(position);

        holder.setTitle(item.getTitle());
        holder.setDescription(item.getDescription());
        holder.setCompleted(item.getCompleted());

    }

    @Override
    public int getTodoCount() {
        return todoList.size();
    }
}
