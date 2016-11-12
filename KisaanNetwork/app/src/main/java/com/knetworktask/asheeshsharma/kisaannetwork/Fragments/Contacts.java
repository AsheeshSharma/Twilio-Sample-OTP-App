package com.knetworktask.asheeshsharma.kisaannetwork.Fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telecom.Connection;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.knetworktask.asheeshsharma.kisaannetwork.Activities.ContactDetailActivity;
import com.knetworktask.asheeshsharma.kisaannetwork.Adapter.ContactListAdapter;
import com.knetworktask.asheeshsharma.kisaannetwork.Model.ContactModel;
import com.knetworktask.asheeshsharma.kisaannetwork.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Asheesh.Sharma on 11-11-2016.
 * Fragment responsible for showing contact recipents list.
 */
public class Contacts extends Fragment {
    public static final String CONTACT_OBJECT = "contactObject";
    final String Json = "{\"phoneBook\":{\"data\":[{\"firstName\":\"Asheesh\",\"lastName\":\"Sharma\",\"pnumber\":9999894738},{\"firstName\":\"Aditya\",\"lastName\":\"Sharma\",\"pnumber\":9111011382},{\"firstName\":\"Rohot\",\"lastName\":\"Sharma\",\"pnumber\":9999894738},{\"firstName\":\"Rahul\",\"lastName\":\"Sharma\",\"pnumber\":9999894738},{\"firstName\":\"Asheesh\",\"lastName\":\"Sharma\",\"pnumber\":9999894738},{\"firstName\":\"Abhinav\",\"lastName\":\"Sharma\",\"pnumber\":9999894738},{\"firstName\":\"Priyank\",\"lastName\":\"Sharma\",\"pnumber\":9999894738},{\"firstName\":\"Abhimanyu\",\"lastName\":\"Sharma\",\"pnumber\":9999894738},{\"firstName\":\"Aradhna\",\"lastName\":\"Sharma\",\"pnumber\":9999894738}]}}";
    JSONObject jsonObject;
    public Contacts(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contacts, container, false);
        ArrayList<ContactModel> arrayList = getDataforContactList();
        final ListView listView = (ListView)rootView.findViewById(R.id.listView);
        ContactListAdapter contactListAdapter = new ContactListAdapter( arrayList, getActivity());
        listView.setAdapter(contactListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContactModel contactModel = (ContactModel)listView.getItemAtPosition(position);
                /*Log.d("Contact is",contactModel.toString() + "");*/
                Intent intent = new Intent(getActivity(), ContactDetailActivity.class);
                intent.putExtra(CONTACT_OBJECT, new Gson().toJson(contactModel));
                startActivity(intent);

            }
        });
        return rootView;
    }

    private ArrayList<ContactModel> getDataforContactList(){
        ArrayList<ContactModel> arrayList = new ArrayList<ContactModel>();
        try {
            jsonObject = new JSONObject(Json);
            jsonObject = jsonObject.getJSONObject("phoneBook");
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for(int i = 0; i < jsonArray.length();i++){
                JSONObject model_object = jsonArray.getJSONObject(i);
                ContactModel contactModel = new ContactModel(model_object.getString("firstName"), model_object.getString("lastName"),model_object.getString("pnumber"));
                /*Log.d("Debug", model_object.getString("firstName"));*/
                arrayList.add(contactModel);
            }
            return arrayList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
