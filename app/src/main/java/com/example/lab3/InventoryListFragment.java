package com.example.lab3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Date;

public class InventoryListFragment extends Fragment {
    Inventory inventory = new Inventory();
    private ArrayList<Inventory> inventories;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inventories = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // зв’язуємо з відповідним макетом
        View view = inflater.inflate(R.layout.fragment_inventory_list, container, false);
        ListView listInventories = (ListView) view.findViewById(R.id.listItems);
        // для формування структури списку використовуємо відповідний адаптер (див.нижче п.9)
        InventoryAdapter adapter = new InventoryAdapter(this.getContext(), inventories);
        listInventories.setAdapter(adapter);
        listInventories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                inventory = inventories.get(position);
                Intent intent = new Intent(getActivity(), InventoryEditActivity.class);
                intent.putExtra("title", inventory.getmTitle());
                intent.putExtra("id", position);
                intent.putExtra("date", inventory.getmDate());
                intent.putExtra("status", inventory.ismSolved());
                startActivityForResult(intent, 0);
            }
        });
        // формування інтенту та запуск активності при натисканні на кнопку додавання
        View fab = (View) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InventoryActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if(data.getBooleanExtra("edit",false)){
                inventory  = inventories.get(data.getIntExtra("id", 0));
                inventory.setmTitle(data.getStringExtra("title"));
                inventory.setmDate((Date) data.getSerializableExtra("date"));
                inventory.setmSolved(data.getBooleanExtra("status", false));
            }
            else {
                inventory.setmTitle(data.getStringExtra("title"));
                inventory.setmDate((Date) data.getSerializableExtra("date"));
                inventory.setmSolved(data.getBooleanExtra("status", false));
                inventories.add(inventory);
            }
        } else {
            Toast.makeText(getActivity(), "Wrong result", Toast.LENGTH_SHORT).show();
        }
    }
}