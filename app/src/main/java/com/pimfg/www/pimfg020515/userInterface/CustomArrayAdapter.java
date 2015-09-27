package com.pimfg.www.pimfg020515.userInterface;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.pimfg.www.pimfg020515.R;

import java.util.ArrayList;

/**
 * Created by Hyuk on 2/5/2015.
 */
public class CustomArrayAdapter extends ArrayAdapter<String> {
    Context context;
    ArrayList arrayList;
    public CustomArrayAdapter(Context context, int resource, ArrayList<String> arrayList) {
        super(context, resource, arrayList);
        this.context=context;
        this.arrayList=arrayList;
    }//end constructor

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }//end getDropDownView()

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }//end getView()

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View mySpinner=inflater.inflate(R.layout.spinner_item, parent, false);
        TextView textView=(TextView)mySpinner.findViewById(R.id.lbl_text);
        textView.setText(arrayList.get(position).toString());
        return mySpinner;
    }//end getCustomView()
}//end class




