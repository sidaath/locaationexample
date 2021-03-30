package com.example.studentlocations;

import android.content.Context;
import android.content.Intent;
import android.media.DrmInitData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.CheckedInputStream;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ViewHolder> {
    ArrayList<Child>localDataset;
    Context context;
    public StudentListAdapter(ArrayList<Child> children) {
        localDataset = children;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView studentName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName = (TextView)itemView.findViewById(R.id.txtChildName);
        }
    }

    @NonNull
    @Override
    public StudentListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_row,parent,false);
        context = parent.getContext();
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentListAdapter.ViewHolder holder, int position) {
        Child child = localDataset.get(position);
        int x = child.getId();
        String id = Integer.toString(x);
        holder.studentName.setText(id);
    }

    @Override
    public int getItemCount() {
        return localDataset.size();
    }
}
