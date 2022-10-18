package com.hrisheekesh.detailsapp;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.hardware.SensorManager;
import android.os.Build;
import android.provider.Settings;
import java.util.TimeZone;

public class MetaData extends MainActivity {

    @SuppressLint({"MissingPermission", "HardwareIds"})
    public MetaData() {
        ID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        myDevice = BluetoothAdapter.getDefaultAdapter();
        deviceName = myDevice.getName();
        deviceModel = Build.MODEL;
        tz = TimeZone.getDefault();
        time = System.currentTimeMillis() - android.os.SystemClock.elapsedRealtime();

    }
}
