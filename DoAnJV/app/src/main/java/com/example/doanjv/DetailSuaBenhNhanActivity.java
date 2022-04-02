package com.example.doanjv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import Model.SuaBenhNhan;

public class DetailSuaBenhNhanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sua_benh_nhan);

        //nhận giá trị tương ứng từ activity qua màn hình Detail là name
        Bundle bundle = getIntent().getExtras();
        if(bundle == null)
        {
            return;
        }
        SuaBenhNhan suaBenhNhan = (SuaBenhNhan) bundle.get("object_Suabenhnhan");
        EditText name = findViewById(R.id.hoten);
        name.setText(suaBenhNhan.getName());
        EditText cmnd = findViewById(R.id.cmnd);
        cmnd.setText(suaBenhNhan.getCmnd());
    }
}