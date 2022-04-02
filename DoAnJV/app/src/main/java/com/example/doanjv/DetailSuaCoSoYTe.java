package com.example.doanjv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import Model.SuaBenhNhan;
import Model.SuaCoSoYTe;

public class DetailSuaCoSoYTe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sua_co_so_yte);
        //nhận giá trị tương ứng từ activity qua màn hình Detail là name
        Bundle bundle = getIntent().getExtras();
        if(bundle == null)
        {
            return;
        }
        SuaCoSoYTe suaCoSoYTe = (SuaCoSoYTe) bundle.get("object_SuaCSYT");
        EditText name = findViewById(R.id.tenCSYT);
        name.setText(suaCoSoYTe.getName());
        EditText cmnd = findViewById(R.id.diachiCSYT);
        cmnd.setText(suaCoSoYTe.getDiachi());
    }
}