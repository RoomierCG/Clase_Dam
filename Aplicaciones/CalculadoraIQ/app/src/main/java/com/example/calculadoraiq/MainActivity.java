package com.example.calculadoraiq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static double buffer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*                    Botones sumar                    */
    public void cero(View view){
        buffer *= 10;
        actualizarTexto(view);
    }
    public void uno(View view){
        buffer *= 10;
        buffer += 1;
        actualizarTexto(view);
    }
    public void dos(View view){
        buffer *= 10;
        buffer += 2;
        actualizarTexto(view);
    }
    public void tres(View view){
        buffer *= 10;
        buffer += 3;
        actualizarTexto(view);
    }
    public void cuatro(View view){
        buffer *= 10;
        buffer += 4;
        actualizarTexto(view);
    }
    public void cinco(View view){
        buffer *= 10;
        buffer += 5;
        actualizarTexto(view);
    }
    public void seis(View view){
        buffer *= 10;
        buffer += 6;
        actualizarTexto(view);
    }
    public void siete(View view){
        buffer *= 10;
        buffer += 7;
        actualizarTexto(view);
    }
    public void ocho(View view){
        buffer *= 10;
        buffer += 8;
        actualizarTexto(view);
    }
    public void nueve(View view){
        buffer *= 10;
        buffer += 9;
        actualizarTexto(view);
    }

    public void actualizarTexto(View view){
        TextView textView = (TextView) findViewById(R.id.textresultado);
        textView.setText(Double.toString(buffer));
    }

}
