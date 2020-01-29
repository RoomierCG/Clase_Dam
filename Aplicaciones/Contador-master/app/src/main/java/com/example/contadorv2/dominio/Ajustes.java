package com.example.contadorv2.dominio;

import java.io.Serializable;

public class Ajustes implements Serializable {

    private boolean permitirValoresNegativos;

    private int valorPorDefectoContador;

    private int valorIncrementoContador;

    public Ajustes(boolean permitirValoresNegativos, int valorPorDefectoContador, int valorIncrementoContador) {

        this.permitirValoresNegativos = permitirValoresNegativos;
        this.valorPorDefectoContador = valorPorDefectoContador;
        this.valorIncrementoContador = valorIncrementoContador;
    }

    public Ajustes(){

        this.permitirValoresNegativos = true;
        this.valorPorDefectoContador = 0;
        this.valorIncrementoContador = 1;
    }

    public boolean isPermitirValoresNegativos() {
        return permitirValoresNegativos;
    }

    public void setPermitirValoresNegativos(boolean permitirValoresNegativos) {
        this.permitirValoresNegativos = permitirValoresNegativos;
    }

    public int getValorPorDefectoContador() {
        return valorPorDefectoContador;
    }

    public void setValorPorDefectoContador(int valorPorDefectoContador) {
        this.valorPorDefectoContador = valorPorDefectoContador;
    }

    public int getValorIncrementoContador() {
        return valorIncrementoContador;
    }

    public void setValorIncrementoContador(int valorIncrementoContador) {
        this.valorIncrementoContador = valorIncrementoContador;
    }
}
