package com.mbakan.applicationmanager;

import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.mbakan.applicationmanager.adapters.ApplicationAdapter;
import com.mbakan.applicationmanager.ui.main.PlaceholderFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyApplicationsFragment extends Fragment {

    private ApplicationAdapter mListViewAdapter;
    private ArrayList<ApplicationItem> mApplicationItems;
    private ListView mListView;
    private Button mListButton;

    public static MyApplicationsFragment newInstance() {
        MyApplicationsFragment fragment = new MyApplicationsFragment();
        return fragment;
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_applications, container, false);


        //mListView = view.findViewById(R.id.list_view);

        /*mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               ApplicationItem item = (ApplicationItem) mListView.getItemAtPosition(position);
                Intent launchIntent = view.getContext()
                        .getPackageManager()
                        .getLaunchIntentForPackage(item.getApplicationPackageName());
                if (launchIntent != null) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                }
            }
        });*/
        mListButton = view.findViewById(R.id.application_button);

/*        mListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
                mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                List<ResolveInfo> pkgAppsList = v.getContext().getPackageManager().queryIntentActivities( mainIntent, 0);
                final PackageManager packageManager = v.getContext().getPackageManager();
                mApplicationItems = new ArrayList<>();
                for( ResolveInfo info : pkgAppsList )
                {
                    if( info.activityInfo.packageName.startsWith("com.mbakan") ) {
                        long installTime = 0;
                        try {
                            installTime = packageManager
                                    .getPackageInfo(info.activityInfo.packageName, 0).firstInstallTime;
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                        mApplicationItems.add(new ApplicationItem(info.loadIcon(packageManager),
                                info.loadLabel(packageManager).toString(),
                                info.activityInfo.packageName,
                                new Date(installTime)));
                    }

                }
                mListView.setAdapter(new ApplicationListAdapter(v.getContext(), mApplicationItems));
            }
        });*/
        return view;
    }
}

