package com.tothenew.poc.shimanshu.webviewsample.webUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by himanshu on 7/9/15.
 */
public class ConntionUtility {
    final int timeoutConnection = 20 * 1000; //in Miliseconds
    final int timeoutSocket = 25 * 1000;



    public String doGet(String url){
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Content-type", "application/json");

        try{
        HttpParams httpParameters = new BasicHttpParams();

            HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
        DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        StatusLine statusLine = httpResponse.getStatusLine();
        if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity entity = httpResponse.getEntity();

            if (entity != null) {
                InputStream in = entity.getContent();
                String result = readStream(in);
                return result;
//                mResponse.setStrResponse(result);
            } else {
                return "error";
//                mResponse.setError("Error : Entity is null");
            }
        } else {

            return "error in response";
//            mResponse.setError("Error : Response status code is not Ok. Response status code : " + statusLine.getStatusCode());
        }
    } catch (Exception e) {
        return "error  response in exception";
//        mResponse.setError("Error : " + e.toString());
    }
//    if (Constant.DEBUG)
//    printResponse(mResponse.getStrResponse());

    //parse the object here

//    return mResponse;
//        return null;
    }

    String readStream(InputStream inputStream) throws IOException {
//        InputStream in = new FileInputStream(new File("C:/temp/test.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }
        reader.close();
        return out.toString();
    }
}
