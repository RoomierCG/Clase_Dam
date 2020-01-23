package com.example.vistasdinamicas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;


public class Login extends AppCompatActivity {

    private ViewGroup layout;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (ViewGroup) findViewById(R.id.sc);
        scrollView = findViewById(R.id.asdasda);

    }

    public void createView(View view){
        LinearLayout contenedor = (LinearLayout) findViewById(R.id.sc);
        LayoutInflater inflater = LayoutInflater.from(this);

        int nirvana = R.layout.nirvana;

        LinearLayout linearLayout = (LinearLayout) inflater.inflate(nirvana, null, false);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        params.topMargin = 15;

        contenedor.addView(linearLayout);

        //scroll to last element
        //http://stackoverflow.com/questions/6438061/can-i-scroll-a-scrollview-programmatically-in-android
        scrollView.post(new Runnable() {
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }



    /*
    public void createView(View view){

        LinearLayout contenedor = (LinearLayout) findViewById(R.id.sc);
        Resources resources = getResources();
        ImageView imageView = new ImageView(this);

        imageView.setImageDrawable(resources.getDrawable(R.drawable.smile));
        contenedor.addView(imageView);

    }
    */

}
