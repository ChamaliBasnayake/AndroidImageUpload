package com.example.function;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ShowImagesActivity extends AppCompatActivity {

    private DatabaseHandler objectDatabaseHandler;
    private RecyclerView objectRecyclerView;

    private RVAdapter objectRVAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_images);
        try
        {
            objectRecyclerView = findViewById(R.id.imagesRV);
            objectDatabaseHandler = new DatabaseHandler(this);
        }
        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    public void getData(View view)
    {
        try {
            objectRVAdapter = new RVAdapter(objectDatabaseHandler.getAllImagesData());
            objectRecyclerView.setHasFixedSize(true);

            objectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            objectRecyclerView.setAdapter(objectRVAdapter);
        }
        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
