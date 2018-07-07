package com.morpheus.chatavanzado;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.morpheus.chatavanzado.WebService.Constantes;
import com.morpheus.chatavanzado.WebService.VolleyRP;

import org.json.JSONObject;

public class ChatActivity extends AppCompatActivity
{
    private EditText edtUsuario, edtContraseña;
    private Button btIngresar;
    private String url = Constantes.HOST + "login_GETUSER.php";
    private VolleyRP volleyRP;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //Elemento de Volley
        volleyRP = VolleyRP.getInstance(this);
        requestQueue = volleyRP.getRequestQueue();

        edtUsuario = (EditText)findViewById(R.id.edtUsuario);
        edtContraseña = (EditText)findViewById(R.id.edtContraseña);
        btIngresar = (Button)findViewById(R.id.btIngresar);

        btIngresar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                url += "?user=" + edtUsuario.getText().toString().trim();
                solicitudJSON(url);
            }
        });
    }

    public void verificarLogin(JSONObject datos)
    {
        Toast.makeText(this, datos.toString(), Toast.LENGTH_SHORT).show();
    }

    public void solicitudJSON(String url)
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Espere, por favor");
        progressDialog.setCancelable(false);
        progressDialog.show();

        JsonObjectRequest solicitud = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                progressDialog.dismiss();
                verificarLogin(response);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                progressDialog.dismiss();
                Toast.makeText(ChatActivity.this, "Ocurrio un error, por favor contacte con el administrador", Toast.LENGTH_SHORT).show();
            }
        });

        //Manda la solicitud
        VolleyRP.addToQueue(solicitud, requestQueue, this, volleyRP);
    }
}
