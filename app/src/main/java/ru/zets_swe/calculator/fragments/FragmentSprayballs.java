package ru.zets_swe.calculator.fragments;


import android.app.Activity;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ru.zets_swe.calculator.Ball;
import ru.zets_swe.calculator.R;
import ru.zets_swe.calculator.Sprayballs;

public class FragmentSprayballs extends Fragment {


    public FragmentSprayballs() {
    }

    Sprayballs sprayballs;

    public static final double pi = 3.1415926535;

    Animation anim_show;
    Animation anim_hide;

    //Раздел объявления Layout
    //****************************************
    LinearLayout L_sprayballs1;
    LinearLayout L_sprayballs2;

    LinearLayout[] layouts = new LinearLayout[2];

    //Раздел объявления Button
    //****************************************
    Button btn_sprayballs1;
    Button btn_sprayballs1_calculate;

    Button btn_sprayballs2;
    Button btn_sprayballs2_calculate;


    // Раздел объявления EditText и Spinner
    //****************************************
    EditText et_sprayballs_the_depth_of_the_sphere_m;
    EditText et_sprayballs_the_diameter_of_the_tank_m;
    EditText et_sprayballs_the_angle_of_the_spray_deg;
    EditText et_sprayballs_rod_lenhgt_sprayball_m;

    Spinner sp_sprayballs_choose_the_type;
    Spinner sp_sprayballs_specify_the_angle;
    EditText et_sprayballs_diameter_of_the_tank_m;
    EditText et_sprayballs_work_flow_m3_h;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_sprayballs, container, false);

        anim_show = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.anim_show);
        anim_hide = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.anim_hide);

        Activity activity = getActivity();
        sprayballs = new Sprayballs(activity);

        //**Тестовый раздел**


            //*******************

        //region 1 - Потеря давления

        //Раздел инициализации Layout
        //****************************************
        L_sprayballs1 = (LinearLayout) rootView.findViewById(R.id.L_sprayballs1);


        // Раздел инициализации Button
        //****************************************
        btn_sprayballs1 = (Button) rootView.findViewById(R.id.btn_sprayballs1);
        btn_sprayballs1_calculate = (Button) rootView.findViewById(R.id.btn_sprayballs1_calculate);


        // Раздел инициализации EditText и Spinner
        //****************************************
        et_sprayballs_the_depth_of_the_sphere_m = (EditText) rootView.findViewById(R.id.et_sprayballs_the_depth_of_the_sphere_m);
        et_sprayballs_the_diameter_of_the_tank_m = (EditText) rootView.findViewById(R.id.et_sprayballs_the_diameter_of_the_tank_m);
        et_sprayballs_the_angle_of_the_spray_deg = (EditText) rootView.findViewById(R.id.et_sprayballs_the_angle_of_the_spray_deg);
        et_sprayballs_rod_lenhgt_sprayball_m = (EditText) rootView.findViewById(R.id.et_sprayballs_rod_lenhgt_sprayball_m);


        btn_sprayballs1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchVisibility(L_sprayballs1);

            }
        });

        btn_sprayballs1_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(et_sprayballs_the_depth_of_the_sphere_m) || isEmpty(et_sprayballs_the_diameter_of_the_tank_m) || isEmpty(et_sprayballs_the_angle_of_the_spray_deg)) {
                    Toast.makeText(getActivity(), R.string.note_empty_field, Toast.LENGTH_LONG).show();
                } else {
                    double h = Double.parseDouble(et_sprayballs_the_depth_of_the_sphere_m.getText().toString());
                    double D = Double.parseDouble(et_sprayballs_the_diameter_of_the_tank_m.getText().toString());
                    double q = Double.parseDouble(et_sprayballs_the_angle_of_the_spray_deg.getText().toString());

                    double H = h + (D/2)*Math.tan((180-q)/2);

                    et_sprayballs_rod_lenhgt_sprayball_m.setText(String.valueOf(String.format("%.2f", H)));

                }

            }
        });

        //endregion


        //region 2 - Подбор головки

        //Раздел инициализации Layout
        //****************************************
        L_sprayballs2 = (LinearLayout) rootView.findViewById(R.id.L_sprayballs2);


        // Раздел инициализации Button
        //****************************************
        btn_sprayballs2 = (Button) rootView.findViewById(R.id.btn_sprayballs2);
        btn_sprayballs2_calculate = (Button) rootView.findViewById(R.id.btn_sprayballs2_calculate);


        // Раздел инициализации EditText и Spinner
        //****************************************
        sp_sprayballs_choose_the_type = (Spinner) rootView.findViewById(R.id.sp_sprayballs_choose_the_type);
        sp_sprayballs_specify_the_angle = (Spinner) rootView.findViewById(R.id.sp_sprayballs_specify_the_angle);
        et_sprayballs_diameter_of_the_tank_m = (EditText) rootView.findViewById(R.id.et_sprayballs_diameter_of_the_tank_m);
        et_sprayballs_work_flow_m3_h = (EditText) rootView.findViewById(R.id.et_sprayballs_work_flow_m3_h);


        btn_sprayballs2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchVisibility(L_sprayballs2);
            }
        });

        btn_sprayballs2_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*                if (isEmpty(et_sprayballs_the_depth_of_the_sphere_m)) {
                    Toast.makeText(getActivity(), R.string.note_empty_field, Toast.LENGTH_LONG).show();
                } else {
                    double h = Double.parseDouble(et_sprayballs_the_depth_of_the_sphere_m.getText().toString());
                    double D = Double.parseDouble(et_sprayballs_the_diameter_of_the_tank_m.getText().toString());
                    double q = Double.parseDouble(et_sprayballs_the_angle_of_the_spray_deg.getText().toString());

                    double H = h + (D/2)*Math.tan((180-q)/2);

                    et_sprayballs_rod_lenhgt_sprayball_m.setText(String.valueOf(String.format("%.2f", H)));

                }*/

            }
        });

        //endregion


        layouts[0] = L_sprayballs1;
        layouts[1] = L_sprayballs2;

        return rootView;
    }


    public void switchVisibility(View view) {
        for (LinearLayout i : layouts) {
            i.setVisibility(View.GONE);
        }
        view.startAnimation(anim_show);
        view.setVisibility(View.VISIBLE);
    }


    public boolean isEmpty(EditText et) {
        if (et.getText().toString().equals("")) {
            return true;
        } else {
            return false;
        }
    }
}
