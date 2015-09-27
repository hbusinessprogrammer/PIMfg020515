package com.pimfg.www.pimfg020515.userInterface;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Kevin on 2/13/2015.
 */
public class Email extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String str="";
        str+= StartActivity.name + "\n"
                + StartActivity.address + "\n"
                + StartActivity.state + "\n"
                + StartActivity.phone + "\n"
                + "\n\n" + "Purchase Order Request\n";

        for(Item item : MainActivity.listItem){
            str+= "\n"
                    //Part Number
                    + item.getCableNumb() + "-"
                    + item.getLeftConnectorNumb()
                    + item.getRightConnectorNumb() + "-"
                    + item.getCableLengthNumb() + "   "
                    //Description Left
                    + item.getdConnectorLeft() + " "
                    + item.getdRightAngleLeft() + " "
                    + item.getdHeadType1Left() + " "
                    + item.getdHeadType2Left() + " "
                    + item.getdReversePolarityLeft()

                    + " to "
                    //Description Right
                    + item.getdConnectorRight() + " "
                    + item.getdRightAngleRight() + " "
                    + item.getdHeadyType1Right() + " "
                    + item.getdHeadType2Right() + " "
                    + item.getdReversePolarityRight() + " "
                    //Length
                    + item.getCableLength() + "inch "
                    + item.getdCableType() + "   "
                    //Qty and Price
                    + "Qty:" + item.getQty() + " @"
                    + item.getPrice() + "each"
                    +"\n";

        }

        String[] to =
                {"company@email.com"};//Set to PIMFG Email later
        String[] cc = {StartActivity.email};

        sendEmail(to, cc, "The Company Purchase Order Request", str);

        MainActivity.listItem.clear();
        finish();

    }


    //---sends an SMS message to another device---
    private void sendEmail(String[] emailAddresses, String[] carbonCopies,
                           String subject, String message) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        String[] to = emailAddresses;
        String[] cc = carbonCopies;
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_CC, cc);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Select Email Application"));
    }
}