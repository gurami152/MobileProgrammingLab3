package com.example.lab3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class InventoryEditActivity extends AppCompatActivity {
    Inventory inventory;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        Intent intent = this.getIntent();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        Bundle bundle = new Bundle();
        bundle.putString("title", intent.getStringExtra("title"));
        bundle.putBoolean("status", intent.getBooleanExtra("status", false));
        pos =  intent.getIntExtra("id",0);

        if(fragment == null){
            fragment = new InventoryEditFragment();
            fragment.setArguments(bundle);
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_item, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        if (item.getItemId() == R.id.action_save) {
            InventoryEditFragment fragment = (InventoryEditFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            inventory = fragment.getmInventory();
            Intent intent = new Intent();
            intent.putExtra("title", inventory.getmTitle());
            intent.putExtra("id", pos);
            intent.putExtra("date", inventory.getmDate());
            intent.putExtra("status", inventory.ismSolved());
            intent.putExtra("edit", true);
            setResult(Activity.RESULT_OK,intent);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.action_delete) {
            Intent intent = new Intent();
            intent.putExtra("id", pos);
            intent.putExtra("delete", true);
            setResult(Activity.RESULT_OK,intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
