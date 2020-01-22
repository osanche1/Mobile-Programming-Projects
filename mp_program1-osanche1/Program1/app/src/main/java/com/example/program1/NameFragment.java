package com.example.program1;

import android.content.Context;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NameFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private String nameEntered;
    private EditText nameField;
    private Button enterButton;
    private TextView greeting;


    public NameFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for our NameFragment
        View myView = inflater.inflate(R.layout.fragment_name, container, false);

        //link variable to XML attributes
        enterButton = myView.findViewById(R.id.enterBttn);
        nameField = myView.findViewById(R.id.nameField);
        greeting = myView.findViewById(R.id.greeting);

        enterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View myView){
                if(mListener != null){
                    nameEntered = nameField.getText().toString();
                    Log.d("Edit", nameField.getText().toString());
                    mListener.onFragmentInteraction(nameEntered);
                    greeting.setText("Hello " + nameEntered + "!");
                    Log.d("Set", greeting.getText().toString());
                }
            }
        });
        return myView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String name);
    }
}
