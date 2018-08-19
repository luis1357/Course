package com.yeah.ruisu.weekend3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BookRecyclerAdapter
        extends RecyclerView.Adapter<BookRecyclerAdapter.ViewHolder>
{
    private static final String TAG ="Adapter: ";

    List<Book> BookList;

    public BookRecyclerAdapter(List<Book> InBookList)
    {
        this.BookList = InBookList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Log.d(TAG, "onCreateViewHolder: ");
        View MyView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, null);

        return new ViewHolder(MyView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Log.d(TAG, "onBindViewHolder: ");

        Book BookTemp = BookList.get(position);

        if(BookTemp != null)
        {
            holder.tvBookName.setText(BookTemp.Name);
            holder.tvBookAuthor.setText(BookTemp.Author);
            holder.tvBookDescription.setText(BookTemp.Description);

            Picasso.with(holder.ivBookPic.getContext())
                    .load(BookTemp.ImageUrl)
                    .fit()
                    .into(holder.ivBookPic);
        }
    }

    @Override
    public int getItemCount()
    {
        return BookList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView tvBookName, tvBookAuthor, tvBookDescription;
        private final ImageView ivBookPic;

        public ViewHolder(View itemView)
        {
            super(itemView);
            this.tvBookName = itemView.findViewById(R.id.tvBookTitle);
            this.tvBookAuthor = itemView.findViewById(R.id.tvBookAuthor);
            this.tvBookDescription = itemView.findViewById(R.id.tvBookDescription);
            this.ivBookPic = itemView.findViewById(R.id.ivBookPic);
        }


    }
}
