package com.example.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button checkButton;
    EditText getTextET;
    RecyclerView recyclerView;
    NoteAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        adapter=new NoteAdapter();
        checkButton=findViewById(R.id.button);
        getTextET=findViewById(R.id.editTextTextPersonName);
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        View.OnClickListener onClickListener=new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.button){
                    onClicked();
                }
            }
        };
        checkButton.setOnClickListener(onClickListener);
    }

    void onClicked(){
        if(getTextET.getText().length()>0){
            String getText=getTextET.getText().toString();
            adapter.items.add(getText);
            adapter.notifyDataSetChanged();
            getTextET.setText("");
        }else{
            Toast.makeText(this, "글을 입력해주세요", Toast.LENGTH_SHORT).show();
        }
    }
}