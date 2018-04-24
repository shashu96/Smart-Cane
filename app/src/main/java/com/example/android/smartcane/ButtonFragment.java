package com.example.android.smartcane;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by Shravan on 4/17/2018.
 */

public class ButtonFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_button, container, false);
        Button startBtn =  view.findViewById(R.id.button_1);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String messageToSend = "Its EMERGENCY!!\nCall me if you got this message";
                String number = "9632123381";

                SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null, null);

                /*
                number = "9845724355";
                SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null,null);

                number = "9008346385";
                SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null,null);
        */
            }
        });

        Button btn_click =  view.findViewById(R.id.button_2);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Uri gmmIntentUri = Uri.parse("geo:0,0?q=");
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        startActivity(mapIntent);
                    }
                }, 1000);
            }
        });
        return view;
    }

}
