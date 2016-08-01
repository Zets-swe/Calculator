package ru.zets_swe.calculator.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import ru.zets_swe.calculator.R;

public class FragmentFiller extends Fragment {

    public static final double pi = 3.1415926535;

    Animation anim_show;
    Animation anim_hide;

    //Раздел объявления Layout
    //****************************************
    LinearLayout L_filler;


    //Раздел объявления Button
    //****************************************
    Button btn_filler;
    Button btn_filler_calculate;


    // Раздел объявления EditText
    //****************************************
    EditText et_filler_the_number_of_filling_heads;
    EditText et_filler_optimal_flow_m3_h;
    EditText et_filler_a_compromise_flow_m3_h;


    public FragmentFiller() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_filler, container, false);

        anim_show = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.anim_show);
        anim_hide = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.anim_hide);


        //region Поток на блок розлива

        //Раздел инициализации Layout
        //****************************************
        L_filler = (LinearLayout) rootView.findViewById(R.id.L_filler);


        // Раздел инициализации Button
        //****************************************
        btn_filler = (Button) rootView.findViewById(R.id.btn_filler);
        btn_filler_calculate = (Button) rootView.findViewById(R.id.btn_filler_calculate);


        // Раздел инициализации EditText и Spinner
        //****************************************
        et_filler_the_number_of_filling_heads = (EditText) rootView.findViewById(R.id.et_filler_the_number_of_filling_heads);
        et_filler_optimal_flow_m3_h = (EditText) rootView.findViewById(R.id.et_filler_optimal_flow_m3_h);
        et_filler_a_compromise_flow_m3_h = (EditText) rootView.findViewById(R.id.et_filler_a_compromise_flow_m3_h);

        btn_filler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchVisibility(L_filler);

            }
        });

        btn_filler_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(et_filler_the_number_of_filling_heads) ){
                    Toast.makeText(getActivity(), R.string.note_empty_field, Toast.LENGTH_LONG).show();
                } else {
                    double n = Double.parseDouble(et_filler_the_number_of_filling_heads.getText().toString());
                    double N1 = 5*60*n/1000;
                    double N2 = 3*60*n/1000;

                    et_filler_optimal_flow_m3_h.setText(String.valueOf(String.format("%.2f", N1)));
                    et_filler_a_compromise_flow_m3_h.setText(String.valueOf(String.format("%.2f", N2)));

                }

            }
        });

        //endregion

        return rootView;

    }


    public void switchVisibility(View view){
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
        } else {
            view.startAnimation(anim_show);
            view.setVisibility(View.VISIBLE);
        }
    }


    public boolean isEmpty(EditText et) {
        if (et.getText().toString().equals("")) {
            return true;
        } else {
            return false;
        }
    }
}
