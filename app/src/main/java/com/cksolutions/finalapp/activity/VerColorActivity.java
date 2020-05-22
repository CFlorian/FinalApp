package com.cksolutions.finalapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cksolutions.finalapp.R;
import com.cksolutions.finalapp.adapter.ListCustomAdapter;
import com.cksolutions.finalapp.model.ColorModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VerColorActivity extends AppCompatActivity {

    private ListView listView;
    private RequestQueue requestQueue;
    private ProgressDialog dialog;
    private ArrayList<ColorModel> arrayColor = new ArrayList<ColorModel>();
    private List<String> dataColor ;
    private ListCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_color);
        initUI();
        peticionData();
        adapter = new ListCustomAdapter(getApplicationContext(),arrayColor);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }

    private void initUI() {
        listView = findViewById(R.id.lvListView);
        requestQueue = Volley.newRequestQueue(this);
        arrayColor = new ArrayList<>();
    }

    private void peticionData() {

        arrayColor.clear();
        dialog = new ProgressDialog(VerColorActivity.this);
        dialog.setMessage("Recuperando Datos...");
        dialog.setCancelable(false);
        dialog.show();
        JsonObjectRequest getDatosWS = new JsonObjectRequest(Request.Method.GET, "https://pruebasck.000webhostapp.com/CAEXATWS.php?function=ObtenerColor&color", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("TAG", "Response " + response);

                        try {
                            JSONArray jsonArray = new JSONArray(response.getString("data"));
                            for (int i=0; i<jsonArray.length(); i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                int red = data.getInt("rojo");
                                int green = data.getInt("verde");
                                int blue = data.getInt("azul");
                                String name = data.getString("nombre");

                                Log.e("TAG", "Data " + name + " RGB " + red+green+blue);
                                arrayColor.add(new ColorModel(red,green,blue,name));

                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.notifyDataSetChanged();
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG", "error Array " + e.getMessage());
                        }
                        /*try {
                            boolean estado =  response.getBoolean("status");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/
                        dialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", "error response " + error.getMessage());
                        dialog.dismiss();
                    }
                }
        );
        requestQueue.add(getDatosWS);
    }
}
