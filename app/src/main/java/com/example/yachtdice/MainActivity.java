package com.example.yachtdice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
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
            btn_score_four, btn_score_full, btn_score_small, btn_score_large, btn_score_yacht, btn_pass;
    private ImageView one, two, three, four, five;
    private int minor_score = 0;        //마이너 보너스 조건
    private int bonus_score = 0;        //보너스 점수
    private int total_score = 0;        //총점수
    private int round = 1;      //라운드 수
    private int chance = 0;     //주사위 던질 기회
    private Boolean image_click = false;    //던지기 전에 클릭 방지
    private Boolean bonus_yacht = false;    //보너스 야추 획득 여부
    private Boolean bonus_minor_exist = false;      //보너스 마이너 획득 여부
    private final int[] image_id = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four,
                            R.drawable.five, R.drawable.six};
    private TextView bonus, total, minor_bonus, yacht_bonus, round_num;
    private ArrayList<Integer> score = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));  //획득할 수 있는 점수
    private ArrayList<Integer> dice_num = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0));
    private ArrayList<Button> button = new ArrayList<>();
    private ArrayList<ImageView> image = new ArrayList<>();
    private ArrayList<Boolean> dice = new ArrayList<Boolean>(Arrays.asList(false, false, false, false, false));
    private ArrayList<Boolean> score_exist = new ArrayList<Boolean>(Arrays.asList(false, false, false, false, false, false, false, false, false, false, false, false));
    Random random = new Random();
    private void reset(){
        if(round==11){  //종료, 다시하기
            AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
            ad.setCancelable(false);
            ad.setIcon(R.mipmap.ic_yacht_foreground);
            ad.setTitle("게임 종료!");
            final EditText result = new EditText(MainActivity.this);
            result.setText("총 점수: " + total.getText());
            ad.setView(result);
            ad.setPositiveButton("다시하기", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    score = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));  //획득할 수 있는 점수
                    dice_num = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0));
                    dice = new ArrayList<Boolean>(Arrays.asList(false, false, false, false, false));
                    score_exist = new ArrayList<Boolean>(Arrays.asList(false, false, false, false, false, false, false, false, false, false, false, false));
                    image_click = false;    //던지기 전에 클릭 방지
                    bonus_yacht = false;    //보너스 야추 획득 여부
                    bonus_minor_exist = false;
                    minor_score = 0;        //마이너 보너스 조건
                    bonus_score = 0;        //보너스 점수
                    total_score = 0;        //총점수
                    round = 1;      //라운드 수
                    chance = 0;
                    for(Button btn:button){
                        btn.setText("0");
                    }
                    bonus.setText("0");
                    total.setText("0");
                    minor_bonus.setText("0/63");
                    yacht_bonus.setText("미획득");
                    round_num.setText("1");
                    dialog.dismiss();
                }
            });
            ad.setNegativeButton("종료", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            ad.show();

        }
        else{
            round_num.setText(Integer.toString(round));
        }
    }
    private void update_minor_bonus(int index){
        minor_score += Integer.parseInt(button.get(index).getText().toString());
        if(!bonus_minor_exist){
            if(minor_score>=63){
                minor_bonus.setText("63/63");
                bonus_score += 30;
                total_score += 30;
                bonus.setText(Integer.toString(bonus_score));
                bonus_minor_exist = true;
            }
            else{
                minor_bonus.setText(Integer.toString(minor_score) + "/63");
            }
        }
    }
    private void nextRound(int index){
        for(Button btn : button){
            btn.setEnabled(false);
        }
        btn_roll.setEnabled(true);
        for(ImageView image0: image) image0.setBackgroundResource(R.drawable.dice_no_background);
        for(int i=0; i<5; i++) dice.set(i, false);
        chance = 0;
        round++;
        image_click = false;

        total_score += Integer.parseInt(button.get(index).getText().toString());
        score_exist.set(index, true);
        total.setText(Integer.toString(total_score));
        for(int i =0; i<12; i++) {
            if (!score_exist.get(i)) button.get(i).setText("0");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        image.add(one);
        image.add(two);
        image.add(three);
        image.add(four);
        image.add(five);

        bonus = findViewById(R.id.bonus);
        total = findViewById(R.id.total);
        minor_bonus = findViewById(R.id.minor_bonus);
        yacht_bonus = findViewById(R.id.yacht_bonus);
        round_num = findViewById(R.id.round_num);

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
        btn_pass = findViewById(R.id.btn_pass);
        button.add(btn_score1);
        button.add(btn_score2);
        button.add(btn_score3);
        button.add(btn_score4);
        button.add(btn_score5);
        button.add(btn_score6);
        button.add(btn_score_three);
        button.add(btn_score_four);
        button.add(btn_score_full);
        button.add(btn_score_small);
        button.add(btn_score_large);
        button.add(btn_score_yacht);






        btn_roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0;i<6; i++) score.set(i, 0);
                for(int i=0; i<12; i++){
                    if(!score_exist.get(i)){
                        button.get(i).setText("0");
                        button.get(i).setEnabled(false);
                    }
                }

                image_click = true;     //주사위 킾 활성화
                chance++;
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
                for(int i =0; i<6; i++){        //마이너 발생
                    if(!score_exist.get(i)){
                        if(score.get(i)>0){
                            button.get(i).setText(Integer.toString(score.get(i) * (i+1)));
                            button.get(i).setEnabled(true);
                        }
                    }
                }

                if(!score_exist.get(6)){        //스리 오브 어 카인드 발생
                    int add = 0;
                    Boolean three = false;
                    for(int j =0; j<6; j++){
                        add += score.get(j) * (j+1);
                        if(score.get(j)==3) three = true;
                    }
                    if(three){
                        button.get(6).setText(Integer.toString(add));
                        button.get(6).setEnabled(true);
                    }
                }
                if(!score_exist.get(7)){        //포 오브 어 카인드 발생
                    int add = 0;
                    Boolean four = false;
                    for(int j =0; j<6; j++){
                        add += score.get(j) * (j+1);
                        if(score.get(j)==4) four = true;
                    }
                    if(four){
                        button.get(7).setText(Integer.toString(add));
                        button.get(7).setEnabled(true);
                    }
                }
                if(!score_exist.get(8)){        //풀 하우스 발생
                    Boolean full1 = false, full2 = false;
                    for(int j =0; j<6; j++){
                        if(score.get(j)==3) full1 = true;
                        if(score.get(j)==2) full2 = true;
                    }
                    if(full1&&full2){
                        button.get(8).setText("25");
                        button.get(8).setEnabled(true);
                    }
                }
                if(!score_exist.get(9)) {       //스몰 스트레이트
                    if(score.get(2)>=1 && score.get(3)>=1){
                        if(score.get(0)>=1&&score.get(1)>=1&&score.get(4)==0){ //1234
                            button.get(9).setText("30");
                            button.get(9).setEnabled(true);
                        }
                        if(score.get(0)==0&&score.get(1)>=1&&score.get(4)>=1&&score.get(5)==0){ //2345
                            button.get(9).setText("30");
                            button.get(9).setEnabled(true);
                        }
                        if(score.get(1)==0&&score.get(4)>=1&&score.get(5)>=1){ //3456
                            button.get(9).setText("30");
                            button.get(9).setEnabled(true);
                        }
                    }
                }
                if(!score_exist.get(10)) {       //라지 스트레이트
                    if(score.get(1)==1 && score.get(2)==1&&score.get(3)==1 && score.get(4)==1){
                        if(score.get(0)==1||score.get(5)==1){ //12345, 23456
                            button.get(10).setText("40");
                            button.get(10).setEnabled(true);
                        }
                    }
                }
                if(!score_exist.get(11)){       //야추 발생
                    Boolean yacht = false;
                    for(int j =0; j<6; j++){
                        if(score.get(j)==5) yacht = true;
                    }
                    if(yacht){
                        button.get(11).setText("50");
                        button.get(11).setEnabled(true);
                    }
                }
                for(int j =0; j<6; j++){        //야추 보너스 발생
                    if(score.get(j)==5){
                        if(bonus_yacht){
                            bonus_score += 50;
                            total_score += 50;
                            bonus.setText(Integer.toString(bonus_score));
                            total.setText(Integer.toString(total_score));
                            yacht_bonus.setText("획득");
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
                if(image_click){
                    if(!dice.get(0)){
                        dice.set(0, true);
                        one.setBackgroundResource(R.drawable.dice_background);
                    }
                    else {
                        dice.set(0, false);
                        one.setBackgroundResource(R.drawable.dice_no_background);
                    }
                }

            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image_click){
                    if(!dice.get(1)){
                        dice.set(1, true);
                        two.setBackgroundResource(R.drawable.dice_background);
                    }
                    else {
                        dice.set(1, false);
                        two.setBackgroundResource(R.drawable.dice_no_background);
                    }
                }

            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image_click){
                    if(!dice.get(2)){
                        dice.set(2, true);
                        three.setBackgroundResource(R.drawable.dice_background);
                    }
                    else {
                        dice.set(2, false);
                        three.setBackgroundResource(R.drawable.dice_no_background);
                    }
                }
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image_click){
                    if(!dice.get(3)){
                        dice.set(3, true);
                        four.setBackgroundResource(R.drawable.dice_background);
                    }
                    else {
                        dice.set(3, false);
                        four.setBackgroundResource(R.drawable.dice_no_background);
                    }
                }

            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image_click){
                    if(!dice.get(4)){
                        dice.set(4, true);
                        five.setBackgroundResource(R.drawable.dice_background);
                    }
                    else {
                        dice.set(4, false);
                        five.setBackgroundResource(R.drawable.dice_no_background);
                    }
                }

            }
        });

        btn_score1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bonus_yacht = false;
                update_minor_bonus(0);
                nextRound(0);
                reset();
            }
        });

        btn_score2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bonus_yacht = false;
                update_minor_bonus(1);
                nextRound(1);
                reset();
            }
        });
        btn_score3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bonus_yacht = false;
                update_minor_bonus(2);
                nextRound(2);
                reset();
            }
        });
        btn_score4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bonus_yacht = false;
                update_minor_bonus(3);
                nextRound(3);
                reset();
            }
        });
        btn_score5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bonus_yacht = false;
                update_minor_bonus(4);
                nextRound(4);
                reset();
            }
        });
        btn_score6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bonus_yacht = false;
                update_minor_bonus(5);
                nextRound(5);
                reset();
            }
        });
        btn_score_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bonus_yacht = false;
                nextRound(6);
                reset();
            }
        });
        btn_score_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bonus_yacht = false;
                nextRound(7);
                reset();
            }
        });
        btn_score_full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bonus_yacht = false;
                nextRound(8);
                reset();
            }
        });
        btn_score_small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bonus_yacht = false;
                nextRound(9);
                reset();
            }
        });
        btn_score_large.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bonus_yacht = false;
                nextRound(10);
                reset();
            }
        });
        btn_score_yacht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bonus_yacht = true;
                nextRound(11);
                reset();
            }
        });
        btn_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bonus_yacht = false;
                for(Button btn : button){
                    btn.setEnabled(false);
                }
                btn_roll.setEnabled(true);
                for(ImageView image0: image) image0.setBackgroundResource(R.drawable.dice_no_background);
                for(int i=0; i<5; i++) dice.set(i, false);
                image_click = false;
                chance = 0;
                round++;
                for(int i =0; i<12; i++) {
                    if (!score_exist.get(i)) button.get(i).setText("0");
                }
                reset();
            }
        });
    }
}