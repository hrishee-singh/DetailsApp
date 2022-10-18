package com.hrisheekesh.detailsapp;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.os.Build;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class AppDetails extends MainActivity {

  /*  @SuppressLint("SuspiciousIndentation")
    @RequiresApi(api = Build.VERSION_CODES.P)
    public PackageInfo getPackageInfo() {

        List<PackageInfo> packList = getPackageManager().getInstalledPackages(0);
        String[] apps = new String[packList.size()];
        for (int i = 0; i < packList.size(); i++) {
            PackageInfo packInfo = packList.get(i);
            apps[i] = packInfo.applicationInfo.loadLabel(getPackageManager()).toString();
        }

        private ArrayList<PackageInfo> getInstalledApps(boolean getSysPackages = false){
            ArrayList<PackageInfo> res = new ArrayList<PackageInfo>();
            List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
            for (int i = 0; i < packs.size(); i++) {
                PackageInfo p = packs.get(i);
                if ((!getSysPackages) && (packageInfo.versionName == null)) {
                    continue ;}

                PackageInfo packageInfo = new PackageInfo();
                packageInfo.appName = (String) getPackageManager().getApplicationLabel(
                        p.applicationInfo);
                packageInfo.packageName = p.packageName;
                packageInfo.versionName = p.versionName;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    packageInfo.certificateHash = packageInfo.signingInfo;
                    res.add(packageInfo);
                }
            }
            return res;
        }
    @SuppressWarnings("InfiniteRecursion")
    public int getPackageSource() {
        return getPackageSource();
    }*/
}