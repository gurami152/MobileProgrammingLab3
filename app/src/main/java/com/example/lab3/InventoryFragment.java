package com.example.lab3;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class InventoryFragment extends Fragment {
    private Inventory mInventory;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInventory = new Inventory();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);
        mTitleField = (EditText) view.findViewById(R.id.inventory_title);
        mTitleField.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // empty
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mInventory.setmTitle(s.toString());
            }


            @Override
            public void afterTextChanged(Editable s) {
                // empty
            }
        });
        mDateButton = (Button) view.findViewById(R.id.inventory_date);
        mDateButton.setText(mInventory.getmDate().toString());
        mDateButton.setEnabled(false);
        mSolvedCheckBox = (CheckBox) view.findViewById(R.id.inventory_solved);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mInventory.setmSolved(isChecked);
            }
        });
        return view;
    }
    public Inventory getmInventory() {
        return mInventory;
    }
}