package com.example.myrecyclerviewexample;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.List;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by kukresa on 11/27/2017.
 */

public class Controller {
    //Testing
    private static Controller controllerInstace;
    private int CUSTOM_DEFAULT_MAX_RETRIES =1;
    private int MAXIMUM_TIME_OUT = 60000;
    private static RequestQueue mRequestQueue ;
    private static ImageLoader mImageLoader;

    public static Controller getInstance(Context context ) {

        if(controllerInstace == null){
            controllerInstace = new Controller(context);
        }
        return controllerInstace;
    }

    private Controller(Context context) {
        mRequestQueue = MySingleton.getInstance(context).getRequestQueue();
        mImageLoader = MySingleton.getInstance(context).getImageLoader();
    }


    public void makeNetworkCalls(int methodType, String url,Response.Listener responseListener , Response.ErrorListener errorListener){

        Log.d("TEST","Making Network Calls");
        JsonArrayRequest request = new JsonArrayRequest(methodType,url,null,responseListener,errorListener);
        request.setRetryPolicy(new DefaultRetryPolicy(MAXIMUM_TIME_OUT, CUSTOM_DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.setShouldCache(true);
        mRequestQueue.add(request);
    }
}
