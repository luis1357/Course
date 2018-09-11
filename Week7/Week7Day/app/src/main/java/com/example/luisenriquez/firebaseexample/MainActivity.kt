package com.example.luisenriquez.firebaseexample

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.example.luisenriquez.firebaseexample.databinding.ActivityMainBinding
import com.example.luisenriquez.firebaseexample.util.Constants
import com.google.firebase.database.*

class MainActivity : AppCompatActivity()
{
    val TAG ="Mainactivity: ";

    lateinit var database : FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    lateinit var binding : ActivityMainBinding
    lateinit var valueEventListener: ValueEventListener

    var userName: String = Constants.ANONYMOUS

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding =DataBindingUtil.setContentView(this, R.layout.activity_main)

        initializeFirebaseComponents()

        addTextWatchers()
    }

    override fun onResume()
    {
        super.onResume()
        addReadListeners()
    }

    override fun onPause()
    {
        super.onPause()
        removeReadListeners()
    }

    fun sendMessage(view: View)
    {
        var message = Message(binding.etMessage.text.toString(), userName)

        databaseReference.push().setValue(message)

        /* Clear Input boc. */
        binding.etMessage.setText("");
    }

    private fun initializeFirebaseComponents()
    {
        database = FirebaseDatabase.getInstance()
        databaseReference = database.reference.child("messages")
    }

    private fun addReadListeners()
    {
        valueEventListener = object : ValueEventListener
        {
            override fun onCancelled(databaseError: DatabaseError)
            {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot)
            {
                dataSnapshot.children.forEach{

                    var message = it.getValue(Message::class.java)

                    Log.d(TAG, message?.text)
                }
            }

        }

        databaseReference.addValueEventListener(valueEventListener)
    }

    private fun removeReadListeners()
    {
        databaseReference.removeEventListener(valueEventListener)
    }

    private fun addTextWatchers()
    {
        binding.etMessage.addTextChangedListener(object : TextWatcher
        {
            override fun afterTextChanged(s: Editable?)
            {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int)
            {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
            {
                binding.btnSend.isEnabled = s.toString().isNotBlank()
            }

        })
    }
}
