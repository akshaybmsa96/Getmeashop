package com.example.akki.timesinternet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Akki on 28-05-2017.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> iname;
    private ArrayList<String> ipic;
    private ArrayList<String> iid;

    public CustomAdapter(Context context , ArrayList<String> name, ArrayList<String> ilink, ArrayList<String> iid) {

        this.context = context;
        this.iname=name;
        this.ipic=ilink;
        this.iid=iid;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

     holder.tv.setText(iname.get(position));
        //Toast.makeText(context,name.get(position),Toast.LENGTH_SHORT).show();
        Picasso.with(context).load(ipic.get(position)).into(holder.im);

    }

    @Override
    public int getItemCount() {

        return iname.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView im;
        TextView tv;

        public ViewHolder(View view) {
            super(view);

            im=(ImageView)view.findViewById(R.id.imageView);
            tv=(TextView)view.findViewById(R.id.textView);

            view.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
         //   Toast.makeText(context,iid.get(getPosition()),Toast.LENGTH_SHORT).show();
            DbTaskItemDes db=new DbTaskItemDes(context);
            db.execute(iid.get(getPosition()));
        }
    }

}
