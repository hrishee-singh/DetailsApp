package com.hrisheekesh.detailsapp;

import static java.util.Calendar.getInstance;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.SigningInfo;
import android.hardware.SensorManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    PackageInfo packageInfo;
    String appName = null;
    String packageName = null;
    String versionName = null;
    SigningInfo certificateHash = null;
    String packageSource = null;

    PackageManager packageManager;
    Map<String, UsageStats> stats;
    List<ApplicationInfo> applicationInfoList;
    UsageStatsManager usageStatsManager;
    Calendar calendar;
    long start;
    long end;
    long lastTimeUsed;
    long actualLastTimeUsed;

    WifiManager wifiManager;
    WifiInfo wifiInfo = null;
    String availableWifi = null;

    String operatorName = null;
    String imei = null;

    String ID = null;
    SensorManager sm = null;
    BluetoothAdapter myDevice;
    String deviceName = null;
    String deviceModel = null;
    TimeZone tz = null;
    Long time = null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CompositionJso obj = new CompositionJso();
        obj.makeJSONObject(appName);




        try {

            File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + appName + ".json");
            FileWriter fileWriter = new FileWriter(file);
            new BufferedWriter(fileWriter);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(obj.toString());
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        finish();

    }
    public class CompositionJso extends JSONObject {

        public JSONObject makeJSONObject(String appName) {

            JSONObject obj = new JSONObject();

            try {
                obj.put("Name", packageName );
                obj.put("App Name", appName);
                obj.put("Version", versionName);
                obj.put("Certificate Hash", certificateHash);
                obj.put("Package Source", packageSource);
                obj.put("App Usage Time", actualLastTimeUsed);
                obj.put("Wifi Info", wifiInfo);
                obj.put("Available Wifi", availableWifi);
                obj.put("Sim Operator Name", operatorName);
                obj.put("IMEI Number", imei);
                obj.put("Device ID", ID);
                obj.put("Sensor Info", sm);
                obj.put("Device Name", deviceName);
                obj.put("Model Name", deviceModel);
                obj.put("Time Zone", tz);
                obj.put("Last Boot TimeStamp", time);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return obj;

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    public void UsageDetail() {
        usageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
        applicationInfoList = packageManager.getInstalledApplications(0);
        calendar = getInstance();
        start = calendar.getTimeInMillis();
        end = System.currentTimeMillis();
        stats = usageStatsManager.queryAndAggregateUsageStats(start, end);
        for (ApplicationInfo info : applicationInfoList) {
            GetAppInfo(info);
        }
    }

    public void GetAppInfo(ApplicationInfo info) {
        UsageStats usageStats = stats.get(info.packageName);

        if (usageStats != null) {
            lastTimeUsed = usageStats.getLastTimeUsed();
            actualLastTimeUsed = System.currentTimeMillis() - lastTimeUsed;
        }
    }

    public void wifi() {

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiInfo = wifiManager.getConnectionInfo();
        //wifiManager.startScan();
        //availableWifi = String.valueOf(wifiManager.getScanResults());

    }
public void simDetails() {

        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        operatorName = String.valueOf(telephonyManager.getSimOperatorName());
    //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        //imei = String.valueOf(telephonyManager.getImei());
   // }

}

    @SuppressLint({"MissingPermission", "HardwareIds"})
    public void MetaData() {
        ID = String.valueOf(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        myDevice = BluetoothAdapter.getDefaultAdapter();
        deviceName = myDevice.getName();
        deviceModel = Build.MODEL;
        tz = TimeZone.getDefault();
        time = System.currentTimeMillis() - android.os.SystemClock.elapsedRealtime();

    }
}
