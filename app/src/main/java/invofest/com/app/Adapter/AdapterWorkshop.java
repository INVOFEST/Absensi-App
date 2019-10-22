package invofest.com.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import invofest.com.app.Model.User;
import invofest.com.app.R;

public class AdapterWorkshop extends RecyclerView.Adapter<AdapterWorkshop.ViewHolder>{

    private Context context;
    private List<User> data;

    public AdapterWorkshop(Context context, List<User> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public AdapterWorkshop.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_wrokshop, parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull AdapterWorkshop.ViewHolder holder, int position) {
        holder.binding(data.get(position), context);
    }

    @Override
    public int getItemCount() {
        if(data!=null){
            return data.size();
        } else{
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvID, tvNama, tvInstansi, tvKategori;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvID = itemView.findViewById(R.id.tvID);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvInstansi = itemView.findViewById(R.id.tvInstansi);
            tvKategori = itemView.findViewById(R.id.tvKategori);
        }

        public void binding(final User user, Context context) {
            tvID.setText(user.getId_peserta());
            tvNama.setText(user.getNama());
            tvInstansi.setText(user.getAsal_institusi());
            tvKategori.setText(user.getJenis_workshop());
        }
    }
}
