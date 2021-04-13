package com.example.lab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class InventoryAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Inventory> inventories;
    public InventoryAdapter(Context context, ArrayList<Inventory> inventories) {
        this.context = context;
        this.inventories = inventories;
    }
        @Override
        public int getCount() {
        return inventories.size();
    }
    @Override
    public Object getItem(int position) {
        return inventories.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_layout_inventory,parent,false);
        }
        Inventory currentInventory = (Inventory) getItem(position);
        TextView textViewInventoryTitle = (TextView) convertView.findViewById(R.id.inventory_title);
        textViewInventoryTitle.setText(currentInventory.getmTitle());
        TextView textViewInventoryDate = (TextView) convertView.findViewById(R.id.inventory_date);
        textViewInventoryDate.setText(currentInventory.getmDate().toString());
    return convertView;
    }
}


