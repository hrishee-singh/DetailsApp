package com.hrisheekesh.detailsapp;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.O)
public class SimDetails extends MainActivity {

    public SimDetails() {
        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        operatorName = String.valueOf(telephonyManager.getSimOperatorName());
        imei = String.valueOf(telephonyManager.getImei());
    }
}
