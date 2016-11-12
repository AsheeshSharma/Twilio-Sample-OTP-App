package com.knetworktask.asheeshsharma.kisaannetwork.Fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.knetworktask.asheeshsharma.kisaannetwork.Activities.ContactDetailActivity;
import com.knetworktask.asheeshsharma.kisaannetwork.Adapter.ContactListAdapter;
import com.knetworktask.asheeshsharma.kisaannetwork.Adapter.SentMessageAdapter;
import com.knetworktask.asheeshsharma.kisaannetwork.Data.DatabaseHandler;
import com.knetworktask.asheeshsharma.kisaannetwork.Model.ContactModel;
import com.knetworktask.asheeshsharma.kisaannetwork.Model.SentMessage;
import com.knetworktask.asheeshsharma.kisaannetwork.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Asheesh.Sharma on 11-11-2016.
 * Fragment responsible for showing list of messages sent.
 */
public class SentMessages extends Fragment {
    DatabaseHandler db;
    public SentMessages(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sent_messages, container, false);
        ArrayList<SentMessage> arrayList = getDataforContactList();
        Collections.reverse(arrayList);
        final ListView listView = (ListView)rootView.findViewById(R.id.listView);
        SentMessageAdapter contactListAdapter = new SentMessageAdapter(arrayList, getActivity().getApplicationContext());
        listView.setAdapter(contactListAdapter);
        return rootView;
    }

    private ArrayList<SentMessage> getDataforContactList(){
        ArrayList<SentMessage> arrayList = new ArrayList<>();

        db = new DatabaseHandler(getActivity());
        if(db!=null){
            Log.d("Database Not Null", "Fetching");
            arrayList = (ArrayList<SentMessage>)db.getAllContacts();
        }else{
            Log.d("Log","NUll");
        }

        return arrayList;
    }
}
