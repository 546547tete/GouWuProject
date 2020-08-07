package com.example.gouwuproject.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.gouwuproject.MainActivity;
import com.example.gouwuproject.R;
import com.example.gouwuproject.SelectActivity;
import com.example.gouwuproject.rcyadapter.HomeAdapter;
import com.example.gouwuproject.viewpageradapter.GallyPageTransformer;
import com.example.gouwuproject.viewpageradapter.HomeImageUtils;
import com.example.gouwuproject.viewpageradapter.HomePagerAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Banner ban;
    private ArrayList<String> list;
    private RecyclerView rcy;
    private ArrayList<String> rcylist;
    private HomeAdapter homeAdapter;
    private View viewFen;
    private ViewFlipper fliper;
    private ArrayList<String> gonggaolist;
    private ViewPager vp;
    private int pagerWidth;
    private List<ImageView> imageViews;
    private LinearLayout ll;
    private ImageView iv_sousuo;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        ban = view.findViewById(R.id.bann);
        rcy = view.findViewById(R.id.rcy_home);
        viewFen = view.findViewById(R.id.viewFen);
        fliper = view.findViewById(R.id.filpper);
        vp = view.findViewById(R.id.vp_home);
        ll = view.findViewById(R.id.ll_vp_home);
        iv_sousuo = view.findViewById(R.id.iv_sousuo_home);
        //点击搜索跳转搜索页面
        initIvsousuo();

        //操作公告的方法
        gonggao();
        //操作轮播图方法
        initBann();
        initRcy();

        initViewPager();
    }

    private void initIvsousuo() {
        iv_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SelectActivity.class));
            }
        });
    }

    private void initViewPager() {

        imageViews = new ArrayList<>();
        ImageView first = new ImageView(getContext());
        first.setImageBitmap(HomeImageUtils.getReverseBitmapById(R.drawable.hualang1, getContext()));
//        first.setImageResource(R.mipmap.first);
        ImageView second = new ImageView(getContext());
        second.setImageBitmap(HomeImageUtils.getReverseBitmapById(R.drawable.hualang2, getContext()));
//        second.setImageResource(R.mipmap.second);
        ImageView third = new ImageView(getContext());
        third.setImageBitmap(HomeImageUtils.getReverseBitmapById(R.drawable.hualang1, getContext()));
//        third.setImageResource(R.mipmap.third);
        ImageView fourth = new ImageView(getContext());
//        fourth.setImageBitmap(ImageUtil.getReverseBitmapById(R.mipmap.fourth,MainActivity.this));
        fourth.setImageResource(R.drawable.hualang2);
        ImageView fifth = new ImageView(getContext());
        fifth.setImageBitmap(HomeImageUtils.getReverseBitmapById(R.drawable.hualang1, getContext()));
//        fifth.setImageResource(R.mipmap.fifth);
        imageViews.add(first);
        imageViews.add(second);
        imageViews.add(third);
        imageViews.add(fourth);
        imageViews.add(fifth);

        vp.setOffscreenPageLimit(3);
        pagerWidth = (int) (getResources().getDisplayMetrics().widthPixels * 3.0f / 5.0f);
        ViewGroup.LayoutParams lp = vp.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(pagerWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        } else {
            lp.width = pagerWidth;
        }
        vp.setLayoutParams(lp);
        vp.setPageMargin(-50);
        ll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return vp.dispatchTouchEvent(motionEvent);
            }
        });
        vp.setPageTransformer(true, new GallyPageTransformer());
        vp.setAdapter(new HomePagerAdapter(imageViews));



    }

    private void gonggao() {
        gonggaolist = new ArrayList<>();
        gonggaolist.add("新用户立领1000元优惠券");
        gonggaolist.add("夏日炎炎，第一波福利还有30秒到达战场");

        setViews();
    }

    private void setViews() {
        for (int i = 0; i < gonggaolist.size(); i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.title_view, null);
            TextView tvTitle = view.findViewById(R.id.tvItem);
            //赋值
            tvTitle.setText(gonggaolist.get(i));
            //动态添加视图
            fliper.addView(view);
        }

        //设置的时间间隔来开始切换所有的View,切换会循环进行
        fliper.startFlipping();
        //视图进入动画
        fliper.setInAnimation(getContext(), R.anim.news_in);
        //视图退出动画
        fliper.setOutAnimation(getContext(), R.anim.news_out);
        //自动开始滚动
        fliper.setAutoStart(true);
        //视图的切换间隔
        fliper.setFlipInterval(3000);
    }

    private void initRcy() {
        String HOME_DISCOUNT_ONE = "https://img14.360buyimg.com/n0/jfs/t3157/231/5756125504/98807/97ab361d/588084a1N06efb01d.jpg";
        String HOME_DISCOUNT_TWO = "https://img10.360buyimg.com/n7/jfs/t5905/106/1120548052/61075/6eafa3a5/592f8f7bN763e3d30.jpg";
        String HOME_DISCOUNT_THREE = "https://img10.360buyimg.com/n7/jfs/t5584/99/6135095454/371625/423b9ba5/59681d91N915995a7.jpg";
        String HOME_DISCOUNT_FOUR = "https://img11.360buyimg.com/n7/jfs/t4447/301/1238553109/193354/13c7e995/58db19a7N25101fe4.jpg";
        String HOME_DISCOUNT_FIVE = "https://img14.360buyimg.com/n1/s190x190_jfs/t7525/189/155179632/395056/e200017f/598fb8a4N7800dee6.jpg";

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.HORIZONTAL);
        rcy.setLayoutManager(manager);
        rcylist = new ArrayList<>();
        rcylist.add(HOME_DISCOUNT_ONE);
        rcylist.add(HOME_DISCOUNT_TWO);
        rcylist.add(HOME_DISCOUNT_THREE);
        rcylist.add(HOME_DISCOUNT_FOUR);
        rcylist.add(HOME_DISCOUNT_FIVE);
        homeAdapter = new HomeAdapter(rcylist, getContext());
        rcy.setAdapter(homeAdapter);
    }

    private void initBann() {
        list = new ArrayList<>();
        String HOME_BANNER_ONE = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502876108368&di=cd9725c81901f6d7499edd76cf2e68e5&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F17%2F37%2F20%2F80Q58PICe3W_1024.jpg";
        String HOME_BANNER_TWO = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502882008108&di=d0cf4a8536aefa5df791716c1053ca66&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01e9495812c7f9a84a0d304fbc135b.jpg";
        String HOME_BANNER_THREE = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502876281925&di=f33e7ef8be268e90ffbffd315f5fb0a3&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F013e1b57d2731c0000018c1beeca11.jpg%40900w_1l_2o_100sh.jpg";
        String HOME_BANNER_FOUR = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503471047&di=679d7a6c499f59d1b0dcd56b62a9aa6c&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.90sheji.com%2Fdianpu_cover%2F11%2F14%2F64%2F55%2F94ibannercsn_1200.jpg";
        list.add(HOME_BANNER_ONE);
        list.add(HOME_BANNER_TWO);
        list.add(HOME_BANNER_THREE);
        list.add(HOME_BANNER_FOUR);
        ban.setImages(list)
                .setBannerAnimation(Transformer.CubeIn)
                .setDelayTime(1500)
                .setIndicatorGravity(BannerConfig.RIGHT)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load(path).into(imageView);
                    }
                })
                .start();
    }
}
