package com.juannarvaez.almacenamiento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabControlesSeleccion;
    ViewPager paginas;
    AdaptadorPaginas adaptadorPaginas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabControlesSeleccion = (TabLayout) findViewById(R.id.tabDeAlmacenamiento);
        paginas = (ViewPager) findViewById(R.id.paginas);
        adaptadorPaginas = new AdaptadorPaginas(getSupportFragmentManager(), tabControlesSeleccion.getTabCount());
        paginas.setAdapter(adaptadorPaginas);
        tabControlesSeleccion.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                paginas.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    adaptadorPaginas.notifyDataSetChanged();
                }
                if (tab.getPosition() == 1) {
                    adaptadorPaginas.notifyDataSetChanged();
                }
                if (tab.getPosition() == 2) {
                    adaptadorPaginas.notifyDataSetChanged();
                }
            }
                @Override
                public void onTabUnselected (TabLayout.Tab tab){

                }

                @Override
                public void onTabReselected (TabLayout.Tab tab){

                }
            });
        paginas.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabControlesSeleccion));
        }
    }