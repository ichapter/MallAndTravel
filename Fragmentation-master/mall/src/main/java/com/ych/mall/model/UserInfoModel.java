package com.ych.mall.model;

import com.ych.mall.utils.UserCenter;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;

/**
 * Created by ych on 2016/9/12.
 */
public class UserInfoModel {
    static String url = Http.SERVER_URL + "api.php?action=";

    static String USER_INFO = url + "person_information";

    //获取用户信息
    public static void getUserInfo(String id, StringCallback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        HttpModel.newInstance(USER_INFO).post(map, callback);
    }

    static String CHANGE_NAME = url + "change_name";

    //修改名字
    public static void changeName(String name, StringCallback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", UserCenter.getInstance().getCurrentUserId());
        map.put("realname", name);
        HttpModel.newInstance(CHANGE_NAME).post(map, callback);
    }

    static String CHANGE_ID = url + "change_idcard";

    //修改身份证
    public static void changeID(String id, StringCallback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", UserCenter.getInstance().getCurrentUserId());
        map.put("idcard", id);
        HttpModel.newInstance(CHANGE_ID).post(map, callback);
    }

    static String CHANGE_GENDER = url + "change_sex";

    //修改性别
    public static void changeGender(String gender, StringCallback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", UserCenter.getInstance().getCurrentUserId());
        if (gender.equals("男"))
            gender = "m";
        else
            gender = "w";
        map.put("sex", gender);
        HttpModel.newInstance(CHANGE_GENDER).post(map, callback);
    }

    static String CHANGE_BIRTHDAY = url + "change_birthday";

    //修改生日
    public static void changeBirthday(String birthday, StringCallback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", UserCenter.getInstance().getCurrentUserId());
        map.put("birthday", birthday);
        HttpModel.newInstance(CHANGE_BIRTHDAY).post(map, callback);

    }

    static String ADD_ADDRESS = url + "address_add";

    //添加收货地址
    public static void addAddress(String receiver, String pro,
                                  String city, String dist,
                                  String address, String defaultOrNot,
                                  String phone, String zipCode, StringCallback callback) {

        HashMap<String, String> map = new HashMap<>();
        map.put("id", UserCenter.getInstance().getCurrentUserId());
        map.put("addressrealname", receiver);
        map.put("prov", pro);
        map.put("city", city);
        map.put("dist", dist);
        map.put("address", address);
        map.put("status", defaultOrNot);
        map.put("addressmobile", phone);
        map.put("zipcode", zipCode);
        HttpModel.newInstance(ADD_ADDRESS).post(map, callback);

    }


    static String EDIT_ADDRESS = url + "change_address";

    //修改收货地址
    public static void editAddress(String addID,
                                   String receiver, String pro,
                                   String city, String dist,
                                   String address, String defaultOrNot,
                                   String phone, String zipCode, StringCallback callback) {

        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", UserCenter.getInstance().getCurrentUserId());
        map.put("addressid", addID);
        map.put("addressrealname", receiver);
        map.put("prov", pro);
        map.put("city", city);
        map.put("dist", dist);
        map.put("address", address);
        map.put("status", defaultOrNot);
        map.put("addressmobile", phone);
        map.put("zipcode", zipCode);
        HttpModel.newInstance(EDIT_ADDRESS).post(map, callback);

    }


    static String USER_CENTER = url + "person_center";

    //个人中心
    public static void userCenter(StringCallback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", UserCenter.getInstance().getCurrentUserId());
        HttpModel.newInstance(USER_CENTER).post(map, callback);
    }

    static String ACCOUNT_MANAGE = url + "person_manage";

    //账户管理
    public static void accountManage(StringCallback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", UserCenter.getInstance().getCurrentUserId());
        HttpModel.newInstance(ACCOUNT_MANAGE).post(map, callback);
    }

    static String USER_BANK = url + "person_bankcard";

    //用户银行卡
    public static void userBank(StringCallback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", UserCenter.getInstance().getCurrentUserId());
        HttpModel.newInstance(USER_BANK).post(map, callback);
    }

    static String ADD_BANK = url + "bankcard_add";

    //添加银行卡
    public static void addBank(String belong, String type, String card_num, String realname, String id_card, String mobile, StringCallback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", UserCenter.getInstance().getCurrentUserId());
        map.put("belong", belong);
        map.put("type", type);
        map.put("card_num", card_num);
        map.put("realname", realname);
        map.put("id_card", id_card);
        map.put("mobile", mobile);
        HttpModel.newInstance(ADD_BANK).post(map, callback);
    }

    static String DEL_BANK = url + "del_bankcard";

    //删除银行卡
    public static void delBank(String id, StringCallback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        HttpModel.newInstance(DEL_BANK).post(map, callback);
    }

    static String MY_FOOT = url + "person_footprint";

    //用户足迹
    public static void userFoot(StringCallback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", UserCenter.getInstance().getCurrentUserId());
        HttpModel.newInstance(MY_FOOT).post(map, callback);
    }

    static String DEL_FOOT = url + "del_footprint";

    //删除单个足迹
    public static void delFoot(StringCallback callback,String id){
        HashMap<String,String> map=new HashMap<>();
        map.put("id",id);
        HttpModel.newInstance(DEL_FOOT).post(map,callback);
    }

    static String CLEAR_FOOT = url + "del_footprint";


    //清除数据
    public static void clearFoot(StringCallback callback){
        HashMap<String,String> map=new HashMap<>();
        map.put("id", UserCenter.getInstance().getCurrentUserId());
        HttpModel.newInstance(CLEAR_FOOT).post(map,callback);

    }

    static String COLLECT_URL = url + "person_collect";

    //用户收藏
    public static void userCollect(StringCallback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", UserCenter.getInstance().getCurrentUserId());
        HttpModel.newInstance(COLLECT_URL).post(map, callback);
    }

    //订单
    static void order(StringCallback callback, int page, String url) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", UserCenter.getInstance().getCurrentUserId());
        HttpModel.newInstance(url + "&page=" + (page + 1)).post(map, callback);
    }

    static String ORDER_ALL = url + "order_list";

    //所有订单
    public static void orderAll(StringCallback callback, int page) {
        order(callback, page, ORDER_ALL);
    }

    static String ORDER_PAY = url + "payment_list";

    //待支付订单
    public static void orderPay(StringCallback callback, int page) {
        order(callback, page, ORDER_PAY);
    }

    static String ORDER_RECEIPT = url + "receipt_goods";

    //待收货订单
    public static void orderAccept(StringCallback callback, int page) {
        order(callback, page, ORDER_RECEIPT);
    }

    static String ORDER_COMMENT = url + "payments_waite";

    //待评论订单
    public static void orderComment(StringCallback callback, int page) {
        order(callback, page, ORDER_COMMENT);
    }

    //取消订单
    static String CANCEL_ORDER = url + "update_payment_success";

    public static void cancelOrder(StringCallback callback, String id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("orders_num", id);
        HttpModel.newInstance(CANCEL_ORDER).post(map, callback);
    }

    //确认收货
    static String GET_SHOP = url + "confirm";

    public static void getShop(StringCallback callback, String id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("orders_num", id);
        HttpModel.newInstance(GET_SHOP).post(map, callback);
    }

    //查看物流
    static String KUAIDI = url + "kuaidi";

    public static void kuaidi(StringCallback callback, String id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("orders_num", id);
        HttpModel.newInstance(KUAIDI).post(map, callback);
    }

    //查看物流

    public static void salesReturn(StringCallback callback, String id, String reson) {
        HashMap<String, String> map = new HashMap<>();
        map.put("orders_num", id);
        map.put("tuihuo_case", reson);
        HttpModel.newInstance(KUAIDI).post(map, callback);
    }

    static String VIP_URL = url + "vip";

    //会员等级

    public static void vipInfo(StringCallback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", UserCenter.getInstance().getCurrentUserId());
        HttpModel.newInstance(VIP_URL).post(map, callback);
    }
}

