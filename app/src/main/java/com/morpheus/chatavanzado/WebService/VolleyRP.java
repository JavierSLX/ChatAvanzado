package com.morpheus.chatavanzado.WebService;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Morpheus on 07/07/2018.
 */

public class VolleyRP
{
    private static VolleyRP volleyRP;
    private RequestQueue queue;

    private VolleyRP(Context context)
    {
        queue = Volley.newRequestQueue(context);
    }

    public static VolleyRP getInstance(Context context)
    {
        if(volleyRP == null)
            volleyRP = new VolleyRP(context);

        return volleyRP;
    }

    public RequestQueue getRequestQueue()
    {
        return queue;
    }

    public static void addToQueue(Request request, RequestQueue queue, Context context, VolleyRP volleyRP)
    {
        if(request != null)
        {
            request.setTag(context);
            if(queue == null)
                queue = volleyRP.getRequestQueue();

            request.setRetryPolicy(new DefaultRetryPolicy(60000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            queue.add(request);
        }
    }
}
