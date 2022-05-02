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

import Model.entity.CoSoYTe;
import api.CoSoYTeService;
import my_interface.ClickItemListener_CSYT;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_TXScoSoYTe extends RecyclerView.Adapter<Adapter_TXScoSoYTe.TXScoSoYTeViewHolder>{

    private View view;
    private List<CoSoYTe> coSoYTeList;
    ClickItemListener_CSYT clickItemListenerCsyt;
    public Adapter_TXScoSoYTe(List<CoSoYTe> coSoYTeList, ClickItemListener_CSYT clickItemListenerCsyt) {
        this.coSoYTeList = coSoYTeList;
        this.clickItemListenerCsyt = clickItemListenerCsyt;
    }

    @NonNull
    @Override
    public Adapter_TXScoSoYTe.TXScoSoYTeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_xoacosoyte, parent, false);
        return new Adapter_TXScoSoYTe.TXScoSoYTeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_TXScoSoYTe.TXScoSoYTeViewHolder holder, int position) {
        CoSoYTe coSoYTe = coSoYTeList.get(position);
        if(coSoYTe == null)
        {
            return;
        }
        holder.ten.setText(coSoYTe.getTenCSYT());
        holder.cmnd.setText(coSoYTe.getDiaChi());
    }

    @Override
    public int getItemCount() {
        if(coSoYTeList != null)
        {
            return coSoYTeList.size();
        }
        return 0;
    }

    public class TXScoSoYTeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView ten;
        private TextView cmnd;
        public LinearLayout linearLayout;

        public TXScoSoYTeViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tenCSYT);
            cmnd = itemView.findViewById(R.id.diachiCSYT);
            linearLayout = itemView.findViewById(R.id.layout_thongtinxoa);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            clickItemListenerCsyt.onClick(view, getAdapterPosition());
        }
    }
    public void filterList(List<CoSoYTe> filterList)
    {
        coSoYTeList = filterList;
        notifyDataSetChanged();
    }

    //hàm để xóa item
    public void RemoveItem(int index, int maCSYT)
    {
        CoSoYTeService.CSYTService.deleteCoSoYTe(maCSYT).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                coSoYTeList.remove(index);
                notifyItemRemoved(index);
                Log.e("Call API", "Xóa thành công");
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("Call API", "Call API thất bại");
            }
        });
    }

    //hàm để khôi phục item
    public void UndoItem(CoSoYTe coSoYTe, int index)
    {
        CoSoYTeService.CSYTService.UndoCoSoYTe(coSoYTe).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                coSoYTeList.add(index, coSoYTe);
                notifyItemInserted(index);
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("Call API", "Call API thất bại");
            }
        });
    }
}
