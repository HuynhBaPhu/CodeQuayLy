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

public class Adapter_XoaBenhNhan extends RecyclerView.Adapter<Adapter_XoaBenhNhan.XoaBenhNhanViewHolder>{
    private View view;
    private List<SuaBenhNhan> suaBenhNhanList;

    public Adapter_XoaBenhNhan(List<SuaBenhNhan> list) {
        this.suaBenhNhanList = list;
    }

    @NonNull
    @Override
    public XoaBenhNhanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_xoabenhnhan, parent, false);
        return new Adapter_XoaBenhNhan.XoaBenhNhanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull XoaBenhNhanViewHolder holder, int position) {
        SuaBenhNhan suaBenhNhan = suaBenhNhanList.get(position);
        if(suaBenhNhan == null)
        {
            return;
        }
        holder.ten.setText(suaBenhNhan.getName());
        holder.cmnd.setText(suaBenhNhan.getCmnd());
    }

    @Override
    public int getItemCount() {
        if(suaBenhNhanList != null)
        {
            return suaBenhNhanList.size();
        }
        return 0;
    }

    public class XoaBenhNhanViewHolder extends RecyclerView.ViewHolder
    {
        private TextView ten;
        private TextView cmnd;
        public LinearLayout linearLayout;

        public XoaBenhNhanViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tvName);
            cmnd = itemView.findViewById(R.id.tvCmnd);
            linearLayout = itemView.findViewById(R.id.layout_thongtinxoa);
        }
    }

    public void filterList(List<SuaBenhNhan> filterList)
    {
        suaBenhNhanList = filterList;
        notifyDataSetChanged();
    }

    //hàm để xóa item
    public void RemoveItem(int index)
    {
        suaBenhNhanList.remove(index);
        notifyItemRemoved(index);
    }

    //hàm để khôi phục item
    public void UndoItem(SuaBenhNhan suaBenhNhan, int index)
    {
        suaBenhNhanList.add(index, suaBenhNhan);
        notifyItemInserted(index);
    }
}
