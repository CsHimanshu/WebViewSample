package com.tothenew.poc.shimanshu.webviewsample.webUtils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.tothenew.poc.shimanshu.webviewsample.ApiManager;
import com.tothenew.poc.shimanshu.webviewsample.ResponseInterface;

/**
 * Created by himanshu on 7/9/15.
 */
public class WebAppInterface {
    private Activity activity;
    private ResponseInterface responseInterface;

    /** Instantiate the interface and set the context */
    public WebAppInterface(Activity activity,ResponseInterface responseInterface) {
        this.activity = activity;
        this.responseInterface=responseInterface;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void getAPIHit(String url){
        ApiManager.apiGet(activity, url, new ResponseInterface() {
            @Override
            public void onSuccessResponse(String responseString) {
                Toast.makeText(activity, responseString, Toast.LENGTH_SHORT).show();
                Log.e("test","Response from string is : " + responseString);
                if(responseInterface!=null){
                    responseInterface.onSuccessResponse(responseString);
                }
            }
        });

    }
}
