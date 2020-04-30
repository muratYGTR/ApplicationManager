package com.mbakan.applicationmanager.ui.main;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import com.mbakan.applicationmanager.ApplicationItem;
import com.mbakan.applicationmanager.R;
import com.mbakan.applicationmanager.adapters.ApplicationListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private ApplicationListAdapter mListViewAdapter;
    private ArrayList<ApplicationItem> mApplicationItems;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        final Button button = root.findViewById(R.id.application_button);
        final ListView listView = root.findViewById(R.id.list_view);
/*        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
                mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                List<ResolveInfo> pkgAppsList = v.getContext().getPackageManager().queryIntentActivities( mainIntent, 0);
                mApplicationItems = new ArrayList<ApplicationItem>();
                for( ResolveInfo info : pkgAppsList )
                {
                    mApplicationItems.add(new ApplicationItem(info.loadIcon(v.getContext().getPackageManager()),
                            info.loadLabel(v.getContext().getPackageManager()).toString()));

                }
                listView.setAdapter(new ApplicationListAdapter(v.getContext(), mApplicationItems));
            }
        });*/
        return root;
    }
}