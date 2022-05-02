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
import android.util.Log;
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
import Adapter.Adapter_TXScoSoYTe;
import Adapter.Adapter_XoaCoSoYTe;
import Model.entity.CoSoYTe;
import api.CoSoYTeService;
import my_interface.ClickItemListener_CSYT;
import my_interface.ItemTouchHelperListener_CSYT;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TXScoSoYTe extends AppCompatActivity implements ItemTouchHelperListener_CSYT {

    private RecyclerView recyclerView;
    private Adapter_TXScoSoYTe adapterTxScoSoYTe;
    private List<CoSoYTe> list = new ArrayList<>();
    private EditText ten;
    private EditText diachi;
    private LinearLayout linearLayout;
    private int maCSYT;
    private ImageButton btnBack;
    private Button btnDatLai;
    private Button themCSYT;
    ClickItemListener_CSYT listenerCsyt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_txsco_so_yte);
        anhxa();
        setData();
        click();
    }

    private void anhxa()
    {
        linearLayout = findViewById(R.id.root_view);
        recyclerView = findViewById(R.id.dsbenhnhan);
        diachi = findViewById(R.id.diachi);
        ten = findViewById(R.id.tenCSYT);
        btnBack = findViewById(R.id.back);
        btnDatLai = findViewById(R.id.btnDatLaiTXS);
        themCSYT = findViewById(R.id.btnThemBN);
    }

    private void setData()
    {
        setOnClickListner();
        getSupportActionBar().hide();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapterTxScoSoYTe = new Adapter_TXScoSoYTe(list, listenerCsyt);
        recyclerView.setAdapter(adapterTxScoSoYTe);
        getAllData();
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        ItemTouchHelper.SimpleCallback simpleCallback = new RcvItemTouchHelper_CSYT(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
    }

    private void click()
    {
        //tim kiem theo ten
        ten.addTextChangedListener(new TextWatcher() {
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

        //tim kiem theo di chi
        diachi.addTextChangedListener(new TextWatcher() {
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

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnDatLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ten.setText("");
                diachi.setText("");
            }
        });

        themCSYT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TXScoSoYTe.this, ThemCoSoYTeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void filterDC(String text)
    {
        List<CoSoYTe> filterList = new ArrayList<>();
        for(CoSoYTe item : list)
        {
            if(item.getDiaChi().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }
        }
        adapterTxScoSoYTe.filterList(filterList);
    }

    private void filterTEN(String text)
    {
        List<CoSoYTe> filterList = new ArrayList<>();
        for(CoSoYTe item : list)
        {
            if(item.getTenCSYT().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }
        }
        adapterTxScoSoYTe.filterList(filterList);
    }

    private void getAllData() {
        CoSoYTeService.CSYTService.getAllCoSoYTe().enqueue(new Callback<List<CoSoYTe>>() {
            @Override
            public void onResponse(Call<List<CoSoYTe>> call, Response<List<CoSoYTe>> response) {
                if(response.body() != null)
                {
                    list.addAll(response.body());
                    adapterTxScoSoYTe.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<CoSoYTe>> call, Throwable t) {
                Toast.makeText(TXScoSoYTe.this,"Call API thất bại!" + t.getMessage(),Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());
            }
        });
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder) {
        if(viewHolder instanceof Adapter_TXScoSoYTe.TXScoSoYTeViewHolder)
        {
            //hiểm thị tên user khi đã xóa ở thanh khôi phục dữ liệu
            String nameDelete = list.get(viewHolder.getAdapterPosition()).getTenCSYT();

            //tạo đối tượng user để thực hiện xóa
            final CoSoYTe coSoYTeDelete = list.get(viewHolder.getAdapterPosition());
            final int index = viewHolder.getAdapterPosition();

            //remove item
            maCSYT = list.get(viewHolder.getAdapterPosition()).getMaCSYT();
            adapterTxScoSoYTe.RemoveItem(index, maCSYT);

            Snackbar snackbar = Snackbar.make(linearLayout, nameDelete + " remove!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapterTxScoSoYTe.UndoItem(coSoYTeDelete, index);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }

    private void setOnClickListner() {
        listenerCsyt = new ClickItemListener_CSYT() {
            @Override
            public void onClick(View v, int position) {
                String res[] = list.get(position).getDiaChi().split(", ");

                Intent intent = new Intent(getApplicationContext(), DetailSuaCoSoYTe.class);
                intent.putExtra("username", list.get(position).getTenCSYT());
                intent.putExtra("tinhTP", res[3]);
                intent.putExtra("QH", res[2]);
                intent.putExtra("PX", res[1]);
                intent.putExtra("TX", res[0]);
                intent.putExtra("sdt", list.get(position).getSdt());
                intent.putExtra("maCSYT",list.get(position).getMaCSYT());
                startActivity(intent);
            }
        };
    }
}