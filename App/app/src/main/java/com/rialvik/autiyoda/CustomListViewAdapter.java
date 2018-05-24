package com.rialvik.autiyoda;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomListViewAdapter extends ArrayAdapter<ListViewElements> {
    private Context mContext;
    private List<ListViewElements> elementsList;

    public  CustomListViewAdapter(@NonNull Context context, @LayoutRes ArrayList<ListViewElements> list) {
        super(context, 0 , list);
        mContext = context;
        elementsList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.custom_list_view,parent,false);

        ListViewElements listViewElements = elementsList.get(position);

        ImageView image = listItem.findViewById(R.id.imageView_item);
        image.setImageResource(listViewElements.getItem_image());

        TextView name = listItem.findViewById(R.id.textView_item_name);
        name.setText(listViewElements.getItem_name());

        TextView release = listItem.findViewById(R.id.textView_item_total);
        release.setText(listViewElements.getItem_total());

        return listItem;
    }
}


