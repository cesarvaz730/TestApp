package com.example.encuentas.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.encuentas.R;
import com.example.encuentas.adapter.AdapterPregunta;
import com.example.encuentas.entity.ClientePromo;
import com.example.encuentas.entity.EncuestaList;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by cvazquez on 02/04/2018.
 */

public class HttpRequestTaskEncuesta extends AsyncTask<Void, Void,EncuestaList[] > {
    Activity baseContext;
    Integer operation;
    ClientePromo clientePromo;
    ProgressDialog dialog;
    public HttpRequestTaskEncuesta(Activity baseContext, Integer operation, ClientePromo clientePromo, ProgressDialog dialog) {
        this.baseContext=baseContext;
        this.operation=operation;
        this.clientePromo=clientePromo;
        this.dialog=dialog;

    }

    @Override
    protected  EncuestaList[] doInBackground(Void... params) {
        String url = "https://testspring732.herokuapp.com/wsfake/api/encuesta/";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        try {

            if(operation==3){
                url+="/"+clientePromo.getTipo();
                ResponseEntity<EncuestaList[]> result = restTemplate.getForEntity(url,EncuestaList[].class);
                return result.getBody();
            }

        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }

    @Override
    protected void onPostExecute(EncuestaList[] result) {
        Log.i("pinta encuesta","result get encuest");
        AdapterPregunta adapter = new AdapterPregunta(baseContext, new ArrayList<EncuestaList>(Arrays.asList(result)));
        ListView lv=(ListView) baseContext.findViewById(R.id.recipe_list_view);
        lv.setAdapter(adapter);
        this.dialog.dismiss();

    }

}
