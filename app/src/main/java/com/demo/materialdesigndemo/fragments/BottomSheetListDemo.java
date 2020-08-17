package com.demo.materialdesigndemo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.materialdesigndemo.ItemAdapter;
import com.demo.materialdesigndemo.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class BottomSheetListDemo extends BottomSheetDialogFragment implements View.OnClickListener {

    ArrayList<String> itemList;
    Context context;
    public BottomSheetListDemo(Context context, ArrayList<String> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_sheet_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        RecyclerView recyclerView = view.findViewById(R.id.bottom_dialog_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context,RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        ItemAdapter adapter = new ItemAdapter(context,itemList, 1);
        recyclerView.setAdapter(adapter);

        EditText searchEditText = view.findViewById(R.id.bottom_dialog_search);
        ImageButton closeDialog = view.findViewById(R.id.bottom_dialog_close);
        Button doneButton = view.findViewById(R.id.bottom_dialog_done);
        searchEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
                            FrameLayout bottomSheet = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);


                            BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
                            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                behavior.setPeekHeight(0);

                        }
                    });
                }
            }
        });
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ArrayList<String> filterItems = new ArrayList<>();
                for (String item: itemList){
                    if (item.toLowerCase().contains(s.toString().toLowerCase())){
                        filterItems.add(item);
                    }
                }
                adapter.filterList(filterItems);

            }
        });
        closeDialog.setOnClickListener(this);
        doneButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bottom_dialog_close:
            case R.id.bottom_dialog_done:
                dismiss();
                break;

        }
    }
}
