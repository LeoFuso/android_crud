package mackleaps.csbc.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import mackleaps.csbc.MainActivity;

/**
 * Created by Leonardo Fuso on 31/05/17.
 */

public class NetworkManager {
    private static final String TAG = "NetworkManager";
    private static NetworkManager instance = null;

    private static final String prefixURL = "http://localhost:8080/Register/services/registro/";

    //for Volley API
    public RequestQueue requestQueue;

    private NetworkManager(Context context)
    {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        //other stuf if you need
    }

    public static synchronized NetworkManager getInstance(Context context)
    {
        if (null == instance)
            instance = new NetworkManager(context);
        return instance;
    }

    //this is so you don't need to pass context each time
    public static synchronized NetworkManager getInstance()
    {
        if (null == instance)
        {
            throw new IllegalStateException(NetworkManager.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }

    public void somePostRequestReturningString(Object param1, final NetworkListener<String> listener)
    {

        String url = prefixURL + "create";

        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("param1", param1);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(jsonParams),
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Log.d(TAG + ": ", "somePostRequest Response : " + response.toString());
                        if(null != response.toString())
                            listener.getResult(response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        if (null != error.networkResponse)
                        {
                            listener.getResult("Error Response code: " + error.networkResponse.statusCode);
                        }
                    }
                });

        requestQueue.add(request);
    }

    public void genericPOST(String json, final NetworkListener<String> listener) throws JSONException {
        String url = prefixURL + "create";
        Log.d(TAG, url);

        JSONObject jsonBody = new JSONObject(json);

        final String requestBody = jsonBody.toString();

        Log.d(TAG, requestBody);

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG + ": ", "somePostRequest Response : " + response.toString());
                        if(null != response.toString())
                            listener.getResult(response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (null != error.networkResponse)
                        {
                            listener.getResult("Error Response code: " + error.networkResponse.statusCode);
                        }
                    }
                }
        ) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
            // this is the relevant method
            @Override
            public byte[] getBody()  throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    Log.d(TAG + ": ", "Unsupported Encoding while trying to get the bytes of %s using %s");
                    return null;
                }
            }
        };
        requestQueue.add(postRequest);
    }

    public void genericGET(String address, final NetworkListener<String> listener){
        Log.d(TAG + ": ", address);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, address, null,
                        new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG + ": ", "somePostRequest Response : " + response.toString());
                        if(null != response.toString())
                            listener.getResult(response.toString());
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        if (null != error.networkResponse)
                        {
                            listener.getResult("Error Response code: " + error.networkResponse.statusCode);
                        }
                    }
                });

        Log.d(TAG + ": ", jsObjRequest.toString());

        requestQueue.add(jsObjRequest);
    }
}


