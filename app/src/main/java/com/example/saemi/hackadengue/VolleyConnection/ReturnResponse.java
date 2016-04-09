package com.example.saemi.hackadengue.VolleyConnection;

/**
 * Created by Jonathan on 09/04/16.
 */

import android.app.Activity;

import com.example.saemi.hackadengue.MapsActivity;

/**
 * Created by paulo on 09/04/16.
 */
public class ReturnResponse {
    private static ReturnResponse ourInstance = new ReturnResponse();

    public static ReturnResponse getInstance() {
        return ourInstance;
    }

    private ReturnResponse() {
    }

    public void goTo(String whoCalled, String response, Activity activity) {
        if (whoCalled.equals("getPoints"))
            MapsActivity.returnConnectionGetClient(activity, response);
    }
}