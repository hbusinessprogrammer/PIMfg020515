package com.pimfg.www.pimfg020515.userInterface;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.pimfg.www.pimfg020515.R;

import java.util.ArrayList;


/**
 * Created by Hyuk on 2/3/2015.
 */
public class Cart extends Activity {

    ListView listView;
    private String[] states={"AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI",
            "ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO",
            "MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","PA","RI",
            "SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        listView=(ListView)findViewById(R.id.lv_cart);
        final ListAdapterCustom listAdapterCustom=
                new ListAdapterCustom(this, R.layout.part, MainActivity.listItem, true);
        listView.setAdapter(listAdapterCustom);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item=MainActivity.listItem.get(position);
                if(item.isSelected())
                    item.setSelected(false);
                else
                    item.setSelected(true);
                //end if else
                listAdapterCustom.notifyDataSetInvalidated();
            }
        });//end listener
        Button btnAdd=(Button)findViewById(R.id.btn_add_cart);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });//end listener

        final Button btnRemove=(Button)findViewById(R.id.btn_remove_cart);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //remove all selected products
                for(int i=MainActivity.listItem.size()-1; i>=0; i--){
                    if(MainActivity.listItem.get(i).isSelected())
                        MainActivity.listItem.remove(i);
                }//end for
                listAdapterCustom.notifyDataSetChanged();
            }//end onClick
        });//end listener

        Button btnSendOrder=(Button)findViewById(R.id.btn_send_order_cart);
        btnSendOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.listItem.size()>0) {
                    displayCustomerInfo();
                }else{
                    Toast.makeText(getBaseContext(), "Nothing in the cart",
                            Toast.LENGTH_LONG).show();
                }//end if else
            }
        });//end listener
    }//end onCreate()

    private void displayCustomerInfo() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this, R.drawable.button_shape);
        LayoutInflater inflater=(LayoutInflater)getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout=inflater.inflate(R.layout.shipping, null);
        final EditText txtName=(EditText)layout.findViewById(R.id.txt_full_name_shipping);
        txtName.setText(StartActivity.name);
        final EditText txtPhone=(EditText)layout.findViewById(R.id.txt_phone_shipping);
        txtPhone.setText(StartActivity.phone);
        final EditText txtEmail=(EditText)layout.findViewById(R.id.txt_email_shipping);
        txtEmail.setText(StartActivity.email);
        builder.setView(layout);
//        builder.setTitle("Shipping Information");
        builder.setPositiveButton("Next", null);
        final AlertDialog alertDialog=builder.show();
        Button alertButton=alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtName.getText().toString().matches("[a-zA-Z ]+"))
                    txtName.setError("Name Please");

                else if(!txtPhone.getText().toString().matches("^(\\d{7}|\\d{10})"))
                    txtPhone.setError("Phone Please");
                else if(!txtEmail.getText().toString()
                        .matches("\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"))
                    txtEmail.setError("Valid Email Please");
                else {
                    StartActivity.name=txtName.getText().toString();
                    StartActivity.phone=txtPhone.getText().toString();
                    StartActivity.email=txtEmail.getText().toString();
                    displayCustomerInfo2();
                    alertDialog.dismiss();
                }//if else
            }//end onClick()
        });//end Button
    }//end displayCustomerInfo

    private void displayCustomerInfo2() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this, R.drawable.button_shape);
        LayoutInflater inflater=(LayoutInflater)getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout=inflater.inflate(R.layout.shipping2, null);
        final EditText txtAddress=(EditText)layout.findViewById(R.id.txt_address_shipping);
        final EditText txtCity=(EditText)layout.findViewById(R.id.txt_city_shipping);
        Spinner spinner=(Spinner)layout.findViewById(R.id.state_shipping);
        ArrayList<String> arrayListStates=new ArrayList<String>();
        for(String s : states){
            arrayListStates.add(s);
        }//end for
        CustomArrayAdapter arrayAdapter=new CustomArrayAdapter(this,
                R.layout.spinner_item, arrayListStates);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                StartActivity.state=states[parent.getSelectedItemPosition()];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });//end listener
        final EditText txtZip=(EditText)layout.findViewById(R.id.txt_zip_shipping);
        builder.setView(layout);
        builder.setPositiveButton("OK", null);
        final AlertDialog alertDialog=builder.show();
        final Button alertButton=alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtAddress.length()==0)
                    txtAddress.setError("Address Please");
                else if(!txtCity.getText().toString().matches("[a-zA-Z ]+"))
                    txtCity.setError("City Please");
                else if(!txtZip.getText().toString().matches("^(\\d{5})"))
                    txtZip.setError("Zip please");
                else {
                    StartActivity.address=txtAddress.getText().toString();
                    StartActivity.city=txtCity.getText().toString();
                    StartActivity.state=txtCity.getText().toString();
                    StartActivity.zip=txtZip.getText().toString();
                    email();
                    alertDialog.dismiss();
                }//end if else
            }//end onClick()
        });//end Button
    }//end displayCustomerInfo2()


    private void email() {
        Intent intent=new Intent(this, Email.class);
        startActivity(intent);
        finish();
    }
}//end class



















