package com.example.gouwuproject.mvp;

import com.example.gouwuproject.bean.Cid294Bean;
import com.example.mvplibrary.model.ModleFractory;
import com.example.mvplibrary.presenter.BasePresenter;

public class MainPresenter extends BasePresenter<MainView> implements MainCallBack{

    @Override
    public void onSuccess(Cid294Bean bean) {
        mView.setData(bean);
    }

    @Override
    public void onFaild(String s) {
        mView.showToast(s);
    }

    public void getDatap() {
        MainModel modle = ModleFractory.creatModle(MainModel.class);
        modle.getDatam(this);
    }
}
