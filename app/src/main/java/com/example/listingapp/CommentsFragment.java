package com.example.listingapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CommentsFragment extends Fragment {
Context context;
private RecyclerView commentsRv;

private ArrayList<ModelComments> commentsList;
private AdapterComments adapterComments;


    public CommentsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comments, container, false);
        commentsRv = view.findViewById(R.id.commentsRv);
        commentsList = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<List<ModelComments>> call=service.getComments(2);
        call.enqueue(new Callback<List<ModelComments>>() {
            @Override
            public void onResponse(Call<List<ModelComments>> call, Response<List<ModelComments>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context, "Response Failed"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                commentsList.addAll(response.body());
                adapterComments = new AdapterComments(context, commentsList);
                commentsRv.setAdapter(adapterComments);
                Toast.makeText(context, "done", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onFailure(Call<List<ModelComments>> call, Throwable t) {

            }
        });

 return view;
    }
}