package com.example.listingapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PostsFragment extends Fragment {

    Context context;
    private RecyclerView postsRv;

    private List<PostsModel> postsList;
    private AdapterPosts adapterPosts;
  //  private Object PostsModel;

    public PostsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context =context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_posts, container, false);
        postsRv = view.findViewById(R.id.postsRv);
        postsRv.setHasFixedSize(true);
        postsRv.setLayoutManager(new LinearLayoutManager(context));
        //postsList = new ArrayList<PostsModel>();
        //adapterPosts = new AdapterPosts(context,postsList);
        adapterPosts = new AdapterPosts(getActivity(),postsList);
        postsRv.setAdapter(adapterPosts);
       // postsList = new ArrayList<>();

        loadJsonData();
        return view;


    }

    private void loadJsonData() {
        try{
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/posts")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiService service = retrofit.create(ApiService.class);

            Call<List<PostsModel>> call =service.getPosts(2);
            call.enqueue(new Callback<List<PostsModel>>() {
                @Override
                public void onResponse(Call<List<PostsModel>> call, Response<List<PostsModel>> response) {
                   postsList = response.body();
                    adapterPosts.setData(postsList);

                }


                @Override
                public void onFailure(Call<List<PostsModel>> call, Throwable t) {

                }
            });

        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    // private void loadPostsData(){
     //   postsList = new ArrayList<>();


        //postsList.add(postsModel);

    //}

}
