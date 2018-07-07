package com.morpheus.chatavanzado.Entidades;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Morpheus on 07/07/2018.
 */

public class Credencial implements Parcelable
{
    private String id;
    private String user;
    private String pass;

    public Credencial(String id, String user, String pass)
    {
        this.id = id;
        this.user = user;
        this.pass = pass;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.id);
        dest.writeString(this.user);
        dest.writeString(this.pass);
    }

    protected Credencial(Parcel in)
    {
        this.id = in.readString();
        this.user = in.readString();
        this.pass = in.readString();
    }

    public static final Parcelable.Creator<Credencial> CREATOR = new Parcelable.Creator<Credencial>()
    {
        @Override
        public Credencial createFromParcel(Parcel source)
        {
            return new Credencial(source);
        }

        @Override
        public Credencial[] newArray(int size)
        {
            return new Credencial[size];
        }
    };
}
