package ru.zets_swe.calculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Zets on 11.08.2016.
 */
public class SprayballsAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<SelectionBall> objects;

    public SprayballsAdapter(Context ctx, ArrayList<SelectionBall> objects) {
        this.ctx = ctx;
        this.objects = objects;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.list_item_sprayball, parent, false);
        }

        SelectionBall p = getSelectionBall(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(R.id.tv_factory)).setText(p.factory);
        ((TextView) view.findViewById(R.id.tv_series)).setText(p.series);
        ((TextView) view.findViewById(R.id.tv_mark)).setText(p.mark);
        ((TextView) view.findViewById(R.id.tv_diameter_min)).setText(p.diameter_min + "");
        ((TextView) view.findViewById(R.id.tv_diameter_max)).setText(p.diameter_max + "");
        ((TextView) view.findViewById(R.id.tv_flow_min)).setText(p.flow_min + "");
        ((TextView) view.findViewById(R.id.tv_flow_max)).setText(p.flow_max + "");
        ((TextView) view.findViewById(R.id.tv_pressure_min)).setText(p.pressure_min + "");
        ((TextView) view.findViewById(R.id.tv_pressure_max)).setText(p.pressure_max + "");


        return view;
    }

    SelectionBall getSelectionBall(int position){
        return (SelectionBall) getItem(position);
    }
}
