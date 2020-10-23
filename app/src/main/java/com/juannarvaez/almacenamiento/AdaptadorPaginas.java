package com.juannarvaez.almacenamiento;


import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class AdaptadorPaginas extends FragmentPagerAdapter {
    int numeroPaginas;

    public AdaptadorPaginas(@NonNull FragmentManager fm, int numPaginas) {
        super(fm, numPaginas);
        this.numeroPaginas = numPaginas;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new RegistarFragment();
            case 1:
                return new BuscarFragment();
            case 2:
                return new ListaFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numeroPaginas;
    }
}
