package com.thanggun99.baithi.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.thanggun99.baithi.model.entity.TinTuc;
import com.thanggun99.baithi.util.Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Thanggun99 on 26/03/2017.
 */

public class DataManager {
    public static final String url = "http://vnexpress.net/rss/the-thao.rss";

    private OnFinishGetDatasListener onFinishGetDatasListener;
    private ProgressDialog progressDialog;
    private ArrayList<TinTuc> tinTucList;
    private String noiDung;
    private String chiTietUrl;


    public DataManager(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Đợi tý...");
    }

    public void getDatas() {
        if (Utils.isConnectingToInternet()) {

            new GetDatasAsyncTask().execute();

        } else {
            onFinishGetDatasListener.onNotConnect();
        }
    }

    public ArrayList<TinTuc> getTinTucList() {
        return tinTucList;
    }

    public void setTinTucList(ArrayList<TinTuc> tinTucList) {
        this.tinTucList = tinTucList;
    }

    public void getDatas(String chiTietUrl) {
        this.chiTietUrl = chiTietUrl;

        if (Utils.isConnectingToInternet()) {

            new GetNoiDungAsyncTask().execute();

        } else {
            onFinishGetDatasListener.onNotConnect();
        }

    }

    public String getNoiDung() {
        return noiDung;
    }

    private class GetDatasAsyncTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                onFinishGetDatasListener.onSuccess();

            } else {
                onFinishGetDatasListener.onFail();
            }
            progressDialog.dismiss();
            super.onPostExecute(aBoolean);
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            //delay(500);
            try {
                Document document = Jsoup.connect(url).get();
                Elements elements = document.getElementsByTag("item");

                if (elements == null) {
                    return false;
                } else if (elements.size() == 0) {
                    return false;
                } else {
                    tinTucList = new ArrayList<>();

                    for (int i = 0; i < elements.size(); i++) {

                        Element element = elements.get(i);

                        String date = element.select("pubDate").text();
                        String title = element.select("title").text();

                        String html = element.select("description").text();
                        Document doc = Jsoup.parse(html);
                        String hinhAnh = doc.select("img").attr("src");
                        String chiTietUrl = doc.select("a").attr("href");
                        String moTa = doc.text();

                        TinTuc tinTuc = new TinTuc(date, title, moTa, hinhAnh, chiTietUrl);

                        tinTucList.add(tinTuc);

                        Log.d(TAG, "date: " + date);
                        Log.d(TAG, "title: " + title);
                        Log.d(TAG, "mota: " + moTa);
                        Log.d(TAG, "hinh: " + hinhAnh);
                        Log.d(TAG, "chitieturl: " + chiTietUrl);
                    }
                    return true;

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    private class GetNoiDungAsyncTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                onFinishGetDatasListener.onSuccess();

            } else {
                onFinishGetDatasListener.onFail();
            }
            progressDialog.dismiss();
            super.onPostExecute(aBoolean);
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            //delay(500);
            try {
                Document document = Jsoup.connect(chiTietUrl).get();
                Elements elements = document.select("div.block_ads_connect");

                if (elements == null) {
                    return false;
                } else if (elements.size() == 0) {
                    return false;
                } else {
                    noiDung = elements.get(0).text();
                    return true;

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    private void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void setOnFinishGetDatasListener(OnFinishGetDatasListener onFinishGetDatasListener) {
        this.onFinishGetDatasListener = onFinishGetDatasListener;
    }

    public interface OnFinishGetDatasListener {
        void onNotConnect();

        void onSuccess();

        void onFail();
    }
}
