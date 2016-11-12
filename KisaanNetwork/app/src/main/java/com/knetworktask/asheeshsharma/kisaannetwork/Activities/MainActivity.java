package com.knetworktask.asheeshsharma.kisaannetwork.Activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.knetworktask.asheeshsharma.kisaannetwork.Adapter.ViewPageAdapter;
import com.knetworktask.asheeshsharma.kisaannetwork.Fragments.Contacts;
import com.knetworktask.asheeshsharma.kisaannetwork.Fragments.SentMessages;
import com.knetworktask.asheeshsharma.kisaannetwork.R;

//Activity responsible for the tab layout and fragment attachment.
public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        setViewPager(viewPager);
        tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            if(bundle.getBoolean(SendMessageActivity.TABSELECT)){
                TabLayout.Tab tab = tabLayout.getTabAt(1);
                tab.select();
            }
        }
    }

    private void setViewPager(ViewPager viewPager){
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPageAdapter.addFragment(new Contacts(),"Contacts");
        viewPageAdapter.addFragment(new SentMessages(),"Sent Messages");
        viewPager.setAdapter(viewPageAdapter);
    }
}
