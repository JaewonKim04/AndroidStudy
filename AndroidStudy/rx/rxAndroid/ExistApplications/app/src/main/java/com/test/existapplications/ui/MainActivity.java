package com.test.existapplications.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.test.existapplications.R;
import com.test.existapplications.adapter.RecyclerViewAdapter;
import com.test.existapplications.data.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.main_rv)
    RecyclerView mainRecyclerView;

    private final Utils utils = new Utils();

    private RecyclerViewAdapter mRecyclerviewAdapter;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainRecyclerView=findViewById(R.id.main_rv);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mainRecyclerView.setLayoutManager(layoutManager);
        mRecyclerviewAdapter = new RecyclerViewAdapter();
        mainRecyclerView.setAdapter(mRecyclerviewAdapter);
        mRecyclerviewAdapter
                .getRecyclerItemPublishSubject()
                .subscribe(s->toast(s.getTitle()));
    }

    @Override
    public void onStart(){
        super.onStart();

        if(mRecyclerviewAdapter == null){
            return;
        }
        utils.getItemObservable(this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    mRecyclerviewAdapter.updateItem(item);
                });


    }

    private void toast(String title){
        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
    }
}