package com.example.yachtdice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private Button btn_roll;


    private ImageView one, two, three, four, five;

    private final int[] image_id = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four,
                            R.drawable.five, R.drawable.six};
    Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);

        btn_roll = findViewById(R.id.btn_roll);
        btn_roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rand_num = random.nextInt(6);
                one.setImageResource(image_id[rand_num]);
                rand_num = random.nextInt(6);
                two.setImageResource(image_id[rand_num]);
                rand_num = random.nextInt(6);
                three.setImageResource(image_id[rand_num]);
                rand_num = random.nextInt(6);
                four.setImageResource(image_id[rand_num]);
                rand_num = random.nextInt(6);
                five.setImageResource(image_id[rand_num]);

            }
        });


        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"주사위를 굴렸습니다", Toast.LENGTH_SHORT).show();
            }
        });


    }
}