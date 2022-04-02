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
import Model.SuaCoSoYTe;

public class Adapter_XoaCoSoYTe extends RecyclerView.Adapter<Adapter_XoaCoSoYTe.XoaCoSoYTeViewHolder>{
    private View view;
    private List<SuaCoSoYTe> suaCoSoYTeList;

    public Adapter_XoaCoSoYTe(List<SuaCoSoYTe> suaCoSoYTeList) {
        this.suaCoSoYTeList = suaCoSoYTeList;
    }

    @NonNull
    @Override
    public XoaCoSoYTeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_xoacosoyte, parent, false);
        return new Adapter_XoaCoSoYTe.XoaCoSoYTeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull XoaCoSoYTeViewHolder holder, int position) {
        SuaCoSoYTe suaCoSoYTe = suaCoSoYTeList.get(position);
        if(suaCoSoYTe == null)
        {
            return;
        }
        holder.ten.setText(suaCoSoYTe.getName());
        holder.cmnd.setText(suaCoSoYTe.getDiachi());
    }

    @Override
    public int getItemCount() {
        if(suaCoSoYTeList != null)
        {
            return suaCoSoYTeList.size();
        }
        return 0;
    }

    public class XoaCoSoYTeViewHolder extends RecyclerView.ViewHolder
    {
        private TextView ten;
        private TextView cmnd;
        public LinearLayout linearLayout;

        public XoaCoSoYTeViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tenCSYT);
            cmnd = itemView.findViewById(R.id.diachiCSYT);
            linearLayout = itemView.findViewById(R.id.layout_thongtinxoa);
        }
    }
    public void filterList(List<SuaCoSoYTe> filterList)
    {
        suaCoSoYTeList = filterList;
        notifyDataSetChanged();
    }

    //hàm để xóa item
    public void RemoveItem(int index)
    {
        suaCoSoYTeList.remove(index);
        notifyItemRemoved(index);
    }

    //hàm để khôi phục item
    public void UndoItem(SuaCoSoYTe suaCoSoYTe, int index)
    {
        suaCoSoYTeList.add(index, suaCoSoYTe);
        notifyItemInserted(index);
    }
}
