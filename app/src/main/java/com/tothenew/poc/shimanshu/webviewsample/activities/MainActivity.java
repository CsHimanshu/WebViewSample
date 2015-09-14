package com.tothenew.poc.shimanshu.webviewsample.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tothenew.poc.shimanshu.webviewsample.R;
import com.tothenew.poc.shimanshu.webviewsample.ResponseInterface;
import com.tothenew.poc.shimanshu.webviewsample.webUtils.MyWebChromeClient;
import com.tothenew.poc.shimanshu.webviewsample.webUtils.MyWebViewClient;
import com.tothenew.poc.shimanshu.webviewsample.webUtils.WebAppInterface;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements ResponseInterface {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webview_sample);
        initialSetup();
        changeUserSettingOfWebview();

        String summary = "<html>\n" +
                "<body>\n" +
                "<input type=\"button\" value=\"Say hello\" onClick=\"showAndroidToast('Hello Android!')\" />\n" +
                "<input type=\"button\" value=\"API GET\" onClick=\"APIGET('http://jsonplaceholder.typicode.com/posts/1')\" />\n" +
                "\n" +
                "<a href=\"http://www.w3schools.com\">Visit W3Schools</a>"+
                "<script type=\"text/javascript\">\n" +
                "    function showAndroidToast(toast) {\n" +
                "        Android.showToast(toast);\n" +
                "    }\n" +
                "    function APIGET(url) {\n" +
                "        Android.getAPIHit(url);\n" +
                "    }\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>";

        loadData(summary);
    }

    private void changeUserSettingOfWebview(){
        WebSettings settings = webView.getSettings();
//        settings.setUseWideViewPort(true);
//        settings.setLoadWithOverviewMode(true);
    }

    private void initialSetup() {
        webView.getSettings().setJavaScriptEnabled(true);

        //Handling Debug of Application
        webView.setWebChromeClient(new MyWebChromeClient());

        //Handling page navigation
        webView.setWebViewClient(new MyWebViewClient());

        //interface added to interact with navite android code
        webView.addJavascriptInterface(new WebAppInterface(this,this),"Android");
    }


    @Override
    public void onSuccessResponse(String responseString) {
        parseJSON(responseString);
    }

    private void parseJSON(String stringJSON){
        try {
            JSONObject jsonObject = new JSONObject(stringJSON);
            int userID = jsonObject.getInt("userId");
            int id = jsonObject.getInt("id");
            String title = jsonObject.getString("title");
            String body = jsonObject.getString("body");

            showData(""+userID,title,body);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showData(String userid,String title,String body){
//        "  <tr>\n" +
//        "  </tr>\n" +
        String htmlData = "<html>\n" +
                "<body>\n" +
                "\n" +
                "<table style=\"width:100%\">\n" +
                "    <tr> UserId : "+ userid+"</tr><br><br>\n" +
                "    <tr> Title : "+title+ "</tr><br><br>\n" +
                "    <tr> Body : "+body+"</tr><br><br>\t\t\n" +
                "\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>";

        loadData(htmlData);
    }


    private void loadData(String dataHTML) {
//        http://jsonplaceholder.typicode.com/posts/1
//        String summary = "<html>\n" +
//                "<body>\n" +
//                "<button type=\"button\" onclick=\"alert('Hello world!')\">Click Me!</button>\n" +
//                "</body>\n" +
//                "</html>";
//        "<a href=\"http://www.w3schools.com\">Visit W3Schools</a>"+
//        String summary = "<html>\n" +
//                "<body>\n" +
//                "<button type=\"button\" onclick=\"alert('Hello world!')\">Click Me!</button>\n" +
//                "</body>\n" +
//                "</html>";
        webView.loadData(dataHTML, "text/html", null);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

}
