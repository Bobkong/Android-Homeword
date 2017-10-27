package com.example.bob.lab3;

import android.app.PendingIntent;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import widget.OnRecyclerItemClickListener;

public class MainActivity extends AppCompatActivity {
    private String mContent;
    private RecyclerView recyclerView = null;
    private MyRecyclerAdapter recycleAdapter;
    public static List<Data> mData = null;
    public static List<Data> mSearchList = null;
    LinearLayoutManager layoutManager;
    private EditText mSearchView;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSearchView = (EditText) findViewById(R.id.search_view);
        recyclerView = (RecyclerView) findViewById(R.id.id_recycle);
        fab = (FloatingActionButton) findViewById(R.id.id_shoplist);
        initData();
        recycleAdapter = new MyRecyclerAdapter(this, mData, 1);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
        recyclerView.addItemDecoration(new MyDecoration(this.getApplicationContext(), MyDecoration.VERTICAL_LIST));
        click_fab();
        send_brodcast();
        recyclerView_click();
        recyclerView.setItemAnimator(new SlideInLeftAnimator());
    }

    private void send_brodcast() {
        Random random = new Random();
        int rand = random.nextInt(mData.size());
        Intent intentBroadcast = new Intent(MyReceiver.ACTION_SUGGEST);
        intentBroadcast.putExtra("num", mData.get(rand).getId());
        sendBroadcast(intentBroadcast);
    }

    private void recyclerView_click() {
        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                int pos = viewHolder.getAdapterPosition();
                int id = mData.get(pos).getId();
                startActivity(new Intent(MainActivity.this, DetailActivity.class)
                        .putExtra("mId", id));
            }

            @Override
            public void onItemLOngClick(final RecyclerView.ViewHolder viewHolder) {
                final int pos = viewHolder.getAdapterPosition();
                String name = mData.get(pos).getName();
                String message = getString(R.string.if_remove_main) + " " + name + "?";
                new AlertDialog.Builder(MainActivity.this).setTitle(R.string.remove_item)
                        .setMessage(message).setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, R.string.click_cancel, Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton(getString(R.string.commit), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id = mData.get(pos).getId();
                        DataManager.getInstance().deletemData(id);
                        recycleAdapter.notifyItemRemoved(pos);
                        Toast.makeText(MainActivity.this, R.string.click_commit, Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
    }

    private void click_fab() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShoplistActivity.class));
            }
        });
    }

    private void initData() {
        mData = new ArrayList<>();
        mData = DataManager.getInstance().getmData();
    }
}
