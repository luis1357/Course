package com.yeah.ruisu.webviews;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "MainActivity";
    private WebView MyWebView;
    private EditText etName, etNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyWebView = findViewById(R.id.MyWbVw);
        WebViewClient MyWebViewClient = new WebViewClient();
        WebSettings MyWebSettings = MyWebView.getSettings();

        MyWebSettings.setJavaScriptEnabled(true);
        MyWebView.setWebViewClient(MyWebViewClient);

        MyWebView.loadUrl("https://developer.android.com");

        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);

    }

    public void SaveContact(View view)
    {
        MyContact contact = new MyContact(etName.getText().toString(),
                                            etNumber.getText().toString());
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.SaveNewContact(contact);


    }

    public void DisplayContact(View view)
    {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<MyContact> contacts = databaseHelper.getContacts();

        for (MyContact contact : contacts)
        {
            Log.d(TAG, "DisplayContact: " + contact.getName() + " " +
                    contact.getNumber());
        }
    }
}
