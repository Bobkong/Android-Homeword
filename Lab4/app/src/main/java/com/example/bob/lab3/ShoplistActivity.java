package com.example.bob.lab3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import widget.OnRecyclerItemClickListener;

/**
 * Created by Bob on 2017/10/16.
 */

public class ShoplistActivity extends AppCompatActivity {
    private String mContent;
    public static RecyclerView recyclerView = null;
    public  static MyRecyclerAdapter recycleAdapter;
    public static List<Data> mData = null;
    public static List<Data> mSearchList = null;
    LinearLayoutManager layoutManager;
    private EditText mSearchView;
    private FloatingActionButton mMainpage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoplist);
        mSearchView  = (EditText)findViewById(R.id.search_view);
        mMainpage = (FloatingActionButton)findViewById(R.id.id_mainpage);
        recyclerView = (RecyclerView)findViewById(R.id.id_recycle);
        initData();
        recycleAdapter = new MyRecyclerAdapter(this,mData,0);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
        recyclerView.addItemDecoration(new MyDecoration(this.getApplicationContext(),MyDecoration.VERTICAL_LIST));
        recyclerView_click();
        mMainpage_click();
        recyclerView.setItemAnimator(new SlideInLeftAnimator());
        EventBus.getDefault().register(this);
    }



    private void mMainpage_click() {
        mMainpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void recyclerView_click() {
        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView){
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                int pos = viewHolder.getAdapterPosition();
                int id = mData.get(pos).getId();
                startActivity(new Intent(ShoplistActivity.this, DetailActivity.class)
                        .putExtra("mId", id));
            }

            @Override
            public void onItemLOngClick(final RecyclerView.ViewHolder viewHolder) {
                final int pos = viewHolder.getAdapterPosition();
                String name = mData.get(pos).getName();
                String message = getString(R.string.if_remove) +" "+ name + "?";
                new AlertDialog.Builder(ShoplistActivity.this).setTitle(R.string.remove_item)
                        .setMessage(message).setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ShoplistActivity.this, R.string.click_cancel, Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton(getString(R.string.commit), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id = mData.get(pos).getId();
                        DataManager.getInstance().deleteShopItem(id);
                        recycleAdapter.notifyItemRemoved(pos);
                        Toast.makeText(ShoplistActivity.this, R.string.click_commit, Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
    }

    private void initData() {
    mData =  DataManager.getInstance().getShoplist();
}

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Data data){
      //
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
