 package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{

    ApiInterface apiInterface;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv);
        apiInterface = retrofitinstance.getRetrofit().create(ApiInterface.class);
        apiInterface.getposts().enqueue(new Callback<List<PostPojo>>()
        {
            @Override
            public void onResponse(Call<List<PostPojo>> call, Response<List<PostPojo>> response)
            {
                if (response.body().size()>0)
                {
                    List<PostPojo> data =response.body();

                    for (int  i=0  ;i<data.size(); i++)
                        textView.append("Serial no:- "+(int)data.get(i).getId() +"\n"+
                                "\n Title:-"+ data.get(i).getTitle()+"\n\n"+
                                "Body:-"+ data.get(i).getBody()+"\n\n\n\n");
                }
                else
                {
                    Toast.makeText(MainActivity.this,"NOt WORKING",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<List<PostPojo>> call, Throwable t)
            {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                
            }
        });

    }
}