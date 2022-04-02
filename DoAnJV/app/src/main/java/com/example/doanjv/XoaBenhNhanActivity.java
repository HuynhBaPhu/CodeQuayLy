package com.example.doanjv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import Adapter.Adapter_SuaBenhNhan;
import Adapter.Adapter_XoaBenhNhan;
import Model.SuaBenhNhan;
import my_interface.ItemTouchHelperListener;

public class XoaBenhNhanActivity extends AppCompatActivity implements ItemTouchHelperListener {

    private RecyclerView recyclerView;
    private Adapter_XoaBenhNhan adapterXoaBenhNhan;
    List<SuaBenhNhan> list;
    private EditText hoten;
    private EditText cmnd;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xoa_benh_nhan);

        linearLayout = findViewById(R.id.root_view);
        recyclerView = findViewById(R.id.dsbenhnhan);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterXoaBenhNhan = new Adapter_XoaBenhNhan(getList());
        recyclerView.setAdapter(adapterXoaBenhNhan);
        //dòng kẻ phân biệt
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        ItemTouchHelper.SimpleCallback simpleCallback = new RcvItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);

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
        adapterXoaBenhNhan.filterList(filterList);
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
        adapterXoaBenhNhan.filterList(filterList);
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
    public void onSwiped(RecyclerView.ViewHolder viewHolder) {
        if(viewHolder instanceof Adapter_XoaBenhNhan.XoaBenhNhanViewHolder)
        {
            //hiểm thị tên user khi đã xóa ở thanh khôi phục dữ liệu
            String nameDelete = list.get(viewHolder.getAdapterPosition()).getName();

            //tạo đối tượng user để thực hiện xóa
            final SuaBenhNhan suaBenhNhanDelete = list.get(viewHolder.getAdapterPosition());
            final int index = viewHolder.getAdapterPosition();

            //remove item
            adapterXoaBenhNhan.RemoveItem(index);

            Snackbar snackbar = Snackbar.make(linearLayout, nameDelete + "remove!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapterXoaBenhNhan.UndoItem(suaBenhNhanDelete, index);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }
}