package com.example.contadorv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.contadorv2.dominio.Ajustes;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE_AJUSTES = "com.example.contadorv2.ajustes";

    private int contador;

    private Ajustes ajustes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contador = 0;
        mostrarResultado();

        Intent intent = getIntent();
        ajustes = (Ajustes) intent.getSerializableExtra(EXTRA_MESSAGE_AJUSTES);

        if (ajustes == null) {

            ajustes = new Ajustes();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {

        bundle.putInt("contador", contador);

        super.onSaveInstanceState(bundle);
    }

    @Override
    public void onRestoreInstanceState(Bundle bundle) {

        super.onRestoreInstanceState(bundle);

        contador = bundle.getInt("contador");

        mostrarResultado();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            abrirAjustes();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void accionBotonIncrementar(View view){

        contador = contador + ajustes.getValorIncrementoContador();

        mostrarResultado();
    }

    public void accionBotonDecrementar(View view){

        contador = contador - ajustes.getValorIncrementoContador();

        if ((contador < 0) && ajustes.isPermitirValoresNegativos() == false) {
            contador = 0;
        }

        mostrarResultado();
    }

    public void accionBotonReiniciar(View view){

        contador = ajustes.getValorPorDefectoContador();

        mostrarResultado();
    }

    private void mostrarResultado(){

        TextView textoResultado = (TextView) findViewById(R.id.valorContador);

        textoResultado.setText(Integer.toString(contador));
    }

    public void abrirAjustes(){

        Intent intent = new Intent(this, AjustesActivity.class);

        intent.putExtra(EXTRA_MESSAGE_AJUSTES, ajustes);

        startActivity(intent);
    }
}
