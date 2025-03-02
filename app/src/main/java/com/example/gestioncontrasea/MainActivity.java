package com.example.gestioncontrasea;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editUsuario,editProvedor,editContraseña, editId;
    FloatingActionButton btnRegistrarContraseña,btnEditarContraseña, btnEliminarContraseña;
    ListView lstListarContraseña;

    Contraseña contraseñajava;

    ArrayAdapter<String> adapter;
    int idSeleccionado  = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializamos los controles

        editUsuario = findViewById(R.id.editUsuario);
        editProvedor = findViewById(R.id.editProvedor);
        editContraseña = findViewById(R.id.editContraseña);
        btnRegistrarContraseña = findViewById(R.id.btnRegistrarContraseña);
        lstListarContraseña = findViewById(R.id.lstListarContraseña);
        btnEditarContraseña = findViewById(R.id.btnEditarContraseña);
        btnEliminarContraseña = findViewById(R.id.btnEliminarContraseña);
        editId = findViewById(R.id.editId);

        contraseñajava = new Contraseña(this);



       actualizarContraseña();

        btnRegistrarContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contraseñajava.insertarContraseña(
                        editUsuario.getText().toString(),
                        editProvedor.getText().toString(),
                        editContraseña.getText().toString());
                actualizarContraseña();
                LimpiarCamposC();

            }

        });



        actualizarContraseña();

        //seleccionar medico a actualizar o eliminar
        lstListarContraseña.setOnItemClickListener(((parent, view, position, id) -> {
            String itemseleccionado = (String) parent.getItemAtPosition(position);
            String[] parts = itemseleccionado.split(" - ");

            if (parts.length ==4){
                idSeleccionado = Integer.parseInt(parts[0]);
                editId.setText(parts[0]);
                editUsuario.setText(parts[0]);
                editProvedor.setText(parts[1]);
                editUsuario.setText(parts[2]);
            }else {
                Toast.makeText(MainActivity.this,"Error al seleccionar medico", Toast.LENGTH_SHORT).show();
            }
        }));

        btnEliminarContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idSeleccionado != -1){
                    contraseñajava.eliminarContraseña(idSeleccionado);
                    LimpiarCamposC();
                    actualizarContraseña();
                }else{
                    Toast.makeText(MainActivity.this,"No seleccionaste un medico de la lista", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnEditarContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idSeleccionado != -1){
                    contraseñajava.actualizarContraseña(
                            idSeleccionado,
                            editUsuario.getText().toString(),
                            editProvedor.getText().toString(),
                            editContraseña.getText().toString()
                    );
                    LimpiarCamposC();
                    actualizarContraseña();
                }else {
                    Toast.makeText(MainActivity.this,"No seleccionastes un medico a lista", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private void actualizarContraseña() {
        List<String> medicos = contraseñajava.obtenerContraseña();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, medicos) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                // Obtener el TextView dentro del ListView
                TextView textView = view.findViewById(android.R.id.text1);

                // Cambiar color del texto a blanco
                textView.setTextColor(Color.BLACK);

                return view;
            }
        };

        lstListarContraseña.setAdapter(adapter);
    }
    private void LimpiarCamposC(){
        editId.setText("");
        editUsuario.setText("");
        editProvedor.setText("");
        editContraseña.setText("");


    }
}