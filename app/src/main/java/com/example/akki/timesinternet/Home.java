package com.example.akki.timesinternet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    public ArrayList<String> iname=new ArrayList<String>();
    public ArrayList<String> ipiclink=new ArrayList<String>();
    public ArrayList<String> iid=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        RecyclerView rv=(RecyclerView)findViewById(R.id.recid) ;
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        getSupportActionBar().setTitle("All Items");
        tb.setTitleTextColor(0XFFFFFFFF);

        DbTaskAllItems db=new DbTaskAllItems(this,iname,ipiclink,iid,rv);
        db.execute();





    }
}
