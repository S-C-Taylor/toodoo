package com.sctaylor.toodoo.screens.home.core;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.sctaylor.toodoo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by simon on 7/24/2018.
 */

public class TodoItemHolder extends RecyclerView.ViewHolder implements HomeContract.TodoItemHolder {

    private TodoItemListener listener;

    @BindView(R.id.textViewTitle)
    TextView titleView;

    @BindView(R.id.textViewDescription)
    TextView descriptionView;

    @BindView(R.id.checkBoxCompleted)
    CheckBox completedView;

    @BindView(R.id.todoMainLayout)
    View container;

    public TodoItemHolder(View itemView, final TodoItemListener listener) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        this.listener = listener;

        this.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.itemClicked(getAdapterPosition());
            }
        });

        this.completedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.itemCompleted(getAdapterPosition(), completedView.isChecked());
            }
        });

        this.container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.itemLongClicked(getAdapterPosition());
                return true;
            }
        });
    }

    @Override
    public void setTitle(String title) {
        titleView.setText(title);
    }

    @Override
    public void setDescription(String description) {
        descriptionView.setText(description);
    }

    @Override
    public void setCompleted(Boolean checked) {
        completedView.setChecked(checked);
    }
}
