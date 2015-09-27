package com.pimfg.www.pimfg020515.userInterface;


import java.text.NumberFormat;

/**
 * Created by Hyuk on 2/3/2015.
 */
public class Item {
    String cableNumb, leftConnectorNumb, rightConnectorNumb;
    String dConnectorLeft, dRightAngleLeft, dHeadType1Left, dHeadType2Left, dReversePolarityLeft,
           dConnectorRight, dRightAngleRight, dHeadyType1Right, dHeadType2Right, dReversePolarityRight,
           dCableType;
    int cableLength, qty;
    double price;
    private boolean selected;


    public Item(String cableNumb, String leftConnectorNumb, String rightConnectorNumb,
                int cableLength,
                String dConnectorLeft, String dRightAngleLeft, String dHeadType1Left,
                String dHeadType2Left, String dReversePolarityLeft,
                String dConnectorRight, String dRightAngleRight, String dHeadyType1Right,
                String dHeadType2Right, String dReversePolarityRight,
                String dCableType, double price, int qty){
        this.cableNumb=cableNumb;
        this.leftConnectorNumb=leftConnectorNumb;
        this.rightConnectorNumb=rightConnectorNumb;
        this.cableLength=cableLength;

        this.dConnectorLeft=dConnectorLeft;
        this.dRightAngleLeft=dRightAngleLeft;
        this.dHeadType1Left=dHeadType1Left;
        this.dHeadType2Left=dHeadType2Left;
        this.dReversePolarityLeft=dReversePolarityLeft;

        this.dConnectorRight=dConnectorRight;
        this.dRightAngleRight=dRightAngleRight;
        this.dHeadyType1Right=dHeadyType1Right;
        this.dHeadType2Right=dHeadType2Right;
        this.dReversePolarityRight=dReversePolarityRight;

        this.dCableType=dCableType;
        this.price=price;
        this.qty=qty;
    }//end constructor()

    public int getQty() {
        return qty;
    }

    public String getPrice() {
        NumberFormat cf= NumberFormat.getCurrencyInstance();
        return cf.format(price);
    }

    public String getdCableType() {
        return dCableType;
    }

    public String getdConnectorRight() {
        return getString(dConnectorRight);
    }//end getdConnectorRight()

    public String getdRightAngleRight(){
        return getString(dRightAngleRight);
    }

    public String getdHeadyType1Right() {
        return getString(dHeadyType1Right);
    }

    public String getdHeadType2Right() {
        return getString(dHeadType2Right);
    }

    public String getdReversePolarityRight() {
        return getString(dReversePolarityRight);
    }

    public String getdConnectorLeft() {
        return getString(dConnectorLeft);
    }//end getdConnectorLeft()

    public String getdRightAngleLeft() {
        return getString(dRightAngleLeft);
    }//end getdRightAngleLeft()

    public String getdHeadType1Left() {
        return getString(dHeadType1Left);
    }//end getdHeadType1Left()

    public String getdHeadType2Left() {
        return getString(dHeadType2Left);
    }//end getdHeadType2Left()

    public String getdReversePolarityLeft() {
        return getString(dReversePolarityLeft);
    }//end getdReversePolarityLeft

    public String getCableNumb(){
        return cableNumb;
    }//end getCableNumb()

    public String getLeftConnectorNumb(){
        return leftConnectorNumb;
    }//end getLeftConnectorNumb()

    public String getRightConnectorNumb(){
        return rightConnectorNumb;
    }//end getRightConnectorNumb()

    public String getCableLengthNumb(){
        return String.format("%04d", cableLength);
    }//end getCableLengthNumb()

    public String getCableLength(){
        return String.valueOf(cableLength);
    }//end getCableLeft()

    private String getString(String string) {
        if(string.length()>2)
            return string+" ";
        else
            return "";
    }//end getString

    public boolean isSelected(){
        return selected;
    }//end isSelected
    public void setSelected(boolean selected){
        this.selected=selected;
    }
}//end class



















