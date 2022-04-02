package com.example.doanjv;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Adapter.Apdapter_TinhTrangSucKhoe;
import Model.TinhTrangSucKhoe;

public class Fragment_TinhTrangSucKhoe extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private Apdapter_TinhTrangSucKhoe apdapterTinhTrangSucKhoe;

    private EditText editText1;
    DatePickerDialog.OnDateSetListener setListener;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_TinhTrangSucKhoe() {
        // Required empty public constructor
    }

    public static Fragment_TinhTrangSucKhoe newInstance(String param1, String param2) {
        Fragment_TinhTrangSucKhoe fragment = new Fragment_TinhTrangSucKhoe();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment__tinh_trang_suc_khoe, container, false);

        //RecyclerView
        recyclerView = view.findViewById(R.id.recyclerview);
        apdapterTinhTrangSucKhoe = new Apdapter_TinhTrangSucKhoe(this.getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext() , RecyclerView.VERTICAL, false );
        recyclerView.setLayoutManager(linearLayoutManager);
        apdapterTinhTrangSucKhoe.setData(getLsTinhTrang());
        recyclerView.setAdapter(apdapterTinhTrangSucKhoe);

        //
        //xuất ra lịch ngày sinh
        //
        editText1 = view.findViewById(R.id.ngay_tinh_trang_suc_khoe);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        editText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                editText1.setText(date);
            }
        };
        return view;
    }
    private List<TinhTrangSucKhoe> getLsTinhTrang() {
        List<TinhTrangSucKhoe> ls = new ArrayList<>();
        ls.add(new TinhTrangSucKhoe("20/12/2020","Huỳnh Bá Phúc","19:20:30" ));
        ls.add(new TinhTrangSucKhoe("19/05/2021","Huỳnh Bá Phúc","07:20:30" ));
        ls.add(new TinhTrangSucKhoe("22/11/2022","Huỳnh Bá Phúc","12:20:30"));
        ls.add(new TinhTrangSucKhoe("23/04/2022","Huỳnh Bá Phúc","13:20:30" ));
        ls.add(new TinhTrangSucKhoe("22/01/2021","Huỳnh Bá Phúc","14:20:30" ));
        ls.add(new TinhTrangSucKhoe("20/12/2020","Huỳnh Bá Phúc","15:20:30" ));
        return ls;
    }
}