package com.example.saemi.hackadengue.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jonathan on 24/04/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    static Context context;
    Constants constants= new Constants();
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = Environment.getExternalStorageDirectory() + "/Android/data/br.gtac.bestprice/database/bestprice.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(DATABASE_NAME, null,
                      SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException e) {
            // database doesn't exist yet.
        }
        return checkDB != null ? true : false;
    }

    public long delete(String table, String id){
        SQLiteDatabase db = getWritableDatabase();
        long ret = 0;
        ret = db.delete(table, "id = ?", new String[] { id });
        db.close();
        return  ret;
    }

    public long deleteOfertaProd(String id){
        SQLiteDatabase db = getWritableDatabase();
        Constants ct = new Constants();
        long ret = 0;
        ret = db.delete(ct.TABLE_OFERTA, "offer_id = ?", new String[] { id });
        db.close();
        return  ret;
    }
    public long deleteOfertaLocal( String local){
        SQLiteDatabase db = getWritableDatabase();
        Constants ct = new Constants();
        long ret = 0;
        ret = db.delete(ct.TABLE_OFERTA, "local = ?", new String[] { local });
        db.close();
        return  ret;
    }


    private static boolean ret;

    public boolean checkID(String table, String id){
        SQLiteDatabase db = getWritableDatabase();
        try {
            String selectQuery = "SELECT id FROM " + table + " WHERE id = '" + id + "'";
            Cursor c = db.rawQuery(selectQuery, null);

            if (c.moveToFirst()) {
                c.getString(c.getColumnIndex(constants.ID));
                ret = true;
            } else {
                ret = false;
            }
            c.close();
            db.close();
        }
        catch(SQLiteException e){
            ret = false;
        }
        return ret == true ? true : false;
    }

    public boolean checkOfferId(String sql){
        SQLiteDatabase db = getWritableDatabase();
        try {
            String selectQuery = sql;
            Cursor c = db.rawQuery(selectQuery, null);

            if (c.moveToFirst()) {
                c.getString(c.getColumnIndex(constants.ID));
                ret = true;
            } else {
                ret = false;
            }
            c.close();
            db.close();
        }
        catch(SQLiteException e){
            ret = false;
        }
        return ret == true ? true : false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(getCreateClause(constants.TABLE_LOCAL,
                  constants.ID,
                  constants.LATITUDE,
                  constants.LONGITUDE,
                  constants.LOCAL,
                  constants.RESOURCE_URI));
        db.execSQL(getCreateClause(constants.TABLE_PRODUTO,
                  constants.ID,
                  constants.NAME,
                  constants.TYPE,
                  constants.MARK,
                  constants.UNIT_WEIGHT,
                  constants.IMAGE,
                  constants.RESOURCE_URI,
                  constants.UNIT));

        db.execSQL(getCreateClause(constants.TABLE_USER,
                  constants.ID,
                  constants.FIRST_NAME,
                  constants.EMAIL,
                  constants.LAST_NAME,
                  constants.RESOURCE_URI,
                  constants.DATE_JOINED,
                  constants.IS_ACTIVE,
                  constants.USERNAME));

        db.execSQL(getCreateClause(constants.TABLE_OFERTA,
                  constants.ID,
                  constants.FILED,
                  constants.IMAGEM_PRODUTO,
                  constants.PREVIEW,
                  constants.PRICE,
                  constants.LOCAL,
                  constants.OFFER_ID,
                  constants.OFFER_PRODUCT,
                  constants.OFFER_MARK,
                  constants.RESOURCE_URI));
        }

    private String getCreateClause(String TableName, String... fields) {
        String createClause = "CREATE TABLE " + TableName + " ( " + fields[0] + " TEXT PRIMARY KEY ";
        for (int i = 1; i < fields.length; i++) {
            createClause += ", " + fields[i] + " TEXT ";
        }
        createClause += " ) ";
        return createClause;
    }

    /*****************************************TABELA LOCAL*****************************************************/

    public long addLocal(Local obj) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(constants.ID, obj.getId());
        values.put(constants.LATITUDE, obj.getLatitude());
        values.put(constants.LONGITUDE, obj.getLongitude());
        values.put(constants.LOCAL, obj.getLocal());
        values.put(constants.RESOURCE_URI, obj.getResource_uri());

        long ret = db.insert(constants.TABLE_LOCAL, null, values);

        db.close();

        return ret;

    }
}
