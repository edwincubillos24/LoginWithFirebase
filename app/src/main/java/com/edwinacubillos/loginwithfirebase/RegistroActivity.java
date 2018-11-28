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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroActivity extends AppCompatActivity {

    EditText eCorreo, eContrasena;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        eCorreo = findViewById(R.id.eCorreo);
        eContrasena = findViewById(R.id.eContrasena);

        mAuth = FirebaseAuth.getInstance();
    }

    public void onRegistrarmeClicked(View view) {
        String correo, contrasena;
        correo = eCorreo.getText().toString();
        contrasena = eContrasena.getText().toString(); //8 digitos

        mAuth.createUserWithEmailAndPassword(correo, contrasena)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("RegistroActivity", "createUserWithEmail:success");
                            createUserInDB();
                            goToMainActivity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("RegistroActivity", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }

    private void createUserInDB() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("usuarios");

        FirebaseUser currentUser = mAuth.getCurrentUser();
        User user = new User (currentUser.getUid(),
                currentUser.getEmail());

        myRef.child(currentUser.getUid()).setValue(user);

    }

    private void goToMainActivity() {
        Intent i = new Intent (this, MainActivity.class);
        startActivity(i);
    }
}
