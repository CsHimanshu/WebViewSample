package com.tothenew.poc.shimanshu.webviewsample;

import android.app.Activity;

import com.tothenew.poc.shimanshu.webviewsample.webUtils.ConntionUtility;

/**
 * Created by himanshu on 7/9/15.
 */
public class ApiManager {

    public static void apiGet(final Activity activity, final String url, final ResponseInterface
            responseInterface){
        new Thread(new Runnable() {
            @Override
            public void run() {
                ConntionUtility conntionUtility = new ConntionUtility();
                final String response = conntionUtility.doGet(url);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(responseInterface!=null){
                            responseInterface.onSuccessResponse(response);
                        }
                    }
                });
            }
        }).start();


    }
}
