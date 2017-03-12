package com.example.rafa.gestorcitas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class PendientesFragment extends Fragment implements CalendarView.OnDateChangeListener {
    private CalendarView cvCalendario;
    private ListView lvCitasPendientes;
    private DatabaseManager2 manager;
    private String fechaseleccionada;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_pendientes, container, false);
        if(v != null){

            cvCalendario = (CalendarView) v.findViewById(R.id.cvCalendario);
            lvCitasPendientes = (ListView) v.findViewById(R.id.lvCitasPendientes);
            manager = new DatabaseManager2(getActivity());

            //buscarCitas();
            //mostrar que fecha se ha seleccionado
            cvCalendario.setOnDateChangeListener(this);


            //pulsacion prolongada de una cita del listView --> Diálogo para EDITAR
            /*lvCitasPendientes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView tv = (TextView)view.findViewById(android.R.id.text1);
                    final String Cita = tv.getText().toString();

                    AlertDialog.Builder diagEditarCita = new AlertDialog.Builder(getActivity());
                    diagEditarCita.setTitle("Editar Citas");
                    diagEditarCita.setMessage("¿Quieres editar esta cita?");
                    diagEditarCita.setCancelable(false);
                    diagEditarCita.setIcon(android.R.drawable.ic_menu_edit);

                    diagEditarCita.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //editar --> ir a la ventana de EditarCita
                            Intent editar = new Intent(getActivity(),EditarCita.class);
                            editar.putExtra("Cita",fechaseleccionada);
                            startActivity(editar);
                        }
                    });
                    diagEditarCita.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //no hacer nada
                        }
                    });
                    diagEditarCita.show();

                    return false;
                }
            });*/

        }
        return v;
    }


    //Buscar TODAS las citas para cuando no tiene ninguna fecha seleccionada
    /*public void buscarCitas(){

        String[] from = new String[] { manager.CN_CLIENTE, manager.CN_FECHA,manager.CN_HORAINICIO};
        int[] to = new int[] {android.R.id.text1,android.R.id.text2};
        cursor = manager.getCitas();
        adapter = new SimpleCursorAdapter(getActivity(),
                android.R.layout.two_line_list_item,
                cursor,
                from,
                to,
                0);
        //lvCitasPendientes.setAdapter(adapter);
    }

    //Buscar citas con la fecha que seleccione
    public void buscarCitadia(String fecha){
        String[] from = new String[] { manager.CN_CLIENTE, manager.CN_FECHA,manager.CN_HORAINICIO};
        int[] to = new int[] {android.R.id.text1,android.R.id.text2};
        Cursor nuevocursor = manager.getCitasdia(fecha);
        adapter.changeCursor(nuevocursor);
        lvCitasPendientes.setAdapter(adapter);
    }*/


    @Override
    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
        //Toast.makeText(getActivity().getApplicationContext(),"Fecha seleccionada "+year+"/"+month+"/"+dayOfMonth,Toast.LENGTH_SHORT).show();
        fechaseleccionada=dayOfMonth+"/"+month+"/"+year;
       // buscarCitadia(fechaseleccionada);
    }
}
