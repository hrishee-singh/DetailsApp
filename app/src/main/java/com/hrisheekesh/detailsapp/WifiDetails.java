package com.hrisheekesh.detailsapp;

import android.content.Context;
import android.net.wifi.WifiManager;

public class WifiDetails extends MainActivity {


    public void wifi() {

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiInfo = wifiManager.getConnectionInfo();
        wifiManager.startScan();
        availableWifi = String.valueOf(wifiManager.getScanResults());

    }
}
