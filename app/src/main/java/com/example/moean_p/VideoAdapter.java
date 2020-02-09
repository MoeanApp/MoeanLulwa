package com.example.moean_p;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moean_p.R;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{

    private static final String TAG = "VideoAdapter";

    private ArrayList<String> mImageNames = new ArrayList<>();

    private Context mContext;



    public VideoAdapter(Context context, ArrayList<String> ImageNames) {

        mImageNames = ImageNames;
        mContext = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder:called");
        holder.t1.setText(mImageNames.get(position));
        holder.parentlayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick:clicked on:" + mImageNames.get(position));
                Toast.makeText(mContext, mImageNames.get(position),Toast.LENGTH_SHORT).show();
            }
        });

    }

    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //CircleImageView image;
        TextView t1;
        RelativeLayout parentlayout;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            //image=itemView.findViewById(R.id.image);
            t1 = itemView.findViewById(R.id.image_name);
            parentlayout = itemView.findViewById(R.id.parent_layout);


        }
    }

}
