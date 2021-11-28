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
        ArrayList<Integer> dice_num = new ArrayList<>();
        dice_num.add(0);
        dice_num.add(0);
        dice_num.add(0);
        dice_num.add(0);
        dice_num.add(0);

        ArrayList<Boolean> dice = new ArrayList<Boolean>();
        dice.add(false);
        dice.add(false);
        dice.add(false);
        dice.add(false);
        dice.add(false);
        btn_roll = findViewById(R.id.btn_roll);
        btn_roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"주사위를 굴렸습니다", Toast.LENGTH_SHORT).show();
                int rand_num;
                if(!dice.get(0)){
                    rand_num = random.nextInt(6);
                    dice_num.set(0, rand_num+1);
                    one.setImageResource(image_id[rand_num]);
                }
                if(!dice.get(1)){
                    rand_num = random.nextInt(6);
                    dice_num.set(0, rand_num+1);
                    two.setImageResource(image_id[rand_num]);
                }
                if(!dice.get(2)){
                    rand_num = random.nextInt(6);
                    dice_num.set(0, rand_num+1);
                    three.setImageResource(image_id[rand_num]);
                }
                if(!dice.get(3)){
                    rand_num = random.nextInt(6);
                    dice_num.set(0, rand_num+1);
                    four.setImageResource(image_id[rand_num]);
                }
                if(!dice.get(4)){
                    rand_num = random.nextInt(6);
                    dice_num.set(0, rand_num+1);
                    five.setImageResource(image_id[rand_num]);
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



    }
}