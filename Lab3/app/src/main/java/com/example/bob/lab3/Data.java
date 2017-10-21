package com.example.bob.lab3;

/**
 * Created by Bob on 2017/10/16.
 */

/**
 * Created by Bob on 2017/7/4.
 */

class Data {
    private int mId;
    private String mName;
    private String mPrice;
    private String mType;
    private String mInfo;
    private int mNum;
    private int mPic;
    Data(int id,String name,String price,String type,String info,int pic,int num){
        mId = id;
        mName = name;
        mPrice = price;
        mType = type;
        mInfo = info;
        mPic = pic;
        mNum = num;
    }
    void setName(String name){
        mName = name;
    }
    void setPrice(String price){
        mPrice = price;
    }
    void setNum(int num){
        mNum = num;
    }
    int getId(){
        return mId;
    }
    String getName(){
        return mName;
    }
    String getPrice(){
        return mPrice;
    }
    String getType(){
        return mType;
    }
    String getInfo(){
        return mInfo;
    }
    int getNum(){
        return mNum;
    }
    int getPic(){
        return mPic;
    }
}

