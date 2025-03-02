package com.example.gestioncontrasea;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AccesoLogin extends AppCompatActivity {

    //credenciales master

    private final String usermaster = "1546173";
    private final String passmaster = "Valeria2005";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.acceso);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        //referenciar controles

        EditText txtUserMaster = findViewById(R.id.txtUserMaster);
        EditText txtPassMaster = findViewById(R.id.txtPassMaster);
        Button btnAcceder = findViewById(R.id.btnAcceder);
        Button btnSalir = findViewById(R.id.btnSalir);

        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usuarioinput = txtUserMaster.getText().toString();
                String passinput = txtPassMaster.getText().toString();


                if (usuarioinput.equals(usermaster) && passinput.equals(passmaster)){
                    Toast.makeText(AccesoLogin.this,"Credenciales Correctas",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AccesoLogin.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(AccesoLogin.this,"Credenciales Incorrectas",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}
