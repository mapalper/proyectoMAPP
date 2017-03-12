package com.example.rafa.gestorcitas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;


public class NuevaCitaFragment extends Fragment implements View.OnClickListener {

    private Spinner spClientes;
    private EditText edFechaCita;
    private EditText edHoraInicio;
    private EditText edHoraFin;
    private EditText edDescripcion;
    private Button btnGuardar;
    private Button btnCancelar;
    private DatabaseManager manager;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;
    private DatabaseManager2 manager2;
    private String itemSeleccionado;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_nueva_cita, container, false);
        if(v != null){
            spClientes = (Spinner) v.findViewById(R.id.spClientes);
            edFechaCita = (EditText) v.findViewById(R.id.edFechaCita);
            edHoraInicio = (EditText) v.findViewById(R.id.edHoraInicio);
            edHoraFin = (EditText) v.findViewById(R.id.edHoraFin);
            edDescripcion = (EditText) v.findViewById(R.id.edDescripcion);
            btnGuardar = (Button) v.findViewById(R.id.btnGuardar);
            btnCancelar = (Button) v.findViewById(R.id.btnCancelar);
            manager = new DatabaseManager(getActivity());
            manager2 = new DatabaseManager2(getActivity());

            rellenarSpinner();
            //comprobar qué elemento del spinner se selecciona
            spClientes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //añadir en itemSeleccionado el cliente escogido

                    itemSeleccionado = parent.getItemAtPosition(position).toString();                   //COMPROBAR!!!!!!!!!
                    //Toast.makeText(getActivity().getApplicationContext(),itemSeleccionado, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    //no ha seleccionado ningún item
                }
            });

            btnGuardar.setOnClickListener(this);
            btnCancelar.setOnClickListener(this);



        }
        return v;
    }


    public void rellenarSpinner(){

        String[] from = new String[] { manager.CN_NOMBRE};
        int[] to = new int[] {android.R.id.text1};
        cursor = manager.getClientes();
        adapter = new SimpleCursorAdapter(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                cursor,
                from,
                to,
                0);
        spClientes.setAdapter(adapter);
    }


    public void guardarCita(String cliente, EditText fecha, EditText horaInicio, EditText horaFin, EditText Descripcion){                 //COMPROBAR CLIENTE !!!!!!
        if (manager2.insertar(cliente,fecha.getText().toString(),horaInicio.getText().toString(),horaFin.getText().toString(),Descripcion.getText().toString()) != -1 ) {
            Toast.makeText(getActivity().getApplicationContext(),"Cita guardada correctamente",Toast.LENGTH_LONG).show();
            fecha.setText(null);
            horaInicio.setText(null);
            horaFin.setText(null);

        }else{

        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnGuardar){
            if (spClientes.getSelectedItem().toString().equals("") || edFechaCita.getText().toString().equals("") || edHoraInicio.getText().toString().equals("")){
                Toast.makeText(getActivity().getApplicationContext(),"Debe introducir Cliente, Fecha y Hora de Cita",Toast.LENGTH_SHORT).show();
            }else{
                guardarCita(itemSeleccionado,edFechaCita,edHoraInicio,edHoraFin,edDescripcion);

            }


        }

        if(v.getId() == R.id.btnCancelar){
            spClientes.setOnItemSelectedListener(null);
            edFechaCita.setText(null);
            edHoraInicio.setText(null);
            edHoraFin.setText(null);
            edDescripcion.setText(null);
        }


    }
}
