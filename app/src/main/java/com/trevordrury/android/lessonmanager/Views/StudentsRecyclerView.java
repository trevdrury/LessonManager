package com.trevordrury.android.lessonmanager.Views;

import android.content.Context;
import androidx.annotation.Nullable;

import android.util.AttributeSet;
import android.view.View;

public class StudentsRecyclerView extends androidx.recyclerview.widget.RecyclerView {

    private View emptyView;

    private AdapterDataObserver dataObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            updateEmptyView();
        }
    };

    public StudentsRecyclerView(Context context) { super(context); }

    public StudentsRecyclerView(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    public StudentsRecyclerView(Context context, AttributeSet attrs, int defStyle) { super(context,attrs,defStyle); }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        if (getAdapter() != null) {
            getAdapter().unregisterAdapterDataObserver(dataObserver);
        }
        if (adapter != null) {
            adapter.registerAdapterDataObserver(dataObserver);
        }
        super.setAdapter(adapter);
        updateEmptyView();
    }

    private void updateEmptyView() {
        if (emptyView != null && getAdapter() != null) {
            boolean showEmptyView = getAdapter().getItemCount() == 0;
            emptyView.setVisibility(showEmptyView ? VISIBLE : GONE);
            setVisibility(showEmptyView ? GONE : VISIBLE);
        }
    }
}
