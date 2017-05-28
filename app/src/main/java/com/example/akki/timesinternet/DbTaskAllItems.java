package com.example.akki.timesinternet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Akki on 04-03-2017.
 */

public class DbTaskAllItems extends AsyncTask<Void,Void,String> {

    Context ctx;
    String Jo,json_string;
    ProgressDialog mProgress;

    public ArrayList<String> iname;
    public ArrayList<String> ipiclink;
    public ArrayList<String> iid;
    RecyclerView rv ;
    CustomAdapter ca;
    DbTaskAllItems(Context ctx,ArrayList<String> iname,ArrayList<String> ipiclink,ArrayList<String> iid,RecyclerView rv )
    {
        this.ctx=ctx;
        this.iname=iname;
        this.ipiclink=ipiclink;
        this.iid=iid;
        this.rv=rv;
    }

    @Override
    public String doInBackground(Void ...voids) {

        String ip_url="http://calidad.getmeashop.com/calidad/api/v3/product/?format=json&limit=100&f=1%20&fields=i|sc|a|o%20|f|prs&loc_currency=INR";

        try {

            URL url = new URL(ip_url);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            InputStream IS=httpURLConnection.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(IS));
            StringBuilder sb=new StringBuilder();

            while((Jo=br.readLine())!=null)
            {
                sb.append(Jo+"\n");
            }

            br.close();
            IS.close();
            httpURLConnection.disconnect();
            String s=sb.toString().trim();
       //     Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
            return s;

        }

        catch (Exception e) {
            e.printStackTrace();

        }

        return "Error";

    }

    @Override
    public void onPreExecute() {
        super.onPreExecute();

        mProgress = new ProgressDialog(ctx);
        mProgress.setMessage("Loading... ");
        mProgress.show();
        mProgress.setCancelable(false);
    }

    @Override
    public void onPostExecute(String result) {
     //   Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();




            json_string = result;

            JSONObject j;
            JSONArray jsonArray;

            ca = new CustomAdapter(ctx,iname,ipiclink,iid);
         //   ca.clear();
            String t, il ,id;
            int count = 0;
            try {

                j = new JSONObject(json_string);
                jsonArray = j.getJSONArray("objects");

               while (count < jsonArray.length()) {
                   JSONObject jo = jsonArray.getJSONObject(count);
                  t = jo.getString("t");
                   il = jo.getString("i");
                    id= jo.getString("id");

                   //    Toast.makeText(ctx,"" + t + " " + id + " ", Toast.LENGTH_LONG).show();

                    iname.add(t);
                    ipiclink.add(il);
                    iid.add(id);

                    count++;
                }
                rv.setHasFixedSize(true);
                rv.setLayoutManager(new GridLayoutManager(ctx,2));
                rv.setAdapter(ca);
                mProgress.dismiss();


            } catch (JSONException e) {
                e.printStackTrace();
            }
        mProgress.dismiss();
    }
}
