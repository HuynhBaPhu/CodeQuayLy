package Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanjv.R;

import java.util.List;

import Model.SuaBenhNhan;
import my_interface.ClickItemUserListener;

public class Adapter_SuaBenhNhan extends RecyclerView.Adapter<Adapter_SuaBenhNhan.SuaBenhNhanViewHolder>{

    private View view;
    private List<SuaBenhNhan> suaBenhNhanList;
    private ClickItemUserListener clickItemUserListener;

    public Adapter_SuaBenhNhan(List<SuaBenhNhan> list, ClickItemUserListener listener) {
        this.suaBenhNhanList = list;
        this.clickItemUserListener = listener;
    }

    @NonNull
    @Override
    public SuaBenhNhanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suabenhnhan, parent, false);
        return new Adapter_SuaBenhNhan.SuaBenhNhanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuaBenhNhanViewHolder holder, int position) {
        SuaBenhNhan suaBenhNhan = suaBenhNhanList.get(position);
        if(suaBenhNhan == null)
        {
            return;
        }
        holder.ten.setText(suaBenhNhan.getName());
        holder.cmnd.setText(suaBenhNhan.getCmnd());

        //sự kiện click RecyclerView để lấy giá trị từ activity nhờ hàm onClickGotoDetail sang Detail
        holder.layoutsuabenhnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemUserListener.onClickItemUser(suaBenhNhan);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(suaBenhNhanList != null)
        {
            return suaBenhNhanList.size();
        }
        return 0;
    }

    public class SuaBenhNhanViewHolder extends RecyclerView.ViewHolder
    {
        private LinearLayout layoutsuabenhnhan;
        private TextView ten;
        private TextView cmnd;

        public SuaBenhNhanViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutsuabenhnhan = itemView.findViewById(R.id.item_suabenhnhan);
            ten = itemView.findViewById(R.id.tvName);
            cmnd = itemView.findViewById(R.id.tvCmnd);
        }
    }
    public void filterList(List<SuaBenhNhan> filterList)
    {
        suaBenhNhanList = filterList;
        notifyDataSetChanged();
    }
}
