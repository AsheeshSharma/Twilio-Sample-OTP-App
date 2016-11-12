package com.knetworktask.asheeshsharma.kisaannetwork.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.knetworktask.asheeshsharma.kisaannetwork.Data.DatabaseHandler;
import com.knetworktask.asheeshsharma.kisaannetwork.Fragments.Contacts;
import com.knetworktask.asheeshsharma.kisaannetwork.Model.ContactModel;
import com.knetworktask.asheeshsharma.kisaannetwork.Model.SentMessage;
import com.knetworktask.asheeshsharma.kisaannetwork.R;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Asheesh.Sharma on 12-11-2016.
 * Activity responsible for sending OTP to the selected user.
 */
public class SendMessageActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView textView;
    Button button;
    private OkHttpClient mClient = new OkHttpClient();
    private Context mContext;
    Bundle bundle;
    String jsonString;
    String toNumber;
    ContactModel contactModel;
    public static final String TABSELECT = "fromFrag";
    DatabaseHandler db;
    String sample="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_message_layout);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView = (TextView)findViewById(R.id.defaultText);
        button = (Button)findViewById(R.id.sendOtp);
        mContext = getApplicationContext();
        generateRandomNumber(textView);
        db = new DatabaseHandler(this);
        Intent intent = getIntent();
        if(intent!=null){
            bundle = intent.getExtras();
        }
        if(bundle!=null){
            jsonString = bundle.getString(Contacts.CONTACT_OBJECT);
        }
        contactModel = new Gson().fromJson(jsonString,ContactModel.class);
        toNumber = "+91" + contactModel.getContactNumber().toString();
        sample = "Hi" + " " +  contactModel.getFirstName().toString() +  ", " + textView.getText().toString();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOTP();
            }
        });
    }

    //6 digit random number generator
    private void generateRandomNumber(TextView textView){
        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(900000);
        String text = "Your OTP is " + Integer.toString(n);
        textView.setText(text);
    }

    //Send OTP using okhttp
    private void sendOTP(){
        try {
            final ProgressDialog pd = new ProgressDialog(SendMessageActivity.this);
            pd.setMessage("Sending Message");
            pd.show();
            post("https://kisaannetwork.herokuapp.com/sms", new  Callback(){

                @Override
                public void onFailure(Call call, IOException e) {
                    Toast.makeText(getApplicationContext(),"Failed. Please Try again in some time!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(pd!=null){
                                pd.dismiss();
                            }
                            button.setVisibility(View.INVISIBLE);
                            SentMessage sentMessage = new SentMessage(toNumber, contactModel.getFirstName(), sample, getDateTime());
                            db.addContact(sentMessage);
                            Toast.makeText(getApplicationContext(),"SMS Sent!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SendMessageActivity.this, MainActivity.class);
                            intent.putExtra(TABSELECT, true);
                            startActivity(intent);
                        }
                    });
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Building Request
    Call post(String url, Callback callback) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("To", toNumber)
                .add("Body",sample)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Call response = mClient.newCall(request);
        response.enqueue(callback);
        return response;

    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
