package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanjv.R;

import java.util.List;

import Model.TinhTrangSucKhoe;

public class Apdapter_TinhTrangSucKhoe extends RecyclerView.Adapter<Apdapter_TinhTrangSucKhoe.Apdapter_TinhTrangSucKhoeViewHolder>{

    private Context context;
    private List<TinhTrangSucKhoe> lstinhTrangSucKhoes;

    public Apdapter_TinhTrangSucKhoe(Context context)
    {
        this.context = context;
    }
    public void setData(List<TinhTrangSucKhoe> ls)
    {
        this.lstinhTrangSucKhoes = ls;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Apdapter_TinhTrangSucKhoeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_tinhtrangsuckhor,parent,false);
        return new Apdapter_TinhTrangSucKhoeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Apdapter_TinhTrangSucKhoeViewHolder holder, int position) {
        TinhTrangSucKhoe TinhTrang = lstinhTrangSucKhoes.get(position);
        if(TinhTrang == null)
        {
            return;
        }
        String res[] = TinhTrang.getDate().split("/");
        holder.tvDayMonth.setText(res[0]+"/"+res[1]);
        holder.tvYear.setText(res[2]);
        holder.tvName.setText(TinhTrang.getName());
        holder.tvTime.setText(TinhTrang.getTime());
    }

    @Override
    public int getItemCount() {
        if(lstinhTrangSucKhoes != null)
        {
            return lstinhTrangSucKhoes.size();
        }
        return 0;
    }

    public class Apdapter_TinhTrangSucKhoeViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDayMonth;
        private TextView tvYear;
        private TextView tvName;
        private TextView tvTime;
        public Apdapter_TinhTrangSucKhoeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDayMonth = (TextView) itemView.findViewById(R.id.tvDate);
            tvYear = (TextView) itemView.findViewById(R.id.tvYear);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);

        }
    }
}
