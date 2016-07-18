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
import android.widget.Toast;

import ru.zets_swe.calculator.R;

public class FragmentPipes extends Fragment{

    private OnFragmentInteractionListener mListener;

    public static final double pi = 3.1415926535;


    Animation anim_show;
    Animation anim_hide;

    //Раздел объявления Layout
    //****************************************
    LinearLayout L_pipes_flowD;


    //Раздел объявления Button
    //****************************************
    Button btn_pipes_flowD;
    Button btn_pipes_flowD_calculate;


    // Раздел объявления EditText
    //****************************************
    EditText et_pipes_flowD_flow;
    EditText et_pipes_flowD_diameter;
    EditText et_pipes_flowD_m_s;
    EditText et_pipes_flowD_l_min;
    EditText et_pipes_flowD_l_s;



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



        return rootView;

/*        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pipes, container, false);*/
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

/*    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
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
}
