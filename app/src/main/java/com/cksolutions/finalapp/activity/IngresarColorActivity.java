package com.cksolutions.finalapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cksolutions.finalapp.R;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class IngresarColorActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etRojo, etVerde, etAzul, etNombre;
    private Button btnGrabar;
    private int iRojo, iVerde, iAzul;
    private String sNombre, sRojo, sVerde, sAzul;
    private TextInputLayout tlRojo, tlVerde, tlAzul, tlNombre;
    private RequestQueue requestQueue;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_color);
        initUI();
    }

    private void initUI() {
        etRojo = findViewById(R.id.etRojo);
        etAzul = findViewById(R.id.etAzul);
        etVerde = findViewById(R.id.etVerde);
        etNombre = findViewById(R.id.etNombre);
        tlRojo = findViewById(R.id.textField1);
        tlVerde = findViewById(R.id.textField2);
        tlAzul = findViewById(R.id.textField3);
        tlNombre = findViewById(R.id.textField4);
        btnGrabar = findViewById(R.id.btnGrabar);
        btnGrabar.setOnClickListener(this);
        requestQueue = Volley.newRequestQueue(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGrabar:
                validarInfo();
                break;
        }
    }

    private void validarInfo() {

        if (!etRojo.getText().toString().isEmpty()&&
                !etVerde.getText().toString().isEmpty()&&
                !etAzul.getText().toString().isEmpty()&&
                !etNombre.getText().toString().isEmpty()){

            try {
                iRojo = Integer.parseInt(etRojo.getText().toString());
                iVerde = Integer.parseInt(etVerde.getText().toString());
                iAzul = Integer.parseInt(etAzul.getText().toString());

                if (!(iRojo<=255)) tlRojo.setError("Ingrese numero menor a 255");
                else if (!(iVerde<=255)) tlVerde.setError("Ingrese numero menor a 255");
                else if (!(iAzul<=255)) tlAzul.setError("Ingrese numero menor a 255");
                else {
                    tlRojo.setError(null);
                    tlVerde.setError(null);
                    tlAzul.setError(null);

                    sRojo = etRojo.getText().toString();
                    sVerde = etVerde.getText().toString();
                    sAzul = etAzul.getText().toString();
                    sNombre = etNombre.getText().toString();

                    dialog = new ProgressDialog(IngresarColorActivity.this);
                    dialog.setMessage("Guardando Datos");
                    dialog.setCancelable(false);
                    dialog.show();

                    StringRequest wsInsertColor = new StringRequest(Request.Method.POST, "https://pruebasck.000webhostapp.com/CAEXATWS.php?function=IngresarColor",
                            new Response.Listener<String>()
                            {
                                @Override
                                public void onResponse(final String response) {
                                    Log.e("TAG", "Response wsInsertColor " + response);
                                    dialog.dismiss();
                                    showToast(response);
                                    reiniciaData();
                                }
                            },
                            new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(final VolleyError error) {
                                    Log.e("TAG", "error response " + error);
                                    dialog.dismiss();
                                }
                            }
                    ) {
                        @Override
                        protected Map<String, String> getParams()
                        {
                            Map<String, String>  params = new HashMap<String, String>();
                            params.put("rojo", sRojo);
                            params.put("verde", sVerde);
                            params.put("azul", sAzul);
                            params.put("nombre", sNombre);
                            return params;
                        }
                    };
                    wsInsertColor.setRetryPolicy(new DefaultRetryPolicy(
                            30000,
                            0,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    requestQueue.add(wsInsertColor);
                }

            }catch (Exception e){
                showToast("Ocurrio un error en Cast Valores");
            }
        }else
            showToast("Ingrese valores en todos los campos");
    }

    private void reiniciaData() {
        etNombre.setText("");
        etRojo.setText("");
        etVerde.setText("");
        etAzul.setText("");

        iRojo = 0;
        iVerde = 0;
        iAzul = 0;

        sRojo = "";
        sVerde = "";
        sAzul = "";
        sNombre = "";
    }

    private void showToast(String mensaje) {
        Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_SHORT).show();
    }
}
