package com.knetworktask.asheeshsharma.kisaannetwork.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.knetworktask.asheeshsharma.kisaannetwork.Model.ContactModel;
import com.knetworktask.asheeshsharma.kisaannetwork.R;

import java.util.ArrayList;

/**
 * Created by Asheesh.Sharma on 12-11-2016.
 * Adapter for items of the contact list
 */
public class ContactListAdapter extends BaseAdapter{
    private ArrayList<ContactModel> arrayList;
    private LayoutInflater layoutInflater;
    public ContactListAdapter(ArrayList<ContactModel> arrayList, Context context){
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
            convertView = layoutInflater.inflate(R.layout.item_contact, null);
            viewHolder = new ViewHolder();
            viewHolder.firstName = (TextView) convertView.findViewById(R.id.firstName);
            viewHolder.LastName = (TextView) convertView.findViewById(R.id.pNumber);
            viewHolder.pNumber = (TextView) convertView.findViewById(R.id.msgText);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.firstName.setText(arrayList.get(position).getFirstName());
        viewHolder.LastName.setText(arrayList.get(position).getLastName());
        viewHolder.pNumber.setText(arrayList.get(position).getContactNumber());
        return convertView;
    }

    static class ViewHolder{
        TextView firstName, LastName, pNumber;
    }
}
