package com.pimfg.www.pimfg020515.userInterface;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pimfg.www.pimfg020515.R;
import com.pimfg.www.pimfg020515.database.DBAdapter;

import java.util.ArrayList;


public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

    private ViewPager viewPager;
    private TabsPagerAdapter tabsPagerAdapter;
    private ActionBar actionBar;
    private int mainPosition;

    public static ArrayList<String> arrayListYesNo, arrayListMaleFemale, arrayListHeadType2;
    public static String strLeftConnector="", strLeftRightAngle="", strLeftHT1="", strLeftHT2="", strLeftReverseP="";
    public static String strCableType="";
    public int intCableLength=6;
    public static String strRightConnector="", strRightRightAngle="", strRightHT1="", strRightHT2="", strRightReverseP="";
    public static ArrayList<Item> listItem =new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        arrayListYesNo=new ArrayList<String>();
        arrayListMaleFemale=new ArrayList<String>();
        arrayListHeadType2=new ArrayList<String>();
        arrayListYesNo.add("No");
        arrayListYesNo.add("Yes");
        arrayListMaleFemale.add("Male");
        arrayListMaleFemale.add("Female");
        arrayListHeadType2.add("No Preference");
        arrayListHeadType2.add("Bulkhead");
        arrayListHeadType2.add("2-Hole Mount");
        arrayListHeadType2.add("4-Hole Mount");
        //initialization
        viewPager=(ViewPager)findViewById(R.id.pager);
        actionBar=getActionBar();

        tabsPagerAdapter=new TabsPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(tabsPagerAdapter);
//        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setTitle(Html.fromHtml("<b>&nbsp;&nbsp;&nbsp;&nbsp;PI Custom RF Builder</b>"));

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        //this can be done with array and for loop but it is only three categories
        actionBar.addTab(actionBar.newTab().setText(
                Html.fromHtml("<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Left<br/>Connector</b>"))
                .setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("Cable").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(
                Html.fromHtml("<P>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Right<br/>Connector</p>"))
                .setTabListener(this));
        final Button btnSubmit=(Button)findViewById(R.id.btn_submit_main);
        btnSubmit.setText("Next");
        //Listener
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            //this is for making the tab follow swaps
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
                mainPosition=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(mainPosition==2)
                    btnSubmit.setText("Submit Order");
                else
                    btnSubmit.setText("Next");
            }
        });//end listener


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mainPosition<2)
                    viewPager.setCurrentItem(mainPosition+1);
                else {
                    if(TextUtils.isEmpty(String.valueOf(Cable.txtCableLength.getText().toString()))){
                        viewPager.setCurrentItem(1);
                        Cable.txtCableLength.setError("Please Enter Quantity");
                        return;
                    }//end inner if
                    if(Integer.parseInt(Cable.txtCableLength.getText().toString())<6){
                        viewPager.setCurrentItem(1);
                        Cable.txtCableLength.setError("Minimum 6 inches");
                        return;
                    }//end inner if
                    intCableLength=Integer.parseInt(Cable.txtCableLength.getText().toString());
                    showAlertDialog();

                }//end if else
            }//end onClick()
        });//end listener


    }//end onCreate()

    private void showAlertDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater inflater=(LayoutInflater)getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout=inflater.inflate(R.layout.qty, null);
        final EditText txtQty=(EditText)layout.findViewById(R.id.txt_qty_qty);
        builder.setTitle("Enter Quantity");
        builder.setView(layout);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(TextUtils.isEmpty(txtQty.getText())){
                    txtQty.setError("Enter Quantity");
                    Toast.makeText(getBaseContext(), "Enter Quantity Please", Toast.LENGTH_LONG).show();
                    return;
                }//end if
                int qty=Integer.parseInt(txtQty.getText().toString());
                createLine(qty);
            }//end onClick()
        });//end listener

        AlertDialog alertDialog=builder.show();

    }//end showAlertDialog()

    private void createLine(int qty) {
        DBAdapter adapter=new DBAdapter(this);
        if(strLeftHT2=="No Preference")
            strLeftHT2="";
        if(strRightHT2=="No Preference")
            strRightHT2="";
        if(strLeftRightAngle=="Yes"){
            strLeftRightAngle="Right Angle";
        }else{
            strLeftRightAngle="";
        }//end if else
        if(strRightRightAngle=="Yes"){
            strRightRightAngle="Right Angle";
        }else{
            strRightRightAngle="";
        }//end if else
        if(strLeftReverseP=="Yes"){
            strLeftReverseP="Reverse Polarity";
        }else{
            strLeftReverseP="";
        }//end if else
        if(strRightReverseP=="Yes"){
            strRightReverseP="Reverse Polarity";
        }else{
            strRightReverseP="";
        }//end if else
        listItem.add(new Item(
                adapter.getCableNo(strCableType),
                adapter.getConnectorNo(strLeftConnector),
                adapter.getConnectorNo(strRightConnector),
                intCableLength,

                strLeftConnector,
                strLeftRightAngle,
                strLeftHT1,
                strLeftHT2,
                strLeftReverseP,

                strRightConnector,
                strRightRightAngle,
                strRightHT1,
                strRightHT2,
                strRightReverseP,

                strCableType,
                (adapter.getConnectorPrice(strLeftConnector))
                        + adapter.getConnectorPrice(strRightConnector)
                        + (adapter.getCablePrice(strCableType) * intCableLength),
                qty
        ));//end adding
        Intent intent=new Intent(getBaseContext(), Cart.class);
        startActivity(intent);
    }//end createLine()

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        //this is when the user tab in the tab and views follow
        viewPager.setCurrentItem(tab.getPosition());
    }//end onTabSelected()

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}//end class
