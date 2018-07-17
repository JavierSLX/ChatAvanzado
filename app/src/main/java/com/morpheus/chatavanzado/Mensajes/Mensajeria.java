package com.morpheus.chatavanzado.Mensajes;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.morpheus.chatavanzado.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Morpheus on 16/07/2018.
 */

public class Mensajeria extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private EditText editText;
    private List<MensajeDeTexto> mensajeDeTextos;
    private MensajeAdapter adapter;
    private int TEXT_LINES = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mensajeria);

        //Le da funcionalidad al icono de la barra
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        editText = (EditText)findViewById(R.id.edtMensaje);
        Button btEnviar = (Button)findViewById(R.id.btEnviar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mensajeDeTextos = new ArrayList<>();

        //Crea una lista de mensajes auxiliares
        /*for(int i = 0; i < 50; i++)
        {
            MensajeDeTexto mensajeDeTexto = new MensajeDeTexto();

            mensajeDeTexto.setId(String.valueOf(i));
            mensajeDeTexto.setMensaje("Hola " + i);
            mensajeDeTexto.setTipoMensaje(i % 2 == 0);
            mensajeDeTexto.setHora("10:34");

            mensajeDeTextos.add(mensajeDeTexto);
        }*/

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

        //Botón Enviar
        btEnviar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(editText.getText().length() > 0)
                {
                    crearMensaje(editText.getText().toString().trim());

                    //Limpia el edittext y arrastra el scroll
                    editText.setText("");
                    setScrollBarChat();
                }
                else
                    Toast.makeText(Mensajeria.this, "Escriba texto", Toast.LENGTH_SHORT).show();
            }
        });

        //Metodo del edittext para cuando cambia y suba el recycler
        editText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                //Checa si salto linea
                if(editText.getLayout().getLineCount() != TEXT_LINES)
                    setScrollBarChat();
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });
    }

    private void crearMensaje(String texto)
    {
        Calendar calendar = Calendar.getInstance();
        String hora = String.format(Locale.getDefault(), "%02d", calendar.get(Calendar.AM_PM));
        String minuto = String.format(Locale.getDefault(), "%02d", calendar.get(Calendar.MINUTE));

        MensajeDeTexto mensaje = new MensajeDeTexto();
        mensaje.setId("0");
        mensaje.setMensaje(texto);
        mensaje.setTipoMensaje(false);
        mensaje.setHora(hora + ":" + minuto);

        //Agrega a la lista y notifica al adaptador que cambie
        mensajeDeTextos.add(mensaje);
        adapter.notifyDataSetChanged();
    }

    private void setScrollBarChat()
    {
        //Arrastra el scroll hasta el mensaje final
        recyclerView.scrollToPosition(adapter.getItemCount() - 1);
    }
}
