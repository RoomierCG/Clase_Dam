package com.example.contadorv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.contadorv2.dominio.Ajustes;

import static com.example.contadorv2.MainActivity.EXTRA_MESSAGE_AJUSTES;

public class AjustesActivity extends AppCompatActivity {

    private Ajustes ajustes;

    private CheckBox checkboxValoresNegativos;

    private EditText editTextValorPorDefectoContador;

    private EditText editTextValorIncrementoContador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        checkboxValoresNegativos = findViewById(R.id.checkboxValoresNegativos);
        editTextValorPorDefectoContador = findViewById(R.id.editTextValorPorDefectoContador);
        editTextValorIncrementoContador = findViewById(R.id.editTextValorIncrementoContador);

        Intent intent = getIntent();
        ajustes = (Ajustes) intent.getSerializableExtra(EXTRA_MESSAGE_AJUSTES);

        checkboxValoresNegativos.setChecked(ajustes.isPermitirValoresNegativos());
        editTextValorPorDefectoContador.setText(Integer.toString(ajustes.getValorPorDefectoContador()));
        editTextValorIncrementoContador.setText(Integer.toString(ajustes.getValorIncrementoContador()));
    }

    public void accionBotonGuardarAjustes(View view) {

        ajustes.setPermitirValoresNegativos(checkboxValoresNegativos.isChecked());
        ajustes.setValorPorDefectoContador(Integer.parseInt(editTextValorPorDefectoContador.getText().toString()));
        ajustes.setValorIncrementoContador(Integer.parseInt(editTextValorIncrementoContador.getText().toString()));

        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra(EXTRA_MESSAGE_AJUSTES, ajustes);

        startActivity(intent);
    }
}
