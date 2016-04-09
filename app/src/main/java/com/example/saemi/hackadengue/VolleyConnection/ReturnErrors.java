package com.example.saemi.hackadengue.VolleyConnection;

/**
 * Created by Jonathan on 09/04/16.
 */
import android.app.Activity;

import com.example.saemi.hackadengue.services.Alerts;

public class ReturnErrors {

    public static void errors(String whoCalled, Activity activity, String statusCode) {
        if (whoCalled.equals("login")) {
            Alerts.menssage("Erro!", "Erro ao trazer os clientes", activity);
        }

    }
}