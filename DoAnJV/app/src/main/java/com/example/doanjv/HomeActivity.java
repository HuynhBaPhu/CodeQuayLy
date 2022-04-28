package com.example.doanjv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import Adapter.AdapterHome;
import Model.ChucNangOHome;

public class HomeActivity extends AppCompatActivity {

    GridView gvChucNang;
    RelativeLayout btnThongTinCaNhiem;
    RelativeLayout btnThongTinCaNhan;
    ArrayList<ChucNangOHome> chucNangOHomeArrayList;
    AdapterHome adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        AnhXa();
        adapter = new AdapterHome(this, R.layout.dong_chuc_nang_home, chucNangOHomeArrayList);
        gvChucNang.setAdapter(adapter);
    }

    private void AnhXa() {
        btnThongTinCaNhiem = (RelativeLayout) findViewById(R.id.btn_thongtincanhiem);
        btnThongTinCaNhan = (RelativeLayout) findViewById(R.id.btn_thongtincanhan);
        gvChucNang = (GridView) findViewById(R.id.gv_services);
        chucNangOHomeArrayList = new ArrayList<>();
        chucNangOHomeArrayList.add(new ChucNangOHome("Thêm bệnh nhân", R.drawable.icons_person_add));
        chucNangOHomeArrayList.add(new ChucNangOHome("Sửa bệnh nhân", R.drawable.icons_edit));
        chucNangOHomeArrayList.add(new ChucNangOHome("Xóa bệnh nhân", R.drawable.icons_person_delete));
        chucNangOHomeArrayList.add(new ChucNangOHome("Tìm kiếm bệnh nhân", R.drawable.icons_find));
        chucNangOHomeArrayList.add(new ChucNangOHome("Thêm cơ sở y tế", R.drawable.icons_hospital));
        chucNangOHomeArrayList.add(new ChucNangOHome("Sửa cơ sở y tể", R.drawable.icons_edit_1));
        chucNangOHomeArrayList.add(new ChucNangOHome("Xóa cơ sở y tế", R.drawable.icons_delete));

        gvChucNang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent intent = new Intent(HomeActivity.this, ThemBenhNhanActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(HomeActivity.this, SuaBenhNhanActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(HomeActivity.this, XoaBenhNhanActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(HomeActivity.this, TraCuuBenhNhanActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(HomeActivity.this, ThemCoSoYTeActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(HomeActivity.this, SuaCoSoYTeActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(HomeActivity.this, XoaCoSoYTeActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });

        btnThongTinCaNhiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

//        btnThongTinCaNhan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HomeActivity.this, ThongTinCaNhanActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}