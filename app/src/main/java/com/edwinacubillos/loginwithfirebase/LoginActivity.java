package com.edwinacubillos.loginwithfirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText eCorreo, eContrasena;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eCorreo = findViewById(R.id.eCorreo);
        eContrasena = findViewById(R.id.eContrasena);

        mAuth = FirebaseAuth.getInstance();
    }

    public void onRegistrarseClicked(View view) {
        Intent i = new Intent(this, RegistroActivity.class);
        startActivity(i);
    }

    public void onIniciarSesionClicked(View view) {
        String correo, contrasena;
        correo = eCorreo.getText().toString();
        contrasena = eContrasena.getText().toString();

        mAuth.signInWithEmailAndPassword(correo, contrasena)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("LoginActivity", "signInWithEmail:success");
                            goToMainActivity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("LoginActivity", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });



    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            goToMainActivity();
        }
    }

    private void goToMainActivity() {
        Intent i = new Intent (this, MainActivity.class);
        startActivity(i);
    }
}
