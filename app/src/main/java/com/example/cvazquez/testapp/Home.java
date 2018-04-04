package com.example.cvazquez.testapp;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.encuentas.fragment.EncuestaFragment;
import com.example.encuentas.activity.Encuesta;
import com.example.encuentas.listener.InteractionListener;

public class Home extends AppCompatActivity implements View.OnClickListener, InteractionListener {
    Integer vista=0;
    ImageButton home;
    private Fragment OurFragment;
    private ProgressDialog dialog;

    @Override
    public void onClick(View v) {
        ImageButton imagen= (ImageButton) findViewById(R.id.imageHome);
        switch (vista) {
            case 0:
              //  replaceFragment();
                   imagen.setBackgroundResource(R.mipmap.s2);
                    vista=1;

                break;
            case 1:
                    imagen.setBackgroundResource(R.mipmap.s5);
                    vista=2;
                break;
            case 2:
                imagen.setBackgroundResource(R.mipmap.s6);
                vista=3;
                break;
            case 3:

                imagen.setBackgroundResource(R.mipmap.s7);
                vista=4;
                break;
            case 4:
                imagen.setBackgroundResource(R.mipmap.s8);
                vista=5;
                break;
            case 5:
                imagen.setBackgroundResource(R.mipmap.s10);
                vista=6;
                break;
            case 6:
                //Intent k = new Intent(Home.this, Encuesta.class);
                //startActivity(k);
               replaceFragment();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        home =(ImageButton)findViewById(R.id.imageHome);
        home.setOnClickListener(this);
    }

    public void replaceFragment() {
        Home x=this;
        this.dialog = new ProgressDialog(x);
        this.dialog.setMessage("Cargando Encuesta...");
        this.dialog.setCancelable(false);
        this.dialog.show();

        OurFragment = new EncuestaFragment().newInstance((Activity)x,this.dialog );

        try {

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main, OurFragment, "tag").addToBackStack("test").commit();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onFragmentInteraction(String string) {
        ImageButton imagen= (ImageButton) findViewById(R.id.imageHome);
        Log.d("listener","listener");
        if(string.equals("init")){
            imagen.setBackgroundColor(Color.WHITE);
        }else{
            imagen.setBackgroundResource(R.mipmap.screenshot_home);
            vista=0;
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            fragmentTransaction.remove(OurFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ImageButton imagen= (ImageButton) findViewById(R.id.imageHome);
        imagen.setBackgroundResource(R.mipmap.screenshot_home);
        vista=0;
    }
}
