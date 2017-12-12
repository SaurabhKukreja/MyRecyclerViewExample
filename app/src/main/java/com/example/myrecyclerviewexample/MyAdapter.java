package com.example.myrecyclerviewexample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by kukresa on 11/27/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Model> mDataset;
    private ImageLoader mImageLoader;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView nameText;
        public TextView phoneText;
        public NetworkImageView image;
        @SuppressLint("WrongViewCast")
        public ViewHolder(View v) {
            super(v);
            nameText = v.findViewById(R.id.name_text);
            phoneText = v.findViewById(R.id.phone_text);
            image = v.findViewById(R.id.imgAvatar);
            image.setDefaultImageResId(R.mipmap.ic_launcher);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),"Position: "+getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Model> myDataset, Context mCOntext) {
        Log.d("TEST",myDataset.get(0).getName());
        mDataset = myDataset;
        mImageLoader = MySingleton.getInstance(mCOntext).getImageLoader();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.mTextView.setText(mDataset.get(position).getName());
        Log.d("TEST","Printing Names onBindView Holder"+mDataset.get(position).getName());
        holder.nameText.setText(mDataset.get(position).getName());
        holder.phoneText.setText(mDataset.get(position).getPhone());
        holder.image.setImageUrl(mDataset.get(position).getImage(),mImageLoader);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
