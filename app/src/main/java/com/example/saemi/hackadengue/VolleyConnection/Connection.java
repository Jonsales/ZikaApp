package com.example.saemi.hackadengue.VolleyConnection;

/**
 * Created by Jonathan on 09/04/16.
 */
import android.app.Activity;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.saemi.hackadengue.services.Alerts;
import com.example.saemi.hackadengue.services.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by paulo on 09/04/16.
 */
public class Connection {


    private String login;
    private String password;
    private Activity activity;
    private RequestQueue rq;
    private HashMap<String, String> params;
    public String url;
    public View view;
    private int METHOD;
    private String whoCalled;

    public Connection(String url, int METHOD, String whoCalled, Activity activity){
        this.url = url;
        this.METHOD = METHOD;
        this.whoCalled = whoCalled;
        this.activity = activity;

        rq = Volley.newRequestQueue(activity);
    }
    public Connection(String url, int METHOD, String whoCalled, Activity activity, HashMap<String, String> params){
        this.url = url;
        this.METHOD = METHOD;
        this.whoCalled = whoCalled;
        this.activity = activity;
        this.params = params;

        rq = Volley.newRequestQueue(activity);
    }

    public void callByJsonStringRequest(){
        StringRequest request = new StringRequest(METHOD, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (whoCalled.equals("login")) {

                        }

                        ReturnResponse.getInstance().goTo(whoCalled, response, getActivity());
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            String statusCode = String.valueOf(error.networkResponse.statusCode);
                            String mensagem = new String(error.networkResponse.data);
                            if (Constants.debug) {
                                String e = new String(String.valueOf(error));
                                if (error.getMessage() == null)
                                    Alerts.menssage("Erro", e, getActivity());
                                else
                                    Alerts.menssage(statusCode + " - Erro!", error.getMessage(), getActivity());
                                Alerts.menssage("Erro Mensagem", mensagem, getActivity());
                            } else
                                ReturnErrors.errors(whoCalled, getActivity(), statusCode);
                        }catch (Exception e){
                            Alerts.menssage("Erro", String.valueOf(error), getActivity());
                        }
                    }
                }){

            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
//                String base64EncodedCredentials = "Basic " + Base64.encodeToString(((
//                                getLogin() != null ? getLogin() : Preferences.getPreferece("login", getActivity()))
//                                + ":" +
//                                (getPassword() != null ? getPassword() : Preferences.getPreferece("password", getActivity()))).getBytes(),
//                        Base64.NO_WRAP);
                HashMap<String, String> header = new HashMap<String, String>();
                header.put("Content-Type", "application/json; charset=utf-8");
                //header.put("Content-Type","application/x-www-form-urlencoded");
                header.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
//                header.put("authorization", base64EncodedCredentials);

                return header;
            }

        };
        request.setTag("tag");
        rq.add(request);

    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMETHOD() {
        return METHOD;
    }

    public void setMETHOD(int METHOD) {
        this.METHOD = METHOD;
    }

    public String getWhoCalled() {
        return whoCalled;
    }

    public void setWhoCalled(String whoCalled) {
        this.whoCalled = whoCalled;
    }
}