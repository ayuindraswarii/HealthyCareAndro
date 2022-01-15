package id.sch.smktelkom_mlg.afinal.xirpl2071330.healthycare;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Ayu Indraswari on 31/03/2018.
 */

//add a pagination kind of effect yang dibutuhkan
public class SliderAdapter extends PagerAdapter {

    //array image
    public int[] slideimage = {
            R.drawable.rawat,
            R.drawable.kotak,
            R.drawable.obat1,
            R.drawable.rs
    };

    //array judul
    public String[] slidejudul = {
            "Penanganan Pertama",
            "Pencegahan",
            "Pengobatan",
            "Rumah Sakit"
    };
    //array penjelasan
    public String[] slideisi = {
            "Dapatkan penanganan pertama yang tepat untuk menangani keluhan mengenai penyakit",
            "Carilah solusi sederhana untuk melakukan pencegahan dan penanganan pertama",
            "Lakukan pengobatan yang tepat untuk menangani penyakit",
            "Bawa ke rumah sakit terdekat jika dirasa tidak bisa ditangani menggunakan solusi sederhana",
    };
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return slidejudul.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    //add slide effect or actually inflate all of the things in this adapter
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //pindah halaman menggunakan inflate
        View view = layoutInflater.inflate(R.layout.slide, container, false);

        //memanggil berdasarkan id
        ImageView slideImageView = view.findViewById(R.id.imageView);
        TextView slideJudul = view.findViewById(R.id.judul);
        TextView slideIsi = view.findViewById(R.id.isi);

        //menempatkan
        slideImageView.setImageResource(slideimage[position]);
        slideJudul.setText(slidejudul[position]);
        slideIsi.setText(slideisi[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
