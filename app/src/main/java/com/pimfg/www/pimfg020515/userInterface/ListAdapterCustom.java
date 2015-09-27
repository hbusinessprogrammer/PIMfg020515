package com.pimfg.www.pimfg020515.userInterface;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.pimfg.www.pimfg020515.R;

import java.util.List;

/**
 * Created by Hyuk on 2/3/2015.
 */
public class ListAdapterCustom extends ArrayAdapter<Item> {
    Context context;
    ViewHolder viewHolder;
    Item item;
    boolean showCheckBox;
    public ListAdapterCustom(Context context, int resource, List<Item> items, boolean cb) {
        super(context, resource, items);
        this.context=context;
        this.showCheckBox=cb;
    }//end constructor()

    public class ViewHolder{
        TextView lblPartNumb;
        TextView lblDescription;
        TextView lblPrice;
        TextView lblQty;
        CheckBox cbRemove;
    }//end ViewHolder

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        viewHolder=null;
        item=getItem(position);
        LayoutInflater layoutInflater=(LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.part, null);
            viewHolder=new ViewHolder();
            viewHolder.lblPartNumb=(TextView)convertView.findViewById(R.id.lbl_part_number_part);
            viewHolder.lblDescription=(TextView)convertView.findViewById(R.id.lbl_description_part);
            viewHolder.lblPrice=(TextView)convertView.findViewById(R.id.lbl_price_part);
            viewHolder.lblQty=(TextView)convertView.findViewById(R.id.lbl_qty_part);
            viewHolder.cbRemove=(CheckBox)convertView.findViewById(R.id.cb_remove_part);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }//end if else

        viewHolder.lblPartNumb.setText(item.getCableNumb()+"-"+item.getLeftConnectorNumb()
                +item.getRightConnectorNumb()+"-"+item.getCableLengthNumb());
        viewHolder.lblDescription.setText(item.getdConnectorLeft()
                +item.getdRightAngleLeft()+item.getdHeadType1Left()
                +item.getdHeadType2Left()+item.getdReversePolarityLeft()+"\nto\n"
                +item.getdConnectorRight()+item.getdRightAngleRight()
                +item.getdHeadyType1Right()+item.getdHeadType2Right()
                +item.getdReversePolarityRight()+item.getCableLength()+" inches "
                +item.getdCableType());
        viewHolder.lblPrice.setText("Price: "+item.getPrice()+" each");
        viewHolder.lblQty.setText("Qty: "+String.valueOf(item.getQty()));

        if(!showCheckBox){
            viewHolder.cbRemove.setVisibility(View.GONE);
        }else {
            if(item.isSelected())
                viewHolder.cbRemove.setChecked(true);
            else
                viewHolder.cbRemove.setChecked(false);
        }//end outer if

        return convertView;
    }//end getView()

}//end class



















