package com.example.gouwuproject.mvp;

import android.util.Log;

import com.example.gouwuproject.bean.Cid294Bean;
import com.example.httplibrary.callback.HttpCallBack;
import com.example.httplibrary.client.HttpClient;
import com.example.mvplibrary.model.BaseModel;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class MainModel implements BaseModel {
    public void getDatam(MainCallBack callback) {
        wananzhuo(callback);
    }

    public void wananzhuo(final MainCallBack callBack) {
        new HttpClient.Builder()
                .setApiUrl("project/list/1/json?cid=294")
                .get()
//                .setJsonBody("{\"parentId\":\"0\"}",true)
                .build()
                .request(new HttpCallBack<Cid294Bean>() {
                    @Override
                    public void onSuccess(Cid294Bean cid294Bean) {
                        callBack.onSuccess(cid294Bean);
                        Log.e("TRG","成功："+cid294Bean.toString());
                    }

                    @Override
                    public Cid294Bean convert(JsonElement result) {
                        return new Gson().fromJson(result,Cid294Bean.class);
                    }

                    @Override
                    public void onError(String message, int code) {
                        callBack.onFaild(message);
                        Log.e("TRG","onError："+message);
                    }

                    @Override
                    public void cancle() {

                    }
                });
    }
}
