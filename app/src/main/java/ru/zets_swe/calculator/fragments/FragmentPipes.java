package ru.zets_swe.calculator.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ru.zets_swe.calculator.R;

public class FragmentPipes extends Fragment{

    private OnFragmentInteractionListener mListener;

    public static final double pi = 3.1415926535;
    String[] diameter_table = {"20", "25", "50", "63", "65", "74", "80", "100", "120", "150", "200"};
    String[] min_flow_table = {"1.7", "2.7", "10.6", "16.8", "1.9", "23.2", "27.1", "42.4", "61.1", "95.4", "169.6"};
    String[] max_flow_table = {"2.3", "3.5", "14.1", "22.4", "23.9", "31.0", "36.2", "56.5", "81.4", "127.2", "226.2"};


    Animation anim_show;
    Animation anim_hide;

    //Раздел объявления Layout
    //****************************************
    LinearLayout L_pipes_flowD;
    LinearLayout L_pipes_flowV;
    LinearLayout L_pipes_flowS;
    LinearLayout L_pipes_flowT;



    //Раздел объявления Button
    //****************************************
    Button btn_pipes_flowD;
    Button btn_pipes_flowD_calculate;

    Button btn_pipes_flowV;
    Button btn_pipes_flowV_calculate;

    Button btn_pipes_flowS;
    Button btn_pipes_flowS_calculate;

    Button btn_pipes_flowT;
    Button btn_pipes_flowT_calculate;


    // Раздел объявления EditText
    //****************************************
    EditText et_pipes_flowD_flow;
    EditText et_pipes_flowD_diameter;
    EditText et_pipes_flowD_m_s;
    EditText et_pipes_flowD_l_min;
    EditText et_pipes_flowD_l_s;

    EditText et_pipes_flowV_diameter;
    EditText et_pipes_flowV_m_s;
    EditText et_pipes_flowV_m3_h;

    EditText et_pipes_flowS_pipe_lenght;
    EditText et_pipes_flowS_diameter;
    EditText et_pipes_flowS_volume;
    EditText et_pipes_flowS_solution_volume;

    EditText et_pipes_flowT_flow;
    EditText et_pipes_flowT_period;
    EditText et_pipes_flowT_reps;
    EditText et_pipes_flowT_amount;





    public FragmentPipes() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pipes, container, false);

        anim_show = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.anim_show);
        anim_hide = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.anim_hide);




        //region Поток от диаметра трубы

        //Раздел инициализации Layout
        //****************************************
        L_pipes_flowD = (LinearLayout) rootView.findViewById(R.id.L_pipes_flowD);


        // Раздел инициализации Button
        //****************************************
        btn_pipes_flowD = (Button) rootView.findViewById(R.id.btn_pipes_flowD);
        btn_pipes_flowD_calculate = (Button) rootView.findViewById(R.id.btn_pipes_flowD_calculate);


        // Раздел инициализации EditText
        //****************************************
        et_pipes_flowD_flow = (EditText) rootView.findViewById(R.id.et_pipes_flowD_flow);
        et_pipes_flowD_diameter = (EditText) rootView.findViewById(R.id.et_pipes_flowD_diameter);
        et_pipes_flowD_m_s = (EditText) rootView.findViewById(R.id.et_pipes_flowD_m_s);
        et_pipes_flowD_l_min = (EditText) rootView.findViewById(R.id.et_pipes_flowD_l_min);
        et_pipes_flowD_l_s = (EditText) rootView.findViewById(R.id.et_pipes_flowD_l_s);



        btn_pipes_flowD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(L_pipes_flowD.getVisibility() == View.VISIBLE){
                    hide(L_pipes_flowD);
                } else {
                    show(L_pipes_flowD);
                }

            }
        });
        btn_pipes_flowD_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(et_pipes_flowD_flow) || isEmpty(et_pipes_flowD_diameter)){
                    Toast.makeText(getActivity(), R.string.note_empty_field, Toast.LENGTH_LONG).show();
                } else {
                    double S = Double.parseDouble(et_pipes_flowD_flow.getText().toString());
                    double D = Double.parseDouble(et_pipes_flowD_diameter.getText().toString());
                    double Vm = S/((D/1000)*(D/1000)*pi/4)/3600;
                    double Vl = S*1000/60;
                    double Vs = S*1000/3600;
                    et_pipes_flowD_m_s.setText(String.valueOf(String.format("%.1f", Vm)));
                    et_pipes_flowD_l_min.setText(String.valueOf(String.format("%.1f", Vl)));
                    et_pipes_flowD_l_s.setText(String.valueOf(String.format("%.0f", Vs)));

                }

            }
        });

        //endregion





        //region Поток от скорости потока

        //Раздел инициализации Layout
        //****************************************
        L_pipes_flowV = (LinearLayout) rootView.findViewById(R.id.L_pipes_flowV);


        // Раздел инициализации Button
        //****************************************
        btn_pipes_flowV = (Button) rootView.findViewById(R.id.btn_pipes_flowV);
        btn_pipes_flowV_calculate = (Button) rootView.findViewById(R.id.btn_pipes_flowV_calculate);


        // Раздел инициализации EditText
        //****************************************
        et_pipes_flowV_diameter = (EditText) rootView.findViewById(R.id.et_pipes_flowV_diameter);
        et_pipes_flowV_m_s = (EditText) rootView.findViewById(R.id.et_pipes_flowV_m_s);
        et_pipes_flowV_m3_h = (EditText) rootView.findViewById(R.id.et_pipes_flowV_m3_h);



        btn_pipes_flowV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(L_pipes_flowV.getVisibility() == View.VISIBLE){
                    hide(L_pipes_flowV);
                } else {
                    show(L_pipes_flowV);
                }

            }
        });
        btn_pipes_flowV_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(et_pipes_flowV_diameter) || isEmpty(et_pipes_flowV_m_s)){
                    Toast.makeText(getActivity(), R.string.note_empty_field, Toast.LENGTH_LONG).show();
                } else {
                    double V = Double.parseDouble(et_pipes_flowV_m_s.getText().toString());
                    double D = Double.parseDouble(et_pipes_flowV_diameter.getText().toString());
                    double F = V*((D/1000)*(D/1000)*pi/4)*3600;

                    et_pipes_flowV_m3_h.setText(String.valueOf(String.format("%.1f", F)));


                }

            }
        });

        ListView lv__pipes_flowV;

        // упаковываем данные в понятную для адаптера структуру
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
                diameter_table.length);
        Map<String, Object> m;
        for (int i = 0; i < diameter_table.length; i++) {
            m = new HashMap<String, Object>();
            m.put("column_1", diameter_table[i]);
            m.put("column_2", min_flow_table[i]);
            m.put("column_3", max_flow_table[i]);
            data.add(m);
        }

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = { "column_1", "column_2", "column_3" };
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.tv_table_row_3_column_1, R.id.tv_table_row_3_column_2, R.id.tv_table_row_3_column_3 };

        // создаем адаптер
        SimpleAdapter sAdapter = new SimpleAdapter(getActivity(), data, R.layout.table_row_3_column, from, to);

        // определяем список и присваиваем ему адаптер
        lv__pipes_flowV = (ListView) rootView.findViewById(R.id.lv__pipes_flowV);

/*        LayoutInflater ltInflater = LayoutInflater.from(getActivity());
        View header = ltInflater.inflate(R.layout.table_row_3_column, null, false);
        TextView tv_table_row_3_column_1 = (TextView) header.findViewById(R.id.tv_table_row_3_column_1);
        tv_table_row_3_column_1.setText(R.string.tv_pipes_flowD_Diameter);
        TextView tv_table_row_3_column_2 = (TextView) header.findViewById(R.id.tv_table_row_3_column_2);
        tv_table_row_3_column_2.setText(R.string.gv_pipes_flowV_header_min_flow_text);
        TextView tv_table_row_3_column_3 = (TextView) header.findViewById(R.id.tv_table_row_3_column_3);
        tv_table_row_3_column_3.setText(R.string.gv_pipes_flowV_header_max_flow_text);
        lv__pipes_flowV.addHeaderView(header);*/
        lv__pipes_flowV.setAdapter(sAdapter);
        setListViewHeightBasedOnChildren(lv__pipes_flowV);


        //endregion





        //region Объем раствора в трубе

        //Раздел инициализации Layout
        //****************************************
        L_pipes_flowS = (LinearLayout) rootView.findViewById(R.id.L_pipes_flowS);


        // Раздел инициализации Button
        //****************************************
        btn_pipes_flowS = (Button) rootView.findViewById(R.id.btn_pipes_flowS);
        btn_pipes_flowS_calculate = (Button) rootView.findViewById(R.id.btn_pipes_flowS_calculate);


        // Раздел инициализации EditText
        //****************************************
        et_pipes_flowS_pipe_lenght = (EditText) rootView.findViewById(R.id.et_pipes_flowS_pipe_lenght);
        et_pipes_flowS_diameter = (EditText) rootView.findViewById(R.id.et_pipes_flowS_diameter);
        et_pipes_flowS_volume = (EditText) rootView.findViewById(R.id.et_pipes_flowS_volume);
        et_pipes_flowS_solution_volume = (EditText) rootView.findViewById(R.id.et_pipes_flowS_solution_volume);



        btn_pipes_flowS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(L_pipes_flowS.getVisibility() == View.VISIBLE){
                    hide(L_pipes_flowS);
                } else {
                    show(L_pipes_flowS);
                }

            }
        });
        btn_pipes_flowS_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(et_pipes_flowS_pipe_lenght) || isEmpty(et_pipes_flowS_diameter)){
                    Toast.makeText(getActivity(), R.string.note_empty_field, Toast.LENGTH_LONG).show();
                } else {
                    double L = Double.parseDouble(et_pipes_flowS_pipe_lenght.getText().toString());
                    double D = Double.parseDouble(et_pipes_flowS_diameter.getText().toString());
                    double Vm = ((D/1000)*(D/1000)*pi/4)*1000;
                    double V = Vm*L;
                    et_pipes_flowS_volume.setText(String.valueOf(String.format("%.1f", Vm)));
                    et_pipes_flowS_solution_volume.setText(String.valueOf(String.format("%.1f", V)));

                }

            }
        });

        //endregion





        //region Расчет объема от потока и времени

        //Раздел инициализации Layout
        //****************************************
        L_pipes_flowT = (LinearLayout) rootView.findViewById(R.id.L_pipes_flowT);

        // Раздел инициализации Button
        //****************************************
        btn_pipes_flowT = (Button) rootView.findViewById(R.id.btn_pipes_flowT);
        btn_pipes_flowT_calculate = (Button) rootView.findViewById(R.id.btn_pipes_flowT_calculate);

        // Раздел инициализации EditText
        //****************************************
        et_pipes_flowT_flow = (EditText) rootView.findViewById(R.id.et_pipes_flowT_flow);
        et_pipes_flowT_period = (EditText) rootView.findViewById(R.id.et_pipes_flowT_period);
        et_pipes_flowT_reps = (EditText) rootView.findViewById(R.id.et_pipes_flowT_reps);
        et_pipes_flowT_amount = (EditText) rootView.findViewById(R.id.et_pipes_flowT_amount);


        btn_pipes_flowT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(L_pipes_flowT.getVisibility() == View.VISIBLE){
                    hide(L_pipes_flowT);
                } else {
                    show(L_pipes_flowT);
                }

            }
        });
        btn_pipes_flowT_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(et_pipes_flowT_flow) || isEmpty(et_pipes_flowT_period) || isEmpty(et_pipes_flowT_reps)){
                    Toast.makeText(getActivity(), R.string.note_empty_field, Toast.LENGTH_LONG).show();
                } else {
                    double S = Double.parseDouble(et_pipes_flowT_flow.getText().toString());
                    double T = Double.parseDouble(et_pipes_flowT_period.getText().toString());
                    double N = Double.parseDouble(et_pipes_flowT_reps.getText().toString());
                    double V = S*1000*T*N/3600;
                    et_pipes_flowT_amount.setText(String.valueOf(String.format("%.1f", V)));

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

    // To animate view slide out from top to bottom
    public void show(View view){
        view.startAnimation(anim_show);
        view.setVisibility(View.VISIBLE);
    }

    // To animate view slide out from bottom to top
    public void hide(View view){
/*        view.startAnimation(anim_hide);
        view.setVisibility(View.INVISIBLE);*/
        view.setVisibility(View.GONE);
    }

    public boolean isEmpty(EditText et){
        if (et.getText().toString().equals("")){
            return true;
        }else {
            return false;
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
