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
    private int total_score = 0;        //총점수
    private int round = 0;      //라운드 수
    private int chance = 0;     //주사위 던질 기회
    private final int[] image_id = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four,
                            R.drawable.five, R.drawable.six};
    private TextView bonus, total;
    private ArrayList<Integer> score = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));  //획득할 수 있는 점수
    private ArrayList<Integer> dice_num = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0));
    private ArrayList<Button> button = new ArrayList<>();
    private ArrayList<Boolean> dice = new ArrayList<Boolean>(Arrays.asList(false, false, false, false, false));
    private ArrayList<Boolean> score_exist = new ArrayList<Boolean>(Arrays.asList(false, false, false, false, false, false, false, false, false, false, false, false));
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
        button.add(btn_score1);
        button.add(btn_score2);
        button.add(btn_score3);
        button.add(btn_score4);
        button.add(btn_score5);
        button.add(btn_score6);
        button.add(btn_score_three);
        button.add(btn_score_four);
        button.add(btn_score_full);
        button.add(btn_score_large);
        button.add(btn_score_small);
        button.add(btn_score_yacht);






        btn_roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<6; i++) score.set(i, 0);

                chance++;
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

                for(Integer number : dice_num){
                    switch(number){
                        case 1:
                            score.set(0, score.get(0)+1);
                            break;
                        case 2:
                            score.set(1, score.get(1)+1);
                            break;
                        case 3:
                            score.set(2, score.get(2)+1);
                            break;
                        case 4:
                            score.set(3, score.get(3)+1);
                            break;
                        case 5:
                            score.set(4, score.get(4)+1);
                            break;
                        default:
                            score.set(5, score.get(5)+1);
                    }
                        }
                for(int i =0; i<6; i++){
                    if(!score_exist.get(i)){
                        if(score.get(i)>0){
                            button.get(i).setText(Integer.toString(score.get(i)));
                            button.get(i).setEnabled(true);
                        }
                    }
                }

                if(chance == 3) {
                    btn_roll.setEnabled(false);
                    chance = 0;
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

                for(Button btn : button){
                    btn.setEnabled(false);
                }
                chance = 0;
                round++;
                btn_roll.setEnabled(true);

                total_score += Integer.parseInt(btn_score1.getText().toString());
                score_exist.set(0, true);
                total.setText(Integer.toString(total_score));
                for(int i =0; i<6; i++) {
                    if (!score_exist.get(i)) button.get(i).setText("0");
                }
                if(round==7){
                    //종료, 다시하기
                }
            }
        });

        btn_score2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(Button btn : button){
                    btn.setEnabled(false);
                }
                chance = 0;
                round++;


                total_score += Integer.parseInt(btn_score2.getText().toString());
                score_exist.set(1, true);
                total.setText(Integer.toString(total_score));
                for(int i =0; i<6; i++) {
                    if (!score_exist.get(i)) button.get(i).setText("0");
                }
                if(round==7){
                    //종료, 다시하기
                }
                btn_roll.setEnabled(true);
            }
        });
        btn_score3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Button btn : button){
                    btn.setEnabled(false);
                }
                chance = 0;
                round++;

                total_score += Integer.parseInt(btn_score3.getText().toString());
                score_exist.set(2, true);
                total.setText(Integer.toString(total_score));
                for(int i =0; i<6; i++) {
                    if (!score_exist.get(i)) button.get(i).setText("0");
                }
                if(round==7){
                    //종료, 다시하기
                }
                btn_roll.setEnabled(true);
            }
        });
        btn_score4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Button btn : button){
                    btn.setEnabled(false);
                }
                chance = 0;
                round++;

                total_score += Integer.parseInt(btn_score4.getText().toString());
                score_exist.set(3, true);
                total.setText(Integer.toString(total_score));
                for(int i =0; i<6; i++) {
                    if (!score_exist.get(i)) button.get(i).setText("0");
                }
                if(round==7){
                    //종료, 다시하기
                }
                btn_roll.setEnabled(true);
            }
        });
        btn_score5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Button btn : button){
                    btn.setEnabled(false);
                }
                chance = 0;
                round++;

                total_score += Integer.parseInt(btn_score5.getText().toString());
                score_exist.set(4, true);
                total.setText(Integer.toString(total_score));
                for(int i =0; i<6; i++) {
                    if (!score_exist.get(i)) button.get(i).setText("0");
                }
                if(round==7){
                    //종료, 다시하기
                }
                btn_roll.setEnabled(true);
            }
        });
        btn_score6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Button btn : button){
                    btn.setEnabled(false);
                }
                chance = 0;
                round++;

                total_score += Integer.parseInt(btn_score6.getText().toString());
                score_exist.set(5, true);
                total.setText(Integer.toString(total_score));
                for(int i =0; i<6; i++) {
                    if (!score_exist.get(i)) button.get(i).setText("0");
                }
                if(round==7){
                    //종료, 다시하기
                }
                btn_roll.setEnabled(true);
            }
        });


    }
}