package com.example.light_control;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
//import android.annotation.SuppressLint;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
//import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
//import com.example.light_control.R;
import android.widget.Button;
//import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
//import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity3 extends AppCompatActivity {
    private Button btnlogout;
    private boolean isFirebaseUpdate;
    private boolean[] isOn = {false,false, false, false,false, false, false};
    private Button btnxoaytrai;
    private Button btnxoayphai;
    private Button btnxoaytrai_motor2;
    private Button btnxoayphai_motor2;
    private Button kichhoat;
    private Button cungpha;
    private Button nguocpha;
    DatabaseReference btnxoaytraidata;
    DatabaseReference btnxoayphaidata;
    DatabaseReference btnxoaytraidata_motor2;
    DatabaseReference btnxoayphaidata_motor2;
    DatabaseReference kichhoat_data;
    DatabaseReference cungpha_data;
    DatabaseReference nguocpha_data;
    DatabaseReference lb1Ref;
    int colorOn = Color.parseColor("#95FFEB"); // Xanh
    int colorOff = Color.parseColor("#A5D6A7"); // Đỏ

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);/////////////////////
        btnlogout=findViewById(R.id.btnlogout);
        btnxoaytrai = findViewById(R.id.btn_xoaytrai);
        btnxoayphai = findViewById(R.id.btn_xoayphai);
        btnxoaytrai_motor2 = findViewById(R.id.btn_xoaytrai_motor2);
        btnxoayphai_motor2 = findViewById(R.id.btn_xoayphai_motor2);
        kichhoat = findViewById(R.id.kichhoat);
        cungpha = findViewById(R.id.cungpha);
        nguocpha = findViewById(R.id.nguocpha);

//nhom15@gmail.com
        //1234567
        btnxoaytraidata=FirebaseDatabase.getInstance().getReference().child("MODECONTROL1");
        btnxoayphaidata=FirebaseDatabase.getInstance().getReference().child("MODECONTROL1");
        btnxoaytraidata_motor2=FirebaseDatabase.getInstance().getReference().child("MODECONTROL1");
        btnxoayphaidata_motor2=FirebaseDatabase.getInstance().getReference().child("MODECONTROL1");
        cungpha_data=FirebaseDatabase.getInstance().getReference().child("MODECONTROL1");
        nguocpha_data=FirebaseDatabase.getInstance().getReference().child("MODECONTROL1");
        kichhoat_data=FirebaseDatabase.getInstance().getReference().child("MODECONTROL2");
        lb1Ref = FirebaseDatabase.getInstance().getReference().child("MODECONTROL1");


        kichhoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isOn[0] = !isOn[0];
                ColorStateList colorStateList = ColorStateList.valueOf(isOn[0] ? colorOn : colorOff);
                kichhoat.setBackgroundTintList(colorStateList);
                kichhoat.setSelected(isOn[0]);
                if (isOn[0]) {
                    kichhoat_data.setValue(1);
                }
                else {
                    kichhoat_data.setValue(0);
                }
            }
        });
        cungpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isOn[6] = !isOn[6];
                cungpha.setSelected(isOn[6]);
                ColorStateList colorStateList = ColorStateList.valueOf(isOn[6] ? colorOn : colorOff);
                cungpha.setBackgroundTintList(colorStateList);
                if (isOn[6]) {
                    cungpha_data.setValue(6);
                }
                else {
                    cungpha_data.setValue(0);
                }
            }
        });
        nguocpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isOn[1] = !isOn[1];
                nguocpha.setSelected(isOn[1]);
                ColorStateList colorStateList = ColorStateList.valueOf(isOn[1] ? colorOn : colorOff);
                nguocpha.setBackgroundTintList(colorStateList);
                if (isOn[1]) {
                    nguocpha_data.setValue(5);
                }
                else {
                    nguocpha_data.setValue(0);
                }
            }
        });

        btnxoaytrai.setOnClickListener(new View.OnClickListener() { //động cơ 1 quay trái
            @Override
            public void onClick(View v) {
                isOn[5] = !isOn[5];
                btnxoaytrai.setSelected(isOn[5]);
                ColorStateList colorStateList = ColorStateList.valueOf(isOn[5] ? colorOn : colorOff);
                btnxoaytrai.setBackgroundTintList(colorStateList);
                if (isOn[5]) {
                    btnxoaytraidata.setValue(1);
                }
                else {
                    btnxoaytraidata.setValue(0);
                }
            }
        });

        btnxoayphai.setOnClickListener(new View.OnClickListener() { //động cơ 1 quay phải
            @Override
            public void onClick(View v) {
                isOn[4] = !isOn[4];
                btnxoayphai.setSelected(isOn[4]);
                ColorStateList colorStateList = ColorStateList.valueOf(isOn[4] ? colorOn : colorOff);
                btnxoayphai.setBackgroundTintList(colorStateList);
                if (isOn[4]) {
                    btnxoayphaidata.setValue(2);
                }
                else {
                    btnxoayphaidata.setValue(0);
                }
            }
        });
        btnxoaytrai_motor2.setOnClickListener(new View.OnClickListener() { //động cơ 2 quay trái
            @Override
            public void onClick(View v) {
                isOn[2] = !isOn[2];
                btnxoaytrai_motor2.setSelected(isOn[2]);
                ColorStateList colorStateList = ColorStateList.valueOf(isOn[2] ? colorOn : colorOff);
                btnxoaytrai_motor2.setBackgroundTintList(colorStateList);
                if (isOn[2]) {
                    btnxoaytraidata_motor2.setValue(3);
                }
                else {
                    btnxoaytraidata_motor2.setValue(0);
                }
            }
        });
        btnxoayphai_motor2.setOnClickListener(new View.OnClickListener() { //động cơ 2 quay phải
            @Override
            public void onClick(View v) {
                isOn[3] = !isOn[3];
                btnxoayphai_motor2.setSelected(isOn[3]);
                ColorStateList colorStateList = ColorStateList.valueOf(isOn[3] ? colorOn : colorOff);
                btnxoayphai_motor2.setBackgroundTintList(colorStateList);
                if (isOn[3]) {
                    btnxoaytraidata_motor2.setValue(4);
                }
                else {
                    btnxoaytraidata_motor2.setValue(0);
                }
            }
        });

        //BUTTONWARNING
//        btnxoaytraidata.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    isFirebaseUpdate = true;
//                    btnxoaytrai = snapshot.getValue(Button.class);
//                    if (btnxoaytrai == 1) {
//                        isOn[4] = true;
//                        btnxoaytrai.setSelected(isOn[4]);
//                    }
//
//                    //  isOn[4] = (btnwrdata != null && btnwrdata == true);
//                    //  btnxoaytrai.setSelected(isOn[4]);
//
//                    else {
//                        isOn[4] = false;
//                        btnxoaytrai.setSelected(isOn[4]);
//                    }
//                }
////                public void onCancelled (DatabaseError Object error;
////                error){
////                    //NOP
////                }
//            }
//        });
        //RELAY1
//        btnxoayphai.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    isFirebaseUpdate1 = true;
//                    btnwrdata1 = snapshot.getValue(Boolean.class);
//                    isOn[0] = (btnwrdata1 != null && btnwrdata1 == true);
//                    btnxoayphai.setSelected(isOn[0]);
//                }else {
//                    isOn[0] = false;
//                    btnxoayphai.setSelected(isOn[0]);
//                }
//            }
//            public void onCancelled(@NonNull DatabaseError error) {
//                //NOP
//                Toast.makeText(MainActivity3.this, "Lỗi đọc dữ liệu: " + error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
        //SEEKBARCANHBAO
//        thanhcanhbao_data.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    giatriCanhbao = snapshot.getValue(Integer.class);
//                    isFirebaseUpdate = true;
//                    thanhcanhbao.setProgress(giatriCanhbao);
//                    muccanhbao.setText(giatriCanhbao + "Ppm");
//                }
//            }
//            public void onCancelled(@NonNull DatabaseError error) {
//                //NOP
//            }
//        });
        //SEEKBARCANHBAO
//        thanhcanhbao.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                if (!isFirebaseUpdate) {
//                    thanhcanhbao_data.setValue(i);
//                    muccanhbao.setText(i + "Ppm");
//                }
//                isFirebaseUpdate = false;
//            }
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//            }
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//            }
//        });

        //NOTIFICATION

        //DHT11
//        nhietdodata=FirebaseDatabase.getInstance().getReference();
//        final ValueEventListener nd_dht11 = nhietdodata.child("FIRE_FLOOR1").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    Boolean fireDetected = snapshot.getValue(Boolean.class); // Lấy giá trị boolean
//                    if (fireDetected != null && fireDetected) {
//                        nhietdoTextView.setText("Phát hiện lửa");
//                        sendFireNotification(1, "lửa"); // Gửi thông báo khi phát hiện lửa
//                    } else {
//                        nhietdoTextView.setText("Không phát hiện lửa");
//                    }
//                } else {
//                    nhietdoTextView.setText("Dữ liệu không tồn tại");
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(MainActivity3.this, "Lỗi đọc dữ liệu: " + error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
        //GAS
//        gasdata = FirebaseDatabase.getInstance().getReference();
//        final ValueEventListener gas_value = gasdata.child("CO2_FLOOR1").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                mq2TextView.setText(snapshot.getValue().toString() + "Ppm");
//                if (snapshot.exists()) {
//                    Integer CO2value = snapshot.getValue(Integer.class);
//                    if (CO2value > 1500) sendFireNotification(1, "khói");
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(MainActivity3.this, "Lỗi đọc dữ liệu: " + error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
        //HUMIDITY
//        humiditydata = FirebaseDatabase.getInstance().getReference();
//        final ValueEventListener humidity_value = humiditydata.child("FIRE_FLOOR2").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    Boolean fire2Detected = snapshot.getValue(Boolean.class); // Lấy giá trị boolean
//                    if (fire2Detected != null && fire2Detected) {
//                        humidityTextView.setText("Phát hiện lửa");
//                        sendFireNotification(2, "lửa");
//                    } else {
//                        humidityTextView.setText("Không phát hiện lửa");
//                    }
//                } else {
//                    humidityTextView.setText("Dữ liệu không tồn tại");
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(MainActivity3.this, "Lỗi đọc dữ liệu: " + error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
        // xu ly nut logout
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
///////////////
    }



}