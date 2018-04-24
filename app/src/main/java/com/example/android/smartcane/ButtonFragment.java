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

    /*private static final String TAG = MainActivity.class.getSimpleName();

    private FusedLocationProviderClient mFusedLocationClient;

    *//**
     * Represents a geographical location.
     *//*
    double mLastLocationLatitude;
    double mLastLocationLongitude;

    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_button, container, false);

        //mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        Button startBtn =  view.findViewById(R.id.button_1);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*StringBuffer messageToSend = new StringBuffer();
                String number = "9632123381";
                messageToSend.append("Its EMERGENCY!!\nCall me if you got this message");
                messageToSend.append("http://maps.google.com?q=");
                messageToSend.append(mLastLocationLatitude);
                messageToSend.append(",");
                messageToSend.append(mLastLocationLongitude);

                SmsManager.getDefault().sendTextMessage(number, null, messageToSend.toString(), null, null);
*/

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

    /*@Override
    public void onStart() {
        super.onStart();

        if (!checkPermissions()) {
            requestPermissions();
        } else {
            getLastLocation();
        }
    }

    @SuppressWarnings("MissingPermission")
    private void getLastLocation(){

        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.

                        mLastLocationLatitude = location.getLatitude();
                        mLastLocationLongitude = location.getLongitude();
                    }
                });
    }

    *//**
     * Return the current state of the permissions needed.
     *//*
    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                REQUEST_PERMISSIONS_REQUEST_CODE);
    }

    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION);

        // Provide an additional rationale to the user. This would happen if the user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale) {
            Log.i(TAG, "Displaying permission rationale to provide additional context.");

            showSnackbar(R.string.permission_rationale, android.R.string.ok,
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Request permission
                            startLocationPermissionRequest();
                        }
                    });

        } else {
            Log.i(TAG, "Requesting permission");
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the user denied the permission
            // previously and checked "Never ask again".
            startLocationPermissionRequest();
        }
    }*/

}
