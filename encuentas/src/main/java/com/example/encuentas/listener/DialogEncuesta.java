package com.example.encuentas.listener;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.util.Log;

import com.example.encuentas.task.HttpRequestTask;
import com.example.encuentas.entity.ClientePromo;

/**
 * Created by cvazquez on 02/04/2018.
 */

public class DialogEncuesta implements DialogInterface.OnClickListener {
    Activity baseContext;
    ClientePromo clientePromo;
    private ProgressDialog dialog;

    public  DialogEncuesta(ClientePromo clientePromo,Activity baseContext,ProgressDialog dialog){
        this.clientePromo=clientePromo;
        this.baseContext=baseContext;
        this.dialog=dialog;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case DialogInterface.BUTTON_POSITIVE:
                //Yes button clicked
                Log.d("switch","positive");
                clientePromo.setStatus("1");
                new HttpRequestTask(baseContext,2,clientePromo, this.dialog).execute();
                break;

            case DialogInterface.BUTTON_NEGATIVE:
                //No button clicked
                Log.d("switch","negative");
                clientePromo.setStatus("0");
                new HttpRequestTask(baseContext,2,clientePromo, this.dialog).execute();
                break;
        }
    }
}
