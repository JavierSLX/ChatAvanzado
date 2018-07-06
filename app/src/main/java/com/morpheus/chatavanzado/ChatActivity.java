package com.morpheus.chatavanzado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChatActivity extends AppCompatActivity
{
    private EditText edtUsuario, edtContraseña;
    private Button btIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        edtUsuario = (EditText)findViewById(R.id.edtUsuario);
        edtContraseña = (EditText)findViewById(R.id.edtContraseña);
        btIngresar = (Button)findViewById(R.id.btIngresar);

        btIngresar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                verificarLogin(edtUsuario.getText().toString().toLowerCase(), edtContraseña.getText().toString().toLowerCase());
            }
        });
    }

    public void verificarLogin(String user, String pass)
    {
        Toast.makeText(this, "El usuario es: " + user + ". Y la contraseña es: " + pass, Toast.LENGTH_SHORT).show();
    }
}
