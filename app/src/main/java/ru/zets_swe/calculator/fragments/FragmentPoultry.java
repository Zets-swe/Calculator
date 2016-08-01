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

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPoultry extends Fragment {


    public static final double pi = 3.1415926535;

    Animation anim_show;
    Animation anim_hide;

    //Раздел объявления Layout
    //****************************************
    LinearLayout L_poultry;


    //Раздел объявления Button
    //****************************************
    Button btn_poultry;
    Button btn_poultry_calculate;


    // Раздел объявления EditText
    //****************************************
    EditText et_poultry_slaughter_carcasses_h;
    EditText et_poultry_work_shift_h;
    EditText et_poultry_concentration;
    EditText et_poultry_the_cost_of_df_rub_kg;
    EditText et_poultry_the_bath_volume_m3;
    EditText et_poultry_makeup_water_m3_h;


    EditText et_poultry_concentration_ppm;
    EditText et_poultry_the_consumption_of_df_filling_kg;
    EditText et_poultry_the_cost_of_filling_rub;
    EditText et_poultry_product_consumption_per_day_kg;
    EditText et_poultry_the_cost_of_day_work_rub;
    EditText et_poultry_processing_cost_rub_carcass;

    EditText et_poultry_concentration_ppm1;
    EditText et_poultry_product_consumption_per_day_kg1;
    EditText et_poultry_the_cost_of_day_work_rub1;
    EditText et_poultry_processing_cost_rub_carcass1;


    public FragmentPoultry() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_poultry, container, false);

        anim_show = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.anim_show);
        anim_hide = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.anim_hide);

        //region Время дозировки насоса

        //Раздел инициализации Layout
        //****************************************
        L_poultry = (LinearLayout) rootView.findViewById(R.id.L_poultry);


        // Раздел инициализации Button
        //****************************************
        btn_poultry = (Button) rootView.findViewById(R.id.btn_poultry);
        btn_poultry_calculate = (Button) rootView.findViewById(R.id.btn_poultry_calculate);


        // Раздел инициализации EditText и Spinner
        //****************************************
        et_poultry_slaughter_carcasses_h = (EditText) rootView.findViewById(R.id.et_poultry_slaughter_carcasses_h);
        et_poultry_work_shift_h = (EditText) rootView.findViewById(R.id.et_poultry_work_shift_h);
        et_poultry_concentration = (EditText) rootView.findViewById(R.id.et_poultry_concentration);
        et_poultry_the_cost_of_df_rub_kg = (EditText) rootView.findViewById(R.id.et_poultry_the_cost_of_df_rub_kg);
        et_poultry_the_bath_volume_m3 = (EditText) rootView.findViewById(R.id.et_poultry_the_bath_volume_m3);
        et_poultry_makeup_water_m3_h = (EditText) rootView.findViewById(R.id.et_poultry_makeup_water_m3_h);


        et_poultry_concentration_ppm = (EditText) rootView.findViewById(R.id.et_poultry_concentration_ppm);
        et_poultry_the_consumption_of_df_filling_kg = (EditText) rootView.findViewById(R.id.et_poultry_the_consumption_of_df_filling_kg);
        et_poultry_the_cost_of_filling_rub = (EditText) rootView.findViewById(R.id.et_poultry_the_cost_of_filling_rub);
        et_poultry_product_consumption_per_day_kg = (EditText) rootView.findViewById(R.id.et_poultry_product_consumption_per_day_kg);
        et_poultry_the_cost_of_day_work_rub = (EditText) rootView.findViewById(R.id.et_poultry_the_cost_of_day_work_rub);
        et_poultry_processing_cost_rub_carcass = (EditText) rootView.findViewById(R.id.et_poultry_processing_cost_rub_carcass);

        et_poultry_concentration_ppm1 = (EditText) rootView.findViewById(R.id.et_poultry_concentration_ppm1);
        et_poultry_product_consumption_per_day_kg1 = (EditText) rootView.findViewById(R.id.et_poultry_product_consumption_per_day_kg1);
        et_poultry_the_cost_of_day_work_rub1 = (EditText) rootView.findViewById(R.id.et_poultry_the_cost_of_day_work_rub1);
        et_poultry_processing_cost_rub_carcass1 = (EditText) rootView.findViewById(R.id.et_poultry_processing_cost_rub_carcass1);


        btn_poultry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchVisibility(L_poultry);

            }
        });

        btn_poultry_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (false) {
                    Toast.makeText(getActivity(), R.string.note_empty_field, Toast.LENGTH_LONG).show();
                } else {
                    double n = Double.parseDouble(et_poultry_slaughter_carcasses_h.getText().toString());
                    double t = Double.parseDouble(et_poultry_work_shift_h.getText().toString());
                    double C = Double.parseDouble(et_poultry_concentration.getText().toString());
                    double P = Double.parseDouble(et_poultry_the_cost_of_df_rub_kg.getText().toString());
                    double V = Double.parseDouble(et_poultry_the_bath_volume_m3.getText().toString());
                    double V1 = Double.parseDouble(et_poultry_makeup_water_m3_h.getText().toString());

                    double С1=C*1500;
                    double Vd=V*1000*C/100;
                    double P1=Vd*P;
                    double Vm=V1*t*1000*C/100+Vd;
                    double Vh=(Vd+Vm)*P;
                    double Pt=Vh/(n*t);

                    double Vm1=V1*t*C*1000/100;
                    double Vh1=Vm1*P;
                    double Pt1=Vh1/(n*t);

                    et_poultry_concentration_ppm.setText(String.valueOf(String.format("%.2f", С1)));
                    et_poultry_the_consumption_of_df_filling_kg.setText(String.valueOf(String.format("%.2f", Vd)));
                    et_poultry_the_cost_of_filling_rub.setText(String.valueOf(String.format("%.2f", P1)));
                    et_poultry_product_consumption_per_day_kg.setText(String.valueOf(String.format("%.2f", Vm)));
                    et_poultry_the_cost_of_day_work_rub.setText(String.valueOf(String.format("%.2f", Vh)));
                    et_poultry_processing_cost_rub_carcass.setText(String.valueOf(String.format("%.2f", Pt)));

                    et_poultry_concentration_ppm1.setText(String.valueOf(String.format("%.2f", С1)));
                    et_poultry_product_consumption_per_day_kg1.setText(String.valueOf(String.format("%.2f", Vm1)));
                    et_poultry_the_cost_of_day_work_rub1.setText(String.valueOf(String.format("%.2f", Vh1)));
                    et_poultry_processing_cost_rub_carcass1.setText(String.valueOf(String.format("%.2f", Pt1)));


                }

            }
        });

        //endregion


        return rootView;
    }


    public void switchVisibility(View view) {
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