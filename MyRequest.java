package com.example.ecelldictionary;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.*;


public class MyRequest extends AsyncTask<String,Integer,String>
{
    final String app_id = "fa0631c4";
    final String app_key = "1fe91dd55df9f38a5f7a062c8e245e61";
    String myUrl;
    Context context;



    MyRequest(Context context)
    {
        this.context=context;

    }

    @Override
    protected String doInBackground(String... params) {
        myUrl=params[0];

        try {
            URL url = new URL(myUrl);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("app_id",app_id);
            urlConnection.setRequestProperty("app_key",app_key);

            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            return stringBuilder.toString();

        }
        catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }



    }

    @Override
    protected void onPostExecute(String s) {
String deff, examples;
        super.onPostExecute(s);
        try {
            JSONObject js = new JSONObject(s);
            JSONArray results = new JSONArray("results");

            JSONObject lEntries =results.getJSONObject(0);
            JSONArray laArray =lEntries.getJSONArray("lexicalEntries");
            JSONObject entries =laArray.getJSONObject(0);
            JSONArray e= entries.getJSONArray("entries");
            JSONObject jsonObject= e.getJSONObject(0);
            JSONArray sensesArray=jsonObject.getJSONArray("senses");
            JSONObject defi= sensesArray.getJSONObject(0);
            JSONArray definition= defi.getJSONArray("definitions");
            deff=definition.getString(0);
            Toast.makeText(context,deff,Toast.LENGTH_SHORT).show();


            JSONObject example = sensesArray.getJSONObject(0);
            JSONArray defiExample =example.getJSONArray("examples");
            JSONObject examp= defiExample.getJSONObject(0);
            JSONArray example2 = examp.getJSONArray("definitions");
            examples=example2.getString(0);



        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
