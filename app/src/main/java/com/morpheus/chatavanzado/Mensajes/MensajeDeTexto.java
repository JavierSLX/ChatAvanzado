package com.morpheus.chatavanzado.Mensajes;

/**
 * Created by Morpheus on 16/07/2018.
 */

public class MensajeDeTexto
{
    private String id;
    private String mensaje;
    private boolean tipoMensaje;
    private String hora;

    public MensajeDeTexto()
    {
    }

    public MensajeDeTexto(String id, String mensaje, boolean tipoMensaje, String hora)
    {
        this.id = id;
        this.mensaje = mensaje;
        this.tipoMensaje = tipoMensaje;
        this.hora = hora;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getMensaje()
    {
        return mensaje;
    }

    public void setMensaje(String mensaje)
    {
        this.mensaje = mensaje;
    }

    public boolean isTipoMensaje()
    {
        return tipoMensaje;
    }

    public void setTipoMensaje(boolean tipoMensaje)
    {
        this.tipoMensaje = tipoMensaje;
    }

    public String getHora()
    {
        return hora;
    }

    public void setHora(String hora)
    {
        this.hora = hora;
    }
}
