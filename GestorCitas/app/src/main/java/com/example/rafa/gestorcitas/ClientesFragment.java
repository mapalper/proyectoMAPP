package com.example.rafa.gestorcitas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class ClientesFragment extends Fragment implements View.OnClickListener {

    private EditText edNombreClientes;
    private Button btnBuscarClientes;
    private Button btnNuevoClientes;
    private ListView lvClientes;
    private DatabaseManager manager;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_clientes, container, false);
        if(v != null){
            edNombreClientes = (EditText) v.findViewById(R.id.edNombreClientes);
            btnBuscarClientes = (Button) v.findViewById(R.id.btnBuscarClientes);
            btnNuevoClientes = (Button) v.findViewById(R.id.btnNuevoClientes);
            lvClientes = (ListView) v.findViewById(R.id.lvClientes);
            manager = new DatabaseManager(getActivity());   //COMPROBAR!!!!!!!!!!!!!!!!!


            //--entrada desde ClientesGragment
            Intent intent = getActivity().getIntent();

            //recargar listView con los clintes
            buscarClientes();

            btnNuevoClientes.setOnClickListener(this);
            btnBuscarClientes.setOnClickListener(this);

            //pulsacion prolongada de un cliente del listView --> Diálogo para ELIMINAR
            lvClientes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView tv = (TextView)view.findViewById(android.R.id.text1);
                    final String nombreCliente = tv.getText().toString();

                    AlertDialog.Builder diagEliminarCliente = new AlertDialog.Builder(getActivity());       //A COMPROBAR !!!!!!!!!!!!!!
                    diagEliminarCliente.setTitle("Eliminar Clientes");
                    diagEliminarCliente.setMessage("¿Quieres eliminar el cliente?");
                    diagEliminarCliente.setCancelable(false);
                    diagEliminarCliente.setIcon(android.R.drawable.ic_delete);

                    diagEliminarCliente.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            manager.eliminarCliente(nombreCliente);
                            buscarClientes();
                            Toast.makeText(getActivity().getApplicationContext(),"Contacto eliminado correctamente",Toast.LENGTH_SHORT).show();
                        }
                    });
                    diagEliminarCliente.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //no hacer nada
                        }
                    });
                    diagEliminarCliente.show();


                    return false;
                }
            });

        }
        return v;

        //return inflater.inflate(R.layout.fragment_clientes, container, false);



    }

    //Buscar TODOS los CLIENTES
    public void buscarClientes(){

        String[] from = new String[] { manager.CN_NOMBRE,manager.CN_TELEFONO};
        int[] to = new int[] {android.R.id.text1,android.R.id.text2};
        cursor = manager.getClientes();
        adapter = new SimpleCursorAdapter(getActivity(),
                android.R.layout.two_line_list_item,
                cursor,
                from,
                to,
                0);
        lvClientes.setAdapter(adapter);
    }

    //Buscar un CLIENTE
    public void buscarCliente(String nombre){
        String[] from = new String[] { manager.CN_NOMBRE,manager.CN_TELEFONO};
        int[] to = new int[] {android.R.id.text1,android.R.id.text2};
        Cursor nuevocursor = manager.getBuscaCliente(nombre);
        adapter.changeCursor(nuevocursor);
        lvClientes.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        //si pulsa boton nuevo Cliente, vamos a la ventana de crear cliente nuevo (Main2ActivityNuevoCliente)
        if(v.getId() == R.id.btnNuevoClientes){
            Intent nuevo = new Intent(getActivity(),Main2ActivityNuevoCliente.class);
            nuevo.putExtra("NombreCliente",edNombreClientes.getText().toString());
            startActivity(nuevo);
        }
        if(v.getId() == R.id.btnBuscarClientes){
            if (edNombreClientes.getText().toString().equals("")){
                Toast.makeText(getActivity().getApplicationContext(),"Mostrando todos los clientes",Toast.LENGTH_SHORT).show();
                buscarClientes();
            } else {
                //buscar un solo cliente
                Toast.makeText(getActivity().getApplicationContext(),"Buscando el nombre del cliente",Toast.LENGTH_SHORT).show();

                buscarCliente(edNombreClientes.getText().toString());
            }
        }
    }
}
