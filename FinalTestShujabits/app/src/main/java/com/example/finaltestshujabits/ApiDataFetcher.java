package com.example.finaltestshujabits;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ApiDataFetcher {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://fakestoreapi.com/products/categories";
    private ArrayList<String> api_items = new ArrayList<>();
    private Context context;

    public ApiDataFetcher(Context context) {
        this.context = context;
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public interface DataFetchListener {
        void onDataFetched(ArrayList<String> data);
        void onError(String errorMessage);
    }

    public void fetchData(final DataFetchListener listener) {
        // String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // Parse the response as a JSON array
                    JSONArray jsonArray = new JSONArray(response);

                    // Clear the existing items in the ArrayList
                    api_items.clear();

                    // Add the default item back to the ArrayList
                    api_items.add("Select an item");

                    // Access elements in the JSON array by index
                    for (int i = 0; i < jsonArray.length(); i++) {
                        String item = jsonArray.getString(i);
                        api_items.add(item);    // added items to the array list
//                        Toast.makeText(context.getApplicationContext(), "Item at index " + i + ": " + item, Toast.LENGTH_LONG).show();
                    }

                    // Notify the listener with the fetched data
                    listener.onDataFetched(api_items);

                } catch (JSONException e) {
                    e.printStackTrace();
                    // Handle JSON parsing error here
                    listener.onError("JSON Parsing Error: " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Notify the listener of the error
                listener.onError("Volley Error: " + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }
}
