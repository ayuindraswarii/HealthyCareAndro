package id.sch.smktelkom_mlg.afinal.xirpl2071330.healthycare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivitySlide extends AppCompatActivity {
    SharedPreferences prefs;
    private ViewPager VviewPager;
    private LinearLayout LlinearLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] linearLayoutText;
    private Button ibtnBalik;
    private Button ibtnLanjut;
    private int CurrentPage;
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addlinearLayoutTextIndicator(i);
            CurrentPage = i;

            if (i == 0) {
                ibtnLanjut.setEnabled(true);
                ibtnBalik.setEnabled(false);
                ibtnBalik.setVisibility(View.INVISIBLE);

                ibtnLanjut.setText("Next");
                ibtnBalik.setText("");
            } else if (i == linearLayoutText.length - 1) {
                ibtnLanjut.setEnabled(true);
                ibtnBalik.setEnabled(true);
                ibtnBalik.setVisibility(View.VISIBLE);

                ibtnLanjut.setText("Start");
                ibtnBalik.setText("Back");
                ibtnLanjut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean("startedBefore", true);
                        editor.apply();
                        startActivity(new Intent(getApplicationContext(), Halaman1.class));
                        finish();
                    }
                });
            } else {
                ibtnLanjut.setEnabled(true);
                ibtnBalik.setEnabled(true);
                ibtnBalik.setVisibility(View.VISIBLE);

                ibtnLanjut.setText("Next");
                ibtnBalik.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button_slide);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean previuslyStarted = prefs.getBoolean("startedBefore", false);
        if (previuslyStarted) {
            startActivity(new Intent(this, Halaman1.class));
            finish();
        }
        //inisialisasi slider
        VviewPager = findViewById(R.id.viewPager);
        LlinearLayout = findViewById(R.id.linearLayout);

        ibtnLanjut = findViewById(R.id.btnLanjut);
        ibtnBalik = findViewById(R.id.btnBalik);

        sliderAdapter = new SliderAdapter(this);

        VviewPager.setAdapter(sliderAdapter);

        addlinearLayoutTextIndicator(0);
        //ganti lingkaran
        VviewPager.addOnPageChangeListener(viewListener);

        //OnClickListener
        ibtnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VviewPager.setCurrentItem(CurrentPage + 1);
            }
        });

        ibtnBalik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VviewPager.setCurrentItem(CurrentPage - 1);
            }
        });
    }

    //membuat lingkaran di bawah
    public void addlinearLayoutTextIndicator(int position) {
        linearLayoutText = new TextView[4];
        LlinearLayout.removeAllViews();

        for (int i = 0; i < linearLayoutText.length; i++) {
            linearLayoutText[i] = new TextView(this);
            linearLayoutText[i].setText(Html.fromHtml("&#8226;"));
            linearLayoutText[i].setTextSize(35);
            linearLayoutText[i].setTextColor(getResources().getColor(R.color.transparant));

            LlinearLayout.addView(linearLayoutText[i]);

        }

        if (linearLayoutText.length > 0) {
            linearLayoutText[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }
}
