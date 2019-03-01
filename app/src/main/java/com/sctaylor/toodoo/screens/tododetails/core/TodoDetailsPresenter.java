package com.sctaylor.toodoo.screens.tododetails.core;

import com.sctaylor.toodoo.application.central.RxBus;
import com.sctaylor.toodoo.application.network.ToodooService;
import com.sctaylor.toodoo.application.repository.PreferencesRepository;
import com.sctaylor.toodoo.models.TodoItem;
import com.sctaylor.toodoo.models.TodoItemUpdate;
import com.sctaylor.toodoo.mvp.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TodoDetailsPresenter extends BasePresenter<TodoDetailsContract.View> implements TodoDetailsContract.Presenter {

    private ToodooService service;
    private PreferencesRepository repository;
    private RxBus rxBus;
    private TodoItem currentItem;

    public TodoDetailsPresenter(TodoDetailsContract.View view, ToodooService service, PreferencesRepository repository, RxBus rxBus) {
        super(view);
        this.service = service;
        this.repository = repository;
        this.rxBus = rxBus;

        currentItem = this.repository.getCurrentItem();
    }

    @Override
    public void start() {
        super.start();

        if (currentItem != null) {

            if (currentItem.getTitle() != null) {
                view.setTitle(currentItem.getTitle());
            }

            if (currentItem.getDescription() != null) {
                view.setDescription(currentItem.getDescription());
            }
        }
    }

    @Override
    public void updateItem(String title, String description) {
        if (currentItem == null || currentItem.getId() == -1) {
            createItem(title, description);
            return; //Create instead of updating
        }

        currentItem.setTitle(title);
        currentItem.setDescription(description);

        view.showProgress();

        addDisposable(service.updateTodoItem(currentItem.getId(), new TodoItemUpdate(currentItem))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        view.hideProgress();
                        repository.storeCurrentItem(currentItem);
                        rxBus.send(repository.getCurrentItem());
                        repository.clearCurrentItem();
                        view.closeDetails();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.hideProgress();
                    }
                }));
    }

    @Override
    public void createItem(String title, String description) {

        if (currentItem == null) {
            currentItem = new TodoItem();
            currentItem.setTitle(title);
            currentItem.setDescription(description);
        }

        view.showProgress();

        addDisposable(service.createTodoItem(new TodoItemUpdate(currentItem))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<TodoItem>() {
                    @Override
                    public void accept(TodoItem todoItem) throws Exception {
                        currentItem = todoItem;
                        repository.storeCurrentItem(currentItem);
                        rxBus.send(currentItem);
                        repository.clearCurrentItem();
                        view.hideProgress();
                        view.closeDetails();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.hideProgress();
                    }
                }));
    }
}