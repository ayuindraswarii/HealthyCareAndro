package id.sch.smktelkom_mlg.afinal.xirpl2071330.healthycare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.hsalf.smilerating.SmileRating;

public class FragmenPenyakit extends AppCompatActivity implements View.OnClickListener {

    EditText komentar;
    TextView textComment;
    Button btnKirim;
    Firebase penyakit, pengobatan, pertolongan;
    TextView tartikel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);
        Firebase.setAndroidContext(this);
        komentar = findViewById(R.id.komentar);
        textComment = findViewById(R.id.textComment);
        btnKirim = findViewById(R.id.btnKirim);
        penyakit = new Firebase("https://healthycare-5213f.firebaseio.com/Penyakit");
        pertolongan = new Firebase("https://healthycare-5213f.firebase.com/Penanganan Pertama");
        tartikel = findViewById(R.id.artikel);

        penyakit.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String pencegahan = dataSnapshot.getValue(String.class);
                tartikel.setText(pencegahan);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        btnKirim.setOnClickListener(this);


        final SmileRating smileRating = findViewById(R.id.srating);
        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley) {
                switch (smiley) {
                    case SmileRating.BAD:
                        Toast.makeText(getApplicationContext(), "Bad Solution", Toast.LENGTH_SHORT).show();
                        break;

                    case SmileRating.GOOD:
                        Toast.makeText(getApplicationContext(), "Good Solution", Toast.LENGTH_SHORT).show();
                        break;

                    case SmileRating.GREAT:
                        Toast.makeText(getApplicationContext(), "Great Solution", Toast.LENGTH_SHORT).show();
                        break;

                    case SmileRating.OKAY:
                        Toast.makeText(getApplicationContext(), "Okay", Toast.LENGTH_SHORT).show();
                        break;

                    case SmileRating.TERRIBLE:
                        Toast.makeText(getApplicationContext(), "Terrible Solution", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnKirim:
                textComment.setText(komentar.getText().toString());
                break;
        }
    }
}
