package com.juannarvaez.almacenamiento;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class RegistarFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText codigoAsignatura, nombreAsignatura, numeroCreditos, semestre;
    private Button botonGuardar;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegistarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistarFragment newInstance(String param1, String param2) {
        RegistarFragment fragment = new RegistarFragment();
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
        View vista = inflater.inflate(R.layout.fragment_registar, container, false);
        codigoAsignatura = (EditText) vista.findViewById(R.id.codigoAsignatura);
        nombreAsignatura = (EditText) vista.findViewById(R.id.nombreAsignatura);
        numeroCreditos = (EditText) vista.findViewById(R.id.numeroCreditos);
        semestre = (EditText) vista.findViewById(R.id.semestre);
        botonGuardar = (Button) vista.findViewById(R.id.botonGuardar);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                AdministrarBaseDeDatos admindb = new AdministrarBaseDeDatos(getContext(), "dbasignaturas", null, 1);
                SQLiteDatabase db = admindb.getWritableDatabase();

                String codigo = codigoAsignatura.getText().toString();
                String nombre = nombreAsignatura.getText().toString();
                String creditos = numeroCreditos.getText().toString();
                String nivel = semestre.getText().toString();
                ContentValues guardar = new ContentValues();
                guardar.put("codigo", codigo);
                guardar.put("nombre_curso", nombre);
                guardar.put("creditos", creditos);
                guardar.put("semestre", nivel);
                db.insert("asignaturas", null, guardar);
                db.close();
                codigoAsignatura.setText("");
                nombreAsignatura.setText("");
                numeroCreditos.setText("");
                semestre.setText("");
                codigoAsignatura.requestFocus();
                Toast.makeText(getContext(),"Información Guardada Correctamente", Toast.LENGTH_SHORT).show(); } });



        return vista;
        }
    }