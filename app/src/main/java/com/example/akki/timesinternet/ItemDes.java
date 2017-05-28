package com.example.akki.timesinternet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ItemDes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_des);
        ImageView im=(ImageView)findViewById(R.id.imageViewid);
        TextView tvname=(TextView)findViewById(R.id.itemtextid);
        TextView tvprice=(TextView)findViewById(R.id.pricetextid);
        TextView tvdes=(TextView)findViewById(R.id.typetextid);
        String i,t,p,d;

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        getSupportActionBar().setTitle("Item Description");
        tb.setTitleTextColor(0XFFFFFFFF);
        tb.setNavigationIcon(R.mipmap.ic_back);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent=getIntent();
        i=intent.getStringExtra("i");
        t=intent.getStringExtra("t");
        p=intent.getStringExtra("p");
        d=intent.getStringExtra("d");

        Picasso.with(this).load(i).into(im);
        tvname.setText(t);
        tvprice.setText("₹ "+p+"/-");
        tvdes.setText("Type : " + d);
    }
}
