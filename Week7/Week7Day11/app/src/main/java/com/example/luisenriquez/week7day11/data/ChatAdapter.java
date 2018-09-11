package com.example.luisenriquez.week7day11.data;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luisenriquez.week7day11.R;
import com.example.luisenriquez.week7day11.data.remote.models.ChatData;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder>
{
    private List<ChatData> mContent = new ArrayList<>();

    public void clearData()
    {
        mContent.clear();
    }

    public void addData(ChatData data)
    {
        mContent.add(data);
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View root = LayoutInflater.from(viewGroup.getContext())
                                    .inflate(R.layout.chat_item,
                                            viewGroup,
                                            false);

        return new ChatViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder chatViewHolder, int i)
    {
        ChatData data = mContent.get(i);

        chatViewHolder.message.setText(data.getMessage());
        chatViewHolder.name.setText(data.getName());
    }

    @Override
    public int getItemCount()
    {
        return mContent.size();
    }

    /**
     * ViewHolder to be the item of the list
     */
    static final class ChatViewHolder extends RecyclerView.ViewHolder
    {

        TextView name;
        TextView message;

        ChatViewHolder(View view)
        {
            super(view);

            name = view.findViewById(R.id.item_username);
            message = view.findViewById(R.id.item_message);
        }
    }
}
