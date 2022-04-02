package com.example.doanjv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import Adapter.Adapter_SuaBenhNhan;
import Adapter.Adapter_TraCuuBenhNhan;
import Model.SuaBenhNhan;
import Model.TraCuuBenhNhan;
import my_interface.ClickItemUserListener;

public class SuaBenhNhanActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter_SuaBenhNhan adapterSuaBenhNhan;
    List<SuaBenhNhan> list;
    private EditText hoten;
    private EditText cmnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_benh_nhan);

        recyclerView = findViewById(R.id.dsbenhnhan);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        //new ClickItemUserListener() để sử lý việc click gọi hàm onClickGotoDetail để đẩy dữ liệu sang Detail
        adapterSuaBenhNhan = new Adapter_SuaBenhNhan(getList(), new ClickItemUserListener() {
            @Override
            public void onClickItemUser(SuaBenhNhan suaBenhNhan) {
                onClickGotoDetail(suaBenhNhan);
            }
        });

        recyclerView.setAdapter(adapterSuaBenhNhan);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);


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
                filterTEN(editable.toString());
            }
        });

        //tim kiem theo cmnd
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
                filterCMND(editable.toString());
            }
        });
    }

    private void filterCMND(String text)
    {
        List<SuaBenhNhan> filterList = new ArrayList<>();
        for(SuaBenhNhan item : list)
        {
            if(item.getCmnd().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }
        }
        adapterSuaBenhNhan.filterList(filterList);
    }

    private void filterTEN(String text)
    {
        List<SuaBenhNhan> filterList = new ArrayList<>();
        for(SuaBenhNhan item : list)
        {
            if(item.getName().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }
        }
        adapterSuaBenhNhan.filterList(filterList);
    }

    private List<SuaBenhNhan> getList() {
        list = new ArrayList<>();
        list.add(new SuaBenhNhan("Huỳnh Bá Phúc", "1"));
        list.add(new SuaBenhNhan("Huỳnh Bá Phúc1", "2"));
        list.add(new SuaBenhNhan("Huỳnh Bá Phúc2", "3"));
        list.add(new SuaBenhNhan("Huỳnh Bá Phúc3", "4"));
        list.add(new SuaBenhNhan("Huỳnh Bá Phúc4", "5"));
        list.add(new SuaBenhNhan("Huỳnh Bá Phúc5", "6"));
        return list;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //Hàm sử lý đẩy giá trị từ activity sang màn hình Detail
    private void onClickGotoDetail(SuaBenhNhan suaBenhNhan)
    {
        Intent intent = new Intent(this, DetailSuaBenhNhanActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_Suabenhnhan", suaBenhNhan);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}