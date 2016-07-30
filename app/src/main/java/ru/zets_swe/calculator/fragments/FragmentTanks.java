package ru.zets_swe.calculator.fragments;

import android.net.Uri;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import ru.zets_swe.calculator.R;

public class FragmentTanks extends Fragment {

    private OnFragmentInteractionListener mListener;

    public static final double pi = 3.1415926535;

    Animation anim_show;
    Animation anim_hide;

    //Раздел объявления Layout
    //****************************************
    LinearLayout L_tanks_flow_wash;
    LinearLayout L_tanks_volume_solution;
    LinearLayout L_tanks3;


    //Раздел объявления Button
    //****************************************
    Button btn_tanks_flow_wash;
    Button btn_tanks_flow_wash_calculate;

    Button btn_tanksF_volume_solution;
    Button btn_tanksF_volume_solution_calculate;

    Button btn_tanks3;
    Button btn_tanks3_calculate;


    // Раздел объявления EditText и Spinner
    //****************************************
    EditText et_tanksF_diameter;
    EditText et_tanks_angle_spray;
    Spinner sp_tanks_pollution_level;
    EditText et_tanks_estimated_flow;

    EditText et_tanksV_diameter;
    EditText et_tanks_cone_height;
    EditText et_tanks_cylinder_height;
    EditText et_tanks_volume_solution;

    EditText et_tanks3_volume_tank;
    EditText et_tanks3_atmospheric_pressure;
    EditText et_tanks3_pressure_in_tank;
    EditText et_tanks3_concentration_naoh;
    EditText et_tanks3_quantity_of_caustic_beats;
    EditText et_tanks3_net_impact;
    EditText et_tanks3_temperature_in_tank;
    EditText et_tanks3_pressure_loss;
    EditText et_tanks3_residual_pressure;
    EditText et_tanks3_loss_CO2;


    public FragmentTanks() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tanks, container, false);

        anim_show = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.anim_show);
        anim_hide = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.anim_hide);


        //region Поток на мойку танка

        //Раздел инициализации Layout
        //****************************************
        L_tanks_flow_wash = (LinearLayout) rootView.findViewById(R.id.L_tanks_flow_wash);


        // Раздел инициализации Button
        //****************************************
        btn_tanks_flow_wash = (Button) rootView.findViewById(R.id.btn_tanks_flow_wash);
        btn_tanks_flow_wash_calculate = (Button) rootView.findViewById(R.id.btn_tanks_flow_wash_calculate);


        // Раздел инициализации EditText и Spinner
        //****************************************
        et_tanksF_diameter = (EditText) rootView.findViewById(R.id.et_tanksF_diameter);
        et_tanks_angle_spray = (EditText) rootView.findViewById(R.id.et_tanks_angle_spray);
        sp_tanks_pollution_level = (Spinner) rootView.findViewById(R.id.sp_tanks_pollution_level);
        et_tanks_estimated_flow = (EditText) rootView.findViewById(R.id.et_tanks_estimated_flow);


        String[] pollution_level = {getString(R.string.pollution_level_easy), getString(R.string.pollution_level_medium), getString(R.string.pollution_level_hard)};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, pollution_level);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tanks_pollution_level.setAdapter(adapter);

        // выделяем элемент
        sp_tanks_pollution_level.setSelection(0);

/*          //Установка размера шрифта для выпадающего списка
            sp_tanks_pollution_level.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                ((TextView) parent.getChildAt(0)).setTextSize(10);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });*/


        btn_tanks_flow_wash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchVisibility(L_tanks_flow_wash);

            }
        });

        btn_tanks_flow_wash_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(et_tanksF_diameter) || isEmpty(et_tanks_angle_spray)){
                    Toast.makeText(getActivity(), R.string.note_empty_field, Toast.LENGTH_LONG).show();
                } else {
                    double D = Double.parseDouble(et_tanksF_diameter.getText().toString());
                    double A = Double.parseDouble(et_tanks_angle_spray.getText().toString());

                    double F1;
                    int pos = sp_tanks_pollution_level.getSelectedItemPosition();
                    switch (pos)  {
                        case 0:
                            F1 = 27*(1/(1-((A-180)/(180*4))))*D*pi*60/1000;
                            break;
                        case 1:
                            F1 = 30*(1/(1-((A-180)/(180*4))))*D*pi*60/1000;
                            break;
                        case 2:
                            F1 = 32*(1/(1-((A-180)/(180*4))))*D*pi*60/1000;
                        default:
                            F1 = 32*(1/(1-((A-180)/(180*4))))*D*pi*60/1000;
                    }

                    et_tanks_estimated_flow.setText(String.valueOf(String.format("%.1f", F1)));

                }

            }
        });

        //endregion



        //region Расчет объема раствора в танке
        //Раздел инициализации Layout
        //****************************************
        L_tanks_volume_solution = (LinearLayout) rootView.findViewById(R.id.L_tanks_volume_solution);


        // Раздел инициализации Button
        //****************************************
        btn_tanksF_volume_solution = (Button) rootView.findViewById(R.id.btn_tanksF_volume_solution);
        btn_tanksF_volume_solution_calculate = (Button) rootView.findViewById(R.id.btn_tanksF_volume_solution_calculate);


        // Раздел инициализации EditText и Spinner
        //****************************************
        et_tanksV_diameter = (EditText) rootView.findViewById(R.id.et_tanksV_diameter);
        et_tanks_cone_height = (EditText) rootView.findViewById(R.id.et_tanks_cone_height);
        et_tanks_cylinder_height = (EditText) rootView.findViewById(R.id.et_tanks_cylinder_height);
        et_tanks_volume_solution = (EditText) rootView.findViewById(R.id.et_tanks_volume_solution);


        btn_tanksF_volume_solution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchVisibility(L_tanks_volume_solution);

            }
        });

        btn_tanksF_volume_solution_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(et_tanksV_diameter) || isEmpty(et_tanks_cone_height) || isEmpty(et_tanks_cylinder_height)){
                    Toast.makeText(getActivity(), R.string.note_empty_field, Toast.LENGTH_LONG).show();
                } else {
                    double D = Double.parseDouble(et_tanksV_diameter.getText().toString());
                    double H1 = Double.parseDouble(et_tanks_cone_height.getText().toString());
                    double H2 = Double.parseDouble(et_tanks_cylinder_height.getText().toString());

                    double V = (D*D)*0.001571+pi*D*H2*0.002+0.002*0.785398*D*(Math.sqrt((D*D+H1*H1)));

                    et_tanks_volume_solution.setText(String.valueOf(String.format("%.3f", V)));

                }

            }
        });

        //endregion



        //region Расчет углекислоты

        //Раздел инициализации Layout
        //****************************************
        L_tanks3 = (LinearLayout) rootView.findViewById(R.id.L_tanks3);


        // Раздел инициализации Button
        //****************************************
        btn_tanks3 = (Button) rootView.findViewById(R.id.btn_tanks3);
        btn_tanks3_calculate = (Button) rootView.findViewById(R.id.btn_tanks3_calculate);


        // Раздел инициализации EditText и Spinner
        //****************************************
        et_tanks3_volume_tank = (EditText) rootView.findViewById(R.id.et_tanks3_volume_tank);
        et_tanks3_atmospheric_pressure = (EditText) rootView.findViewById(R.id.et_tanks3_atmospheric_pressure);
        et_tanks3_pressure_in_tank = (EditText) rootView.findViewById(R.id.et_tanks3_pressure_in_tank);
        et_tanks3_concentration_naoh = (EditText) rootView.findViewById(R.id.et_tanks3_concentration_naoh);
        et_tanks3_quantity_of_caustic_beats = (EditText) rootView.findViewById(R.id.et_tanks3_quantity_of_caustic_beats);
        et_tanks3_net_impact = (EditText) rootView.findViewById(R.id.et_tanks3_net_impact);
        et_tanks3_temperature_in_tank = (EditText) rootView.findViewById(R.id.et_tanks3_temperature_in_tank);
        et_tanks3_pressure_loss = (EditText) rootView.findViewById(R.id.et_tanks3_pressure_loss);
        et_tanks3_residual_pressure = (EditText) rootView.findViewById(R.id.et_tanks3_residual_pressure);
        et_tanks3_loss_CO2 = (EditText) rootView.findViewById(R.id.et_tanks3_loss_CO2);

        btn_tanks3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchVisibility(L_tanks3);
            }
        });

        btn_tanks3_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(et_tanks3_volume_tank) || isEmpty(et_tanks3_atmospheric_pressure) || isEmpty(et_tanks3_pressure_in_tank) || isEmpty(et_tanks3_concentration_naoh) || isEmpty(et_tanks3_quantity_of_caustic_beats) || isEmpty(et_tanks3_net_impact) || isEmpty(et_tanks3_temperature_in_tank)) {
                    Toast.makeText(getActivity(), R.string.note_empty_field, Toast.LENGTH_LONG).show();
                } else {
                    double V = Double.parseDouble(et_tanks3_volume_tank.getText().toString());
                    double P = Double.parseDouble(et_tanks3_atmospheric_pressure.getText().toString());
                    double P1 = Double.parseDouble(et_tanks3_pressure_in_tank.getText().toString());
                    double C = Double.parseDouble(et_tanks3_concentration_naoh.getText().toString());
                    double n = Double.parseDouble(et_tanks3_quantity_of_caustic_beats.getText().toString());
                    double V1 = Double.parseDouble(et_tanks3_net_impact.getText().toString());
                    double t = Double.parseDouble(et_tanks3_temperature_in_tank.getText().toString());

                    double m = C/100*1000*n*V1*100/40;
                    double Pl = m*8.314*(273+t)/V/100000;
                    double Po = P1+P-Pl;
                    double Cl = 44*m/1000;

                    et_tanks3_pressure_loss.setText(String.valueOf(String.format("%.2f", Pl)));
                    et_tanks3_residual_pressure.setText(String.valueOf(String.format("%.2f", Po)));
                    et_tanks3_loss_CO2.setText(String.valueOf(String.format("%.2f", Cl)));

                }

            }
        });

        //endregion


        return rootView;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
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
