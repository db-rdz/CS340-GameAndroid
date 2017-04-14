package com.example.ryanblaser.tickettoride.GUI.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.DestCard;
import com.example.ryanblaser.tickettoride.R;

import java.util.List;

/**
 * Created by natha on 3/29/2017.
 */

public class DestinationListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<DestCard> _listDataHeader;
    private List<Integer> _listDataChild;

    public DestinationListAdapter(Context _context, List<DestCard> _listDataHeader, List<Integer> _listDataChild) {
        this._context = _context;
        this._listDataHeader = _listDataHeader;
        this._listDataChild = _listDataChild;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final DestCard destCard = (DestCard) getGroup(childPosition);
        final int points = (Integer) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_route_info, null);
        }

        TextView destinationName = (TextView) convertView
                .findViewById(R.id.destinationName);


        String destinationText = "";
        if (destCard.get_isCompleted()) { //If the route is completed, show it on the player info screen
            destinationText += "*~~ COMPLETED ~~*     ";
        }
        destinationText += "Route: " + destCard.get_destination().getLeft() + " - > " +
                destCard.get_destination().getRight() + ",\t\tPoints: " + points;

        destinationName.setText( destinationText );

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return _listDataHeader.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return 1;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText("Your destination cards");
        lblListHeader.setBackgroundColor(Color.WHITE);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
