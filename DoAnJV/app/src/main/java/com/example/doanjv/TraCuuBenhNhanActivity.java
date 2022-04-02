package com.example.doanjv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import Adapter.Adapter_TraCuuBenhNhan;
import Model.TraCuuBenhNhan;

public class TraCuuBenhNhanActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter_TraCuuBenhNhan adapterTraCuuBenhNhan;
    private SearchView searchView;
    List<TraCuuBenhNhan> list;

    private EditText editText1;
    private EditText hoten;
    private EditText namsinh;
    private EditText cmnd;
    private EditText diachi;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tra_cuu_benh_nhan);

        recyclerView = findViewById(R.id.dsbenhnhan);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapterTraCuuBenhNhan = new Adapter_TraCuuBenhNhan(getList());
        recyclerView.setAdapter(adapterTraCuuBenhNhan);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        //
        //xuất ra lịch ngày sinh
        //
        editText1 = findViewById(R.id.namsinh);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        editText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(TraCuuBenhNhanActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                editText1.setText(date);
            }
        };

        //tim kiem theo ten
        hoten = findViewById(R.id.hoten);
        hoten.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

        //tim kiem nam sinh
        namsinh = findViewById(R.id.namsinh);
        namsinh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter2(editable.toString());
            }
        });

        //tim kiem cmnd
        cmnd = findViewById(R.id.cmnd);
        cmnd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter3(editable.toString());
            }
        });

        //tim kiem dia chi
        diachi = findViewById(R.id.diachi);
        diachi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter4(editable.toString());
            }
        });
    }

    private void filter(String text)
    {
        List<TraCuuBenhNhan> filterList = new ArrayList<>();
        for(TraCuuBenhNhan item : list)
        {
            if(item.getName().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }
        }
        adapterTraCuuBenhNhan.filterList(filterList);
    }
    private void filter2(String text)
    {
        List<TraCuuBenhNhan> filterList = new ArrayList<>();
        for(TraCuuBenhNhan item : list)
        {
            if(item.getDate().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }
        }
        adapterTraCuuBenhNhan.filterList(filterList);
    }
    private void filter3(String text)
    {
        List<TraCuuBenhNhan> filterList = new ArrayList<>();
        for(TraCuuBenhNhan item : list)
        {
            if(item.getCmnd().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }
        }
        adapterTraCuuBenhNhan.filterList(filterList);
    }
    private void filter4(String text)
    {
        List<TraCuuBenhNhan> filterList = new ArrayList<>();
        for(TraCuuBenhNhan item : list)
        {
            if(item.getDiachi().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }
        }
        adapterTraCuuBenhNhan.filterList(filterList);
    }

    private List<TraCuuBenhNhan> getList() {
        list = new ArrayList<>();
        list.add(new TraCuuBenhNhan("Huỳnh Bá Phúc", "19/10/2001", "342075479", "0767286897", "ĐT"));
        list.add(new TraCuuBenhNhan("Huỳnh Bá A", "19/10/2001", "342075479", "0767286897", "ĐT"));
        list.add(new TraCuuBenhNhan("Huỳnh Bá B", "19/10/2001", "342075479", "0767286897", "ĐT"));
        list.add(new TraCuuBenhNhan("Huỳnh Bá C", "19/10/2001", "342075479", "0767286897", "ĐT"));
        list.add(new TraCuuBenhNhan("Huỳnh Bá D", "19/10/2001", "342075479", "0767286897", "ĐT"));
        list.add(new TraCuuBenhNhan("Huỳnh Bá E", "19/10/2001", "342075479", "0767286897", "ĐT"));
        return list;
    }
}