package com.example.akki.timesinternet;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.example.akki.timesinternet.R.id.imageView;

/**
 * Created by Akki on 28-05-2017.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> iname;
    private ArrayList<String> ipic;
    private ArrayList<String> iid;
    private ArrayList<String> iprice;
    Activity activity;

    public CustomAdapter(Context context , ArrayList<String> name, ArrayList<String> ilink, ArrayList<String> iid,ArrayList<String> iprice,Activity activity) {

        this.context = context;
        this.iname=name;
        this.ipic=ilink;
        this.iid=iid;
        this.iprice=iprice;
        this.activity=activity;
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

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        ImageView im;
        TextView tv;


        public ViewHolder(View view) {
            super(view);

            im=(ImageView)view.findViewById(imageView);
            tv=(TextView)view.findViewById(R.id.textView);

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);


        }

        @Override
        public void onClick(View view) {
         //   Toast.makeText(context,iid.get(getPosition()),Toast.LENGTH_SHORT).show();
            DbTaskItemDes db=new DbTaskItemDes(context);
            db.execute(iid.get(getPosition()));
        }

        @Override
        public boolean onLongClick(View view) {
            PopupMenu popupMenu;

            popupMenu = new PopupMenu(context, view);
            popupMenu.inflate(R.menu.menu);
            popupMenu.show();

            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    int mid=item.getItemId();

                    if(mid==R.id.save_pic)
                    {
                      //  Toast.makeText(context,"wait",Toast.LENGTH_SHORT).show();

                        if (ContextCompat.checkSelfPermission(activity,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {

                            Toast.makeText(context,"Permission Not Granted",Toast.LENGTH_SHORT).show();
                        }

                        else {

                            im.setDrawingCacheEnabled(true);
                            Bitmap b = im.getDrawingCache();
                            MediaStore.Images.Media.insertImage(context.getContentResolver(), b, "pic", "des");
                            Toast.makeText(context, "Image Saved", Toast.LENGTH_SHORT).show();
                        }
                    }

                   return false;
                }
            });
            return false;
        }
    }

}
