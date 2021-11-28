package com.example.yachtdice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private Button btn_roll, btn_score1, btn_score2, btn_score3, btn_score4, btn_score5, btn_score6, btn_score_three,
            btn_score_four, btn_score_full, btn_score_small, btn_score_large, btn_score_yacht;
    private ImageView one, two, three, four, five;
    private int score = 0;
    private final int[] image_id = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four,
                            R.drawable.five, R.drawable.six};
    private TextView bonus, total;
    private ArrayList<Integer> dice_num = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0));
    private ArrayList<Button> button = new ArrayList<>(Arrays.asList(btn_roll,btn_score1, btn_score2, btn_score3, btn_score4, btn_score5, btn_score6, btn_score_three,
            btn_score_four, btn_score_full, btn_score_small, btn_score_large, btn_score_yacht));
    private ArrayList<Boolean> dice = new ArrayList<Boolean>(Arrays.asList(false, false, false, false, false));

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
        bonus = findViewById(R.id.bonus);
        total = findViewById(R.id.total);

        btn_roll = findViewById(R.id.btn_roll);
        btn_score1 = findViewById(R.id.btn_score1);
        btn_score2 = findViewById(R.id.btn_score2);
        btn_score3 = findViewById(R.id.btn_score3);
        btn_score4 = findViewById(R.id.btn_score4);
        btn_score5 = findViewById(R.id.btn_score5);
        btn_score6 = findViewById(R.id.btn_score6);
        btn_score_three = findViewById(R.id.btn_score_three);
        btn_score_four = findViewById(R.id.btn_score_four);
        btn_score_full = findViewById(R.id.btn_score_full);
        btn_score_large = findViewById(R.id.btn_score_large);
        btn_score_small = findViewById(R.id.btn_score_small);
        btn_score_yacht = findViewById(R.id.btn_score_yacht);




        btn_roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"주사위를 굴렸습니다", Toast.LENGTH_SHORT).show();
                int rand_num;
                //btn_roll.setEnabled(false);
                if(!dice.get(0)){
                    rand_num = random.nextInt(6);
                    dice_num.set(0, rand_num+1);
                    one.setImageResource(image_id[rand_num]);
                }
                if(!dice.get(1)){
                    rand_num = random.nextInt(6);
                    dice_num.set(1, rand_num+1);
                    two.setImageResource(image_id[rand_num]);
                }
                if(!dice.get(2)){
                    rand_num = random.nextInt(6);
                    dice_num.set(2, rand_num+1);
                    three.setImageResource(image_id[rand_num]);
                }
                if(!dice.get(3)){
                    rand_num = random.nextInt(6);
                    dice_num.set(3, rand_num+1);
                    four.setImageResource(image_id[rand_num]);
                }
                if(!dice.get(4)){
                    rand_num = random.nextInt(6);
                    dice_num.set(4, rand_num+1);
                    five.setImageResource(image_id[rand_num]);
                }
                score = 0;
                for(Integer number : dice_num){
                    if(number==1) score++;
                }
                if(score>0){
                    btn_score1.setText(Integer.toString(score));
                    btn_score1.setEnabled(true);
                }

            }
        });


        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!dice.get(0)){
                    dice.set(0, true);
                    one.setBackgroundResource(R.drawable.dice_background);
                }
                else {
                    dice.set(0, false);
                    one.setBackgroundResource(R.drawable.dice_no_background);
                }
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!dice.get(1)){
                    dice.set(1, true);
                    two.setBackgroundResource(R.drawable.dice_background);
                }
                else {
                    dice.set(1, false);
                    two.setBackgroundResource(R.drawable.dice_no_background);
                }
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!dice.get(2)){
                    dice.set(2, true);
                    three.setBackgroundResource(R.drawable.dice_background);
                }
                else {
                    dice.set(2, false);
                    three.setBackgroundResource(R.drawable.dice_no_background);
                }
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!dice.get(3)){
                    dice.set(3, true);
                    four.setBackgroundResource(R.drawable.dice_background);
                }
                else {
                    dice.set(3, false);
                    four.setBackgroundResource(R.drawable.dice_no_background);
                }
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!dice.get(4)){
                    dice.set(4, true);
                    five.setBackgroundResource(R.drawable.dice_background);
                }
                else {
                    dice.set(4, false);
                    five.setBackgroundResource(R.drawable.dice_no_background);
                }
            }
        });

        btn_score1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_score1.setEnabled(false);
                total.setText(btn_score1.getText());
            }
        });


    }
}