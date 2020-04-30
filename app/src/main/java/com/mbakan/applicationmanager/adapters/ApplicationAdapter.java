package com.mbakan.applicationmanager.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.mbakan.applicationmanager.ApplicationItem;
import com.mbakan.applicationmanager.MainActivity;
import com.mbakan.applicationmanager.R;

import java.util.ArrayList;
import java.util.List;


public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.ApplicationViewHolder>
        implements Filterable {

    private List<ApplicationItem> mApplicationItems;
    private List<ApplicationItem> mApplicationItemsSearch;
    private Context mContext;

    public ApplicationAdapter(List<ApplicationItem> items, Context context)
    {
        mContext = context;
        mApplicationItems = items;
    }

    @NonNull
    @Override
    public ApplicationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View appAdapterView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.application_item, viewGroup, false);
        return new ApplicationViewHolder(appAdapterView);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicationViewHolder applicationViewHolder, int i) {
        final ApplicationItem applicationInfo = mApplicationItems.get(i);
        applicationViewHolder.vPackageName.setText(applicationInfo.getApplicationPackageName());
        applicationViewHolder.vName.setText(applicationInfo.getApplicationName());
        applicationViewHolder.vIcon.setImageDrawable(applicationInfo.getIcon());
                applicationViewHolder.vIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchIntent = view.getContext()
                        .getPackageManager()
                        .getLaunchIntentForPackage(applicationInfo.getApplicationPackageName());
                if (launchIntent != null) {
                    mContext.startActivity(launchIntent);//null pointer check in case package name was not found
                }
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return mApplicationItems.get(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return mApplicationItems.size();
    }

    public void clear() {
        mApplicationItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                final FilterResults oReturn = new FilterResults();
                final List<ApplicationItem> results = new ArrayList<>();
                if (mApplicationItemsSearch== null) {
                    mApplicationItemsSearch = mApplicationItems;
                }
                if (charSequence != null) {
                    if (mApplicationItemsSearch != null && mApplicationItemsSearch.size() > 0) {
                        for (final ApplicationItem appInfo : mApplicationItemsSearch) {
                            if (appInfo.getApplicationName().toLowerCase().contains(charSequence.toString())) {
                                results.add(appInfo);
                            }
                        }
                    }
                    oReturn.values = results;
                    oReturn.count = results.size();
                }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                if (filterResults.count > 0) {
                    //MainActivity.setResultsMessage(false);
                } else {
                    //MainActivity.setResultsMessage(true);
                }
                mApplicationItems = (ArrayList<ApplicationItem>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ApplicationViewHolder extends RecyclerView.ViewHolder {
        TextView vName;
        TextView vPackageName;
        TextView vInstallTime;
        ImageView vIcon;

        public ApplicationViewHolder(View v) {
            super(v);
            vName = v.findViewById(R.id.app_name);
            vPackageName = v.findViewById(R.id.package_name);
            vIcon = v.findViewById(R.id.application_icon);
            vInstallTime = v.findViewById(R.id.install_time);

        }
    }

}
