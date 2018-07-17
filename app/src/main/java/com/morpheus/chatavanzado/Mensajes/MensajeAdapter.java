package com.morpheus.chatavanzado.Mensajes;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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

        //holder.setIsRecyclable(false);

        //Cuando el mensaje es entrante cambia el color y la posicion de aparicion
        RelativeLayout.LayoutParams layoutParamsCard = (RelativeLayout.LayoutParams)holder.cardView.getLayoutParams();
        FrameLayout.LayoutParams frameParamsLinear = (FrameLayout.LayoutParams)holder.container.getLayoutParams();

        LinearLayout.LayoutParams linearParamsHora = (LinearLayout.LayoutParams)holder.txtHora.getLayoutParams();
        LinearLayout.LayoutParams linearParamsMensaje = (LinearLayout.LayoutParams)holder.txtMensaje.getLayoutParams();

        //EMISOR
        if(!mensajeDeTextos.get(position).isTipoMensaje())
        {
            holder.container.setBackgroundResource(R.drawable.in_message_bg);

            //Se obtienen los parametros de la cardview (y lo manda a la derecha)
            layoutParamsCard.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
            layoutParamsCard.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            frameParamsLinear.gravity = Gravity.RIGHT;

            //Parametros de ubicacion de la fecha y mensaje
            linearParamsHora.gravity = Gravity.RIGHT;
            linearParamsMensaje.gravity = Gravity.RIGHT;
            holder.txtMensaje.setGravity(Gravity.RIGHT);
        }
        //RECEPTOR
        else
        {
            holder.container.setBackgroundResource(R.drawable.out_message_bg);

            //Se obtienen los parametros de la cardview (y lo manda a la izquierda)
            layoutParamsCard.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            layoutParamsCard.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            frameParamsLinear.gravity = Gravity.LEFT;

            //Parametros de ubicacion de la fecha
            linearParamsHora.gravity = Gravity.LEFT;
            linearParamsMensaje.gravity = Gravity.LEFT;
        }

        holder.cardView.setLayoutParams(layoutParamsCard);
        holder.container.setLayoutParams(frameParamsLinear);
        holder.txtHora.setLayoutParams(linearParamsHora);
        holder.txtMensaje.setLayoutParams(linearParamsMensaje);

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
