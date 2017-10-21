package com.example.bob.lab3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bob on 2017/10/16.
 */

class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>{
    private List<Data> mData = new ArrayList<>();
    int mode = 0;
    private int position;
    @Override
    public void onBindViewHolder(final MyRecyclerAdapter.MyViewHolder holder, int position) {
        if(mode == 0){
            holder.tv.setText(mData.get(position).getName());
            holder.first.setText(mData.get(position).getName().substring(0,1));
            holder.price.setText(mData.get(position).getPrice());
            holder.num.setText("× " + mData.get(position).getNum());
        }
        else{
            holder.tv.setText(mData.get(position).getName());
            holder.first.setText(mData.get(position).getName().substring(0,1));
            holder.price.setText(null);
            holder.num.setText(null);
        }
    }

    public int getItemPosition(){
        return position;
    }

    public void setPosition(int position){
        this.position = position;
    }

    private Context mcontext;
    private LayoutInflater inflater;

    public MyRecyclerAdapter(Context context,List<Data> datas,int mode){
        this.mcontext = context;
        this.mode = mode;
        this.mData = datas;
        inflater = LayoutInflater.from(mcontext);
    }
    /* public void addNote(String note){
         mData.add(0,note);
         notifyItemInserted(0);
     }*/
    public int getItemCount(){
        return mData.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_record, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        TextView first;
        TextView price;
        TextView num;
        public MyViewHolder(View view){
            super(view);
            tv = (TextView)view.findViewById(R.id.id_name);
            first = (TextView)view.findViewById(R.id.id_first);
            price = (TextView)view.findViewById(R.id.id_price);
            num = (TextView)view.findViewById(R.id.id_num);
        }
    }

}
