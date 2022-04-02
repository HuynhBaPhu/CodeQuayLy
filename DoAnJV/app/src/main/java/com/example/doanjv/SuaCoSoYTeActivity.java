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

import java.util.ArrayList;
import java.util.List;

import Adapter.Adapter_SuaBenhNhan;
import Adapter.Adapter_SuaCoSoYTe;
import Model.SuaBenhNhan;
import Model.SuaCoSoYTe;
import my_interface.ClickItemListener_CSYT;
import my_interface.ClickItemUserListener;

public class SuaCoSoYTeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter_SuaCoSoYTe adapterSuaCoSoYTe;
    List<SuaCoSoYTe> list;
    private EditText hoten;
    private EditText cmnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_co_so_yte);

        recyclerView = findViewById(R.id.dsbenhnhan);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        //new ClickItemUserListener() để sử lý việc click gọi hàm onClickGotoDetail để đẩy dữ liệu sang Detail
        adapterSuaCoSoYTe = new Adapter_SuaCoSoYTe(getList(), new ClickItemListener_CSYT() {
            @Override
            public void onClickItemUser(SuaCoSoYTe suaCoSoYTe) {
                onClickGotoDetail(suaCoSoYTe);
            }
        });

        recyclerView.setAdapter(adapterSuaCoSoYTe);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        //tim kiem theo ten csyt
        hoten = findViewById(R.id.tenCSYT);
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

        //tim kiem theo dia chi
        cmnd = findViewById(R.id.diachi);
        cmnd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filterDC(editable.toString());
            }
        });
    }
    private void filterDC(String text)
    {
        List<SuaCoSoYTe> filterList = new ArrayList<>();
        for(SuaCoSoYTe item : list)
        {
            if(item.getDiachi().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }
        }
        adapterSuaCoSoYTe.filterList(filterList);
    }

    private void filterTEN(String text)
    {
        List<SuaCoSoYTe> filterList = new ArrayList<>();
        for(SuaCoSoYTe item : list)
        {
            if(item.getName().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }
        }
        adapterSuaCoSoYTe.filterList(filterList);
    }
    private List<SuaCoSoYTe> getList() {
        list = new ArrayList<>();
        list.add(new SuaCoSoYTe("CSYT1", "1"));
        list.add(new SuaCoSoYTe("CSYT2", "2"));
        list.add(new SuaCoSoYTe("CSYT3", "3"));
        list.add(new SuaCoSoYTe("CSYT4", "4"));
        list.add(new SuaCoSoYTe("CSYT5", "5"));
        list.add(new SuaCoSoYTe("CSYT6", "6"));
        return list;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    //Hàm sử lý đẩy giá trị từ activity sang màn hình Detail
    private void onClickGotoDetail(SuaCoSoYTe suaCoSoYTe)
    {
        Intent intent = new Intent(this, DetailSuaCoSoYTe.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_SuaCSYT", suaCoSoYTe);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}