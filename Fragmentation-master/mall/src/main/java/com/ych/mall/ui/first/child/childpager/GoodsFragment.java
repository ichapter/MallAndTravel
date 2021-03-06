package com.ych.mall.ui.first.child.childpager;

import android.content.Intent;

import android.graphics.Paint;

import android.net.Uri;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fyales.tagcloud.library.TagBaseAdapter;
import com.fyales.tagcloud.library.TagCloudLayout;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.ych.mall.R;
import com.ych.mall.bean.GoodsDetailBean;
import com.ych.mall.bean.ParentBean;
import com.ych.mall.bean.TravelDetailBean;
import com.ych.mall.event.LoginEvent;
import com.ych.mall.event.MainEvent;
import com.ych.mall.model.Http;
import com.ych.mall.model.MallAndTravelModel;
import com.ych.mall.model.UserInfoModel;
import com.ych.mall.ui.BuyVipActivity_;
import com.ych.mall.ui.LoginActivity_;
import com.ych.mall.ui.PayActivity_;
import com.ych.mall.ui.base.BaseFragment;
import com.ych.mall.ui.fourth.WebViewActivity_;
import com.ych.mall.utils.KV;
import com.ych.mall.utils.UserCenter;
import com.ych.mall.widget.SlideShowView;
import com.zhy.http.okhttp.callback.StringCallback;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


/**
 * Created by ych on 2016/9/5.
 */
@EFragment(R.layout.fragment_goods)
public class GoodsFragment extends BaseFragment {
    public static final int TYPE_GOODS = 1;
    public static final int TYPE_TRAVEL = 2;
    private int currentType = TYPE_TRAVEL;
    @ViewById(R.id.fg_img)
    SlideShowView showView;
    @ViewById(R.id.fg_bottom)
    LinearLayout bottomLL;
    @ViewById(R.id.fg_city)
    TextView city;
    @ViewById(R.id.fg_package)
    LinearLayout packagell;
    @ViewById(R.id.fg_points)
    TextView points;
    @ViewById(R.id.fg_protocol)
    FrameLayout protocol;
    @ViewById(R.id.fg_stock)
    TextView stock;
    @ViewById(R.id.fg_time)
    TextView time;
    @ViewById(R.id.fg_order)
    Button order;
    @ViewById
    TextView mTitle;
    @ViewById
    TextView mPriceNew, mPriceTV, mPriceChildTV;
    @ViewById
    TextView mPriceOld;
    @ViewById
    TagCloudLayout mTags;
    @ViewById
    TextView mGroup;
    @ViewById
    CheckBox mProtocol;
    @ViewById
    FrameLayout mLoading;
    @ViewById(R.id.ll_people_number)
    LinearLayout llPeopleNumber;
    @ViewById(R.id.tv_num_adult)
    TextView tvNumAdult;
    @ViewById(R.id.tv_num_children)
    TextView tvNumChildren;
    @ViewById(R.id.tv_num)
    TextView tvNum;
    @ViewById(R.id.ll_children)
    LinearLayout llChildren;
    @ViewById
    TextView onCollect;
    @ViewById
    LinearLayout llNum;
    @ViewById
    LinearLayout travelButton;
    @ViewById
    TextView onTMain, onMain, onShop;
    private int numAdult;
    private int numChildren;
    private int num = 1;

    @Click
    void onTMain() {
        back();
        EventBus.getDefault().post(new MainEvent(0));
    }

    @Click
    void onMain() {
        back();
        EventBus.getDefault().post(new MainEvent(0));
    }

    @Click
    void onShop() {
        back();
        EventBus.getDefault().post(new MainEvent(2));
    }

    @Click
    void onService() {
        web("http://kefu.easemob.com/webim/im.html?tenantId=29457");
    }

    @Click
    void onProtocol() {
        web("http://www.zzumall.com/index.php/Mobile/Tourism/lvyou_xieyi");
    }

    @Click
    void onPhoneNumber() {
        String phone = "021-52218886";
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    List<GoodsDetailBean.Taocan> datas;
    List<TravelDetailBean.Taocan> mDate;
    TagBaseAdapter tAdapter;
    String groupId;
    String groupTitle;
    String mId;
    //成人价
    String mPrice;
    String mImgUrl;
    String mTitleText;
    //儿童价

    String mChildPrice;
    String mPoint;
    String goodsID;
    String goodsUrl = "http://www.zzumall.com/index.php/Mobile/Goods/goods_goods/id/";
    String travelUrl = "http://www.zzumall.com/index.php/Mobile/Tourism/tourism_tourism/id/";
    String userId;
    //String goodsUrl = "http://www.zzumall.com/index.php/Mobile/Goods/goods_detail_m.html?id=";
    //  String travelUrl = "http://www.zzumall.com/index.php/Mobile/Tourism/tourism_detail_m.html?id=";
    String protocolUrl = "http://www.zzumall.com/index.php/Mobile/Tourism/lvyou_xieyi";

    @Click
    void tv_sub_num_adult() {
        numAdult--;
        if (numAdult <= 0)
            numAdult = 0;
        tvNumAdult.setText(numAdult + "");
        //setPrice();
    }

    @Click
    void tv_add_num_adult() {
        numAdult++;
        tvNumAdult.setText(numAdult + "");
        //setPrice();
    }

    @Click
    void tv_sub_num_children() {
        numChildren--;
        if (numChildren <= 0)
            numChildren = 0;
        tvNumChildren.setText(numChildren + "");
        //setChildPrice();
    }


    @Click
    void tv_add_num_children() {
        numChildren++;
        tvNumChildren.setText(numChildren + "");
        // setChildPrice();
    }

    @Click
    void tv_add_num() {
        num++;
        tvNum.setText(num + "");
    }

    @Click
    void tv_sub_num() {
        num--;
        if (num <= 0)
            num = 0;
        tvNum.setText(num + "");
    }

    //商品购买
    @Click
    void onBuy() {
        if (datas != null && datas.size() > 0) {
            if (groupTitle == null) {
                TOT("请选择套餐");
                return;
            }
        }
        Bundle bundle = new Bundle();
        bundle.putString(KV.GOODS_ID, mId);
        bundle.putInt("num", num);
        bundle.putString("Taocan", groupTitle);
        bundle.putInt("TYPE", TYPE_GOODS);
        startActivity(new Intent(getActivity(), PayActivity_.class).putExtras(bundle));
    }

    //旅游预订
    @Click
    public void fg_order() {
        if (UserCenter.getInstance().isTourist())
            return;
        if (groupTitle == null) {
            TOT("请选择套餐");
            return;
        }
        if (mProtocol.isChecked() == false) {
            TOT("请同意旅游协议");
            return;
        }
        if (Integer.parseInt(getT(tvNumChildren)) + Integer.parseInt(getT(tvNumAdult)) < 1) {
            TOT("至少选择一个人数");
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putString(KV.GOODS_ID, mId);
        bundle.putInt("TYPE", TYPE_TRAVEL);
        bundle.putString("Date", groupTitle + "");
        bundle.putString("ChildrenPrice", mChildPrice);
        bundle.putString("AdultPrice", mPrice);
        bundle.putString("ChildrenNum", tvNumChildren.getText().toString());
        bundle.putString("AdultNum", tvNumAdult.getText().toString());
        startActivity(new Intent(getActivity(), PayActivity_.class).putExtras(bundle));

    }

    //分享
    @Click
    void ivShare() {
        umShare();
    }

    @Click
    void onCollect() {
        if (mPrice != null) {
            mLoading.setVisibility(View.VISIBLE);
            UserInfoModel.addCollect(addCallBack, mId, mImgUrl, mPrice, mTitleText, "0", currentType);
        } else
            TOT("请选择出发时间");
    }

    UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            TOT("分享成功啦");
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            TOT("分享失败啦");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            TOT("分享取消了");
        }
    };

    private void umShare() {
        UMImage image = new UMImage(getActivity(), R.drawable.icon_logo);//资源文件
        String url;
        if (currentType == TYPE_GOODS) {
            url = goodsUrl + mId + "/pid/" + UserCenter.getInstance().getCurrentUserId() + ".html";
        } else {
            url = travelUrl + mId + "/pid/" + UserCenter.getInstance().getCurrentUserId() + ".html";
        }


        final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
                {
                        SHARE_MEDIA.WEIXIN,
                        SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN_CIRCLE
                };
        new ShareAction(getActivity()).setDisplayList(displaylist)
                .withText(mTitle.getText().toString())
                .withTitle("掌中游")
                .withMedia(image)
                .withTargetUrl(url)
                .setListenerList(umShareListener)
                .open();
    }


    public static GoodsFragment newInstance(int type, String id) {
        Bundle bundle = new Bundle();
        bundle.putInt(KV.TYPE, type);
        bundle.putString(KV.ID, id);
        GoodsFragment fragment = new GoodsFragment_();
        fragment.setArguments(bundle);
        return fragment;
    }

    @AfterViews
    public void initView() {
        MainEvent e = new MainEvent(-2);
        e.setBottomStatus(false);
        EventBus.getDefault().post(e);
        setTAG("goods");
        currentType = getArguments().getInt(KV.TYPE, TYPE_TRAVEL);
        mId = getArguments().getString(KV.ID);
        if (currentType == TYPE_GOODS) {
            goodsInit();
            MallAndTravelModel.goodsDetail(goodsCallBack, mId);
        } else {
            llNum.setVisibility(View.GONE);
            MallAndTravelModel.travelDetail(travelCallBack, mId);
        }
        if (UserCenter.getInstance().isTourist()) {
            order.setBackgroundColor(getActivity().getResources().getColor(R.color.text_gray));
            order.setText("请晋升为会员，继续购物");
            order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    back();
                    if (UserCenter.getInstance().isLoggin())
                        startActivity(new Intent(getActivity(), BuyVipActivity_.class));
                    else
                        startActivity(new Intent(getActivity(), LoginActivity_.class));
                }
            });
            if (currentType == TYPE_GOODS) {
                order.setVisibility(View.VISIBLE);
                bottomLL.setVisibility(View.GONE);
                travelButton.setVisibility(View.VISIBLE);

            }
        }

    }

    private void goodsInit() {
        stock.setVisibility(View.VISIBLE);
        protocol.setVisibility(View.GONE);
        time.setVisibility(View.GONE);
        city.setVisibility(View.GONE);
        packagell.setVisibility(View.VISIBLE);
        bottomLL.setVisibility(View.VISIBLE);

        llPeopleNumber.setVisibility(View.GONE);
        travelButton.setVisibility(View.GONE);
    }

    @Click
    void onShopCar() {
        if (datas != null && datas.size() > 0) {
            if (groupTitle == null) {
                TOT("请选择套餐");
                return;
            }
        }
        MallAndTravelModel.addShopCar(shopCallBack, mId, groupTitle, mPoint, mPrice, num + "");
    }

    @Override
    public void onStop() {
        super.onStop();
        MainEvent e = new MainEvent(-2);
        e.setBottomStatus(true);
        EventBus.getDefault().post(e);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MainEvent e = new MainEvent(-2);
        e.setBottomStatus(true);
        EventBus.getDefault().post(e);
    }

    private void goods(GoodsDetailBean.GoodsDetailData t) {
        if (t == null)
            return;
        mImgUrl = Http.GOODS_PIC_URL + t.getPic_url();
        mTitleText = t.getTitle();
        sT(mTitle, t.getTitle());
        sT(mPriceNew, "￥" + t.getPrice_new());
        sT(mPriceOld, t.getPrice_old());
        mPriceOld.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
        points.setText("送积分（" + t.getFanli_jifen() + "积分）");
        stock.setText("库存：" + t.getKucun() + "件");
        mPrice = t.getPrice_new();
        mPoint = t.getFanli_jifen();

        if (t.getPic_tuji() != null) {

            String[] banner = new String[t.getPic_tuji().size()];
            int c = 0;
            for (String s : t.getPic_tuji()) {
                banner[c] = Http.GOODS_PIC_URL + t.getPic_tuji().get(c);
                c++;
            }
            showView.setData(banner);
        }
        datas = t.getTaocan();
        if (datas == null || datas.size() < 1)
            return;
        mGroup.setText("选择套餐");
        List<String> tagDatas = new ArrayList<>();
        for (GoodsDetailBean.Taocan ta : datas) {
            tagDatas.add(ta.getGuige_title());
        }
        tAdapter = new TagBaseAdapter(getActivity(), tagDatas);
        mTags.setAdapter(tAdapter);
        mTags.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            @Override
            public void itemClick(int position) {
                groupTitle = datas.get(position).getGuige_title();
                sT(mPriceNew, datas.get(position).getGuige_price_new());
            }
        });

    }

    private void travel(TravelDetailBean.TravelDetailData t) {
        if (t == null)
            return;
        mImgUrl = Http.GOODS_PIC_URL + t.getPic_url();
        mTitleText = t.getTitle();
        mPriceOld.setVisibility(View.GONE);
        sT(mTitle, t.getTitle());

        sT(mPriceNew, t.getPrice_new());
        //sT(mPriceOld, t.getPrice_old());
        points.setText("送积分（" + t.getFanli_jifen() + "积分）");
        //stock.setText("库存：" + t.getKucun() + "件");
        //mPrice = t.getPrice_new();
        mPoint = t.getFanli_jifen();
        if (t.getPic_tuji() != null && t.getPic_tuji().size() != 0) {
            log(t.getPic_tuji().toString());
            String[] banner = new String[t.getPic_tuji().size()];
            int c = 0;
            for (String s : t.getPic_tuji()) {
                banner[c] = Http.GOODS_PIC_URL + t.getPic_tuji().get(c);
                c++;
            }
            showView.setData(banner);
        }

        sT(city, "出发城市：" + t.getChufa_address());
        time.setVisibility(View.GONE);
        mDate = t.getTaocan();
        if (mDate == null)
            return;
        packagell.setVisibility(View.VISIBLE);
        mGroup.setText("选择出发日期");
        List<String> tagDatas = new ArrayList<>();
        for (TravelDetailBean.Taocan tc : mDate) {
            tagDatas.add(tc.getChufa_date());
        }
        tAdapter = new TagBaseAdapter(getActivity(), tagDatas);
        mTags.setAdapter(tAdapter);
        mTags.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            @Override
            public void itemClick(int position) {
                groupTitle = mDate.get(position).getChufa_date();
                sT(mPriceNew, mDate.get(position).getChufa_price());
                mPrice = mDate.get(position).getChufa_price();
                mChildPrice = mDate.get(position).getChufa_price_et();
                Log.e("KTY  price", mChildPrice + "");
                if (mChildPrice == null || mChildPrice.equals("0")) {
                    llChildren.setVisibility(View.GONE);
                } else {
                    llChildren.setVisibility(View.VISIBLE);
                }
                sT(mPriceTV, mPrice);
                sT(mPriceChildTV, mChildPrice);
            }
        });
    }

    void web(String url) {
        startActivity(new Intent(getActivity(), WebViewActivity_.class).putExtra(KV.URL, url));
    }

    //    void setPrice() {
//        double price = Double.parseDouble(mPrice);
//        double total = Tools.mul(price, Double.parseDouble(getT(tvNumAdult)));
//        sT(mPriceTV, total + "");
//    }
//
//    void setChildPrice(){
//        double price = Double.parseDouble(mChildPrice);
//        double total = Tools.mul(price, Double.parseDouble(getT(tvNumChildren)));
//        sT(mPriceChildTV, total + "");
//    }
    //商品
    StringCallback goodsCallBack = new StringCallback() {
        @Override
        public void onError(Call call, Exception e, int id) {
            TOT("网络连接失败");
            mLoading.setVisibility(View.GONE);
        }

        @Override
        public void onResponse(String response, int id) {
            if (mLoading != null)
                mLoading.setVisibility(View.GONE);
            try {
                GoodsDetailBean bean = Http.model(GoodsDetailBean.class, response);
                if (bean.getCode().equals("200")) {
                    goods(bean.getData().get(0));
                    onCollect.setVisibility(View.VISIBLE);
                } else
                    TOT(bean.getMessage());
            } catch (Exception e) {

            }

        }
    };
    //旅游
    StringCallback travelCallBack = new StringCallback() {
        @Override
        public void onError(Call call, Exception e, int id) {
            TOT("网络连接失败");
            mLoading.setVisibility(View.GONE);
        }

        @Override
        public void onResponse(String response, int id) {
            mLoading.setVisibility(View.GONE);
            log(response);
            TravelDetailBean bean = Http.model(TravelDetailBean.class, response);
            if (bean.getCode().equals("200")) {
                travel(bean.getData().get(0));
                onCollect.setVisibility(View.VISIBLE);
            } else
                TOT(bean.getMessage());
        }
    };
    //添加购物车
    StringCallback shopCallBack = new StringCallback() {
        @Override
        public void onError(Call call, Exception e, int id) {
            TOT("网络连接失败");
            if (mLoading != null)
                mLoading.setVisibility(View.GONE);
        }

        @Override
        public void onResponse(String response, int id) {
            if (mLoading != null)
                mLoading.setVisibility(View.GONE);
            ParentBean bean = Http.model(ParentBean.class, response);
            if (bean.getCode().equals("200")) {
                EventBus.getDefault().post(new LoginEvent());
            }
            TOT(bean.getMessage());

        }
    };
    //添加收藏
    StringCallback addCallBack = new StringCallback() {
        @Override
        public void onError(Call call, Exception e, int id) {
            TOT("网络连接失败");
            if (mLoading != null)
                mLoading.setVisibility(View.GONE);
        }

        @Override
        public void onResponse(String response, int id) {
            if (mLoading != null)
                mLoading.setVisibility(View.GONE);
            ParentBean bean = Http.model(ParentBean.class, response);
            if (bean.getCode().equals("200")) {
                TOT("收藏成功");
            } else
                TOT(bean.getMessage());
        }
    };
}
