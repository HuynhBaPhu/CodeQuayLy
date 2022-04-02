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
import my_interface.ClickItemListener_CSYT;
import my_interface.ClickItemUserListener;

public class Adapter_SuaCoSoYTe extends RecyclerView.Adapter<Adapter_SuaCoSoYTe.SuaCoSoYTeViewHolder>{
    private View view;
    private List<SuaCoSoYTe> suaCoSoYTeList;
    private ClickItemListener_CSYT clickItemUserListener;

    public Adapter_SuaCoSoYTe(List<SuaCoSoYTe> suaCoSoYTeList, ClickItemListener_CSYT clickItemUserListener) {
        this.suaCoSoYTeList = suaCoSoYTeList;
        this.clickItemUserListener = clickItemUserListener;
    }

    @NonNull
    @Override
    public SuaCoSoYTeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suacosoyte, parent, false);
        return new Adapter_SuaCoSoYTe.SuaCoSoYTeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuaCoSoYTeViewHolder holder, int position) {
        SuaCoSoYTe suaCoSoYTe = suaCoSoYTeList.get(position);
        if(suaCoSoYTe == null)
        {
            return;
        }
        holder.ten.setText(suaCoSoYTe.getName());
        holder.cmnd.setText(suaCoSoYTe.getDiachi());

        //sự kiện click RecyclerView để lấy giá trị từ activity nhờ hàm onClickGotoDetail sang Detail
        holder.layoutsuacsyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemUserListener.onClickItemUser(suaCoSoYTe);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(suaCoSoYTeList != null)
        {
            return suaCoSoYTeList.size();
        }
        return 0;
    }

    public class SuaCoSoYTeViewHolder extends RecyclerView.ViewHolder
    {
        private LinearLayout layoutsuacsyt;
        private TextView ten;
        private TextView cmnd;

        public SuaCoSoYTeViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutsuacsyt = itemView.findViewById(R.id.item_suacsyt);
            ten = itemView.findViewById(R.id.tenCSYT);
            cmnd = itemView.findViewById(R.id.diachi);
        }
    }
    public void filterList(List<SuaCoSoYTe> filterList)
    {
        suaCoSoYTeList = filterList;
        notifyDataSetChanged();
    }
}
