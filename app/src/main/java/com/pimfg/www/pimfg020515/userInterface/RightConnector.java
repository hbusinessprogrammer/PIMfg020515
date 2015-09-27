package com.pimfg.www.pimfg020515.userInterface;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.pimfg.www.pimfg020515.R;
import com.pimfg.www.pimfg020515.database.DBAdapter;

public class RightConnector extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.right_connector, container, false);
        return view;
    }//end onCreateView()

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final DBAdapter adapter=new DBAdapter(getActivity());
        Spinner spConnector=(Spinner)getActivity().findViewById(R.id.sp_right_connector);
        CustomArrayAdapter arrayAdapter=new CustomArrayAdapter(getActivity(),
                R.layout.spinner_item, adapter.getConnectorType());
        spConnector.setAdapter(arrayAdapter);
        spConnector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.strRightConnector=adapter.getConnectorType().get(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });//end listener

        Spinner spRightAngle=(Spinner)getActivity().findViewById(R.id.sp_right_angle_right);
        final CustomArrayAdapter adRightAngle=new CustomArrayAdapter(getActivity(),
                R.layout.spinner_item, MainActivity.arrayListYesNo);
        spRightAngle.setAdapter(adRightAngle);
        spRightAngle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.strRightRightAngle=MainActivity.arrayListYesNo.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });//end listener

        Spinner spHeadType1=(Spinner)getActivity().findViewById(R.id.sp_head_type_1_right);
        CustomArrayAdapter adHeadType1=new CustomArrayAdapter(getActivity(), R.layout.spinner_item, MainActivity.arrayListMaleFemale);
        spHeadType1.setAdapter(adHeadType1);
        spHeadType1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.strRightHT1=MainActivity.arrayListMaleFemale.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });//end listener

        Spinner spHeadType2=(Spinner)getActivity().findViewById(R.id.sp_head_type_2_right);
        CustomArrayAdapter adHeadType2=new CustomArrayAdapter(getActivity(), R.layout.spinner_item, MainActivity.arrayListHeadType2);
        spHeadType2.setAdapter(adHeadType2);
        spHeadType2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.strRightHT2=MainActivity.arrayListHeadType2.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });//end listener

        Spinner spReversePolarity=(Spinner)getActivity().findViewById(R.id.sp_reverse_polarity_right);
        CustomArrayAdapter adReverse=new CustomArrayAdapter(getActivity(), R.layout.spinner_item, MainActivity.arrayListYesNo);
        spReversePolarity.setAdapter(adReverse);
        spReversePolarity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.strRightReverseP=MainActivity.arrayListYesNo.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });//end listener
    }//end onActivityCreated()

    @Override
    public void onStart() {
        super.onStart();
    }//end onStart()
}//end class
























