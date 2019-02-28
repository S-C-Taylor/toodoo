package com.sctaylor.toodoo.mvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by simon on 7/11/2018.
 */

public abstract class BasePresenter<V> {

    private CompositeDisposable disposables = new CompositeDisposable();
    public V view;

    protected BasePresenter(V view) {
        this.view = view;
    }

    /**
     * Contains common setup actions needed for all presenters, if any.
     * Subclasses may override this.
     */
    public void start() {

    }

    /**
     * Contains common cleanup actions needed for all presenters, if any.
     * Subclasses may override this.
     */
    public void stop() {
        disposables.clear();
    }

    public void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    public V getView() {
        return view;
    }

    public void setView(V view) {
        this.view = view;
    }
}
