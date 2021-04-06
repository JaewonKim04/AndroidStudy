package com.example.clickergame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button plusButton,minusButton;
        TextView textView;
        plusButton = findViewById(R.id.plus_btn);
        minusButton = findViewById(R.id.minus_btn);
        textView = findViewById(R.id.text_view);
        String text=Integer.toString(count);
        textView.setText(text);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.plus_btn){
                    count++;
                    String text=Integer.toString(count);
                    textView.setText(text);
                }else if(v.getId()==R.id.minus_btn){
                    count--;
                    String text=Integer.toString(count);
                    textView.setText(text);
                }
            }
        };
        plusButton.setOnClickListener(onClickListener);
        minusButton.setOnClickListener(onClickListener);
    }
}