package com.example.progetto_android.conndata.utils;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

//si occupa si comunicare con la servlet, invia e riceve dati che convertirà i HttpResponse e HttpTaskResult
public class HttpTask extends Thread {

    private final URL url;
    private final Map<String, String> params;
    private final OnHttpTaskCompleted<HttpResponse>[] callbacks;

    /* Provare senza questo costruttore
    public HttpTask(String url, OnHttpTaskCompleted<HttpResponse>... callbacks) throws MalformedURLException{
        this.url = new URL(url);
        this.params = new HashMap<>();
        this.callbacks = callbacks;
    }*/

    @SafeVarargs
    // ... = numero qualsiasi di parametro, tipo * di python
    public HttpTask(String url, Map<String, String> params, OnHttpTaskCompleted<HttpResponse>... callbacks) throws MalformedURLException {
        this.url = new URL(url);
        this.params = new HashMap<>(params);        // creami una mappa che parte dai parametri che ti sto mandando
        this.callbacks = callbacks;
    }

    @Override
    public void run() { //start thread
        StringBuilder str = new StringBuilder();
        int responseCode = HttpURLConnection.HTTP_BAD_REQUEST;
        HttpURLConnection conn = null;
        OnPostExecuteTask res = (result) -> {
            for (OnHttpTaskCompleted<HttpResponse> callback : callbacks)
                callback.onHttpTaskCompleted(result);
        };

        try {
            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, String> param : params.entrySet()) {
                if (postData.length() != 0)
                    postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(param.getValue(), "UTF-8"));
            }
            // creo un array di byte perché getOutputStream() vuole un array di Byte
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(500);
            //questo serve per mandare richieste in un messaggio, quindi sono nascoste,
            // col metogo GET invece le richieste vengono inserite nell'url e quindi non sono nascoste
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            InputStream in;
            responseCode = conn.getResponseCode();
            if (responseCode < HttpURLConnection.HTTP_BAD_REQUEST) {
                in = conn.getInputStream();
            } else {
                in = conn.getErrorStream();
                if (in == null) {
                    //onPostExecute(new HttpTaskResult<>(new HttpResponse("", responseCode)));
                    res.execute(new HttpTaskResult<>(new HttpResponse("", responseCode)));      //lambda
                }
            }

            in = new BufferedInputStream(in);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line = reader.readLine();
            while (line != null) {
                str.append(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            Log.e("HttpTaskError", e.getMessage(), e);
            //onPostExecute(new HttpTaskResult<HttpResponse>(e));
            res.execute(new HttpTaskResult<>(e));       // lambda
        } finally {
            if (conn != null)
                conn.disconnect();
        }
        // è andato tutto bene
        //onPostExecute(new HttpTaskResult<>(new HttpResponse(str.toString(), responseCode)));
        //HttpTaskResult è un oggetto che contiene al suo interno un altro
        // oggetto di tipo HttpResponse
        res.execute(new HttpTaskResult<>(new HttpResponse(str.toString(), responseCode)));      // lambda
    }

    /*protected void onPostExecute(HttpTaskResult<HttpResponse> result){
        for(OnHttpTaskCompleted<HttpResponse> callback : callbacks){
            callback.onHttpTaskCompleted(result);
        }
    }*/

}
