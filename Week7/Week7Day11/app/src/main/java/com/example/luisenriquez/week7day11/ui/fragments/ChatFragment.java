package com.example.luisenriquez.week7day11.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luisenriquez.week7day11.ActivityCallback;
import com.example.luisenriquez.week7day11.R;
import com.example.luisenriquez.week7day11.data.ChatAdapter;
import com.example.luisenriquez.week7day11.data.remote.models.ChatData;
import com.example.luisenriquez.week7day11.utils.Constants;
import com.example.luisenriquez.week7day11.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.Objects;

public class ChatFragment extends Fragment
{
    /** Activity callback **/
    private ActivityCallback mCallback;

    /** Database instance **/
    private DatabaseReference mReference;

    /** UI Components **/
    private EditText mChatInput;
    private ChatAdapter mAdapter;

    /** Class variables **/
    private String mUsername;
    private String mUserId;

    /**
     * Create a instance of this fragment
     *
     * @return fragment instance
     */
    public static ChatFragment newInstance()
    {
        return new ChatFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        mUsername = Utils.getLocalUsername(Objects.requireNonNull(getContext()));
        mUserId = Utils.getLocalUserId(getContext());

        setupConnection();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_chat, container, false);

        mChatInput = root.findViewById(R.id.chat_input);
        mChatInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                ChatData data = new ChatData();
                data.setMessage(mChatInput.getText().toString());
                data.setId(mUserId);
                data.setName(mUsername);

                mReference.child(String.valueOf(new Date().getTime())).setValue(data);

                closeAndClean();
                return true;
            }
        });

        RecyclerView chat = root.findViewById(R.id.chat_message);
        chat.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new ChatAdapter();
        chat.setAdapter(mAdapter);

        return root;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        mCallback = (ActivityCallback) context;
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.action_logout)
        {
            FirebaseAuth.getInstance().signOut();
            mCallback.logout();
        }

        return super.onOptionsItemSelected(item);
    }

    private void closeAndClean()
    {
        Utils.closeKeyboard(Objects.requireNonNull(getContext()),
                            mChatInput);
        mChatInput.setText("");
    }

    private void setupConnection()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mReference = database.getReference(Constants.DATABASE_NAME);

        mReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                Log.d(Constants.LOG_TAG,"SUCCESS!");
                handleReturn(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Log.e(Constants.LOG_TAG,"ERROR: " + databaseError.getMessage());

                Toast.makeText(getContext(),
                                "Failed to Connect to Database!",
                                Toast.LENGTH_SHORT).show();

                mCallback.logout();
            }
        });
    }

    private void handleReturn(DataSnapshot dataSnapshot)
    {
        mAdapter.clearData();

        for(DataSnapshot item : dataSnapshot.getChildren())
        {
            ChatData data = item.getValue(ChatData.class);
            mAdapter.addData(data);
        }

        mAdapter.notifyDataSetChanged();
    }
}
