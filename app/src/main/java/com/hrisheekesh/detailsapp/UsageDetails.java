package com.hrisheekesh.detailsapp;

import static java.util.Calendar.*;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.RequiresApi;

public class UsageDetails extends MainActivity {

    PackageManager packageManager = getPackageManager();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    public UsageDetails() {
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
}


