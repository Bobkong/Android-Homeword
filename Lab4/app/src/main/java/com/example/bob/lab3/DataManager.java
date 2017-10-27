package com.example.bob.lab3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bob on 2017/10/19.
 */

public class DataManager {
    public static List<Data> mData;
    public static List<Data> shoplist;
    public static List<Data> detail_menu_datas;
    private static DataManager instance = new DataManager();
    public static DataManager getInstance(){
        return instance;
    }

    private DataManager(){
        mData = new ArrayList<>();
        mData.add(new Data(0,"Enchated Forest","¥ 5.00","作者","Johanna Basford",R.drawable.enchatedforest,0));
        mData.add(new Data(1,"Arla Milk","¥ 59.00","产地","德国",R.drawable.arla,0));
        mData.add(new Data(2,"Devondale Milk","¥ 79.00","产地","澳大利亚",R.drawable.devondale,0));
        mData.add(new Data(3,"Kindle Oasis","¥ 2399.00","版本","8GB",R.drawable.kindle,0));
        mData.add(new Data(4,"waitrose 早餐麦片","¥ 179.00","重量","2Kg",R.drawable.waitrose,0));
        mData.add(new Data(5,"Ferrero Rocher","¥ 132.59","重量","300g",R.drawable.ferrero,0));
        mData.add(new Data(6,"Mcvitie's 饼干","¥ 14.90","产地","英国",R.drawable.mcvitie,0));
        mData.add(new Data(7,"Maltesers","¥ 141.43","重量","118g",R.drawable.maltesers,0));
        mData.add(new Data(8,"Lindt","¥ 139.43","重量","249g",R.drawable.lindt,0));
        mData.add(new Data(9,"Borggreve","¥ 28.90","重量","640g",R.drawable.borggreve,0));


        shoplist = new ArrayList<>();
        shoplist.add(new Data(4,"waitrose 早餐麦片","¥ 179.00","重量","2Kg",R.drawable.waitrose,1));
        shoplist.add(new Data(1,"Arla Milk","¥ 59.00","产地","德国",R.drawable.arla,1));

        detail_menu_datas = new ArrayList<>();
        detail_menu_datas.add(new Data("一键下单"));
        detail_menu_datas.add(new Data("分享产品"));
        detail_menu_datas.add(new Data("不感兴趣"));
        detail_menu_datas.add(new Data("查看更多商品促销信息"));

    }
    public List<Data> getmData(){
        return mData;
    }
    public List<Data> getShoplist(){
        return shoplist;
    }
    public List<Data> getDetail_menu_datas(){
        return detail_menu_datas;
    }
    public void addShopItem(Data newItem){
        shoplist.add(newItem);
    }
    public void deletemData(int id){
        for(int i = 0;i < mData.size();i++){
            if(mData.get(i).getId() == id){
                mData.remove(i);
            }
        }
    }
    public void deleteShopItem(int id){
        for(int i = 0;i < shoplist.size();i++){
            if(shoplist.get(i).getId() == id){
                shoplist.remove(i);
            }
        }
    }
    public Data findShopList(int id){
        for(int i = 0;i < shoplist.size();i++){
            if(shoplist.get(i).getId() == id){
                return shoplist.get(i);
            }
        }
        return null;
    }
    public Data findMainItem(int id){
        for(int i = 0;i < mData.size();i++){
            if(mData.get(i).getId() == id){
                return mData.get(i);
            }
        }
        return null;
    }
}
