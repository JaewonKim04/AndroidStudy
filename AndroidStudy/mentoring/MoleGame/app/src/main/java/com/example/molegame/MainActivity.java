package com.example.molegame;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.molegame.R;

public class MainActivity extends AppCompatActivity {

    Button plusButton;
    Button minusButton;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plusButton=findViewById(R.id.plus_button);
        minusButton=findViewById(R.id.minus_button);
        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.plus_button){
                    count++;
                    Toast.makeText(getApplicationContext(),String.valueOf(count),Toast.LENGTH_SHORT).show();
                }else if(v.getId()==R.id.minus_button){
                    count--;
                    Toast.makeText(getApplicationContext(),String.valueOf(count),Toast.LENGTH_SHORT).show();
                }
                buttonChange();
            }
        };
        plusButton.setOnClickListener(onClickListener);
        minusButton.setOnClickListener(onClickListener);
    }
    void buttonChange(){
        Display display=getWindowManager().getDefaultDisplay();
        Point size=new Point();
        display.getRealSize(size);
        int width=size.x-100;
        int height=size.y-100;
        FrameLayout.LayoutParams plusParams=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
        int plusTop=(int)(Math.random()*height+1);
        int plusLeft=(int)(Math.random()*width+1);
        plusParams.setMargins(plusLeft,plusTop,0,0);
        plusButton.setLayoutParams(plusParams);
        FrameLayout.LayoutParams minusParams=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
        int minusTop=(int)(Math.random()*height+1);
        int minusLeft=(int)(Math.random()*width+1);
        minusParams.setMargins(minusLeft,minusTop,0,0);
        minusButton.setLayoutParams(minusParams);

    }
}