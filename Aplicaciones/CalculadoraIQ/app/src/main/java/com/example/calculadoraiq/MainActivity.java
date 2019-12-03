package com.example.calculadoraiq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {

    public static double buffer = 0;
    public static double buffer2 = 0;
    public static double buffer3 = 0;
    public static int operador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /********************** BOTONES ************************/
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

    /********************** OPERACIONES LOGICAS ************************/

    public void C(View view){
        buffer = 0;
        buffer2 = 0;
        actualizarTexto(view);
    }

    public void DEL(View view){
        buffer = buffer - (buffer%10);
        buffer/=10;
        actualizarTexto(view);
    }

    public void suma(View view){
        operador = 1;
        buffer2 = buffer;
        buffer = 0;
        actualizarTexto(view);
    }

    public void resta(View view){
        operador = 2;
        buffer2 = buffer;
        buffer = 0;
        actualizarTexto(view);
    }

    public void multiplicacion (View view){
        operador = 3;
        buffer2 = buffer;
        buffer = 0;
        actualizarTexto(view);
    }

    public void division(View view){
        operador = 4;
        buffer2 = buffer;
        buffer = 0;
        actualizarTexto(view);
    }

    public void negapositivo(View view){
        buffer *= -1;
        actualizarTexto(view);
    }

    public void resultado(View view){

        double resultado;
        //variables locales

        switch (operador){
            case 1:
                resultado = buffer + buffer2;
                buffer3 = buffer;
                buffer = resultado;
                actualizarTexto(view);
                buffer = 0;
                operador = 0;
                buffer3 = 0;
                break;
            case 2:
                resultado = buffer - buffer2;
                buffer3 = buffer;
                buffer = resultado;
                actualizarTexto(view);
                buffer = 0;
                operador = 0;
                buffer3 = 0;
                break;
            case 3:
                resultado = buffer * buffer2;
                buffer3 = buffer;
                buffer = resultado;
                actualizarTexto(view);
                buffer = 0;
                operador = 0;
                buffer3 = 0;
                break;
            case 4:
                resultado = buffer / buffer2;
                buffer3 = buffer;
                buffer = resultado;
                actualizarTexto(view);
                buffer = 0;
                operador = 0;
                buffer3 = 0;
                break;
            case 5:
                resultado = Math.pow(buffer2,buffer);
                buffer3 = buffer;
                buffer = resultado;
                actualizarTexto(view);
                buffer = 0;
                operador = 0;
                buffer3 = 0;
                break;
            case 6:
                //estas variable se usara para añadir tantos ceros como marque el usuario
                int cont = 1;
                resultado = 1;

                while ( cont <= buffer){
                    resultado *= 10;
                    cont++;
                }
                buffer = resultado * buffer2;
                actualizarTexto(view);
                buffer = 0;
                operador = 0;
                break;
            case 7:
                buffer = buffer2 % buffer;
                actualizarTexto(view);
                buffer = 0;
                operador = 0;
                break;

        }
    }

    /********************** OPERACIONES CIENTIFICAS ************************/

    public void valorAlCuadrado(View view){
        buffer = Math.pow(buffer,2);
        buffer2 = buffer;
        actualizarTexto(view);
    }

    public void bufferelevados(View view){
        operador = 5;
        buffer2 = buffer;
        buffer = 0;
        actualizarTexto(view);
    }

    public void sin(View view){
        buffer = Math.sin(buffer);
        buffer2 = buffer;
        actualizarTexto(view);
    }

    public void cos(View view){
        buffer = Math.cos(buffer);
        buffer2 = buffer;
        actualizarTexto(view);
    }

    public void tan(View view){
        buffer = Math.tan(buffer);
        buffer2 = buffer;
        actualizarTexto(view);
    }

    public void valorAlCubo(View view){
        buffer = Math.pow(buffer,3);
        buffer2 = buffer;
        actualizarTexto(view);
    }

    // numero raiz de numero
    public void varraizvar(View view){
    }

    public void log(View view){
        buffer = Math.log(buffer);
        buffer2 = buffer;
        actualizarTexto(view);
    }

    public void exp(View view){
        operador = 6;
        buffer2 = buffer;
        buffer = 0;
        actualizarTexto(view);

    }

    public void mod(View view){
        operador = 7;
        buffer2 = buffer;
        buffer = 0;
        actualizarTexto(view);
    }

    /********************** TextView ************************/

    public void actualizarTexto(View view){
        TextView textView = (TextView) findViewById(R.id.textresultado);
        TextView textView1 = (TextView) findViewById(R.id.buffer);

        textView.setText(Double.toString(buffer));

        //Hacemos que si le hemos dado al boton de suma o resta el textview buffer no tendra simbolo
        if(buffer2 == 0){
            textView1.setText("");
        }
        if (operador != 1 && operador != 2 && operador != 3 && operador != 4 && operador != 5){
            textView1.setText(Double.toString(buffer2));
        }else{
            switch (operador){
                case 1:
                    textView1.setText(Double.toString(buffer2) + " + " + Double.toString(buffer));
                    break;
                case 2:
                    textView1.setText(Double.toString(buffer2) + " - " + Double.toString(buffer));
                    break;
                case 3:
                    textView1.setText(Double.toString(buffer2) + " * " + Double.toString(buffer));
                    break;
                case 4:
                    textView1.setText(Double.toString(buffer2) + " / " + Double.toString(buffer));
                    break;
                case 5:
                    textView1.setText(Double.toString(buffer2) + " ^ " + Double.toString(buffer));
                    break;
            }
        }
        if  (operador != 1 && operador != 2 && operador != 3 && operador != 4 || buffer3 != 0){
            switch (operador){
                case 1:
                    textView1.setText(Double.toString(buffer2) + " + " + Double.toString(buffer3) + " = " + Double.toString(buffer));
                    break;
                case 2:
                    textView1.setText(Double.toString(buffer2) + " - " + Double.toString(buffer3) + " = " + Double.toString(buffer));
                    break;
                case 3:
                    textView1.setText(Double.toString(buffer2) + " * " + Double.toString(buffer3) + " = " + Double.toString(buffer));
                    break;
                case 4:
                    textView1.setText(Double.toString(buffer2) + " / " + Double.toString(buffer3) + " = " + Double.toString(buffer));
                    break;
                case 5:
                    textView1.setText(Double.toString(buffer2) + " ^ " + Double.toString(buffer3) + " = " + Double.toString(buffer));
                    break;
            }
        }
    }

}
