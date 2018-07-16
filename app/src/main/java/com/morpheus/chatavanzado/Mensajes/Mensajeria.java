package com.morpheus.chatavanzado.Mensajes;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.morpheus.chatavanzado.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Morpheus on 16/07/2018.
 */

public class Mensajeria extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private List<MensajeDeTexto> mensajeDeTextos;
    private MensajeAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mensajeria);

        //Le da funcionalidad al icono de la barra
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mensajeDeTextos = new ArrayList<>();

        //Crea una lista de mensajes auxiliares
        for(int i = 0; i < 10; i++)
        {
            MensajeDeTexto mensajeDeTexto = new MensajeDeTexto();

            mensajeDeTexto.setId(String.valueOf(i));
            mensajeDeTexto.setMensaje("Hola " + i);
            mensajeDeTexto.setTipoMensaje(true);
            mensajeDeTexto.setHora("10:34");

            mensajeDeTextos.add(mensajeDeTexto);
        }

        adapter = new MensajeAdapter(mensajeDeTextos);
        recyclerView.setAdapter(adapter);

        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
    }
}
