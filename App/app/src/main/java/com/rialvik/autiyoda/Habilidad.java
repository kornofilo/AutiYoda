package com.rialvik.autiyoda;

public class Habilidad {
    private String name;
    private int cantidadPasos;
    private String pasos[];
    private int imagenesPasos[];

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCantidadPasos() {
        return cantidadPasos;
    }

    public void setCantidadPasos(int cantidadPasos) {
        this.cantidadPasos = cantidadPasos;
    }

    public String[] getPasos() {
        return pasos;
    }

    public void setPasos(String[] pasos) {
        this.pasos = pasos;
    }

    public int[] getImagenesPasos() {
        return imagenesPasos;
    }

    public void setImagenesPasos(int[] imagenesPasos) {
        this.imagenesPasos = imagenesPasos;
    }
}
