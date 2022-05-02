package Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanjv.R;

import java.util.List;

import Model.entity.BenhNhanCustom;
import api.BenhNhanService;
import my_interface.ClickItemUserListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_TXSbenhNhan extends RecyclerView.Adapter<Adapter_TXSbenhNhan.TXSbenhNhanViewHolder>{

    private View view;
    private List<BenhNhanCustom> benhNhanCustomList;
    private ClickItemUserListener listener;

    public Adapter_TXSbenhNhan(List<BenhNhanCustom> list, ClickItemUserListener listener) {
        this.benhNhanCustomList = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Adapter_TXSbenhNhan.TXSbenhNhanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_xoabenhnhan, parent, false);
        return new Adapter_TXSbenhNhan.TXSbenhNhanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_TXSbenhNhan.TXSbenhNhanViewHolder holder, int position) {
        BenhNhanCustom benhNhanCustom = benhNhanCustomList.get(position);
        if(benhNhanCustom == null)
        {
            return;
        }
        holder.ten.setText(benhNhanCustom.getCmnd_BenhNhan().getHoTen());
        holder.cmnd.setText(benhNhanCustom.getCmnd_BenhNhan().getCmnd());
    }

    @Override
    public int getItemCount() {
        if(benhNhanCustomList != null)
        {
            return benhNhanCustomList.size();
        }
        return 0;
    }

    public class TXSbenhNhanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView ten;
        private TextView cmnd;
        public LinearLayout linearLayout;

        public TXSbenhNhanViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tvName);
            cmnd = itemView.findViewById(R.id.tvCmnd);
            linearLayout = itemView.findViewById(R.id.layout_thongtinxoa);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    public void filterList(List<BenhNhanCustom> filterList)
    {
        benhNhanCustomList = filterList;
        notifyDataSetChanged();
    }

    //hàm để xóa item
    public void RemoveItem(int index, int maBN)
    {
        BenhNhanService.benhNhanService.deleteBenhNhan(maBN).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                benhNhanCustomList.remove(index);
                notifyItemRemoved(index);
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("Call API", "Call API thất bại");
            }
        });
    }

    //hàm để khôi phục item
    public void UndoItem(BenhNhanCustom benhNhanCustom, int index)
    {
        BenhNhanService.benhNhanService.UndoBenhNhan(benhNhanCustom).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                benhNhanCustomList.add(index, benhNhanCustom);
                notifyItemInserted(index);
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("Call API", "Call API thất bại");
            }
        });
    }
}
