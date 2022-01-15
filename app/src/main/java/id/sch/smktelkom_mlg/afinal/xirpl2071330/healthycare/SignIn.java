package id.sch.smktelkom_mlg.afinal.xirpl2071330.healthycare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Ayu Indraswari on 23/04/2018.
 */

public class SignIn extends AppCompatActivity {
    Button signout;
    TextView username;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sukses_masuk);

        mAuth = FirebaseAuth.getInstance();
        signout = findViewById(R.id.signout);
        username = findViewById(R.id.tvName);


        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(getApplicationContext(), daftar.class));
        }

        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            username.setText("Welcome " + user.getDisplayName());
        }

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), daftar.class));
            }
        });
    }
}
