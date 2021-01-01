package com.example.moura.devmobile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter  extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {
    private List<User> usersList;

    private Context ctx;
    private ConsultUsersFragment consultUsersFragment;
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView email,password,function;
          private ConsultUsersFragment consultUsersFragment;
          private CheckBox checkBox ;
          private RelativeLayout relativeLayout;
        public MyViewHolder(View view,ConsultUsersFragment consultUsersFragment) {
            super(view);
            email= (TextView) view.findViewById(R.id.emailConsultUser);
            password = (TextView) view.findViewById(R.id.passwordConsultUser);
            function = (TextView) view.findViewById(R.id.functionConsultUser);
            checkBox=view.findViewById(R.id.user_checked);
            relativeLayout=view.findViewById(R.id.layout_for_users_row);
            relativeLayout.setOnLongClickListener(consultUsersFragment);
             checkBox.setOnClickListener(this);
            this.consultUsersFragment=consultUsersFragment;
        }

        @Override
        public void onClick(View v) {
             consultUsersFragment.prepareSelection(v,getAdapterPosition());
        }
    }
    public UsersAdapter(List<User> userList, ConsultUsersFragment consultUsersFragment) {
        this.usersList = userList;

        this.consultUsersFragment=consultUsersFragment;
    }
    @Override
    public UsersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.consult_users_row, parent, false);

        return new UsersAdapter.MyViewHolder(itemView,consultUsersFragment);
    }
    @Override
    public void onBindViewHolder(UsersAdapter.MyViewHolder holder, int position) {
        User user = usersList.get(position);
        holder.email.setText(user.getEmail());
        holder.password.setText(user.getPassword());
        holder.function.setText(user.getFunction());
        if(!holder.consultUsersFragment.is_in_action_mode)
        {
            holder.checkBox.setVisibility(View.GONE);
        }else
        {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(false);
        }
    }
    @Override
    public int getItemCount() {
        return usersList.size();
    }
     public void updateAdapter(List<User>list)
     {
         for (User user : list)
         {
             usersList.remove(user);







         }

     notifyDataSetChanged();

     }


    }
