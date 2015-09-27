package com.pimfg.www.pimfg020515.userInterface;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Hyuk on 11/27/2014.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }//end constructor

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new LeftConnector();
            case 1:
                return new Cable();
            case 2:
                return new RightConnector();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}//end class
