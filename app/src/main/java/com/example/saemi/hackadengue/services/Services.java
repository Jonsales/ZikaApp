package com.example.saemi.hackadengue.services;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Environment;

import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Jonathan on 22/04/2015.
 */
public class Services {
      public static ArrayList uriString;
      public static double latitude, longitude;
      public Activity activity;
      static LatLng inicio;
      static double seekbarKm;
      public static String idOferta, Text, uriCortada, produto, marca;
      private static boolean init = false;

      /*
      ************************************GETTERS E SETTERS*******************************************
      */
      public void setInit(boolean init){
            this.init = init;
      }

      public boolean getInit(){
            return init;
      }

      public String getProduto(){
            return produto;
      }

      public void setProduto(String produto){
            this.produto = produto;
      }

      public String getMarca(){
            return marca;
      }

      public void setMarca(String marca){
            this.marca = marca;
      }

      public double getLatitude() { return latitude;}

      public void setLatitude(double latitude) { this.latitude = latitude;}

      public double getLongitude() { return longitude;}

      public void setLongitude(double longitude) { this.longitude = longitude;}

      public String getIdOferta(){
            return idOferta;
      }

      public void setIdOferta(String idOferta){
            this.idOferta = idOferta;
      }

      public double getSeekbarKm() {
            return seekbarKm;
      }

      public void setSeekbarKm(double seekbarKm) {
            this.seekbarKm = seekbarKm;
      }

      public static LatLng getLatLngInicio() {
            return inicio;
      }

      public void setLatLngInicio(LatLng inicio) {
            this.inicio = inicio;
      }

      public static String getUriCortada() {
            return uriCortada;
      }

      public void setUriCortada(String uriCortada) {
            this.uriCortada = uriCortada;
      }

      public String getText() {
            return Text;
      }

      public void setText(String text) {
            this.Text = text;
      }

      public ArrayList getUriString() {
            return uriString;
      }

      public void setUriString(ArrayList uriString) {
            Services.uriString = uriString;
      }

      /*
      *******************************************UTILS**********************************************
      */

      public void getProdutoMarca(String desc) {

            if (desc.length() > 0 || !(desc.equals(null))) {
                  StringTokenizer idSplit = new StringTokenizer(desc, "-");
                  this.setProduto(idSplit.nextToken());
                  this.setMarca(idSplit.nextToken());
            }
      }

      public void devolveUri(String produto) {
            String uri, produtoDesc;
            try{
            if (uriString.size() > 0 || !(uriString.equals(null))) {
                  setUriCortada("");
                  for (int i = 0; i <= (uriString.size()) - 1; i++) {
                        StringTokenizer idSplit = new StringTokenizer(uriString.get(i).toString(), "-");
                        uri = idSplit.nextToken();
                        produtoDesc = idSplit.nextToken();
                        if (produtoDesc.equals(produto)) {
                              setUriCortada(uri);
                              break;
                        }

                  }
            }
            else
                  setUriCortada("");
            }catch(Exception e){
                  setUriCortada("");
            }
      }

      public double CalculaKm(LatLng destino) {
            double sLatitudeRadians = destino.latitude * (Math.PI / 180.0);
            double sLongitudeRadians = destino.longitude * (Math.PI / 180.0);
            double eLatitudeRadians = inicio.latitude * (Math.PI / 180.0);
            double eLongitudeRadians = inicio.longitude * (Math.PI / 180.0);

            double dLongitude = eLongitudeRadians - sLongitudeRadians;
            double dLatitude = eLatitudeRadians - sLatitudeRadians;

            // Using 3956 as the number of miles around the earth and 6371.0 for km
            double result = 6371.0 * 2.0 *
                      Math.atan2(Math.sqrt(Math.pow(Math.sin(dLatitude / 2.0), 2.0) +
                                Math.cos(sLatitudeRadians) * Math.cos(eLatitudeRadians) *
                                          Math.pow(Math.sin(dLongitude / 2.0), 2.0)), Math.sqrt(1.0 - Math.pow(Math.sin(dLatitude / 2.0), 2.0) +
                                Math.cos(sLatitudeRadians) * Math.cos(eLatitudeRadians) *
                                          Math.pow(Math.sin(dLongitude / 2.0), 2.0)));
            return result;
      }

      public LatLng devolveLatLng(String LatLng) {
            double lat = 0, lng = 0;
            StringTokenizer idSplit = new StringTokenizer(LatLng, ";");
            lat = Double.parseDouble(idSplit.nextToken());
            lng = Double.parseDouble(idSplit.nextToken());
            setLatitude(lat);
            setLongitude(lng);
            LatLng destino = new LatLng(lat, lng);
            return destino;
      }

      private Bitmap loadImgCustomerFromFile(Bitmap id) {
            String imagesPath = Environment.getExternalStorageDirectory() + "/bestPrice/Mercados/";

            String filename = imagesPath + id;

            File file = new File(filename);

            if (file.exists()) {

                  Bitmap image;
                  image = BitmapFactory.decodeFile(filename);

                  if (image != null) {
                        image = getRoundedCornerBitmap(image);
                        return image;
                  } else {
                        return null;
                  }

            } else {
                  return null;
            }

      }

      public Bitmap getRoundedCornerBitmap(Bitmap pBitmap) {

            int width = 150;
            int height = pBitmap.getHeight() * 150 / pBitmap.getWidth();


            Bitmap bitmap = Bitmap.createScaledBitmap(pBitmap, width, height, true);

            int heightDiff = (width - height) / 4;
            height = width;
            Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(output);

            final int color = 0xffffffff;
            final Paint paint = new Paint();
            final Rect rect = new Rect(2, 2, width, height);

            final RectF rectF = new RectF(rect);
            final float roundPx = width / 4;

            paint.setAntiAlias(true);

            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

            canvas.drawBitmap(bitmap, 2, heightDiff, paint);

            return output;

      }

      public static void CopyStream(InputStream is, OutputStream os){
            final int buffer_size=1024;
            try
            {
                  byte[] bytes=new byte[buffer_size];
                  for(;;)
                  {
                        int count=is.read(bytes, 0, buffer_size);
                        if(count==-1)
                              break;
                        os.write(bytes, 0, count);
                  }
            }
            catch(Exception ex){}
      }

}
