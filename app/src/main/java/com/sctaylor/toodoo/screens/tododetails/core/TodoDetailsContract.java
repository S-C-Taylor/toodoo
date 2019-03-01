package com.sctaylor.toodoo.screens.tododetails.core;

public interface TodoDetailsContract {

    interface View {
        void setTitle(String title);
        void setDescription(String title);
        void completeSave();
        void closeDetails();
        void showProgress();
        void hideProgress();
    }

    interface Presenter {
        void updateItem(String title, String description);
        void createItem(String title, String description);
    }
}