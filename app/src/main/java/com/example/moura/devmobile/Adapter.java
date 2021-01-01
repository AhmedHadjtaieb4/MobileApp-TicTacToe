package com.example.moura.devmobile;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class Adapter  extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private List<User> usersList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView email,password,function;

        public MyViewHolder(View view) {
            super(view);
            email= (TextView) view.findViewById(R.id.emailConsultUser);
            function = (TextView) view.findViewById(R.id.functionConsultUser);

        }
    }
    public Adapter(List<User> userList) {
        this.usersList = userList;
    }
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.users_msg_list, parent, false);

        return new Adapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(Adapter.MyViewHolder holder, int position) {
        User user = usersList.get(position);
        holder.email.setText(user.getEmail());
        holder.function.setText(user.getFunction());
    }
    @Override
    public int getItemCount() {
        return usersList.size();
    }



}