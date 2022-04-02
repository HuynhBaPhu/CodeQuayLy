package Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanjv.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Model.TraCuuBenhNhan;

public class Adapter_TraCuuBenhNhan extends RecyclerView.Adapter<Adapter_TraCuuBenhNhan.TraCuuBenhNhanViewHolder>{

    private View view;
    private List<TraCuuBenhNhan> traCuuBenhNhanList;

    public Adapter_TraCuuBenhNhan(List<TraCuuBenhNhan> traCuuBenhNhanList)
    {
        this.traCuuBenhNhanList = traCuuBenhNhanList;
    }

    @NonNull
    @Override
    public TraCuuBenhNhanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_benhnhan, parent, false);
        return new TraCuuBenhNhanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TraCuuBenhNhanViewHolder holder, int position) {
        TraCuuBenhNhan traCuuBenhNhan = traCuuBenhNhanList.get(position);
        if(traCuuBenhNhan == null)
        {
            return;
        }
        holder.ten.setText(traCuuBenhNhan.getName());
        holder.ngaysinh.setText(traCuuBenhNhan.getDate());
        holder.cmnd.setText(traCuuBenhNhan.getCmnd());
        holder.sdt.setText(traCuuBenhNhan.getSdt());
        holder.diachi.setText(traCuuBenhNhan.getDiachi());
    }

    @Override
    public int getItemCount() {
        if(traCuuBenhNhanList != null)
        {
            return traCuuBenhNhanList.size();
        }
        return 0;
    }

    public void filterList(List<TraCuuBenhNhan> filterList)
    {
        traCuuBenhNhanList = filterList;
        notifyDataSetChanged();
    }

    public class TraCuuBenhNhanViewHolder extends RecyclerView.ViewHolder
    {
        private TextView ten;
        private TextView ngaysinh;
        private TextView cmnd;
        private TextView sdt;
        private TextView diachi;
        public TraCuuBenhNhanViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.name);
            ngaysinh = itemView.findViewById(R.id.ngaysinh);
            cmnd = itemView.findViewById(R.id.cmnd);
            sdt = itemView.findViewById(R.id.sdt);
            diachi = itemView.findViewById(R.id.diachi);
        }
    }
}
