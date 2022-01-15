package id.sch.smktelkom_mlg.afinal.xirpl2071330.healthycare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


/**
 * Created by Retno Dewi on 23/04/2018.
 */

public class daftar extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email, password, name;
    private Button signin, signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        mAuth = FirebaseAuth.getInstance();

        signin = findViewById((R.id.signin));
        signup = findViewById(R.id.signup);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        name = findViewById(R.id.etName);

        if (mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), SignIn.class));
        }

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getemail = email.getText().toString().trim();
                String getpassword = password.getText().toString().trim();
                callsignin(getemail, getpassword);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getemail = email.getText().toString().trim();
                String getepassword = password.getText().toString().trim();
                callsignup(getemail, getepassword);
            }
        });

    }

    private void callsignup(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("TESTING", "Sign up Successful" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Log.d("TESTING", "Sign up failed:" + task.getException());
                            Toast.makeText(daftar.this, "Sign up Failed", Toast.LENGTH_SHORT).show();
                        } else {
                            userProfile();
                            Toast.makeText(daftar.this, "Created Account.",
                                    Toast.LENGTH_SHORT).show();
                            Log.d("TESTING", "Create Account");
                        }

                        // ...
                    }
                });
    }

    private void userProfile() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(name.getText().toString().trim()).build();

            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d("TESTING", "User profile updated.");
                            }
                        }
                    });
        }

    }

    private void callsignin(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("TESTING", "sign In Succesfull:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TESTING", "Sign with email failed:" + task.getException());
                            Toast.makeText(daftar.this, "Failed", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Intent i = new Intent(daftar.this, SignIn.class);
                            finish();
                            startActivity(i);
                        }

                        // ...
                    }
                });
    }
}



