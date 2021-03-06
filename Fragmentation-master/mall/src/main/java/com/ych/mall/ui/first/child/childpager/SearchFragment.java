package com.ych.mall.ui.first.child.childpager;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.ych.mall.R;
import com.ych.mall.bean.SearchBean;
import com.ych.mall.model.Http;
import com.ych.mall.model.MallAndTravelModel;
import com.ych.mall.model.RecyclerViewModel;
import com.ych.mall.model.YViewHolder;
import com.ych.mall.ui.base.BaseFragment;
import com.ych.mall.ui.first.child.GoodsViewPagerFragment;
import com.ych.mall.utils.KV;
import com.ych.mall.widget.ClearEditText;
import com.zhy.http.okhttp.callback.StringCallback;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by gb on 2016/9/20.
 */
@EFragment(R.layout.fragment_first_search)
public class SearchFragment extends BaseFragment implements RecyclerViewModel.RModelListener<SearchBean.SearchData> {
    @ViewById(R.id.swipe)
    SwipeRefreshLayout layout;
    @ViewById(R.id.recycle)
    RecyclerView rv;
    @ViewById(R.id.mLoading)
    TextView mLoading;
    @ViewById(R.id.onSearch)
    ClearEditText onSearch;
    @ViewById(R.id.ivSearch)
    ImageView ivSearch;
    String title;
    int type;


    public static SearchFragment newInstance(String title, int type) {
        Bundle bundle = new Bundle();
        bundle.putString(KV.TITLE, title);
        bundle.putInt(KV.TYPE, type);
        SearchFragment fragment = new SearchFragment_();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Click
    void onBack() {
        back();
    }

    @Click
    void ivSearch() {
        title = onSearch.getText().toString();
        mLoading.setVisibility(View.VISIBLE);
        mLoading.setText("加载中...");
        if (rvm.isEmpty()) {
            rvm.newInit();
        } else
            rvm.onRefresh();

        hideSoftKeyBord();
    }


    RecyclerViewModel<SearchBean.SearchData> rvm;

    @AfterViews
    public void initViews() {
        title = getArguments().getString(KV.TITLE);
        type = getArguments().getInt(KV.TYPE);
        initModel();

    }

    @Override
    public void getData(StringCallback callback, int page) {
        MallAndTravelModel.goodsSearch(callback, title, page);
    }

    @Override
    public void onErr(int state) {
        TOT("网络链接失败");
        mLoading.setText("网络链接失败");
    }

    @Override
    public List<SearchBean.SearchData> getList(String str) {
        SearchBean bean = Http.model(SearchBean.class, str);
        if (bean.getCode().equals("200")) {
            mLoading.setVisibility(View.GONE);
            return bean.getData();
        } else {
            mLoading.setText(bean.getMessage());
        }
        return null;
    }

    private void initModel() {
        rvm = new RecyclerViewModel<SearchBean.SearchData>(getActivity(),
                this,
                rv,
                layout,
                R.layout.item_goods_list);
        rvm.setMiniSize(9);
        rvm.init();
    }


    @Override
    public void covert(YViewHolder holder, final SearchBean.SearchData t) {
        holder.setText(R.id.name, t.getTitle());
        holder.setText(R.id.price, "￥" + t.getPrice_new());
        holder.setVisible(R.id.ll_fanli, View.GONE);
        holder.loadImg(getActivity(), R.id.pic, Http.GOODS_PIC_URL + t.getPic_url());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == GoodsFragment.TYPE_GOODS) {
                 start(GoodsViewPagerFragment.newInstance(GoodsFragment.TYPE_GOODS, t.getId()));
                } else if (type == GoodsFragment.TYPE_TRAVEL) {
                   start(GoodsViewPagerFragment.newInstance(GoodsFragment.TYPE_TRAVEL, t.getId()));
                }
            }
        });
    }


}
