package com.example.encuentas.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.encuentas.R;
import com.example.encuentas.listener.InteractionListener;
import com.example.encuentas.task.HttpRequestTask;


public class EncuestaFragment extends Fragment implements View.OnClickListener{

private InteractionListener mListener;
    Activity home;
    ProgressDialog dialog;
    public  EncuestaFragment newInstance(Activity home, ProgressDialog dialog) {
        this.home=home;
        this.dialog=dialog;
       // EncuestaFragment fragment = new EncuestaFragment();
        return this;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_encuesta, container, false);
       // mEditText = (EditText) view.findViewById(R.id.fragment_our_edit_text);
        Button button = (Button) view.findViewById(R.id.buttonEnviar);
        button.setOnClickListener(this);
        new HttpRequestTask(home,1,null, this.dialog).execute();
        sentString("init");
        return view;
    }
    public void sentString(String send) {
        mListener.onFragmentInteraction(send);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InteractionListener) {
            mListener = (InteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement InteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        sentString("OK");
    }
}
