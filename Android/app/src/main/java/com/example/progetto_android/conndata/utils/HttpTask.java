package com.example.progetto_android.conndata.utils;

import android.os.AsyncTask;
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
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Questa classe si occupa di effettuare una singola richiesta http in maniera asincrona
 */
public class HttpTask extends AsyncTask<OnHttpTaskCompleted<HttpResponse>, Void, HttpTaskResult<HttpResponse>> {

    private URL url;
    private Map<String, String> params;
    //utilizzato come messaggio contente il risultato o null se è avvenuto un errore
    private OnHttpTaskCompleted<HttpResponse>[] callbacks;

    //si occupa di comunicare con la servlet, invia e riceve dati che convertirà in HttpResponse e HttpResult
    public HttpTask(String url, Map<String, String> params) throws MalformedURLException {
        this.params = new HashMap<>(params); // Clone
        //creo l'url in base al metodo che mi serve nella servlet
        if(Objects.equals(params.get("method"), "GET")){
            //inserisco nell'URL i parametri per la doGET
            this.url = new URL(url.concat(Objects.requireNonNull(params.get("url"))));
        }else {
            this.url = new URL(url);
        }
    }

    @SafeVarargs
    @Override
    //'..' è come '*' su python, permette di ricevere x argomenti in parametro
    protected final HttpTaskResult<HttpResponse> doInBackground(OnHttpTaskCompleted<HttpResponse>... callbacks) {
        this.callbacks = callbacks;

        StringBuilder builder = new StringBuilder();
        int responseCode;
        HttpURLConnection conn = null;

        try {

            StringBuilder postData = new StringBuilder();
            //se il metodo richiesto è GET non può contenere corpo la richiesta
            if (!(Objects.equals(params.get("method"), "GET"))) {
                for (Map.Entry<String, String> param : params.entrySet()) {
                    if (postData.length() != 0) postData.append('&');
                    postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                    postData.append('=');
                    postData.append(URLEncoder.encode(param.getValue(), "UTF-8"));
                }
            }
            //array di byte perché getOutputStream() riceve come parametro un array di byte
            byte[] postDataBytes = postData.toString().getBytes(StandardCharsets.UTF_8);

            //stabilisco connessione con la servlet
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(500);
            //Inserisco il metodo da evocare
            conn.setRequestMethod(params.get("method"));
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            if(!(Objects.equals(params.get("method"), "GET"))) {
                conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
                conn.getOutputStream().write(postDataBytes);
            }
            InputStream in;
            // controllo che non ci siano errori nella connessione al server (http manda codice es 404)
            if ((responseCode = conn.getResponseCode()) < HttpURLConnection.HTTP_BAD_REQUEST) {
                in = conn.getInputStream();
            } else {
                in = conn.getErrorStream();
                if (in == null) {
                    return new HttpTaskResult<>(new HttpResponse("", responseCode));
                }
            }

            in = new BufferedInputStream(in);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            //inserisco quello che ho ricevuto nel builder
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

        } catch (IOException e) {
            Log.e("HttpTaskError", e.getMessage(), e);
            return new HttpTaskResult<>(e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return new HttpTaskResult<>(new HttpResponse(builder.toString(), responseCode));
    }

    //Interfaccia necessaria per passare il risultato direttamente alla repository chiamante
    @Override
    protected void onPostExecute(HttpTaskResult<HttpResponse> result) {
        for (OnHttpTaskCompleted<HttpResponse> callback : callbacks) {
            callback.onHttpTaskCompleted(result);
        }
    }
}
