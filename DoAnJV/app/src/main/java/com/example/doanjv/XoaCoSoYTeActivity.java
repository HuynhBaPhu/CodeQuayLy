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

import Adapter.Adapter_XoaBenhNhan;
import Adapter.Adapter_XoaCoSoYTe;
import Model.SuaBenhNhan;
import Model.SuaCoSoYTe;
import my_interface.ItemTouchHelperListener_CSYT;

public class XoaCoSoYTeActivity extends AppCompatActivity implements ItemTouchHelperListener_CSYT {

    private RecyclerView recyclerView;
    private Adapter_XoaCoSoYTe adapterXoaCoSoYTe;
    List<SuaCoSoYTe> list;
    private EditText hoten;
    private EditText cmnd;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xoa_co_so_yte);

        linearLayout = findViewById(R.id.root_view);
        recyclerView = findViewById(R.id.dsbenhnhan);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterXoaCoSoYTe = new Adapter_XoaCoSoYTe(getList());
        recyclerView.setAdapter(adapterXoaCoSoYTe);
        //dòng kẻ phân biệt
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        ItemTouchHelper.SimpleCallback simpleCallback = new RcvItemTouchHelper_CSYT(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);

        //tim kiem theo ten
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

        //tim kiem theo di chi
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
        adapterXoaCoSoYTe.filterList(filterList);
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
        adapterXoaCoSoYTe.filterList(filterList);
    }

    private List<SuaCoSoYTe> getList() {
        list = new ArrayList<>();
        list.add(new SuaCoSoYTe("CSYT 1", "1"));
        list.add(new SuaCoSoYTe("CSYT 2", "2"));
        list.add(new SuaCoSoYTe("CSYT 3", "3"));
        list.add(new SuaCoSoYTe("CSYT 4", "4"));
        list.add(new SuaCoSoYTe("CSYT 5", "5"));
        list.add(new SuaCoSoYTe("CSYT 6", "6"));
        return list;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder) {
        if(viewHolder instanceof Adapter_XoaCoSoYTe.XoaCoSoYTeViewHolder)
        {
            //hiểm thị tên user khi đã xóa ở thanh khôi phục dữ liệu
            String nameDelete = list.get(viewHolder.getAdapterPosition()).getName();

            //tạo đối tượng user để thực hiện xóa
            final SuaCoSoYTe suaCoSoYTeDelete = list.get(viewHolder.getAdapterPosition());
            final int index = viewHolder.getAdapterPosition();

            //remove item
            adapterXoaCoSoYTe.RemoveItem(index);

            Snackbar snackbar = Snackbar.make(linearLayout, nameDelete + "remove!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapterXoaCoSoYTe.UndoItem(suaCoSoYTeDelete, index);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }
}