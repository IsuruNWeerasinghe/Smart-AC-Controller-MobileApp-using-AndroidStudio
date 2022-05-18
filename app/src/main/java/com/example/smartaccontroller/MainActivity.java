package com.example.smartaccontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button powerBtn, tempUpBtn, tempDownBtn, fanSpdBtn, swingBtn, eSavingBtn, cleanBtn, jetCoolBtn, modeBtn;
    TextView tempTxt, swingTxt, fanSpdTxt;
    int acPwr = 1;
    int acTemp = 24;
    int acFan = 3;
    int acSwing = 0;
    int acMode = 2;
    int acJetCool = 0;
    int acESaving = 0;
    int acClean = 0;


    String acstatus1 = "on";
    Handler handler;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onStart() {
        super.onStart();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("acSettings/temp");
        databaseReference.setValue(acTemp);

        databaseReference = firebaseDatabase.getReference("acSettings/fanSpd");
        databaseReference.setValue(acFan);

        databaseReference = firebaseDatabase.getReference("acSettings/swing");
        databaseReference.setValue(acSwing);

        databaseReference = firebaseDatabase.getReference("acSettings/eSaving");
        databaseReference.setValue(acESaving);

        databaseReference = firebaseDatabase.getReference("acSettings/clean");
        databaseReference.setValue(acClean);

        databaseReference = firebaseDatabase.getReference("acSettings/jetCool");
        databaseReference.setValue(acJetCool);

        databaseReference = firebaseDatabase.getReference("acSettings/mode");
        databaseReference.setValue(acMode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        powerBtn = findViewById(R.id.powerBtn);
        tempDownBtn = findViewById(R.id.tempdownbtn);
        tempUpBtn = findViewById(R.id.tempupbtn);
        fanSpdBtn = findViewById(R.id.fanspdbtn);
        swingBtn = findViewById(R.id.swingbtn);
        eSavingBtn = findViewById(R.id.esavingbtn);
        cleanBtn = findViewById(R.id.cleanbtn);
        jetCoolBtn = findViewById(R.id.jetcoolbtn);
        modeBtn = findViewById(R.id.modebtn);

        tempTxt = findViewById(R.id.temptxtbx);
        swingTxt = findViewById(R.id.swingtxtbx);
        fanSpdTxt = findViewById(R.id.fanspdtxtbx);

        handler = new Handler();

        tempTxt.setText(String.valueOf(acTemp) + " C");
        if(acFan == 1) { fanSpdTxt.setText("LOW");}
        if(acFan == 2) { fanSpdTxt.setText("MEDIUM");}
        if(acFan == 3) { fanSpdTxt.setText("HIGH");}
        if(acFan == 4) { fanSpdTxt.setText("AUTO");}
        if(acSwing == 0) { swingTxt.setText("Off");}
        if(acSwing == 1) { swingTxt.setText("On");}


        powerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acPwr = acPwr + 1;
                if(acPwr > 1){ acPwr = 0;}
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("acSettings/power");
                databaseReference.setValue(acPwr);

                if(acPwr == 1){
                    acTemp = 24;
                    acFan = 3;
                    acSwing = 0;
                    acMode = 2;
                    acJetCool = 0;
                    acESaving = 0;
                    acClean = 0;

                    databaseReference = firebaseDatabase.getReference("acSettings/temp");
                    databaseReference.setValue(acTemp);
                    databaseReference = firebaseDatabase.getReference("acSettings/fanSpd");
                    databaseReference.setValue(acFan);
                    databaseReference = firebaseDatabase.getReference("acSettings/swing");
                    databaseReference.setValue(acSwing);
                    databaseReference = firebaseDatabase.getReference("acSettings/eSaving");
                    databaseReference.setValue(acESaving);
                    databaseReference = firebaseDatabase.getReference("acSettings/clean");
                    databaseReference.setValue(acClean);
                    databaseReference = firebaseDatabase.getReference("acSettings/jetCool");
                    databaseReference.setValue(acJetCool);
                    databaseReference = firebaseDatabase.getReference("acSettings/mode");
                    databaseReference.setValue(acMode);

                    tempTxt.setText(String.valueOf(acTemp) + " C");
                    if(acFan == 1) { fanSpdTxt.setText("Low");}
                    if(acFan == 2) { fanSpdTxt.setText("Medium");}
                    if(acFan == 3) { fanSpdTxt.setText("High");}
                    if(acFan == 4) { fanSpdTxt.setText("AUTO");}
                    if(acSwing == 0) { swingTxt.setText("Off");}
                    if(acSwing == 1) { swingTxt.setText("On");}
                }
            }
        });
        tempDownBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acTemp = acTemp - 1;
                if(acTemp < 18) {acTemp = 18;}
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("acSettings/temp");
                databaseReference.setValue(acTemp);
                tempTxt.setText(String.valueOf(acTemp) + " C");

            }
        });
        tempUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acTemp = acTemp + 1;
                if(acTemp > 30) {acTemp = 30;}
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("acSettings/temp");
                databaseReference.setValue(acTemp);
                tempTxt.setText(String.valueOf(acTemp)  + " C");

            }
        });
        fanSpdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acFan = acFan + 1;
                if(acFan > 4){ acFan = 1;}
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("acSettings/fanSpd");
                databaseReference.setValue(acFan);
                if(acFan == 1) { fanSpdTxt.setText("LOW");}
                if(acFan == 2) { fanSpdTxt.setText("MEDIUM");}
                if(acFan == 3) { fanSpdTxt.setText("HIGH");}
                if(acFan == 4) { fanSpdTxt.setText("AUTO");}
            }
        });
        swingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acSwing = acSwing + 1;
                if(acSwing > 1){ acSwing = 0;}
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("acSettings/swing");
                databaseReference.setValue(acSwing);
                if(acSwing == 0) { swingTxt.setText("Off");}
                if(acSwing == 1) { swingTxt.setText("On");}

            }
        });
        eSavingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acESaving = acESaving + 1;
                if(acESaving > 1){ acESaving = 0;}
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("acSettings/eSaving");
                databaseReference.setValue(acESaving);

            }
        });
        cleanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acClean = acClean + 1;
                if(acClean > 1){ acClean = 0;}
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("acSettings/clean");
                databaseReference.setValue(acClean);

            }
        });
        jetCoolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acJetCool = acJetCool + 1;
                if(acJetCool > 1){ acJetCool = 0;}
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("acSettings/jetCool");
                databaseReference.setValue(acJetCool);

            }
        });
        modeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acMode = acMode + 1;
                if (acMode > 3){acMode = 1;}
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("acSettings/mode");
                databaseReference.setValue(acMode);

            }
        });

    }
}