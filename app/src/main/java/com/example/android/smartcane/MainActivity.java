package com.example.android.smartcane;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.telephony.SmsManager;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = (Button) findViewById(R.id.button_1);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String messageToSend = "Its EMERGENCY!!\nCall me if you got this message";
                String number = "9632123381";

                SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null,null);

                number = "9845724355";
                SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null,null);

                number = "9008346385";
                SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null,null);
            }
        });
    }
}
