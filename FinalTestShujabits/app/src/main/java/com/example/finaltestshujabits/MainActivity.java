package com.example.finaltestshujabits;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://fakestoreapi.com/products/categories";
    private ArrayList<String> api_items = new ArrayList<>();
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the RequestQueue
        mRequestQueue = Volley.newRequestQueue(this);

        // Create a spinner
        spinner = findViewById(R.id.spinner_fakestore); // Replace with your Spinner ID

        // Create an ArrayAdapter using your ArrayList
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, api_items);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // Add a default item to the ArrayList
        api_items.add("Select an item");

        // Set the default item as selected
        spinner.setSelection(0);

        // Find the button by its ID
        Button startNextActivityButton = findViewById(R.id.button_start_next_activity);

        // Set a click listener for the button
        startNextActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected item from the spinner
                String selectedValue = (String) spinner.getSelectedItem();

                // Check if the selected item is not the default text
                if (selectedValue.equals("electronics")) {
                    // Create an intent to start the NextActivity
                    Intent intent = new Intent(MainActivity.this, ImageActivity.class);

                    // Pass the selected item as an extra to the NextActivity
                    intent.putExtra("selectedItem", selectedValue);

                    // Start the NextActivity
                    startActivity(intent);
                }
                else  if (selectedValue.equals("jewelery")) {
                    Intent intent = new Intent(MainActivity.this, Jewelery.class);

                    intent.putExtra("selectedItem", selectedValue);

                    startActivity(intent);
                }

                else if (selectedValue.equals("men's clothing")) {
                }

                else if (selectedValue.equals("women's clothing")) {

                }
                else {
                    // Handle the case where no item is selected or "Select an item" is selected
                    Toast.makeText(getApplicationContext(), "Please select an item from the spinner.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Fetch data from the API
        ApiDataFetcher dataFetcher = new ApiDataFetcher(this);
        dataFetcher.fetchData(new ApiDataFetcher.DataFetchListener() {
            @Override
            public void onDataFetched(ArrayList<String> data) {
                // Create an ArrayAdapter using the fetched data
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, data);

                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // Apply the adapter to the spinner
                spinner.setAdapter(adapter);
            }

            @Override
            public void onError(String errorMessage) {
                // Handle the error, e.g., display a message to the user
                Toast.makeText(getApplicationContext(), "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
