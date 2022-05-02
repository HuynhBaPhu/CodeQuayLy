package com.example.doanjv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import Adapter.Adapter_TXSbenhNhan;
import Adapter.Adapter_XoaBenhNhan;
import Model.entity.BenhNhanCustom;
import api.BenhNhanService;
import my_interface.ClickItemUserListener;
import my_interface.ItemTouchHelperListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TXSbenhNhan extends AppCompatActivity implements ItemTouchHelperListener {

    private RecyclerView recyclerView;
    private Adapter_TXSbenhNhan adapterTxSbenhNhan;
    private List<BenhNhanCustom> list = new ArrayList<>();
    private EditText hoten;
    private EditText cmnd;
    private LinearLayout linearLayout;
    private int maBN;
    private ImageButton btnBack;
    private Button btnDatLai;
    private Button btnThemBenhNhan;
    private ClickItemUserListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_txsbenh_nhan);
        anhxa();
        setDATA();
        click();
    }

    private void anhxa()
    {
        linearLayout = findViewById(R.id.manhinhTXS);
        recyclerView = findViewById(R.id.dsbenhnhan);
        btnDatLai = findViewById(R.id.btnDatLaiTXS);
        hoten = findViewById(R.id.hoten);
        cmnd = findViewById(R.id.cmnd);
        btnBack = findViewById(R.id.back);
        btnThemBenhNhan = findViewById(R.id.btnThemBN);
    }

    private void setDATA()
    {
        setOnClickListner();
        getSupportActionBar().hide();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapterTxSbenhNhan = new Adapter_TXSbenhNhan(list, listener);
        recyclerView.setAdapter(adapterTxSbenhNhan);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        ItemTouchHelper.SimpleCallback simpleCallback = new RcvItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
        getAllData();
    }

    private void click()
    {
        //tim kiem theo ten
        hoten.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) { filterTEN(editable.toString()); }
        });

        //tim kiem theo cmnd
        cmnd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) { filterCMND(editable.toString()); }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        btnDatLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hoten.setText("");
                cmnd.setText("");
            }
        });

        btnThemBenhNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TXSbenhNhan.this, ThemBenhNhanActivity.class);
                startActivity(intent);
            }
        });
    }

    private void filterCMND(String text)
    {
        List<BenhNhanCustom> filterList = new ArrayList<>();
        for(BenhNhanCustom item : list)
        {
            if(item.getCmnd_BenhNhan().getCmnd().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }
        }
        adapterTxSbenhNhan.filterList(filterList);
    }

    private void filterTEN(String text)
    {
        List<BenhNhanCustom> filterList = new ArrayList<>();
        for(BenhNhanCustom item : list)
        {
            if(item.getCmnd_BenhNhan().getHoTen().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }
        }
        adapterTxSbenhNhan.filterList(filterList);
    }

    private void getAllData() {
        BenhNhanService.benhNhanService.getAllBenhNhan().enqueue(new Callback<List<BenhNhanCustom>>() {
            @Override
            public void onResponse(Call<List<BenhNhanCustom>> call, Response<List<BenhNhanCustom>> response) {
                if(response.body() != null)
                {
                    list.addAll(response.body());
                    adapterTxSbenhNhan.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<BenhNhanCustom>> call, Throwable t) {
                Toast.makeText(TXSbenhNhan.this,"Call API thất bại!" + t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder) {
        if(viewHolder instanceof Adapter_TXSbenhNhan.TXSbenhNhanViewHolder)
        {
            //hiểm thị tên user khi đã xóa ở thanh khôi phục dữ liệu
            String nameDelete = list.get(viewHolder.getAdapterPosition()).getCmnd_BenhNhan().getHoTen();

            //tạo đối tượng user để thực hiện xóa
            final BenhNhanCustom BenhNhanDelete = list.get(viewHolder.getAdapterPosition());
            final int index = viewHolder.getAdapterPosition();

            //remove item
            maBN = list.get(viewHolder.getAdapterPosition()).getMaBN();
            adapterTxSbenhNhan.RemoveItem(index, maBN);

            Snackbar snackbar = Snackbar.make(linearLayout, nameDelete + " remove!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapterTxSbenhNhan.UndoItem(BenhNhanDelete, index);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }

    private void setOnClickListner()
    {
        listener = new ClickItemUserListener() {
            @Override
            public void onClick(View v, int position) {
                int solanmac = list.get(position).getSoLanMac();
                int solantiem = list.get(position).getSoMuiVacin();
                int macsyt = list.get(position).getMaCSYT_BenhNhan().getMaCSYT();
                String solanmacc = String.valueOf(solanmac);
                String solantiemm = String.valueOf(solantiem);
                String macsytt = String.valueOf(macsyt);
                String res[] = list.get(position).getCmnd_BenhNhan().getDiaChi().split(", ");

                Intent intent = new Intent(getApplicationContext(), DetailSuaBenhNhanActivity.class);
                intent.putExtra("username", list.get(position).getCmnd_BenhNhan().getHoTen());
                intent.putExtra("ngaysinh", list.get(position).getCmnd_BenhNhan().getNgaySinh());
                intent.putExtra("gioitinh", list.get(position).getCmnd_BenhNhan().getGioiTinh());
                intent.putExtra("tinhTP", res[3]);
                intent.putExtra("QH", res[1]);
                intent.putExtra("PX", res[1]);
                intent.putExtra("TX", res[0]);
                intent.putExtra("sdt", list.get(position).getCmnd_BenhNhan().getSdt());
                intent.putExtra("cmnd", list.get(position).getCmnd_BenhNhan().getCmnd());
                intent.putExtra("ngayphathien", list.get(position).getNgayPhatHien());
                intent.putExtra("ketqua", list.get(position).getTrangThai());
                intent.putExtra("solanmac",  solanmacc);
                intent.putExtra("solantiem", solantiemm);
                intent.putExtra("macsyt", macsytt);
                intent.putExtra("maBN",list.get(position).getMaBN());
                startActivity(intent);
            }
        };
    }
}