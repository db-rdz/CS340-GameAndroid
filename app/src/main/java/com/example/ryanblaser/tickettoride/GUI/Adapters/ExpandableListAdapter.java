package com.example.ryanblaser.tickettoride.GUI.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.ryanblaser.tickettoride.Client.GameModels.PlayerModel.Player;
import com.example.ryanblaser.tickettoride.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by benjamin on 6/03/17.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader;
    private HashMap<String, Player> _listDataChild;
    private HashMap<String, String> _scoreboard; //TESTING


    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, Player> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

//    //TESTING
//    public ExpandableListAdapter(Context context, List<String> listDataHeader,
//                                 HashMap<String, String> _scoreboard) {
//        this._context = context;
//        this._listDataHeader = listDataHeader;
//        this._scoreboard = _scoreboard;
//    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition));
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final Player childPlayer = (Player) getChild(groupPosition, childPosition);
//        final String player = (String) getChild(groupPosition, childPosition); //TESTING

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView Points = (TextView) convertView
                .findViewById(R.id.points);

        TextView TrainCards = (TextView) convertView
                .findViewById(R.id.trainCards);

        TextView DestinationCards = (TextView) convertView
                .findViewById(R.id.destinationCards);

        String pointsText = "Points: " + String.valueOf(childPlayer.get_points());
        String trainCardsText = "Train Cards: " + String.valueOf(childPlayer.get_noOfTrainCards());
        String destCardsText = "Destination Cards: " + String.valueOf(childPlayer.get_noOfDestCards() );

        Points.setText( pointsText );
        TrainCards.setText( trainCardsText );
        DestinationCards.setText( destCardsText );

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(this._listDataChild.get(this._listDataHeader.get(groupPosition)) != null){
            return 1;
        }
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        Player currentPlayer = _listDataChild.get(headerTitle);
        lblListHeader.setBackgroundColor(currentPlayer.get_playerColor());

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
