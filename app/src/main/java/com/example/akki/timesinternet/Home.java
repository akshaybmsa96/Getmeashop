package com.example.akki.timesinternet;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Home extends AppCompatActivity {

    public ArrayList<String> iname = new ArrayList<String>();
    public ArrayList<String> ipiclink = new ArrayList<String>();
    public ArrayList<String> iid = new ArrayList<String>();
    public ArrayList<String> iprice=new ArrayList<String>();
    RecyclerView rv;
    CustomAdapter ca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rv = (RecyclerView) findViewById(R.id.recid);
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        ca = new CustomAdapter(this,iname,ipiclink,iid,iprice,this);

        getSupportActionBar().setTitle("All Items");
        tb.setTitleTextColor(0XFFFFFFFF);

        DbTaskAllItems db = new DbTaskAllItems(this, iname, ipiclink, iid,iprice ,rv,this,ca);
        db.execute();
        registerForContextMenu(rv);


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        101);


            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //granted
                } else {
                    //not granted
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sort_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        int id = item.getItemId();

        if(id==R.id.Alphaas)
        {

            sortbyname(1);
            ca.notifyDataSetChanged();

        }
        if(id==R.id.Alphades)
        {
            sortbyname(2);
            ca.notifyDataSetChanged();
        }
        if(id==R.id.Costas)
            sortbycost(2);
            ca.notifyDataSetChanged();
        {

        }
        if(id==R.id.Costdes)
        {
            sortbycost(1);
            ca.notifyDataSetChanged();
        }

        return super.onOptionsItemSelected(item);
    }

    void sortbyname(int type)
    {
        int i,j;

        if(type==1) {

            for (i = 0; i < iname.size(); i++) {
                for (j = 0; j < iname.size(); j++) {


                    if (iname.get(j).compareTo(iname.get(i))>0) {

                        String temp = iname.get(i);
                        iname.set(i, iname.get(j));
                        iname.set(j, temp);


                        temp = iid.get(i);
                        iid.set(i, iid.get(j));
                        iid.set(j, temp);


                        temp = ipiclink.get(i);
                        ipiclink.set(i, ipiclink.get(j));
                        ipiclink.set(j, temp);


                        temp = iprice.get(i);
                        iprice.set(i, iprice.get(j));
                        iprice.set(j, temp);


                    }
                }
            }
        }

        else if(type==2)
        {
            for (i = 0; i < iname.size(); i++) {
                for (j = 0; j < iname.size(); j++) {

                    if (iname.get(j).compareTo(iname.get(i))<0) {
                        String temp = iname.get(i);
                        iname.set(i, iname.get(j));
                        iname.set(j, temp);


                        temp = iid.get(i);
                        iid.set(i, iid.get(j));
                        iid.set(j, temp);

                        temp = ipiclink.get(i);
                        ipiclink.set(i, ipiclink.get(j));
                        ipiclink.set(j, temp);

                        temp = iprice.get(i);
                        iprice.set(i, iprice.get(j));
                        iprice.set(j, temp);


                    }
                }
            }

        }
    }

    void sortbycost(int type)
    {
        int i,j;

        if(type==1) {

            for (i = 0; i < iprice.size(); i++) {
                for (j = 0; j < iprice.size(); j++) {
                    if (Float.parseFloat(iprice.get(i))>=Float.parseFloat(iprice.get(j))) {
                        String temp = iprice.get(i);
                        iprice.set(i, iprice.get(j));
                        iprice.set(j, temp);


                        temp = iid.get(i);
                        iid.set(i, iid.get(j));
                        iid.set(j, temp);


                        temp = ipiclink.get(i);
                        ipiclink.set(i, ipiclink.get(j));
                        ipiclink.set(j, temp);


                        temp = iname.get(i);
                        iname.set(i, iname.get(j));
                        iname.set(j, temp);


                    }
                }
            }
        }

        else if(type==2) {

            for (i = 0; i < iprice.size(); i++) {
                for (j = 0; j < iprice.size(); j++) {
                    if (Float.parseFloat(iprice.get(i))< Float.parseFloat(iprice.get(j))) {
                        String temp = iprice.get(i);
                        iprice.set(i, iprice.get(j));
                        iprice.set(j, temp);


                        temp = iid.get(i);
                        iid.set(i, iid.get(j));
                        iid.set(j, temp);


                        temp = ipiclink.get(i);
                        ipiclink.set(i, ipiclink.get(j));
                        ipiclink.set(j, temp);


                        temp = iname.get(i);
                        iname.set(i, iname.get(j));
                        iname.set(j, temp);


                    }
                }
            }
        }
    }
}
