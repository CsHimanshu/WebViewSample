package com.tothenew.poc.shimanshu.webviewsample.webUtils;

import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;

/**
 * Created by himanshu on 7/9/15.
 */
public class MyWebChromeClient extends WebChromeClient {
    public boolean onConsoleMessage(ConsoleMessage cm) {
        Log.d("test", cm.message() + " -- From line "
                + cm.lineNumber() + " of "
                + cm.sourceId());
        return true;
    }
}
