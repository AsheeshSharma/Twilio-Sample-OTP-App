package com.knetworktask.asheeshsharma.kisaannetwork.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.knetworktask.asheeshsharma.kisaannetwork.Fragments.Contacts;
import com.knetworktask.asheeshsharma.kisaannetwork.Model.ContactModel;
import com.knetworktask.asheeshsharma.kisaannetwork.R;

/**
 * Created by Asheesh.Sharma on 12-11-2016.
 * Contains the Activity responsible for representing details of a contact
 */
public class ContactDetailActivity extends AppCompatActivity {
    TextView mfullName, mcontactNumber;
    Button sendMessage;
    String jsonObject;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_detail);
        mfullName = (TextView)findViewById(R.id.fullName);
        mcontactNumber = (TextView)findViewById(R.id.contactNumber);
        sendMessage = (Button)findViewById(R.id.sendBtn);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            jsonObject = bundle.getString(Contacts.CONTACT_OBJECT);
        }
        final ContactModel contactModel = new Gson().fromJson(jsonObject, ContactModel.class);

        updateUI(contactModel);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_ = new Intent(ContactDetailActivity.this,SendMessageActivity.class );
                intent_.putExtra(Contacts.CONTACT_OBJECT, new Gson().toJson(contactModel));
                startActivity(intent_);
            }
        });
    }

    //Updates the name and number of the selected user.
    private  void updateUI(ContactModel contactModel){
        mfullName.setText(contactModel.getFirstName() + " " + contactModel.getLastName());
        mcontactNumber.setText(contactModel.getContactNumber());
    }
}
