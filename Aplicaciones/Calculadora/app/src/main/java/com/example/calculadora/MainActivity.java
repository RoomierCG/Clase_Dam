package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static int resultado;
    public static int buffer = 0;
    public static int cero;
    public static int uno;
    public static int dos;
    public static int tres;
    public static int cuatro;
    public static int cinco;
    public static int seis;
    public static int siete;
    public static int ocho;
    public static int nueve;
    /*Varibables*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void uno(View view){

        
        actualizarTexto(view);
    }

    public void actualizarTexto(View view){
        TextView textView = (TextView) findViewById(R.id.textresultado);
        textView.setText(Integer.toString(buffer));
    }

}
