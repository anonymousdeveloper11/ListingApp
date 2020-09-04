package com.example.listingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterComments extends RecyclerView.Adapter<AdapterComments.HolderComments>{

    private Context context;
    public ArrayList<ModelComments> commentsList;

    public AdapterComments(Context context, ArrayList<ModelComments> commentsList) {
        this.context = context;
        this.commentsList = commentsList;
    }

    @NonNull
    @Override
    public HolderComments onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_comments,parent,false);
        return new HolderComments(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderComments holder, int position) {
        commentsList = new ArrayList<>();

        ModelComments modelComments = commentsList.get(position);
        holder.postIdTv.setText(modelComments.getPostId());
        holder.idTv.setText(modelComments.getId());
        holder.nameTv.setText(modelComments.getName());
        holder.emailTv.setText(modelComments.getEmail());
        holder.bodyTv.setText(modelComments.getBody());

    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    class HolderComments extends RecyclerView.ViewHolder{

        private TextView postIdTv,idTv,nameTv,emailTv,bodyTv;

        public HolderComments(@NonNull View itemView) {
            super(itemView);
            postIdTv =itemView.findViewById(R.id.postIdTv);
            idTv = itemView.findViewById(R.id.idTv);
            nameTv = itemView.findViewById(R.id.nameTv);
            emailTv = itemView.findViewById(R.id.emailTv);
            bodyTv = itemView.findViewById(R.id.bodyTv);
        }
    }
}
