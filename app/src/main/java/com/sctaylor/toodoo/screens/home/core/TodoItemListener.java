package com.sctaylor.toodoo.screens.home.core;

/**
 * Created by simon on 7/24/2018.
 */

public interface TodoItemListener {
    void itemClicked(int position);
    void itemCompleted(int position, boolean completed);
}
