package com.example.bob.lab3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Bob on 2017/10/16.
 */

public class DetailActivity extends AppCompatActivity {
    ImageView mBack,mStar,mShop,mImageView;
    TextView mPrice,mType,mName,mInfo;
    boolean isStar = false;
    int mId,mPic;
    boolean isExist = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mId = getIntent().getIntExtra("mId",0);
        mBack = (ImageView)findViewById(R.id.back);
        mStar = (ImageView)findViewById(R.id.star);
        mShop = (ImageView)findViewById(R.id.shop);
        mPrice = (TextView)findViewById(R.id.price);
        mType = (TextView)findViewById(R.id.type);
        mInfo = (TextView)findViewById(R.id.info);
        mName = (TextView)findViewById(R.id.item_name);
        mImageView = (ImageView) findViewById(R.id.item_pic);
        initView();
        back_click();
        star_click();
        shop_click();
    }

    private void initView() {
        for(int i = 0;i < MainActivity.mData.size();i++){
            if(MainActivity.mData.get(i).getId() == mId){
                mName.setText(MainActivity.mData.get(i).getName());
                mPrice.setText(MainActivity.mData.get(i).getPrice());
                mType.setText(MainActivity.mData.get(i).getType());
                mInfo.setText(MainActivity.mData.get(i).getInfo());
                mPic = MainActivity.mData.get(i).getPic();
                mImageView.setImageDrawable(getResources().getDrawable(mPic));
                isExist = true;
            }
        }
        if(!isExist){
            mName.setText(null);
            mPrice.setText(null);
            mType.setText(null);
            mInfo.setText(null);
            mImageView.setImageDrawable(null);
            Toast.makeText(DetailActivity.this,getString(R.string.remove_info),Toast.LENGTH_SHORT).show();
        }
    }

    private void shop_click() {
        mShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isExist){
                    if(DataManager.getInstance().findShopList(mId)==null) {
                        Data newitem = new Data(mId, mName.getText().toString(), mPrice.getText().toString(), mType.getText().toString(), mInfo.getText().toString(), mPic,1);
                        DataManager.getInstance().addShopItem(newitem);
                        Toast.makeText(DetailActivity.this, getString(R.string.addto_shoplist), Toast.LENGTH_SHORT).show();
                        if(ShoplistActivity.recycleAdapter!=null)
                        ShoplistActivity.recycleAdapter.notifyDataSetChanged();
                    }
                    else{
                        Data item = DataManager.getInstance().findShopList(mId);
                        int num = item.getNum();
                        num++;
                        item.setNum(num);
                        Toast.makeText(DetailActivity.this, getString(R.string.addto_shoplist), Toast.LENGTH_SHORT).show();
                        if(ShoplistActivity.recycleAdapter!=null)
                        ShoplistActivity.recycleAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    private void star_click() {
        mStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isStar){
                    mStar.setImageDrawable(getResources().getDrawable(R.drawable.empty_star));
                }
                else{
                    mStar.setImageDrawable(getResources().getDrawable(R.drawable.full_star));
                }
                isStar = !isStar;
            }
        });
    }

    private void back_click() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
