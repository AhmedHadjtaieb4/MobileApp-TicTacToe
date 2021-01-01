package com.example.moura.devmobile;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moura.devmobile.Message;
import com.example.moura.devmobile.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder>{


    private List<Message> messagesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView message, from;

        public MyViewHolder(View view) {
            super(view);
            from = (TextView) view.findViewById(R.id.from_email);
            message = (TextView) view.findViewById(R.id.text_of_the_message);
        }
    }

    public MessageAdapter(List<Message> messagesList) {
        this.messagesList = messagesList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_row, parent, false);

        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Message message = messagesList.get(position);
        holder.message.setText(message.getMessage());
        holder.from.setText(message.getFrom());

    }


    @Override
    public int getItemCount() {
        return messagesList.size();
    }
}


