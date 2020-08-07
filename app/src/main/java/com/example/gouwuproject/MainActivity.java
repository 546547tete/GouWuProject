package com.example.gouwuproject;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.gouwuproject.fragment.ClassificationFragment;
import com.example.gouwuproject.fragment.FindFragment;
import com.example.gouwuproject.fragment.HomeFragment;
import com.example.gouwuproject.fragment.MineFragment;
import com.example.gouwuproject.fragment.ShopCartFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar bnb;
    private HomeFragment homeFragment;
    private ClassificationFragment classificationFragment;
    private FindFragment findFragment;
    private ShopCartFragment shopCartFragment;
    private MineFragment mineFragment;
    private FragmentTransaction transaction;
    private Fragment mFragment;
    private Toolbar toolbr;
    private FrameLayout frm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


        bnb.setTabSelectedListener(this);

        initBottomNavigationBar();

        initFragment();
    }

    private void initView() {
        setSupportActionBar(toolbr);
        frm = findViewById(R.id.frm);
        bnb = findViewById(R.id.bnb);
    }

    private void initBottomNavigationBar() {
        //按钮右上角红点
//        BadgeItem badgeItem = new BadgeItem();
//        badgeItem.setHideOnSelect(false)
//                .setText("10")
//                .setBackgroundColorResource(R.color.colorAccent)
//                .setBorderWidth(0);

        bnb.setActiveColor(R.color.colorPrimaryDark)//设置Item选中颜色方法
                .setInActiveColor(R.color.tou)//设置Item未选中颜色方法
                .setBarBackgroundColor("#FFFFFF");//背景颜色
        bnb.setMode(BottomNavigationBar.MODE_FIXED);//按钮的样式
        bnb.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bnb.addItem(new BottomNavigationItem(R.drawable.btn_nav_home_normal, "主页"))
                .addItem(new BottomNavigationItem(R.drawable.btn_nav_category_normal, "分类"))
                .addItem(new BottomNavigationItem(R.drawable.btn_nav_cart_normal, "购物车"))
                .addItem(new BottomNavigationItem(R.drawable.btn_nav_msg_normal, "消息"))
                .addItem(new BottomNavigationItem(R.drawable.btn_nav_user_normal, "我的"))
                .setFirstSelectedPosition(0)//默认选中页面
                .initialise();
    }

    private void initFragment() {
        homeFragment = new HomeFragment();
        classificationFragment = new ClassificationFragment();
        findFragment = new FindFragment();
        shopCartFragment = new ShopCartFragment();
        mineFragment = new MineFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frm, homeFragment).commit();
        mFragment = homeFragment;
    }

    private void switchFragment(Fragment fragment) {
        //判断当前显示的Fragment是不是切换的Fragment
        if (mFragment != fragment) {
            //判断切换的Fragment是否已经添加过
            if (!fragment.isAdded()) {
                //如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
                getSupportFragmentManager().beginTransaction().hide(mFragment)
                        .add(R.id.frm, fragment).commit();
            } else {
                //如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来
                getSupportFragmentManager().beginTransaction().hide(mFragment).show(fragment).commit();
            }
            mFragment = fragment;
        }
    }

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                switchFragment(homeFragment);
                break;
            case 1:
                switchFragment(classificationFragment);
                break;
            case 2:
                switchFragment(findFragment);
                break;
            case 3:
                switchFragment(shopCartFragment);
                break;
            case 4:
                switchFragment(mineFragment);
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
