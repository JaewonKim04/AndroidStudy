package com.test.existapplications.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.existapplications.R;
import com.test.existapplications.model.RecyclerItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<RecyclerItem> itemList = new ArrayList<>();

    private final PublishSubject<RecyclerItem> recyclerItemPublishSubject;

    public RecyclerViewAdapter() {
        this.recyclerItemPublishSubject = PublishSubject.create();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder cHolder = (MyViewHolder) holder;
        final RecyclerItem item = itemList.get(position);
        Log.e("아이템", "얻은값:"+item.getImage().toString());
        cHolder.mImage.setImageDrawable(item.getImage());
        cHolder.mTitle.setText(item.getTitle());
        cHolder.getClickObserver(item).subscribe(recyclerItemPublishSubject);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void updateItem(List<RecyclerItem> items) {
        itemList.addAll(items);
        this.notifyDataSetChanged();
    }

    public void updateItem(RecyclerItem item) {
        itemList.add(item);
        this.notifyDataSetChanged();
    }

    public PublishSubject<RecyclerItem> getRecyclerItemPublishSubject() {
        return recyclerItemPublishSubject;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.item_iv)
        ImageView mImage;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.item_tv)
        TextView mTitle;

        private MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        Observable<RecyclerItem> getClickObserver(RecyclerItem item) {
            return Observable.create(e -> itemView.setOnClickListener(
                    view -> e.onNext(item)
            ));
        }
    }
}
