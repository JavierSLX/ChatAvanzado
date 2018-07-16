package com.morpheus.chatavanzado.Mensajes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.morpheus.chatavanzado.R;

import java.util.List;

/**
 * Created by Morpheus on 16/07/2018.
 */

public class MensajeAdapter extends RecyclerView.Adapter<MensajeAdapter.MensajeHolder>
{
    private List<MensajeDeTexto> mensajeDeTextos;

    public MensajeAdapter(List<MensajeDeTexto> mensajeDeTextos)
    {
        this.mensajeDeTextos = mensajeDeTextos;
    }

    @Override
    public MensajeHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_mensaje, parent, false);
        return new MensajeHolder(view);
    }

    @Override
    public void onBindViewHolder(MensajeHolder holder, int position)
    {
        holder.txtMensaje.setText(mensajeDeTextos.get(position).getMensaje());
        holder.txtHora.setText(mensajeDeTextos.get(position).getHora());
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

        public MensajeHolder(View itemView)
        {
            super(itemView);
            txtMensaje = (TextView)itemView.findViewById(R.id.txtMensaje);
            txtHora = (TextView)itemView.findViewById(R.id.txtHora);
            container = (LinearLayout)itemView.findViewById(R.id.container);
        }
    }
}
