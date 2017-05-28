package com.example.akki.timesinternet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Akki on 28-05-2017.
 */

public class DbTaskItemDes extends AsyncTask<String , Void , String> {

    Context ctx;
    String Jo,json_string;
    ProgressDialog mProgress;
    DbTaskItemDes(Context ctx)
    {
        this.ctx=ctx;

    }

    @Override
    protected String doInBackground(String... params) {

        String iid=params[0];
        String ip_url="http://calidad.getmeashop.com/calidad/api/v3/product/?format=json&limit=100&f=1%20&fields=i|sc|a|o%20|f|prs|d|p&loc_currency=INR&id="+iid;



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
            //  Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
            return s;

        }

        catch (Exception e) {
            e.printStackTrace();
        }


        return "Error";
    }

    @Override
    protected void onPreExecute() {
        mProgress = new ProgressDialog(ctx);
        mProgress.setMessage("Loading... ");
        mProgress.show();
        mProgress.setCancelable(false);
    }

    @Override
    protected void onPostExecute(String result) {
        json_string=result;

        JSONObject j;
        JSONArray jsonArray;
        int count = 0;
        String d,i,p,t;

        try {

            j = new JSONObject(json_string);
            jsonArray=j.getJSONArray("objects");

            JSONObject jo = jsonArray.getJSONObject(count);

                d = jo.getString("d");
                i = jo.getString("i");
                p= jo.getString("p");
                t= jo.getString("t");

            Intent intent=new Intent(ctx,ItemDes.class);
            intent.putExtra("d",d);
            intent.putExtra("i",i);
            intent.putExtra("p",p);
            intent.putExtra("t",t);
            ctx.startActivity(intent);



           mProgress.dismiss();



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
