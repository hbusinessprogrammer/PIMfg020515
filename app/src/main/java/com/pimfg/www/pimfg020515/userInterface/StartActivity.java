package com.pimfg.www.pimfg020515.userInterface;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;

import com.pimfg.www.pimfg020515.R;

/**
 * Created by Hyuk on 2/6/2015.
 */
public class StartActivity extends Activity {
    OwnerInfo ownerInfo;
    public static String name, creditCard, creditCardSecurity,
            email, phone, address, city, state, zip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startpi);
        ownerInfo=new OwnerInfo(this);
        name=ownerInfo.name;
        email=ownerInfo.accountName;
        phone=ownerInfo.phone;
        ImageButton btnStart=(ImageButton)findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });//end listener
    }//end onCreate
}//end class
