package com.morpheus.chatavanzado;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.morpheus.chatavanzado.Entidades.Credencial;
import com.morpheus.chatavanzado.WebService.Constantes;
import com.morpheus.chatavanzado.WebService.VolleyRP;

import org.json.JSONException;
import org.json.JSONObject;

public class ChatActivity extends AppCompatActivity
{
    private EditText edtUsuario, edtContraseña;
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
        Button btIngresar = (Button)findViewById(R.id.btIngresar);

        btIngresar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String url = Constantes.HOST + "login_GETUSER.php?user=" + edtUsuario.getText().toString().trim();
                solicitudJSON(url);
            }
        });
    }

    public void verificarLogin(JSONObject datos)
    {
        try
        {
            String resultado = datos.getString("resultado");

            //Las distintas respuestas del JSON
            switch (resultado)
            {
                //Cuando existe el usuario
                case "CC":
                    //Se obtiene el JSON de datos
                    JSONObject usuario = datos.getJSONObject("datos");

                    //Saca los datos del usuario y verifica el logeo
                    Credencial credencial = new Credencial(usuario.getString("id").trim(), usuario.getString("usuario").trim(), usuario.getString("pass").trim());

                    if(credencial.getUser().equals(edtUsuario.getText().toString().trim()) && credencial.getPass().equals(edtContraseña.getText().toString().trim()))
                    {
                        //Inicializa la nueva actividad (pasandole la credencial)
                        Intent intent = new Intent(this, NewActivity.class);
                        intent.putExtra("CREDENCIAL", credencial);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(this, "Acceso no correcto", Toast.LENGTH_SHORT).show();

                    break;

                //Cuando no existe el usuario
                case "SU":
                    Toast.makeText(this, "El usuario no existe", Toast.LENGTH_SHORT).show();
                    break;

                //Cuando no se le mando un usuario a la URL
                case "SD":
                    Toast.makeText(this, "Error al ingresar la URL", Toast.LENGTH_SHORT).show();
                    break;
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(this, "Error JSON Login", Toast.LENGTH_SHORT).show();
        }
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
