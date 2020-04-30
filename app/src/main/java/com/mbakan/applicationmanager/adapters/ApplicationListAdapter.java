package com.mbakan.applicationmanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mbakan.applicationmanager.ApplicationItem;
import com.mbakan.applicationmanager.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ApplicationListAdapter extends ArrayAdapter<ApplicationItem> {

    private final LayoutInflater mInflater;
    private final Context mContext;
    private ViewHolder holder;
    private final ArrayList<ApplicationItem> mApplicationItems;

    public ApplicationListAdapter(Context context, ArrayList<ApplicationItem> applicationItems) {
        super(context,0, applicationItems);
        mContext = context;
        mApplicationItems = applicationItems;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mApplicationItems.size();
    }

    @Override
    public ApplicationItem getItem(int position) {
        return mApplicationItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mApplicationItems.get(position).hashCode();
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.application_item, null);
            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.app_name);
            holder.icon = convertView.findViewById(R.id.application_icon);
            holder.packageName = convertView.findViewById(R.id.package_name);
            holder.installTime = convertView.findViewById(R.id.install_time);
            convertView.setTag(holder);
        }

        else{
            //Get viewholder we already created
            holder = (ViewHolder)convertView.getTag();
        }

        ApplicationItem item = mApplicationItems.get(position);
        if( item != null )
        {
            holder.icon.setImageDrawable(item.getIcon());
            holder.name.setText(item.getApplicationName());
            holder.packageName.setText(item.getApplicationPackageName());
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY hh:mm");
            holder.installTime.setText(dateFormat.format(item.getInstallTime()));
        }

        return convertView;

    };

    //View Holder Pattern for better performance
    private static class ViewHolder {
        TextView name;
        TextView packageName;
        TextView installTime;
        ImageView icon;
    }
}
