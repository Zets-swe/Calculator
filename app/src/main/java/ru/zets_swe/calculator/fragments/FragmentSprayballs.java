package ru.zets_swe.calculator.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ru.zets_swe.calculator.Ball;
import ru.zets_swe.calculator.R;
import ru.zets_swe.calculator.SelectionBall;
import ru.zets_swe.calculator.Sprayballs;
import ru.zets_swe.calculator.SprayballsAdapter;

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

    LinearLayout L_sprayballs2_list;

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


    // Раздел объявления массивов для spinner
    //****************************************
    List<String> types;
    List<String> angle;


    //Раздел объявления ListView
    //****************************************
    ListView lv_sprayballs;
    ArrayList<SelectionBall> selectedSprayballs = new ArrayList<SelectionBall>();
    SprayballsAdapter sprayballsAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_sprayballs, container, false);

        anim_show = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.anim_show);
        anim_hide = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.anim_hide);

        Activity activity = getActivity();
        sprayballs = new Sprayballs(activity);
        sprayballs.init();

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
        L_sprayballs2_list = (LinearLayout) rootView.findViewById(R.id.L_sprayballs2_list);


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


        types = sprayballs.getUniqType();
        ArrayAdapter<String> typesAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, types);
        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_sprayballs_choose_the_type.setAdapter(typesAdapter);

        // Раздел инициализации ListView
        //****************************************
        lv_sprayballs = (ListView) rootView.findViewById(R.id.lv_sprayballs);
        fillData();
        sprayballsAdapter = new SprayballsAdapter(getContext(), selectedSprayballs);
        lv_sprayballs.setAdapter(sprayballsAdapter);
        setListViewHeightBasedOnChildren(lv_sprayballs);



        sp_sprayballs_choose_the_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
/*                List<String> newAngle = sprayballs.getTypeAngles(itemSelected.toString());
                ArrayAdapter<String> angleAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, newAngle);
                angleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_sprayballs_specify_the_angle.setAdapter(angleAdapter);*/

                angle = sprayballs.getTypeAngles(sp_sprayballs_choose_the_type.getSelectedItem().toString());
                ArrayAdapter<String> angleAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, angle);
                angleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_sprayballs_specify_the_angle.setAdapter(angleAdapter);

                Toast.makeText(getActivity(), "Ваш выбор: " + types.get(selectedItemPosition), Toast.LENGTH_SHORT).show();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btn_sprayballs2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchVisibility(L_sprayballs2);
            }
        });

        btn_sprayballs2_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(et_sprayballs_diameter_of_the_tank_m) || isEmpty(et_sprayballs_work_flow_m3_h)) {
                    Toast.makeText(getActivity(), R.string.note_empty_field, Toast.LENGTH_LONG).show();
                } else {
                    String T = sp_sprayballs_choose_the_type.getSelectedItem().toString();
                    String A = sp_sprayballs_specify_the_angle.getSelectedItem().toString();
                    double D = Double.parseDouble(et_sprayballs_diameter_of_the_tank_m.getText().toString());
                    double F = Double.parseDouble(et_sprayballs_work_flow_m3_h.getText().toString());

                    L_sprayballs2_list.startAnimation(anim_show);
                    L_sprayballs2_list.setVisibility(View.VISIBLE);

                    //Toast.makeText(getActivity(), T + " " + A + " " + D + " " + F , Toast.LENGTH_LONG).show();

                }

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


    // генерируем данные для адаптера
    void fillData(){
        for (int i = 1; i <= 20; i++) {
            selectedSprayballs.add(new SelectionBall("Factory " + i, "Series " + i, "Mark " + i, i * 10, i * 10, i * 10, i * 10, i * 10, i * 10));
        }
    }



    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}