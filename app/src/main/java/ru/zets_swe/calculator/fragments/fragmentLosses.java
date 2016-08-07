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

public class FragmentLosses extends Fragment {

    public static final double pi = 3.1415926535;

    Animation anim_show;
    Animation anim_hide;

    //Раздел объявления Layout
    //****************************************
    LinearLayout L_losses1;
    LinearLayout L_losses2;
    LinearLayout L_losses3;
    LinearLayout L_losses4;
    LinearLayout L_losses5;

    LinearLayout[] layouts = new LinearLayout[5];

    //Раздел объявления Button
    //****************************************
    Button btn_losses_pressure_loss;
    Button btn_losses_pressure_loss_calculate;

    Button btn_losses2;
    Button btn_losses2_calculate;

    Button btn_losses3;
    Button btn_losses3_calculate;

    Button btn_losses4;
    Button btn_losses4_calculate;

    Button btn_losses5;
    Button btn_losses5_calculate;

    // Раздел объявления EditText
    //****************************************
    EditText et_losses1_flow_rate;
    EditText et_losses1_pressure;
    EditText et_losses1_pipe_diameter;
    EditText et_losses1_pipe_lenght;
    EditText et_losses1_turns;
    EditText et_losses1_pressure_loss;

    EditText et_losses2_volume_circuit;
    EditText et_losses2_volume_tank_to_the_washing;
    EditText et_losses2_volume_tank_after_washing;
    EditText et_losses2_concentration_beginning_wash;
    EditText et_losses2_concentration_end_wash;
    EditText et_losses2_used_concentrate;
    EditText et_losses2_loss_detergent;

    EditText et_losses3_volume_circuit;
    EditText et_losses3_volume_tank_to_the_washing;
    EditText et_losses3_volume_tank_after_washing;
    EditText et_losses3_concentration_beginning_wash;
    EditText et_losses3_concentration_end_wash;
    EditText et_losses3_used_concentrate;
    EditText et_losses3_loss_detergent;
    Spinner sp_losses3_concentrate;
    EditText et_losses3_loss_of_liquid_caustic_kg;
    EditText et_losses3_loss_of_liquid_caustic_l;

    EditText et_losses4_flow_m3_h;
    EditText et_losses4_period_s;
    EditText et_losses4_number_of_repetition;
    EditText et_losses4_cost_of_water_rub_m3;
    EditText et_losses4_cost_of_drains_rub_m3;
    EditText et_losses4_the_number_of_washes_per_year;
    EditText et_losses4_amount_of_the_losses_l;
    EditText et_losses4_loss_cycle_rub;
    EditText et_losses4_loss_per_year_rub;

    EditText et_losses5_water_volume_m3;
    EditText et_losses5_water_temperature_deg;
    EditText et_losses5_required_water_temperature_deg;
    EditText et_losses5_boiler_productivity;
    Spinner sp_losses5_energy_source;
    EditText et_losses5W_spent_energy_kw;
    EditText et_losses5W_spent_fuel_kg;
    EditText et_losses5flow_rate_m3;
    EditText et_losses5period_min;
    EditText et_losses5_temperature_beginning_heating;
    EditText et_losses5_temperature_of_heating_deg;
    EditText et_losses5H_spent_energy_kw;
    EditText et_losses5H_spent_fuel_kg;
    EditText et_losses5_total_amount_energy_1;
    EditText et_losses5_total_amount_energy_2;
    EditText et_losses5_total_amount_fuel;


    public FragmentLosses() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_losses, container, false);

        anim_show = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.anim_show);
        anim_hide = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.anim_hide);


        //region 1 - Потеря давления

        //Раздел инициализации Layout
        //****************************************
        L_losses1 = (LinearLayout) rootView.findViewById(R.id.L_losses1);


        // Раздел инициализации Button
        //****************************************
        btn_losses_pressure_loss = (Button) rootView.findViewById(R.id.btn_losses_pressure_loss);
        btn_losses_pressure_loss_calculate = (Button) rootView.findViewById(R.id.btn_losses_pressure_loss_calculate);


        // Раздел инициализации EditText и Spinner
        //****************************************
        et_losses1_flow_rate = (EditText) rootView.findViewById(R.id.et_losses1_flow_rate);
        et_losses1_pressure = (EditText) rootView.findViewById(R.id.et_losses1_pressure);
        et_losses1_pipe_diameter = (EditText) rootView.findViewById(R.id.et_losses1_pipe_diameter);
        et_losses1_pipe_lenght = (EditText) rootView.findViewById(R.id.et_losses1_pipe_lenght);
        et_losses1_turns = (EditText) rootView.findViewById(R.id.et_losses1_turns);
        et_losses1_pressure_loss = (EditText) rootView.findViewById(R.id.et_losses1_pressure_loss);


        btn_losses_pressure_loss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchVisibility(L_losses1);

            }
        });

        btn_losses_pressure_loss_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(et_losses1_flow_rate) || isEmpty(et_losses1_pressure) || isEmpty(et_losses1_pipe_diameter) || isEmpty(et_losses1_pipe_lenght) || isEmpty(et_losses1_turns)) {
                    Toast.makeText(getActivity(), R.string.note_empty_field, Toast.LENGTH_LONG).show();
                } else {
                    double V = Double.parseDouble(et_losses1_flow_rate.getText().toString());
                    double H = Double.parseDouble(et_losses1_pressure.getText().toString());
                    double D = Double.parseDouble(et_losses1_pipe_diameter.getText().toString());
                    double L = Double.parseDouble(et_losses1_pipe_lenght.getText().toString());
                    double n = Double.parseDouble(et_losses1_turns.getText().toString());
                    double dP;
                    double x;
                    double x1 = ((V * 1000) / 3600) * 3300000;
                    double x2 = Math.pow(D, 2.63);
                    double x4;
                    if (D < 50) {
                        x4 = 146;
                    } else {
                        x4 = 149;
                    }
                    double x3 = Math.pow((x1 / (x2 * x4)), 1.852);

                    if (D == 0) {
                        x = 0;
                    } else if (D < 70) {
                        x = x3 * 0.0981 * (n + L) / 100;
                    } else {
                        x = x3 * 0.0981 * ((n * 1.5) + L) / 100;
                    }

                    if (x == 0) {
                        dP = 0;
                    } else {
                        dP = H * 0.0981 + x;
                    }

                    et_losses1_pressure_loss.setText(String.valueOf(String.format("%.2f", dP)));

                }

            }
        });

        //endregion


        //region 2 - Расчет потерь моющего средства

        //Раздел инициализации Layout
        //****************************************
        L_losses2 = (LinearLayout) rootView.findViewById(R.id.L_losses2);


        // Раздел инициализации Button
        //****************************************
        btn_losses2 = (Button) rootView.findViewById(R.id.btn_losses2);
        btn_losses2_calculate = (Button) rootView.findViewById(R.id.btn_losses2_calculate);


        // Раздел инициализации EditText и Spinner
        //****************************************
        et_losses2_volume_circuit = (EditText) rootView.findViewById(R.id.et_losses2_volume_circuit);
        et_losses2_volume_tank_to_the_washing = (EditText) rootView.findViewById(R.id.et_losses2_volume_tank_to_the_washing);
        et_losses2_volume_tank_after_washing = (EditText) rootView.findViewById(R.id.et_losses2_volume_tank_after_washing);
        et_losses2_concentration_beginning_wash = (EditText) rootView.findViewById(R.id.et_losses2_concentration_beginning_wash);
        et_losses2_concentration_end_wash = (EditText) rootView.findViewById(R.id.et_losses2_concentration_end_wash);
        et_losses2_used_concentrate = (EditText) rootView.findViewById(R.id.et_losses2_used_concentrate);
        et_losses2_loss_detergent = (EditText) rootView.findViewById(R.id.et_losses2_loss_detergent);


        btn_losses2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchVisibility(L_losses2);
            }
        });

        btn_losses2_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(et_losses2_volume_circuit) || isEmpty(et_losses2_volume_tank_to_the_washing) || isEmpty(et_losses2_volume_tank_after_washing) || isEmpty(et_losses2_concentration_beginning_wash) || isEmpty(et_losses2_concentration_end_wash)) {
                    Toast.makeText(getActivity(), R.string.note_empty_field, Toast.LENGTH_LONG).show();
                } else {
                    double V = Double.parseDouble(et_losses2_volume_circuit.getText().toString());
                    double V1 = Double.parseDouble(et_losses2_volume_tank_to_the_washing.getText().toString());
                    double V2 = Double.parseDouble(et_losses2_volume_tank_after_washing.getText().toString());
                    double C1 = Double.parseDouble(et_losses2_concentration_beginning_wash.getText().toString());
                    double C2 = Double.parseDouble(et_losses2_concentration_end_wash.getText().toString());
                    double P = ((V1 * C1) / 100) - ((V2 * C2) / 100);
                    double L = (P / ((V * C1) / 100)) * 100;

                    et_losses2_used_concentrate.setText(String.valueOf(String.format("%.2f", P)));
                    et_losses2_loss_detergent.setText(String.valueOf(String.format("%.2f", L)));

                }

            }
        });

        //endregion


        //region 3 - Расчет потерь каустика

        //Раздел инициализации Layout
        //****************************************
        L_losses3 = (LinearLayout) rootView.findViewById(R.id.L_losses3);


        // Раздел инициализации Button
        //****************************************
        btn_losses3 = (Button) rootView.findViewById(R.id.btn_losses3);
        btn_losses3_calculate = (Button) rootView.findViewById(R.id.btn_losses3_calculate);


        // Раздел инициализации EditText и Spinner
        //****************************************
        et_losses3_volume_circuit = (EditText) rootView.findViewById(R.id.et_losses3_volume_circuit);
        et_losses3_volume_tank_to_the_washing = (EditText) rootView.findViewById(R.id.et_losses3_volume_tank_to_the_washing);
        et_losses3_volume_tank_after_washing = (EditText) rootView.findViewById(R.id.et_losses3_volume_tank_after_washing);
        et_losses3_concentration_beginning_wash = (EditText) rootView.findViewById(R.id.et_losses3_concentration_beginning_wash);
        et_losses3_concentration_end_wash = (EditText) rootView.findViewById(R.id.et_losses3_concentration_end_wash);
        et_losses3_used_concentrate = (EditText) rootView.findViewById(R.id.et_losses3_used_concentrate);
        et_losses3_loss_detergent = (EditText) rootView.findViewById(R.id.et_losses3_loss_detergent);
        sp_losses3_concentrate = (Spinner) rootView.findViewById(R.id.sp_losses3_concentrate);
        et_losses3_loss_of_liquid_caustic_kg = (EditText) rootView.findViewById(R.id.et_losses3_loss_of_liquid_caustic_kg);
        et_losses3_loss_of_liquid_caustic_l = (EditText) rootView.findViewById(R.id.et_losses3_loss_of_liquid_caustic_l);

        String[] concentrate = {"20", "25", "30", "35", "40", "45", "50"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, concentrate);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_losses3_concentrate.setAdapter(adapter);

        // выделяем элемент
        sp_losses3_concentrate.setSelection(4);

        btn_losses3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchVisibility(L_losses3);
            }
        });

        btn_losses3_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(et_losses3_volume_circuit) || isEmpty(et_losses3_volume_tank_to_the_washing) || isEmpty(et_losses3_volume_tank_after_washing) || isEmpty(et_losses3_concentration_beginning_wash) || isEmpty(et_losses3_concentration_end_wash)) {
                    Toast.makeText(getActivity(), R.string.note_empty_field, Toast.LENGTH_LONG).show();
                } else {
                    double V = Double.parseDouble(et_losses3_volume_circuit.getText().toString());
                    double V1 = Double.parseDouble(et_losses3_volume_tank_to_the_washing.getText().toString());
                    double V2 = Double.parseDouble(et_losses3_volume_tank_after_washing.getText().toString());
                    double C1 = Double.parseDouble(et_losses3_concentration_beginning_wash.getText().toString());
                    double C2 = Double.parseDouble(et_losses3_concentration_end_wash.getText().toString());
                    double P = ((V1 * C1) / 100) - ((V2 * C2) / 100);
                    double L = (P / ((V * C1) / 100)) * 100;

                    double CC = Double.parseDouble(sp_losses3_concentrate.getSelectedItem().toString());
                    double s;
                    int pos = sp_losses3_concentrate.getSelectedItemPosition();
                    switch (pos) {
                        case 0:
                            s = 1.22;
                            break;
                        case 1:
                            s = 1.28;
                            break;
                        case 2:
                            s = 1.33;
                        case 3:
                            s = 1.38;
                        case 4:
                            s = 1.43;
                        case 5:
                            s = 1.48;
                        case 6:
                            s = 1.53;
                        default:
                            s = 1.43;
                    }

                    double Lk = (((V1 * C1) / 100) - ((V2 * C2) / 100) / CC) * 100;
                    double Lm = Lk / s;

                    et_losses3_used_concentrate.setText(String.valueOf(String.format("%.2f", P)));
                    et_losses3_loss_detergent.setText(String.valueOf(String.format("%.2f", L)));
                    et_losses3_loss_of_liquid_caustic_kg.setText(String.valueOf(String.format("%.2f", Lk)));
                    et_losses3_loss_of_liquid_caustic_l.setText(String.valueOf(String.format("%.2f", Lm)));

                }

            }
        });

        //endregion


        //region 4 - Расчет потерь раствора от потока и времени

        //Раздел инициализации Layout
        //****************************************
        L_losses4 = (LinearLayout) rootView.findViewById(R.id.L_losses4);


        // Раздел инициализации Button
        //****************************************
        btn_losses4 = (Button) rootView.findViewById(R.id.btn_losses4);
        btn_losses4_calculate = (Button) rootView.findViewById(R.id.btn_losses4_calculate);


        // Раздел инициализации EditText и Spinner
        //****************************************
        et_losses4_flow_m3_h = (EditText) rootView.findViewById(R.id.et_losses4_flow_m3_h);
        et_losses4_period_s = (EditText) rootView.findViewById(R.id.et_losses4_period_s);
        et_losses4_number_of_repetition = (EditText) rootView.findViewById(R.id.et_losses4_number_of_repetition);
        et_losses4_cost_of_water_rub_m3 = (EditText) rootView.findViewById(R.id.et_losses4_cost_of_water_rub_m3);
        et_losses4_cost_of_drains_rub_m3 = (EditText) rootView.findViewById(R.id.et_losses4_cost_of_drains_rub_m3);
        et_losses4_the_number_of_washes_per_year = (EditText) rootView.findViewById(R.id.et_losses4_the_number_of_washes_per_year);
        et_losses4_amount_of_the_losses_l = (EditText) rootView.findViewById(R.id.et_losses4_amount_of_the_losses_l);
        et_losses4_loss_cycle_rub = (EditText) rootView.findViewById(R.id.et_losses4_loss_cycle_rub);
        et_losses4_loss_per_year_rub = (EditText) rootView.findViewById(R.id.et_losses4_loss_per_year_rub);

        btn_losses4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchVisibility(L_losses4);
            }
        });

        btn_losses4_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(et_losses4_flow_m3_h) || isEmpty(et_losses4_period_s) || isEmpty(et_losses4_number_of_repetition) || isEmpty(et_losses4_cost_of_water_rub_m3) || isEmpty(et_losses4_cost_of_drains_rub_m3) || isEmpty(et_losses4_the_number_of_washes_per_year)) {
                    Toast.makeText(getActivity(), R.string.note_empty_field, Toast.LENGTH_LONG).show();
                } else {
                    double S = Double.parseDouble(et_losses4_flow_m3_h.getText().toString());
                    double T = Double.parseDouble(et_losses4_period_s.getText().toString());
                    double N = Double.parseDouble(et_losses4_number_of_repetition.getText().toString());
                    double p1 = Double.parseDouble(et_losses4_cost_of_water_rub_m3.getText().toString());
                    double p2 = Double.parseDouble(et_losses4_cost_of_drains_rub_m3.getText().toString());
                    double N1 = Double.parseDouble(et_losses4_the_number_of_washes_per_year.getText().toString());

                    double V = S * 1000 * T * N / 3600;
                    double p3 = V * (p1 + p2) / 1000;
                    double p4 = p3 * N1;


                    et_losses4_amount_of_the_losses_l.setText(String.valueOf(String.format("%.0f", V)));
                    et_losses4_loss_cycle_rub.setText(String.valueOf(String.format("%.0f", p3)));
                    et_losses4_loss_per_year_rub.setText(String.valueOf(String.format("%.0f", p4)));

                }

            }
        });

        //endregion


        //region 5 - Расчет тепловых потерь

        //Раздел инициализации Layout
        //****************************************
        L_losses5 = (LinearLayout) rootView.findViewById(R.id.L_losses5);


        // Раздел инициализации Button
        //****************************************
        btn_losses5 = (Button) rootView.findViewById(R.id.btn_losses5);
        btn_losses5_calculate = (Button) rootView.findViewById(R.id.btn_losses5_calculate);


        // Раздел инициализации EditText и Spinner
        //****************************************
        et_losses5_water_volume_m3 = (EditText) rootView.findViewById(R.id.et_losses5_water_volume_m3);
        et_losses5_water_temperature_deg = (EditText) rootView.findViewById(R.id.et_losses5_water_temperature_deg);
        et_losses5_required_water_temperature_deg = (EditText) rootView.findViewById(R.id.et_losses5_required_water_temperature_deg);
        et_losses5_boiler_productivity = (EditText) rootView.findViewById(R.id.et_losses5_boiler_productivity);
        sp_losses5_energy_source = (Spinner) rootView.findViewById(R.id.sp_losses5_energy_source);
        et_losses5W_spent_energy_kw = (EditText) rootView.findViewById(R.id.et_losses5W_spent_energy_kw);
        et_losses5W_spent_fuel_kg = (EditText) rootView.findViewById(R.id.et_losses5W_spent_fuel_kg);
        et_losses5flow_rate_m3 = (EditText) rootView.findViewById(R.id.et_losses5flow_rate_m3);
        et_losses5period_min = (EditText) rootView.findViewById(R.id.et_losses5period_min);
        et_losses5_temperature_beginning_heating = (EditText) rootView.findViewById(R.id.et_losses5_temperature_beginning_heating);
        et_losses5_temperature_of_heating_deg = (EditText) rootView.findViewById(R.id.et_losses5_temperature_of_heating_deg);
        et_losses5H_spent_energy_kw = (EditText) rootView.findViewById(R.id.et_losses5H_spent_energy_kw);
        et_losses5H_spent_fuel_kg = (EditText) rootView.findViewById(R.id.et_losses5H_spent_fuel_kg);
        et_losses5_total_amount_energy_1 = (EditText) rootView.findViewById(R.id.et_losses5_total_amount_energy_1);
        et_losses5_total_amount_energy_2 = (EditText) rootView.findViewById(R.id.et_losses5_total_amount_energy_2);
        et_losses5_total_amount_fuel = (EditText) rootView.findViewById(R.id.et_losses5_total_amount_fuel);


        String[] energy_source = {getString(R.string.text_sp_losses5_energy_sources0), getString(R.string.text_sp_losses5_energy_sources1), getString(R.string.text_sp_losses5_energy_sources2), getString(R.string.text_sp_losses5_energy_sources3), getString(R.string.text_sp_losses5_energy_sources4), getString(R.string.text_sp_losses5_energy_sources5), getString(R.string.text_sp_losses5_energy_sources6), getString(R.string.text_sp_losses5_energy_sources7), getString(R.string.text_sp_losses5_energy_sources8), getString(R.string.text_sp_losses5_energy_sources9), getString(R.string.text_sp_losses5_energy_sources10), getString(R.string.text_sp_losses5_energy_sources11), getString(R.string.text_sp_losses5_energy_sources12), getString(R.string.text_sp_losses5_energy_sources13), getString(R.string.text_sp_losses5_energy_sources14)};
        final String[] energy_cal = {"4780", "7880", "6900", "3500", "8900", "27000", "20500", "4200", "7600", "10025", "10290", "9915", "7500", "8380", "860"};

        ArrayAdapter<String> adapter_energy_source = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, energy_source);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_losses5_energy_source.setAdapter(adapter_energy_source);

        // выделяем элемент
        sp_losses5_energy_source.setSelection(1);

        btn_losses5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchVisibility(L_losses5);
            }
        });

        btn_losses5_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (false) {//TODO: Подумать как сделать проверку
                    Toast.makeText(getActivity(), R.string.note_empty_field, Toast.LENGTH_LONG).show();
                } else {
                    double V = Double.parseDouble(et_losses5_water_volume_m3.getText().toString());
                    double T1 = Double.parseDouble(et_losses5_water_temperature_deg.getText().toString());
                    double T2 = Double.parseDouble(et_losses5_required_water_temperature_deg.getText().toString());
                    double m = Double.parseDouble(et_losses5_boiler_productivity.getText().toString());
                    int pos = sp_losses5_energy_source.getSelectedItemPosition();
                    double Kl = Double.parseDouble(energy_cal[pos]);
                    double E1 = (100 * 1000 * V * (T2 - T1)) / m;
                    double M1 = E1 / Kl;

                    double P = Double.parseDouble(et_losses5flow_rate_m3.getText().toString());
                    double t = Double.parseDouble(et_losses5period_min.getText().toString());
                    double Tn = Double.parseDouble(et_losses5_temperature_beginning_heating.getText().toString());
                    double Tp = Double.parseDouble(et_losses5_temperature_of_heating_deg.getText().toString());
                    double E2 = (100 * 16.667 * P * t * (Tp - Tn)) / m;
                    double M2 = E2 / Kl;

                    double E = E1 + E2; //Ккал
                    double Ed = (E / 4.1840) / 1000; //МДж
                    double M = M1 + M2; //кг

                    et_losses5W_spent_energy_kw.setText(String.valueOf(String.format("%.2f", E1)));
                    et_losses5W_spent_fuel_kg.setText(String.valueOf(String.format("%.2f", M1)));
                    et_losses5H_spent_energy_kw.setText(String.valueOf(String.format("%.2f", E2)));
                    et_losses5H_spent_fuel_kg.setText(String.valueOf(String.format("%.2f", M2)));
                    et_losses5_total_amount_energy_1.setText(String.valueOf(String.format("%.2f", E)));
                    et_losses5_total_amount_energy_2.setText(String.valueOf(String.format("%.2f", Ed)));
                    et_losses5_total_amount_fuel.setText(String.valueOf(String.format("%.2f", M)));

                }

            }
        });

        //endregion

        layouts[0] = L_losses1;
        layouts[1] = L_losses2;
        layouts[2] = L_losses3;
        layouts[3] = L_losses4;
        layouts[4] = L_losses5;

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
