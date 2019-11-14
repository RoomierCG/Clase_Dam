package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static double resultado;
    public static String buffer = "";
    public static double buffer2;
    public static int opcion = 0;
    public static String simbolo = "";
    /*Varibables*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void C(View view){
        buffer = "0";
        TextView textView = (TextView) findViewById(R.id.textresultado);
        textView.setText(buffer);
    }

    public void resultado(View view){
        switch (opcion){
            case 1:
                resultado = buffer2 + (Integer.parseInt(buffer));
                TextView textView = (TextView) findViewById(R.id.textresultado);
                textView.setText(Double.toString(resultado));
                buffer2 = 0;
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }
    public void cero(View view){
        buffer += "0";
        actualizarTexto(view);
    }
    public void uno(View view){
        buffer += "1";
        actualizarTexto(view);
    }
    public void dos(View view){
        buffer += "2";
        actualizarTexto(view);
    }
    public void tres(View view){
        buffer += "3";
        actualizarTexto(view);
    }
    public void cuatro(View view){
        buffer += "4";
        actualizarTexto(view);
    }
    public void cinco(View view){
        buffer += "5";
        actualizarTexto(view);
    }
    public void seis(View view){
        buffer += "6";
        actualizarTexto(view);
    }
    public void siete(View view){
        buffer += "7";
        actualizarTexto(view);
    }
    public void ocho(View view){
        buffer += "8";
        actualizarTexto(view);
    }
    public void nueve(View view){
        buffer += "9";
        actualizarTexto(view);
    }
    public void suma(View view){
        buffer2 = Double.parseDouble(buffer);
        buffer = "";
        simbolo = "+";
        actualizarTexto(view);
        opcion = 1;
    }


    public void actualizarTexto(View view){
        String localvariable = Double.toString(buffer2);

        TextView textView = (TextView) findViewById(R.id.textresultado);
        textView.setText(buffer);
        TextView textView1 = (TextView) findViewById(R.id.buffer);
        textView1.setText( localvariable + simbolo + buffer);

    }

}
