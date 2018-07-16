package com.morpheus.chatavanzado.Mensajes;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.morpheus.chatavanzado.R;

import java.util.List;

/**
 * Created by Morpheus on 16/07/2018.
 */

public class MensajeAdapter extends RecyclerView.Adapter<MensajeAdapter.MensajeHolder>
{
    private List<MensajeDeTexto> mensajeDeTextos;
    private Context context;

    public MensajeAdapter(List<MensajeDeTexto> mensajeDeTextos)
    {
        this.mensajeDeTextos = mensajeDeTextos;
    }

    @Override
    public MensajeHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_mensaje, parent, false);
        return new MensajeHolder(view);
    }

    @Override
    public void onBindViewHolder(MensajeHolder holder, final int position)
    {
        holder.txtMensaje.setText(mensajeDeTextos.get(position).getMensaje());
        holder.txtHora.setText(mensajeDeTextos.get(position).getHora());

        //Cuando el mensaje es entrante cambia el color y la posicion de aparicion
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)holder.cardView.getLayoutParams();
        if(!mensajeDeTextos.get(position).isTipoMensaje())
        {
            holder.container.setBackgroundResource(R.drawable.in_message_bg);

            //Se obtienen los parametros de la cardview (y lo manda a la derecha)
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        }
        else
        {
            holder.container.setBackgroundResource(R.drawable.out_message_bg);

            //Se obtienen los parametros de la cardview (y lo manda a la izquierda)
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        }

        holder.cardView.setLayoutParams(layoutParams);

        holder.container.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(context, String.valueOf(mensajeDeTextos.get(position).isTipoMensaje()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mensajeDeTextos.size();
    }

    public static class MensajeHolder extends RecyclerView.ViewHolder
    {
        TextView txtMensaje, txtHora;
        LinearLayout container;
        CardView cardView;

        public MensajeHolder(View itemView)
        {
            super(itemView);
            txtMensaje = (TextView)itemView.findViewById(R.id.txtMensaje);
            txtHora = (TextView)itemView.findViewById(R.id.txtHora);
            container = (LinearLayout)itemView.findViewById(R.id.container);
            cardView = (CardView)itemView.findViewById(R.id.cardView);
        }
    }
}
