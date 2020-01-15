package com.example.vistasdinamicas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ScrollView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void createView(View view){

        ScrollView contenedor = (ScrollView) findViewById(R.id.sc);

        LayoutInflater inflater = LayoutInflater.from(this);
        View laViewInflada = inflater.inflate(R.layout.nirvana, contenedor, false);


    }
}
