package com.sctaylor.toodoo.screens.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.sctaylor.toodoo.R;
import com.sctaylor.toodoo.application.ToodooApplication;
import com.sctaylor.toodoo.screens.home.core.HomeContract;
import com.sctaylor.toodoo.screens.home.core.HomePresenter;
import com.sctaylor.toodoo.screens.home.core.TodoItemAdapter;
import com.sctaylor.toodoo.screens.home.core.TodoItemListener;
import com.sctaylor.toodoo.screens.home.dagger.components.DaggerHomeActivityComponent;
import com.sctaylor.toodoo.screens.home.dagger.components.HomeActivityComponent;
import com.sctaylor.toodoo.screens.home.dagger.modules.HomeActivityModule;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeContract.HomeView, TodoItemListener {


    @BindView(R.id.recyclerViewTodoItems)
    RecyclerView todoItems;

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    Picasso picasso;

    @Inject
    HomePresenter presenter;

    @Inject
    TodoItemAdapter todoItemAdapter;

    private KProgressHUD hudLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HomeActivityComponent component = DaggerHomeActivityComponent.builder()
                .homeActivityModule(new HomeActivityModule(this))
                .toodooApplicationComponent(ToodooApplication.get(this).getComponent())
                .build();

        component.injectUserActivity(this);

        ButterKnife.bind(this);

        presenter.start();

        hudLoader = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setDetailsLabel("Loading todo items...")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        todoItems.setAdapter(todoItemAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setAutoMeasureEnabled(false);
        llm.setOrientation(LinearLayoutManager.VERTICAL);


        todoItems.setLayoutManager(llm);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(todoItems.getContext(), llm.getOrientation());
        todoItems.addItemDecoration(dividerItemDecoration);

        todoItemAdapter.notifyDataSetChanged();

        presenter.loadTodoItems();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                presenter.loadTodoItems();
            }
        });
    }

    @Override
    public void updateTodoList() {
        todoItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void itemClicked(int position) {

    }

    @Override
    public void itemCompleted(int position, boolean completed) {
        presenter.completeItem(position, completed);
    }

    @Override
    public void itemLongClicked(final int position) {
        AlertDialog.Builder alert = new AlertDialog.Builder(
                HomeActivity.this);
        alert.setTitle("Delete Item");
        alert.setMessage("Are you sure to delete this item?");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do your work here
                presenter.deleteItem(position);
                dialog.dismiss();

            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        alert.show();
    }

    @Override
    public void showProgress() {
        hudLoader.show();
    }

    @Override
    public void hideProgress() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        hudLoader.dismiss();
    }
}
