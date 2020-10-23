package com.juannarvaez.almacenamiento;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuscarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuscarFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText codigoAsignatura;
    private TextView nombreAsignatura, numeroCreditos, semestre;
    private Button botonBuscar, botonActualizar, botonEliminar;

    public BuscarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuscarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuscarFragment newInstance(String param1, String param2) {
        BuscarFragment fragment = new BuscarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_buscar, container, false);
        codigoAsignatura = (EditText) vista.findViewById(R.id.codigoAsignatura);
        nombreAsignatura = (TextView) vista.findViewById(R.id.nombreAsignatura);
        numeroCreditos = (TextView) vista.findViewById(R.id.numeroCreditos);
        semestre = (TextView) vista.findViewById(R.id.semestre);
        botonBuscar = (Button) vista.findViewById(R.id.botonBuscar);
        botonActualizar = (Button) vista.findViewById(R.id.botonActualizar);
        botonEliminar = (Button) vista.findViewById(R.id.botonEliminar);
        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                AdministrarBaseDeDatos admindb = new AdministrarBaseDeDatos(getContext(), "dbasignaturas", null, 1);
                SQLiteDatabase db = admindb.getWritableDatabase();
                String codigo = codigoAsignatura.getText().toString();
                Cursor registro = db.rawQuery("SELECT nombre_curso, creditos, semestre FROM asignaturas WHERE codigo = " + codigo, null);
                if (registro.moveToFirst()) {
                    nombreAsignatura.setText(registro.getString(0));
                    numeroCreditos.setText(registro.getString(1));
                    semestre.setText(registro.getString(2));
                    db.close();
                } else {
                    Toast.makeText(getContext(), "La Asignatura No Está Registrada", Toast.LENGTH_SHORT).show();
                    db.close();
                }
            }
        });

        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdministrarBaseDeDatos admindb = new AdministrarBaseDeDatos(getContext(), "dbasignaturas", null, 1);
                SQLiteDatabase db = admindb.getWritableDatabase();
                String codigo = codigoAsignatura.getText().toString();
                int eliminar = db.delete("asignaturas", "codigo=" + codigo, null);
                db.close();
                codigoAsignatura.setText("");
                nombreAsignatura.setText("");
                numeroCreditos.setText("");
                semestre.setText("");
                if(eliminar != 0){
                    Toast.makeText(getContext(),"La asignatura se Elimino",Toast.LENGTH_SHORT).show();
                }else{ Toast.makeText(getContext(),"La Asignatura No Está Registrada", Toast.LENGTH_SHORT).show();
                }
            }
        });
        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActualizarActivity.class);
                getActivity().startActivity(intent);

            }
        });
        return vista;
    }
}