package com.ych.mall.model;

import android.util.Log;

import com.ych.mall.utils.UserCenter;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ych on 2016/9/5.
 */
public class MallAndTravelModel {
    static String urlGoods = Http.SERVER_URL + "goods.php?action=";
    static String urlTravel = Http.SERVER_URL + "tour.php?action=";
    static String home = Http.SERVER_URL + "home.php?action=";

    public static void searchResult(StringCallback callback, int page) {
        Map<String, String> p = new HashMap<String, String>();
        HttpModel hp = HttpModel.newInstance("");

        callback.onResponse("ok", 0);


        //hp.post(p, callback);
    }

    public static void searchTravelResult(StringCallback callback, int page) {
        Map<String, String> p = new HashMap<String, String>();
        HttpModel hp = HttpModel.newInstance("");

        callback.onResponse("ok", 0);

    }

    static String LEFT_SORT = urlGoods + "top_category";


    //一级分类
    public static void sortLeft(StringCallback callback) {
        HashMap<String, String> map = new HashMap<>();
        HttpModel.newInstance(LEFT_SORT).post(map, callback);
    }

    static String LEFT_SORT_TRAVEL = urlTravel + "tour_top_category";


    //一级分类(旅游)
    public static void sortLeftTravel(StringCallback callback) {
        HashMap<String, String> map = new HashMap<>();
        HttpModel.newInstance(LEFT_SORT_TRAVEL).post(map, callback);
    }

    static String RIGHT_SORT = urlGoods + "two_level_class";


    //二级分类
    public static void sortRight(StringCallback callback, String id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        HttpModel.newInstance(RIGHT_SORT).post(map, callback);
    }

    static String RIGHT_SORT_TRAVEL = urlTravel + "tour_two_level";

    //二级分类(旅游)
    public static void sortRightTravel(StringCallback callback, String id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        HttpModel.newInstance(RIGHT_SORT_TRAVEL).post(map, callback);
    }

    static String GOODS_LIST = urlGoods + "goods_list";

    //商品列表
    public static void goodsList(StringCallback callback, int page, String id) {
        HashMap<String, String> map = new HashMap<>();

        HttpModel.newInstance(GOODS_LIST + "&id=" + id + "&page=" + (page + 1)).post(map, callback);
    }

    static String TRAVEL_LIST = urlTravel + "goods_list";

    //旅游列表
    public static void travelList(StringCallback callback, int page, String id) {
        HashMap<String, String> map = new HashMap<>();
        HttpModel.newInstance(TRAVEL_LIST + "&id=" + id + "&page=" + (page + 1)).post(map, callback);
    }

    static String SHOPCAR_URL = urlGoods + "display_goods_cart";

    //购物车列表
    public static void shopCarList(StringCallback callback, int page) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", UserCenter.getInstance().getCurrentUserId());

        HttpModel.newInstance(SHOPCAR_URL + "&page=" + (page + 1)).post(map, callback);
    }

    static String SHOPCAR_DEL = urlGoods + "del_goods_cart";

    //购物车删除
    public static void shopCarDel(StringCallback callback, String id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("cart_id", id);
        HttpModel.newInstance(SHOPCAR_DEL).post(map, callback);
    }

    static String SHOPCAR_EDIT = urlGoods + "edit_goods_num";

    //增删购物车数量
    public static void shopCarNum(StringCallback callback, String id, String num) {
        HashMap<String, String> map = new HashMap<>();
        map.put("cart_id", id);
        map.put("goods_num", num);
        HttpModel.newInstance(SHOPCAR_EDIT).post(map, callback);
    }


    static String CLEAR_SHOPCAR = urlGoods + "empty_goods_cart";

    //清空购物车
    public static void clearShopCar(StringCallback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", UserCenter.getInstance().getCurrentUserId());
        HttpModel.newInstance(CLEAR_SHOPCAR).post(map, callback);
    }

    static String GOODS_DETAIL = urlGoods + "goods_details";

    //商品详情
    public static void goodsDetail(StringCallback callback, String id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("user_id", UserCenter.getInstance().getCurrentUserId());
        HttpModel.newInstance(GOODS_DETAIL).post(map, callback);
    }

    static String GOODS_SEARCH = home + "goods_search";

    //商品搜索
    public static void goodsSearch(StringCallback callback, String title, int page) {
        HashMap<String, String> map = new HashMap<>();
        map.put("title", title);
        HttpModel.newInstance(GOODS_SEARCH+"&page="+(page+1)).post(map, callback);

    }

    static String ADD_SHOPCAR = urlGoods + "add_goods_cart";

    //添加购物车
    public static void addShopCar(StringCallback callback, String id, String title, String point, String price) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", UserCenter.getInstance().getCurrentUserId());
        map.put("goods_id", id);
        if (title != null)
            map.put("taocan", title);
        else
            map.put("taocan", "");
        map.put("fanli_jifen", point);
        map.put("price_new", price);
        HttpModel.newInstance(ADD_SHOPCAR).post(map, callback);
    }


    static String TRAVEL_DETAIL = urlTravel + "tour_synopsis";

    //旅游详情
    public static void travelDetail(StringCallback callback, String id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("user_id", UserCenter.getInstance().getCurrentUserId());
        HttpModel.newInstance(TRAVEL_DETAIL).post(map, callback);
    }


    static String HOME_MALL_URL = home + "index";

    //商城主页
    public static void homeMall(StringCallback callback, int page) {
        HashMap<String, String> map = new HashMap<>();

        HttpModel.newInstance(HOME_MALL_URL + "&page=" + (page + 1) + "&pagesize=10").post(map, callback);
    }

    static String HOME_TRAVEL_URL=home+"tourism_index";

    public static void homeTravel(StringCallback callback,int page){
        HashMap<String, String> map = new HashMap<>();

        HttpModel.newInstance(HOME_TRAVEL_URL + "&page=" + (page + 1) + "&pagesize=10").post(map, callback);
    }
}



