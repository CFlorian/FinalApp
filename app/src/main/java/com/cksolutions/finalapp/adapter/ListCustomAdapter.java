package com.cksolutions.finalapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cksolutions.finalapp.R;
import com.cksolutions.finalapp.model.ColorModel;

import java.util.ArrayList;

public class ListCustomAdapter extends ArrayAdapter<ColorModel> {

    //private ArrayList<ColorModel> dataSet;


    private static class ViewHolder {
        TextView tvName;
        LinearLayout lvItem;
    }


    public ListCustomAdapter (Context context, ArrayList<ColorModel> color){
        super(context, R.layout.list_item, color);
        //this.dataSet = color;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ColorModel colorModel = getItem(position);
        ListCustomAdapter.ViewHolder viewHolder;

        final View result;
        if (convertView == null){
            viewHolder = new ListCustomAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            viewHolder.tvName = convertView.findViewById(R.id.tvName);
            viewHolder.lvItem = convertView.findViewById(R.id.lvItemColor);

            result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ListCustomAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }


        viewHolder.tvName.setText(colorModel.getName());
        viewHolder.lvItem.setBackgroundColor(Color.rgb(colorModel.getRed(),colorModel.getGreen(),colorModel.getBlue()));
        return convertView;
    }
}
