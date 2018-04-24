package com.example.android.smartcane;


import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    BluetoothDevice deviceToPair;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* Bluetooth bluetooth= new Bluetooth(this);
        bluetooth.enableBluetooth();
       List<BluetoothDevice> devices =  bluetooth.getPairedDevices();

       for(BluetoothDevice device:devices)
       {
           if(device.getName().equalsIgnoreCase("HC-05"))
           {
               deviceToPair =device;
           }

       }

       if(deviceToPair!=null)
       {
           bluetooth.connectToDevice(deviceToPair);
       }else{
           Toast.makeText(this,"Device not found",Toast.LENGTH_SHORT).show();
       }
*/
        FragmentManager fragmentManager= getSupportFragmentManager();
        Fragment fragment= fragmentManager.findFragmentById(R.id.viewpager_container);

        if(fragment==null)
        {
            fragment=new com.example.android.smartcane.ViewPagerFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.viewpager_container,fragment)
                    .commit();

        }
    }
}