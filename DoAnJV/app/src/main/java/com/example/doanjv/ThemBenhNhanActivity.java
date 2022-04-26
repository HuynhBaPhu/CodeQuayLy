package com.example.doanjv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;
import java.util.Objects;

import Model.entity.BenhNhanCustom;
import Model.entity.CoSoYTe;
import Model.entity.ConNguoi;
import api.BenhNhanService;
import api.CoSoYTeService;
import api.ConNguoiService;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemBenhNhanActivity extends AppCompatActivity {

    private EditText edtHoten;
    private EditText edtNgaySinh;
    private RadioGroup rdbGioiTinh;
    private RadioButton rdbNam;
    private RadioButton rdbNu;
    private EditText edtTinhTP;
    private EditText edtQuanH;
    private EditText edtPhuongX;
    private EditText edtSonha;
    private EditText edtSdt;
    private EditText edtCCCD;
    private EditText edtNgayKetQua;
    private Spinner edtKetqua;
    private EditText edtSoLanDT;
    private Spinner edtLichSu;
    private EditText edtCSYT;
    private Button btnLuuThongTin;
    private ProgressDialog progressDialog;
    FirebaseStorage storage;
    DatePickerDialog.OnDateSetListener setListener;
    DatePickerDialog.OnDateSetListener setListener1;
    // Create a storage reference from our app
    StorageReference storageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_benh_nhan);
        progressDialog = new ProgressDialog(ThemBenhNhanActivity.this);
        anhxa();
        click();
    }

    private void anhxa()
    {
        edtHoten = (EditText) findViewById(R.id.hoten);
        edtNgaySinh = (EditText) findViewById(R.id.ngaysinh);
        rdbGioiTinh = findViewById(R.id.rdbGioitinh);
        rdbNam = (RadioButton) findViewById(R.id.nam);
        rdbNu = (RadioButton) findViewById(R.id.nu);
        edtTinhTP = (EditText) findViewById(R.id.TinhTP);
        edtQuanH = (EditText) findViewById(R.id.Quan_huyen);
        edtPhuongX = (EditText) findViewById(R.id.Phuongxa);
        edtSonha = (EditText) findViewById(R.id.Sonha);
        edtSdt = (EditText) findViewById(R.id.sdt);
        edtCCCD = (EditText) findViewById(R.id.cmnd);
        edtNgayKetQua = (EditText) findViewById(R.id.editTextDate_NgayKetQuaXetNghiem);
        edtKetqua = (Spinner) findViewById(R.id.v_spinner1);
        edtSoLanDT = (EditText) findViewById(R.id.editTextDate_SoLanDuongTinh);
        edtLichSu = (Spinner) findViewById(R.id.v_spinner2);
        edtCSYT = (EditText) findViewById(R.id.edtCSYT);
        btnLuuThongTin = (Button) findViewById(R.id.btn_luu);
        rdbNam.setChecked(true);
    }

    private void click()
    {
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        edtNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ThemBenhNhanActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                edtNgaySinh.setText(date);
            }
        };

        edtNgayKetQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog1 = new DatePickerDialog(ThemBenhNhanActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener1,year,month,day);
                datePickerDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog1.show();
            }
        });
        setListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date1 = day+"/"+month+"/"+year;
                edtNgayKetQua.setText(date1);
            }
        };

        btnLuuThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                progressDialog.setTitle("Vui lòng chờ!");
//                progressDialog.show();
                DangKyConNguoi();
            }
        });
    }

    private void DangKyConNguoi()
    {
        ConNguoi conNguoi = new ConNguoi();
        conNguoi.setCmnd(edtCCCD.getText().toString().trim());
        conNguoi.setGioiTinh(rdbNam.isChecked() == true ? "Nam":"Nữ");
        conNguoi.setSdt(edtSdt.getText().toString().trim());
        conNguoi.setNgaySinh(edtNgaySinh.getText().toString());
        conNguoi.setHoTen(edtHoten.getText().toString().trim());
        String dc = edtSonha.getText().toString().trim()+", "+edtPhuongX.getText().toString().trim()+", "
                +edtQuanH.getText().toString().trim()+", "+edtTinhTP.getText().toString().trim();
        conNguoi.setDiaChi(dc);

        ConNguoiService.conNguoiService.addConNguoi(conNguoi).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.body() == false)
                    Toast.makeText(ThemBenhNhanActivity.this,"Đã tồn tại con người!",Toast.LENGTH_SHORT).show();
                else
                {
                    Toast.makeText(ThemBenhNhanActivity.this,"Đã thêm người mới!",Toast.LENGTH_SHORT).show();
                    themMoiBenhNhan(conNguoi);
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(ThemBenhNhanActivity.this,"Call API thất bại!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void themMoiBenhNhan(ConNguoi conNguoi)
    {
        String Macsyt = edtCSYT.getText().toString();
        String SoLanMac = edtSoLanDT.getText() + "";
        String SoMuiVaccine = edtLichSu.getSelectedItem().toString();
        int soLanMac = Integer.parseInt(SoLanMac);
        int soMuiVaccine = Integer.parseInt(SoMuiVaccine);
        int macsyt = Integer.parseInt(Macsyt);

        BenhNhanCustom benhNhanCustom = new BenhNhanCustom();
        benhNhanCustom.setNgayPhatHien(edtNgayKetQua.getText().toString());
        benhNhanCustom.setSoLanMac(soLanMac);
        benhNhanCustom.setSoMuiVacin(soMuiVaccine);
        benhNhanCustom.setCmnd_BenhNhan(conNguoi);
        benhNhanCustom.setTrangThai(edtKetqua.getSelectedItem().toString());

        BenhNhanService.benhNhanService.addBenhNhan(benhNhanCustom, macsyt).enqueue(new Callback<BenhNhanCustom>() {
            @Override
            public void onResponse(Call<BenhNhanCustom> call, Response<BenhNhanCustom> response) {
                if(response.body() != null)
                {
                    Toast.makeText(ThemBenhNhanActivity.this,"Thêm mới thông tin thành công!",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(ThemBenhNhanActivity.this,"Thêm mới thông tin thất bại!",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<BenhNhanCustom> call, Throwable t) {
                Toast.makeText(ThemBenhNhanActivity.this,"Call API thất bại!",Toast.LENGTH_SHORT).show();
            }
        });
    }
//    private void themMoiBenhNhan(ConNguoi conNguoi) {
//        if(edtCSYT.getText().toString() == null)
//        {
//            themBenhNhan(null,conNguoi);
//        }
//        else
//        {
//            String Macsyt = edtCSYT.getText().toString();
//            int macsyt = Integer.parseInt(Macsyt);
//            CoSoYTeService.CSYTService.getOneCoSoYTe(macsyt).enqueue(new Callback<CoSoYTe>() {
//                @Override
//                public void onResponse(Call<CoSoYTe> call, Response<CoSoYTe> response) {
//                    CoSoYTe coSoYTe = response.body();
//                    if(coSoYTe != null)
//                    {
//                        themBenhNhan(coSoYTe,conNguoi);
//                        Toast.makeText(ThemBenhNhanActivity.this,"Tìm và thêm thành công CSYT", Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
//                        Toast.makeText(ThemBenhNhanActivity.this,"Thêm CSYT thất bại", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                @Override
//                public void onFailure(Call<CoSoYTe> call, Throwable t) {
//                    Toast.makeText(ThemBenhNhanActivity.this,"Không tồn tại cơ sở y tế đã nhập!", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    }
//
//    private void themBenhNhan(CoSoYTe coSoYTe,ConNguoi conNguoi)
//    {
//        String SoLanMac = edtSoLanDT.getText() + "";
//        String SoMuiVaccine = edtLichSu.getSelectedItem().toString();
//        int soLanMac = Integer.parseInt(SoLanMac);
//        int soMuiVaccine = Integer.parseInt(SoMuiVaccine);
//
//        BenhNhanCustom benhNhanCustom = new BenhNhanCustom();
//        benhNhanCustom.setNgayPhatHien(edtNgayKetQua.getText().toString());
//        benhNhanCustom.setSoLanMac(soLanMac);
//        benhNhanCustom.setSoMuiVacin(soMuiVaccine);
//        benhNhanCustom.setCmnd_BenhNhan(conNguoi);
//        benhNhanCustom.setMaCSYT_BenhNhan(coSoYTe);
//        BenhNhanService.benhNhanService.addBenhNhan(benhNhanCustom).enqueue(new Callback<BenhNhanCustom>() {
//            @Override
//            public void onResponse(Call<BenhNhanCustom> call, Response<BenhNhanCustom> response) {
//                if(response.body() != null)
//                {
//                    Toast.makeText(ThemBenhNhanActivity.this,"Thêm mới thông tin thành công!",Toast.LENGTH_SHORT).show();
//                }
//                else
//                    Toast.makeText(ThemBenhNhanActivity.this,"Thêm mới thông tin thất bại!",Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onFailure(Call<BenhNhanCustom> call, Throwable t) {
//                Toast.makeText(ThemBenhNhanActivity.this,"Call API thất bại!",Toast.LENGTH_SHORT).show();
//                Log.e("benhnhan",t.toString());
//            }
//        });
//    }
}