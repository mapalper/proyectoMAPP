package com.example.rafa.gestorcitas;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2ActivityNuevoCliente extends AppCompatActivity implements View.OnClickListener {

    private EditText edNombreNuevo;
    private EditText edTelefonoNuevo;
    private EditText edEmailNuevo;
    private Button btnGuardarNuevo;
    private Button btnCancelarNuevo;
    //private Button btnCerrarNuevo;
    private DatabaseManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_nuevo_cliente);

        edNombreNuevo = (EditText) findViewById(R.id.edNombreNuevo);
        edTelefonoNuevo = (EditText) findViewById(R.id.edTelefonoNuevo);
        edEmailNuevo = (EditText) findViewById(R.id.edEmailNuevo);
        btnGuardarNuevo = (Button) findViewById(R.id.btnGuardarNuevo);
        btnCancelarNuevo = (Button) findViewById(R.id.btnCancelarNuevo);
        //btnCerrarNuevo = (Button) findViewById(R.id.btnCerrarNuevo);
        manager = new DatabaseManager(this);

        //--entrada desde ClientesGragment
        Intent intent = getIntent();
        Bundle extraActividades = intent.getExtras();
        if (extraActividades != null){
            String Nombre = extraActividades.getString("NombreCliente");
            edNombreNuevo.setText(Nombre);
        }

        //btnCerrarNuevo.setOnClickListener(this);
        btnCancelarNuevo.setOnClickListener(this);
        btnGuardarNuevo.setOnClickListener(this);


    }

    //Dialogo para guardar cliente
    public void dialogoInsertarCliente(){
        AlertDialog.Builder diagInsertarCliente = new AlertDialog.Builder(this);

        diagInsertarCliente.setTitle("Almacenar Cliente");
        diagInsertarCliente.setMessage("¿Deseas guardar cliente?");
        diagInsertarCliente.setCancelable(false);
        diagInsertarCliente.setIcon(android.R.drawable.ic_dialog_alert);
        diagInsertarCliente.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                guardarCliente(edNombreNuevo,edTelefonoNuevo,edEmailNuevo);
            }
        });
        diagInsertarCliente.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        diagInsertarCliente.show();
    }

    public void guardarCliente(EditText nombre, EditText telefono, EditText email){
        if (manager.insertar(nombre.getText().toString(),telefono.getText().toString(),email.getText().toString()) != -1 ) {
            Toast.makeText(getApplicationContext(),"Cliente guardado correctamente",Toast.LENGTH_LONG).show();
            nombre.setText(null);
            telefono.setText(null);
            email.setText(null);

        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnGuardarNuevo){
            if (edNombreNuevo.getText().toString().equals("") || edTelefonoNuevo.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(),"Debe introducir Nombre y Telefono",Toast.LENGTH_SHORT).show();
            }else{
                dialogoInsertarCliente();
            }
        }

        if(v.getId() == R.id.btnCancelarNuevo){
            edNombreNuevo.setText(null);
            edTelefonoNuevo.setText(null);
            edEmailNuevo.setText(null);
        }
        //si pulsamos cerrar, volvemos a la pestaña CLIENTES --> ClientesFragment
        //no enviamos ningún dato, solo volvemos a la otra ventana      FUNCIONA PERO DA UN ERROR!!!!!!!!!!!!!!
        //prescindir de este botón porque puede volver atrás desde el menú normal del teléfono
        /*if(v.getId() == R.id.btnCerrarNuevo){
            Intent volver = new Intent(Main2ActivityNuevoCliente.this, ClientesFragment.class.getSuperclass());
            startActivity(volver);
        }*/
    }
}
