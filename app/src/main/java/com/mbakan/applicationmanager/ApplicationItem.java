package com.mbakan.applicationmanager;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.widget.ImageView;

import java.util.Date;

public class ApplicationItem {


    private Drawable mIcon;
    private String mApplicationName;
    private String mApplicationPackageName;
    private Date mInstallTime;

    public Date getInstallTime() {
        return mInstallTime;
    }

    public void setInstallTime(Date installTime) {
        mInstallTime = installTime;
    }

    public String getApplicationName() {
        return mApplicationName;
    }

    public void setApplicationName(String applicationName) {
        mApplicationName = applicationName;
    }


    public ApplicationItem(Drawable icon, String applicationName,
                           String applicationPackageName, Date installTime)

    {
        mIcon = icon;
        mApplicationName = applicationName;
        mApplicationPackageName = applicationPackageName;
        mInstallTime = installTime;
    }
    public String getApplicationPackageName() {
        return mApplicationPackageName;
    }

    public void setApplicationPackageName(String applicationPackageName) {
        mApplicationPackageName = applicationPackageName;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public void setIcon(Drawable icon) {
        mIcon = icon;
    }
}
