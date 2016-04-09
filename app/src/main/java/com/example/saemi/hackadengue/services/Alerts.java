package com.example.saemi.hackadengue.services;

/**
 * Created by Jonathan on 09/04/16.
 */

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class Alerts {
    private static Alerts ourInstance = new Alerts();

    public static Alerts getInstance() {
        return ourInstance;
    }

    private Alerts() {
    }

    public static void menssage(String title, String message, Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setMessage(message);
        builder.setTitle(title);
        builder.create().show();
    }
//
//    public void aceitaExcluirCliente(Activity activity){
//        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Connection task = new Connection("url", Request.Method.DELETE, "deleteClient",AllActivitys.clienteActivity);
//                task.callByJsonStringRequest();
//                dialog.cancel();
//            }
//        });
//        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//        builder.setMessage("Tem certeza que deseja excluir esse cliente?");
//        builder.setTitle("Aviso!");
//        builder.create().show();
//    }

}