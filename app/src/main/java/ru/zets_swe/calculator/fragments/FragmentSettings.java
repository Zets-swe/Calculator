package ru.zets_swe.calculator.fragments;


import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Locale;

import ru.zets_swe.calculator.OpenFileDialog;
import ru.zets_swe.calculator.R;
import ru.zets_swe.calculator.Sprayballs;


public class FragmentSettings extends Fragment {

    SharedPreferences sPref;

    Sprayballs sprayballs;

    final String LANGUAGE = "language";
    final String RUS_LANGUAGE = "ru";
    final String EN_LANGUAGE = "en";

    RadioButton rb_sprayballs_rus;
    RadioButton rb_sprayballs_eng;
    RadioButton rb_sprayballs_update_local;
    RadioButton rb_sprayballs_update_web;

    Button btn_sprayballs_set_language;
    Button btn_sprayballs_choose_file;
    Button btn_sprayballs_set_update;

    EditText et_sprayballs_local_file_path;
    EditText et_sprayballs_web_file_path;

    public FragmentSettings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        rb_sprayballs_rus = (RadioButton) rootView.findViewById(R.id.rb_sprayballs_rus);
        rb_sprayballs_eng = (RadioButton) rootView.findViewById(R.id.rb_sprayballs_eng);
        rb_sprayballs_update_local = (RadioButton) rootView.findViewById(R.id.rb_sprayballs_update_local);
        rb_sprayballs_update_web = (RadioButton) rootView.findViewById(R.id.rb_sprayballs_update_web);

        btn_sprayballs_set_language = (Button) rootView.findViewById(R.id.btn_sprayballs_set_language);
        btn_sprayballs_choose_file = (Button) rootView.findViewById(R.id.btn_sprayballs_choose_file);
        btn_sprayballs_set_update = (Button) rootView.findViewById(R.id.btn_sprayballs_set_update);

        et_sprayballs_local_file_path = (EditText) rootView.findViewById(R.id.et_sprayballs_local_file_path);
        et_sprayballs_web_file_path = (EditText) rootView.findViewById(R.id.et_sprayballs_web_file_path);


        sPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String lang = sPref.getString(LANGUAGE, Locale.getDefault().getLanguage());

        if (lang.equals(RUS_LANGUAGE)) {
            rb_sprayballs_rus.setChecked(true);
        } else {
            rb_sprayballs_eng.setChecked(true);
        }

        btn_sprayballs_set_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rb_sprayballs_rus.isChecked()) {
                    setLanguage(RUS_LANGUAGE);
                } else {
                    setLanguage(EN_LANGUAGE);
                }
                getActivity().recreate();

            }
        });

        rb_sprayballs_update_local.setChecked(true);

        btn_sprayballs_choose_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenFileDialog fileDialog = new OpenFileDialog(getActivity())
                        .setFilter(".*\\.csv")
                        .setOpenDialogListener(new OpenFileDialog.OpenDialogListener() {
                            @Override
                            public void OnSelectedFile(String fileName) {
                                et_sprayballs_local_file_path.setText(fileName);
                            }
                        });
                fileDialog.show();

            }
        });

        btn_sprayballs_set_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String file_path;
                if (rb_sprayballs_update_local.isChecked()) {
                    file_path = et_sprayballs_local_file_path.getText().toString();
                    sprayballs = new Sprayballs(getActivity());
                    File source = new File(file_path);
                    File destination = new File(Environment.getExternalStorageDirectory().getPath() + "/sprayballs.csv");
                    try {
                        sprayballs.copyCSV(source, destination);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    file_path = et_sprayballs_web_file_path.getText().toString();
                    Toast.makeText(getActivity(), file_path, Toast.LENGTH_SHORT).show();
                    URL website = null;
                    try {
                        website = new URL(file_path);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    ReadableByteChannel rbc = null;
/*                    try {
                        rbc = Channels.newChannel(website.openStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + "/sprayballs.csv");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {
                        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                }

            }
        });

        rb_sprayballs_update_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb_sprayballs_update_web.setChecked(false);
            }
        });

        rb_sprayballs_update_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb_sprayballs_update_local.setChecked(false);
            }
        });


        return rootView;
    }


    void setLanguage(String lan) {
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(LANGUAGE, lan);
        ed.commit();
        Log.d("Lan=", sPref.getString(LANGUAGE, "").toString());

    }

}
