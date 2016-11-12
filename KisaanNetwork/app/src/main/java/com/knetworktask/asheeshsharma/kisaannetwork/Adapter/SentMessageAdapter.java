package com.knetworktask.asheeshsharma.kisaannetwork.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.knetworktask.asheeshsharma.kisaannetwork.Model.ContactModel;
import com.knetworktask.asheeshsharma.kisaannetwork.Model.SentMessage;
import com.knetworktask.asheeshsharma.kisaannetwork.R;

import java.util.ArrayList;

/**
 * Created by Asheesh.Sharma on 12-11-2016.
 * Adapter for showing list fo messages sent
 */
public class SentMessageAdapter extends BaseAdapter {
    private ArrayList<SentMessage> arrayList;
    private LayoutInflater layoutInflater;
    public SentMessageAdapter(ArrayList<SentMessage> arrayList, Context context){
        this.arrayList = arrayList;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.item_message, null);
            viewHolder = new ViewHolder();
            viewHolder.firstName = (TextView) convertView.findViewById(R.id.firstName);
            viewHolder.pNumber = (TextView) convertView.findViewById(R.id.pNumber);
            viewHolder.messageText = (TextView) convertView.findViewById(R.id.msgText);
            viewHolder.dateTime = (TextView) convertView.findViewById(R.id.dateTime);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.pNumber.setVisibility(View.GONE);
        Log.d("Check", arrayList.get(position).getmNumber() + "" + arrayList.get(position).getmText() + " " + arrayList.get(position).getmDate());
        viewHolder.firstName.setText(arrayList.get(position).getmName());
        viewHolder.messageText.setText(arrayList.get(position).getmText());
        viewHolder.pNumber.setText(arrayList.get(position).getmNumber());
        viewHolder.dateTime.setText(arrayList.get(position).getmDate());
        return convertView;
    }

    static class ViewHolder{
        TextView firstName, messageText, pNumber, dateTime;
    }
}

