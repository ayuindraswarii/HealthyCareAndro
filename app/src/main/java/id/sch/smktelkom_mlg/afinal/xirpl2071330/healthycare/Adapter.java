package id.sch.smktelkom_mlg.afinal.xirpl2071330.healthycare;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter extends RecyclerView.Adapter {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.isi_pertolongan_pertama, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return dtpencegahan.penyakit.length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView penyakit, keterangan;
        private ImageView img;


        public ListViewHolder(View itemView) {
            super(itemView);
            penyakit = itemView.findViewById(R.id.textView_penyakit);
            keterangan = itemView.findViewById(R.id.textView_keterangan);
            img = itemView.findViewById(R.id.slide_image);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position) {
            penyakit.setText(dtpenyakit.penyakit[position]);
            keterangan.setText(dtpenyakit.keterangan[position]);
            img.setImageResource(dtpenyakit.img[position]);
        }

        @Override
        public void onClick(View v) {

        }
    }
}