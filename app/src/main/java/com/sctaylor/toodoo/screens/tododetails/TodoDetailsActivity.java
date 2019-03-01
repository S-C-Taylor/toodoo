package com.sctaylor.toodoo.screens.tododetails;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.sctaylor.toodoo.R;
import com.sctaylor.toodoo.application.ToodooApplication;
import com.sctaylor.toodoo.screens.home.HomeActivity;
import com.sctaylor.toodoo.screens.tododetails.core.TodoDetailsContract;
import com.sctaylor.toodoo.screens.tododetails.core.TodoDetailsPresenter;
import com.sctaylor.toodoo.screens.tododetails.dagger.components.DaggerTodoDetailsActivityComponent;
import com.sctaylor.toodoo.screens.tododetails.dagger.components.TodoDetailsActivityComponent;
import com.sctaylor.toodoo.screens.tododetails.dagger.modules.TodoDetailsActivityModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodoDetailsActivity extends Activity implements TodoDetailsContract.View {


    @BindView(R.id.editTextTitle)
    TextView titleField;

    @BindView(R.id.editTextDescription)
    TextView descriptionField;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.textViewSave)
    TextView textViewSave;

    @Inject
    TodoDetailsPresenter presenter;

    private KProgressHUD hudLoader;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_details);

        TodoDetailsActivityComponent component = DaggerTodoDetailsActivityComponent.builder()
                .todoDetailsActivityModule(new TodoDetailsActivityModule(this))
                .toodooApplicationComponent(ToodooApplication.get(this).getComponent())
                .build();

        component.injectTodoDetailsActivity(this);

        ButterKnife.bind(this);

        presenter.start();

        hudLoader = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setDetailsLabel("Loading todo items...")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.arrow_left));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });

        textViewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (titleField.getText().toString().isEmpty()) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(
                            TodoDetailsActivity.this);
                    alert.setTitle("Invalid Data");
                    alert.setMessage("Please enter a title for the Toodoo item.");

                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });

                    alert.show();
                } else {
                    presenter.updateItem(titleField.getText().toString(), descriptionField.getText().toString());
                }
            }
        });
    }

    @Override
    public void setTitle(String title) {
        titleField.setText(title);
    }

    @Override
    public void setDescription(String description) {
        descriptionField.setText(description);
    }

    @Override
    public void completeSave() {

    }

    @Override
    public void closeDetails() {
        onBackPressed();
    }

    @Override
    public void showProgress() {
        hudLoader.show();
    }

    @Override
    public void hideProgress() {
        hudLoader.dismiss();
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, TodoDetailsActivity.class);

        return intent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}