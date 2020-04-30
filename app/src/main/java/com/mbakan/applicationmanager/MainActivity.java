package com.mbakan.applicationmanager;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import com.mbakan.applicationmanager.adapters.ApplicationAdapter;
import com.mbakan.applicationmanager.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ApplicationItem> mApplicationItems;
    private RecyclerView mListView;
    private LinearLayoutManager mLinearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mListView = findViewById(R.id.app_list);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pkgAppsList = getPackageManager().queryIntentActivities(mainIntent, 0);
        final PackageManager packageManager = getPackageManager();
        mApplicationItems = new ArrayList<>();
        for (ResolveInfo info : pkgAppsList) {
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
        mListView.setLayoutManager(new LinearLayoutManager(this));
        mListView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        mListView.setAdapter(new ApplicationAdapter(mApplicationItems, this));



    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);
        return true;
/*        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);*/
/*        try {
            SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    // this is your adapter that will be filtered
                    myAdapter.getFilter().filter(newText);
                    System.out.println("on text chnge text: " + newText);
                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    // this is your adapter that will be filtered
                    myAdapter.getFilter().filter(query);
                    System.out.println("on query submit: " + query);
                    return true;
                }
            };
            searchView.setOnQueryTextListener(textChangeListener);
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
    }
}