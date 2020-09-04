package com.example.listingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class AdapterPosts extends RecyclerView.Adapter<AdapterPosts.HolderPosts>{

    private Context context;
    public List<PostsModel> postsList;

    public AdapterPosts(Context context, List<PostsModel> postsList) {
        this.context = context;
        this.postsList = postsList;
    }

    class HolderPosts extends RecyclerView.ViewHolder{
        private TextView userIdTv, idTv,titleTv,bodyTv;

        public HolderPosts(@NonNull View itemView) {
            super(itemView);
            userIdTv = itemView.findViewById(R.id.userIdTv);
            idTv =itemView.findViewById(R.id.idTv);
            titleTv = itemView.findViewById(R.id.titleTv);
            bodyTv = itemView.findViewById(R.id.bodyTv);
        }
    }

    @NonNull
    @Override
    public HolderPosts onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_posts,parent,false);

        return new HolderPosts(view);



    }

    @Override
    public void onBindViewHolder(@NonNull HolderPosts holder, int position) {
        //postsList = new ArrayList<>();
       // postsList.get(0);
        PostsModel postsModel = postsList.get(position);



      holder.userIdTv.setText(String.valueOf(postsModel.getUserId()));
        //holder.userIdTv.setText(postsModel.getUserId());
        //holder.idTv.setText(postsModel.getId());
        holder.idTv.setText(String.valueOf(postsModel.getId()));
        holder.titleTv.setText(postsModel.getTitle());
       // holder.titleTv.setText(String.valueOf(postsModel.getTitle()));
       holder.bodyTv.setText(postsModel.getBody());
      //  holder.bodyTv.setText(String.valueOf(postsModel.getBody()));

    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public void setData(@NonNull List<PostsModel> list){
        if(list.size() > 0) {
            postsList = list;
        }
        this.notifyDataSetChanged();
    }


}
