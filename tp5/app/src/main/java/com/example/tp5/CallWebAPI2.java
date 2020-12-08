package com.example.tp5;

import android.os.AsyncTask;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class CallWebAPI2 extends AsyncTask<String, String, String> {
    private TextView mTextView;

    public CallWebAPI2(TextView mTextView){
        this.mTextView=mTextView;
    }

    @Override
    protected String doInBackground(String... params) {
        URL url;
        try {
            url =new URL("http://ip-api.com/xml/" + params[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            XmlPullParserFactory pullParserFactory;
            GeoIP geoIP = null;
            try {
                pullParserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = pullParserFactory.newPullParser();
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(in, null);
                geoIP = new GeoIP();
                geoIP.parseXML(parser);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            in.close();
            return geoIP.toString();
        }catch (Exception e){
        }
        return "error";
    }
    protected void onPostExecute(String result) {
        mTextView.setText(result);
    }
} // end CallWebAPI2