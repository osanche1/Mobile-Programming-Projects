package com.example.program2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.Fragment;


public class tipFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    private EditText totalBillField;
    private EditText tipField;
    private RadioGroup radioControls;
    private Button calculateBttn;
    private TextView displayTotal;


    public tipFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment to attach the xml file
        View myView = inflater.inflate(R.layout.tip_fragment, container, false);

        //link each variable to its corresponding xml attribute
        totalBillField = myView.findViewById(R.id.totalBill);
        tipField = myView.findViewById(R.id.tip);
        radioControls =  myView.findViewById(R.id.radioGroup);
        calculateBttn = myView.findViewById(R.id.calculateBttn);
        displayTotal = myView.findViewById(R.id.finalPrice);
        calculateBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    try {
                        mListener.onFragmentInteractionListener(totalBillField.getText().toString());

                        double billCost = Double.parseDouble(totalBillField.getText().toString());
                        int tipValue = Integer.parseInt(tipField.getText().toString());
                        int selected = radioControls.getCheckedRadioButtonId();
                        int options = 0;
                        if (selected == 2131165272) {
                            options = 1; //Not rounding
                        }
                        else if (selected == 2131165289) {
                            options = 2; // Round Tip
                        }
                        else if (selected == 2131165290) {
                            options = 3; // Round Total
                        } else {
                            Toast.makeText(getActivity(), "Error detected", Toast.LENGTH_LONG).show();
                        }
                        if (options >= 1 && options <= 3 && billCost >= 0.01 && tipValue >= 1) {
                            calculateBill(billCost, tipValue, options);
                        } else {
                            Toast.makeText(getActivity(), "You have an error with your input", Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(), "The bill must be at least 0.01,\ntip must be at least 1", Toast.LENGTH_LONG).show();
                        }
                    }catch (Exception a){
                        Toast.makeText(getActivity(), "Caught an error", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        return myView;
    }

    public void calculateBill(double billCost ,double tip, int option){
        try {
            double total = 0.00;
            if (option == 1) {
                total = billCost * (tip * 0.01);
                total = billCost + total;
            } else if (option == 2) {
                total = billCost * (tip * 0.01);
                total = Math.round(total);
                total = billCost + total;
            } else if (option == 3) {
                total = billCost * (tip * 0.01);
                total = billCost + total;
                total = Math.round(total);
            }
            Toast.makeText(getActivity(), "With a tip of: " + tip + "%\nYour total bill is: $" + total, Toast.LENGTH_LONG).show();
        }catch (Exception z){
            Toast.makeText(getActivity(), "An exception was caught", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = getActivity();
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteractionListener(String name);
    }
}

