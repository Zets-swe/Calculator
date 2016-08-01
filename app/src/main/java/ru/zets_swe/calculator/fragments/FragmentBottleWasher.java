package ru.zets_swe.calculator.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import ru.zets_swe.calculator.R;

public class FragmentBottleWasher extends Fragment {


    public static final double pi = 3.1415926535;

    Animation anim_show;
    Animation anim_hide;

    //Раздел объявления Layout
    //****************************************
    LinearLayout L_bottle_washer1;
    LinearLayout L_bottle_washer2;


    //Раздел объявления Button
    //****************************************
    Button btn_bottle_washer1;
    Button btn_bottle_washer1_calculate;

    Button btn_bottle_washer2;
    Button btn_bottle_washer2_calculate;


    // Раздел объявления EditText
    //****************************************
    EditText et_bottle_washer1_1_productivity_l_h;
    EditText et_bottle_washer1_the_outer_volume_l;
    EditText et_bottle_washer1_1_time_dosage_s;
    EditText et_bottle_washer1_2_time_dosage_s;
    EditText et_bottle_washer1_the_past_volume_of_concentrate_ml;
    EditText et_bottle_washer1_2_productivity_l_h;

    Spinner sp_bottle_washer2_manufacture;
    EditText et_bottle_washer2_the_amount_of_alkaline_baths_m3;
    EditText et_bottle_washer2_the_volume_of_the_bath_hot_rinsing_m3;
    EditText et_bottle_washer2_the_volume_of_the_bath_warm_rinse_m3;
    EditText et_bottle_washer2_water_consumption_m3_h;
    EditText et_bottle_washer2_the_nominal_performance_of_the_bottle_washer_b_h;
    EditText et_bottle_washer2_the_average_performance_of_the_bottle_washer_b_h;
    EditText et_bottle_washer2_the_transfer_of_the_caustic_in_the_bottle_ml;
    EditText et_bottle_washer2_while_working_in_bmm_month_h;
    EditText et_bottle_washer2_the_number_of_hours_per_shift_h;
    EditText et_bottle_washer2_the_concentration_of_amp_washing;
    EditText et_bottle_washer2_the_concentration_of_antifoam;
    EditText et_bottle_washer2_the_concentration_of_antikiller;
    EditText et_bottle_washer2_the_concentration_of_the_disinfectant;
    EditText et_bottle_washer2_Vu1;
    EditText et_bottle_washer2_Vd1;
    EditText et_bottle_washer2_Vu2;
    EditText et_bottle_washer2_Vd2;
    EditText et_bottle_washer2_Va2;
    EditText et_bottle_washer2_Vp2;
    EditText et_bottle_washer2_Vu3;
    EditText et_bottle_washer2_Vd3;
    EditText et_bottle_washer2_Va3;
    EditText et_bottle_washer2_Vp3;
    EditText et_bottle_washer2_Vu4;
    EditText et_bottle_washer2_Vd4;
    EditText et_bottle_washer2_Va4;
    EditText et_bottle_washer2_Vp4;


    public FragmentBottleWasher() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bottle_washer, container, false);

        anim_show = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.anim_show);
        anim_hide = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.anim_hide);


        //region Время дозировки насоса

        //Раздел инициализации Layout
        //****************************************
        L_bottle_washer1 = (LinearLayout) rootView.findViewById(R.id.L_bottle_washer1);


        // Раздел инициализации Button
        //****************************************
        btn_bottle_washer1 = (Button) rootView.findViewById(R.id.btn_bottle_washer1);
        btn_bottle_washer1_calculate = (Button) rootView.findViewById(R.id.btn_bottle_washer1_calculate);


        // Раздел инициализации EditText и Spinner
        //****************************************
        et_bottle_washer1_1_productivity_l_h = (EditText) rootView.findViewById(R.id.et_bottle_washer1_1_productivity_l_h);
        et_bottle_washer1_the_outer_volume_l = (EditText) rootView.findViewById(R.id.et_bottle_washer1_the_outer_volume_l);
        et_bottle_washer1_1_time_dosage_s = (EditText) rootView.findViewById(R.id.et_bottle_washer1_1_time_dosage_s);
        et_bottle_washer1_2_time_dosage_s = (EditText) rootView.findViewById(R.id.et_bottle_washer1_2_time_dosage_s);
        et_bottle_washer1_the_past_volume_of_concentrate_ml = (EditText) rootView.findViewById(R.id.et_bottle_washer1_the_past_volume_of_concentrate_ml);
        et_bottle_washer1_2_productivity_l_h = (EditText) rootView.findViewById(R.id.et_bottle_washer1_2_productivity_l_h);


        btn_bottle_washer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchVisibility(L_bottle_washer1);

            }
        });

        btn_bottle_washer1_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (false) {
                    Toast.makeText(getActivity(), R.string.note_empty_field, Toast.LENGTH_LONG).show();
                } else {
                    double S = Double.parseDouble(et_bottle_washer1_1_productivity_l_h.getText().toString());
                    double V1 = Double.parseDouble(et_bottle_washer1_the_outer_volume_l.getText().toString());
                    double t = 3600 * V1 / S;
                    double T = Double.parseDouble(et_bottle_washer1_2_time_dosage_s.getText().toString());
                    double V2 = Double.parseDouble(et_bottle_washer1_the_past_volume_of_concentrate_ml.getText().toString());
                    double K = V2 / T * 3600 / 1000;

                    et_bottle_washer1_1_time_dosage_s.setText(String.valueOf(String.format("%.2f", t)));
                    et_bottle_washer1_2_productivity_l_h.setText(String.valueOf(String.format("%.2f", K)));

                }

            }
        });

        //endregion


        //region Расчет добавок в БММ

        //Раздел инициализации Layout
        //****************************************
        L_bottle_washer2 = (LinearLayout) rootView.findViewById(R.id.L_bottle_washer2);


        // Раздел инициализации Button
        //****************************************
        btn_bottle_washer2 = (Button) rootView.findViewById(R.id.btn_bottle_washer2);
        btn_bottle_washer2_calculate = (Button) rootView.findViewById(R.id.btn_bottle_washer2_calculate);


        // Раздел инициализации EditText и Spinner
        //****************************************
        sp_bottle_washer2_manufacture = (Spinner) rootView.findViewById(R.id.sp_bottle_washer2_manufacture);
        et_bottle_washer2_the_amount_of_alkaline_baths_m3 = (EditText) rootView.findViewById(R.id.et_bottle_washer2_the_amount_of_alkaline_baths_m3);
        et_bottle_washer2_the_volume_of_the_bath_hot_rinsing_m3 = (EditText) rootView.findViewById(R.id.et_bottle_washer2_the_volume_of_the_bath_hot_rinsing_m3);
        et_bottle_washer2_the_volume_of_the_bath_warm_rinse_m3 = (EditText) rootView.findViewById(R.id.et_bottle_washer2_the_volume_of_the_bath_warm_rinse_m3);
        et_bottle_washer2_water_consumption_m3_h = (EditText) rootView.findViewById(R.id.et_bottle_washer2_water_consumption_m3_h);
        et_bottle_washer2_the_nominal_performance_of_the_bottle_washer_b_h = (EditText) rootView.findViewById(R.id.et_bottle_washer2_the_nominal_performance_of_the_bottle_washer_b_h);
        et_bottle_washer2_the_average_performance_of_the_bottle_washer_b_h = (EditText) rootView.findViewById(R.id.et_bottle_washer2_the_average_performance_of_the_bottle_washer_b_h);
        et_bottle_washer2_the_transfer_of_the_caustic_in_the_bottle_ml = (EditText) rootView.findViewById(R.id.et_bottle_washer2_the_transfer_of_the_caustic_in_the_bottle_ml);
        et_bottle_washer2_while_working_in_bmm_month_h = (EditText) rootView.findViewById(R.id.et_bottle_washer2_while_working_in_bmm_month_h);
        et_bottle_washer2_the_number_of_hours_per_shift_h = (EditText) rootView.findViewById(R.id.et_bottle_washer2_the_number_of_hours_per_shift_h);
        et_bottle_washer2_the_concentration_of_amp_washing = (EditText) rootView.findViewById(R.id.et_bottle_washer2_the_concentration_of_amp_washing);
        et_bottle_washer2_the_concentration_of_antifoam = (EditText) rootView.findViewById(R.id.et_bottle_washer2_the_concentration_of_antifoam);
        et_bottle_washer2_the_concentration_of_antikiller = (EditText) rootView.findViewById(R.id.et_bottle_washer2_the_concentration_of_antikiller);
        et_bottle_washer2_the_concentration_of_the_disinfectant = (EditText) rootView.findViewById(R.id.et_bottle_washer2_the_concentration_of_the_disinfectant);

        et_bottle_washer2_Vu1 = (EditText) rootView.findViewById(R.id.et_bottle_washer2_Vu1);
        et_bottle_washer2_Vd1 = (EditText) rootView.findViewById(R.id.et_bottle_washer2_Vd1);
        et_bottle_washer2_Vu2 = (EditText) rootView.findViewById(R.id.et_bottle_washer2_Vu2);
        et_bottle_washer2_Vd2 = (EditText) rootView.findViewById(R.id.et_bottle_washer2_Vd2);
        et_bottle_washer2_Va2 = (EditText) rootView.findViewById(R.id.et_bottle_washer2_Va2);
        et_bottle_washer2_Vp2 = (EditText) rootView.findViewById(R.id.et_bottle_washer2_Vp2);
        et_bottle_washer2_Vu3 = (EditText) rootView.findViewById(R.id.et_bottle_washer2_Vu3);
        et_bottle_washer2_Vd3 = (EditText) rootView.findViewById(R.id.et_bottle_washer2_Vd3);
        et_bottle_washer2_Va3 = (EditText) rootView.findViewById(R.id.et_bottle_washer2_Va3);
        et_bottle_washer2_Vp3 = (EditText) rootView.findViewById(R.id.et_bottle_washer2_Vp3);
        et_bottle_washer2_Vu4 = (EditText) rootView.findViewById(R.id.et_bottle_washer2_Vu4);
        et_bottle_washer2_Vd4 = (EditText) rootView.findViewById(R.id.et_bottle_washer2_Vd4);
        et_bottle_washer2_Va4 = (EditText) rootView.findViewById(R.id.et_bottle_washer2_Va4);
        et_bottle_washer2_Vp4 = (EditText) rootView.findViewById(R.id.et_bottle_washer2_Vp4);

        String[] manufacture = {"KHS", "Simonazi", "Krones", "Klinger", "PAC", "Ferrari"};
        ArrayAdapter<String> adapter_manufacture = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, manufacture);
        adapter_manufacture.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_bottle_washer2_manufacture.setAdapter(adapter_manufacture);

        // выделяем элемент
        sp_bottle_washer2_manufacture.setSelection(0);

        btn_bottle_washer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchVisibility(L_bottle_washer2);

            }
        });

        btn_bottle_washer2_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (false) {
                    Toast.makeText(getActivity(), R.string.note_empty_field, Toast.LENGTH_LONG).show();
                } else {
                    double V1 = Double.parseDouble(et_bottle_washer2_the_amount_of_alkaline_baths_m3.getText().toString());
                    double V2 = Double.parseDouble(et_bottle_washer2_the_volume_of_the_bath_hot_rinsing_m3.getText().toString());
                    double V3 = Double.parseDouble(et_bottle_washer2_the_volume_of_the_bath_warm_rinse_m3.getText().toString());
                    double Vw = Double.parseDouble(et_bottle_washer2_water_consumption_m3_h.getText().toString());
                    double S1 = Double.parseDouble(et_bottle_washer2_the_nominal_performance_of_the_bottle_washer_b_h.getText().toString());
                    double S2 = Double.parseDouble(et_bottle_washer2_the_average_performance_of_the_bottle_washer_b_h.getText().toString());
                    double m = Double.parseDouble(et_bottle_washer2_the_transfer_of_the_caustic_in_the_bottle_ml.getText().toString());
                    double T = Double.parseDouble(et_bottle_washer2_while_working_in_bmm_month_h.getText().toString());
                    double T1 = Double.parseDouble(et_bottle_washer2_the_number_of_hours_per_shift_h.getText().toString());
                    double C1 = Double.parseDouble(et_bottle_washer2_the_concentration_of_amp_washing.getText().toString());
                    double C2 = Double.parseDouble(et_bottle_washer2_the_concentration_of_antifoam.getText().toString());
                    double C3 = Double.parseDouble(et_bottle_washer2_the_concentration_of_antikiller.getText().toString());
                    double C4 = Double.parseDouble(et_bottle_washer2_the_concentration_of_the_disinfectant.getText().toString());

                    double m1 = m*S2/1000000;

                    double Vu1 = C1 * V1 * 10;
                    double Vd1 = C2 * V1 * 10;

                    double Vu2 = C1 * m1 / 100000;
                    double Vd2 = C2 * m1 / 100000;
                    double Va2 = Vw * C3 * 10;
                    double Vp2 = Vw * C4 * 10;

                    double Vu3 = Vu2 * T1 / 1000000;
                    double Vd3 = Vd2 * T1 / 1000000;
                    double Va3 = Va2 * T1;
                    double Vp3 = Vp2 * T1;

                    double Vu4 = Vu2 * T / 1000000;
                    double Vd4 = Vd2 * T / 1000000;
                    double Va4 = Va2 * T;
                    double Vp4 = Vp2 * T;



                    et_bottle_washer2_Vu1.setText(String.valueOf(String.format("%.2f", Vu1)));
                    et_bottle_washer2_Vd1.setText(String.valueOf(String.format("%.2f", Vd1)));
                    et_bottle_washer2_Vu2.setText(String.valueOf(String.format("%.2f", Vu2)));
                    et_bottle_washer2_Vd2.setText(String.valueOf(String.format("%.2f", Vd2)));
                    et_bottle_washer2_Va2.setText(String.valueOf(String.format("%.2f", Va2)));
                    et_bottle_washer2_Vp2.setText(String.valueOf(String.format("%.2f", Vp2)));
                    et_bottle_washer2_Vu3.setText(String.valueOf(String.format("%.2f", Vu3)));
                    et_bottle_washer2_Vd3.setText(String.valueOf(String.format("%.2f", Vd3)));
                    et_bottle_washer2_Va3.setText(String.valueOf(String.format("%.2f", Va3)));
                    et_bottle_washer2_Vp3.setText(String.valueOf(String.format("%.2f", Vp3)));
                    et_bottle_washer2_Vu4.setText(String.valueOf(String.format("%.2f", Vu4)));
                    et_bottle_washer2_Vd4.setText(String.valueOf(String.format("%.2f", Vd4)));
                    et_bottle_washer2_Va4.setText(String.valueOf(String.format("%.2f", Va4)));
                    et_bottle_washer2_Vp4.setText(String.valueOf(String.format("%.2f", Vp4)));

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
