package com.example.coursehub.service;

import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.coursehub.environemnt.Environment;

import org.json.JSONObject;

public class AuthService extends AsyncTask<Pair<Long, Context, Response.Listener<JSONObject>, Response.ErrorListener>, Void, Void> {

    private void getUserById(Long id, Context context, Response.Listener<JSONObject> response, Response.ErrorListener err) {

        final String userDetailsUrl = Environment.getBaseUrl() + "user/" + id;

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, userDetailsUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                response.onResponse(jsonObject);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                err.onErrorResponse(volleyError);

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    protected Void doInBackground(Pair<Long, Context, Response.Listener<JSONObject>, Response.ErrorListener>... pairs) {

        Long id = pairs[0].first;
        Context context = pairs[0].second;
        Response.Listener<JSONObject> response = pairs[0].third;
        Response.ErrorListener err = pairs[0].fourth;
        getUserById(id, context, response, err);
        return null;
    }
}
