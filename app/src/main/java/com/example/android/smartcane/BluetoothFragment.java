package com.example.android.smartcane;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import me.aflak.bluetooth.Bluetooth;

public class BluetoothFragment extends Fragment implements Bluetooth.CommunicationCallback {

    private String name;
    int pos=0;
    private Bluetooth b;
    private EditText message;
    private Button send;
    private TextView text;
    private ScrollView scrollView;
    private boolean registered=false;
    private BluetoothDevice deviceToPair;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bluetooth, container, false);

        text = view.findViewById(R.id.text);
        message = view.findViewById(R.id.message);
        send = view.findViewById(R.id.send);
        scrollView = view.findViewById(R.id.scrollView);

        text.setMovementMethod(new ScrollingMovementMethod());
        send.setEnabled(false);


        return view;
    }




    @Override
    public void onResume() {
        super.onResume();

        b = new Bluetooth(getActivity());
        b.enableBluetooth();
        b.setCommunicationCallback(this);

        tryConnecting();


        Display("Connecting...");
//        b.connectToDevice(b.getPairedDevices().get(pos));

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = message.getText().toString();
                message.setText("");
                b.send(msg);
                Display("You: "+msg);
            }
        });

        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        getActivity().registerReceiver(mReceiver, filter);
        registered=true;
    }

    private void tryConnecting() {
        List<BluetoothDevice> devices =  b.getPairedDevices();

        if (devices!=null)
        {
            for(BluetoothDevice device:devices)
            {
                if(device.getAddress().equalsIgnoreCase("98:D3:31:30:AE:83"))
                {
                    deviceToPair =device;
                    makeConnection();
                }

            }
        }else{
            tryConnecting();
        }

    }

    private void makeConnection() {
        if(deviceToPair!=null)
        {
            b.connectToDevice(deviceToPair);
        }else{
            tryConnecting();
            Toast.makeText(getContext(),"Device not found",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(registered) {
            getActivity().unregisterReceiver(mReceiver);
            registered=false;
        }
    }


    public void Display(final String s){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.append(s + "\n");
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    @Override
    public void onConnect(BluetoothDevice device) {
        Display("Connected to "+device.getName()+" - "+device.getAddress());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                send.setEnabled(true);
            }
        });
    }

    @Override
    public void onDisconnect(BluetoothDevice device, String message) {
        Display("Disconnected!");
        Display("Connecting again...");
        b.connectToDevice(device);
    }

    @Override
    public void onMessage(String message) {
        Display(name+": "+message);
    }

    @Override
    public void onError(String message) {
        Display("Error: "+message);
    }

    @Override
    public void onConnectError(final BluetoothDevice device, String message) {
        Display("Error: "+message);
        Display("Trying again in 3 sec.");
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        b.connectToDevice(device);
                    }
                }, 2000);
            }
        });
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        if(registered) {
                            getActivity().unregisterReceiver(mReceiver);
                            registered=false;
                        }

                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        if(registered) {
                            getActivity().unregisterReceiver(mReceiver);
                            registered=false;
                        }

                        break;
                }
            }
        }
    };


}