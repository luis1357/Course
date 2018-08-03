package com.example.admin.daily4;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    /* First we get the components. */
    TextView tvMyUrl, tvPhnNmbr, tvEml;

    Button btnSrf, btnCll, btnCptr, btnShr, btnEml;

    ImageView ivPht;

    /* Doing some stuff so we can capture images using the camera. */
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* We bind the controls. */
        tvMyUrl = findViewById(R.id.txtBxWeb);
        tvPhnNmbr = findViewById(R.id.txtBxCall);
        tvEml = findViewById(R.id.txtBxEmail);

        ivPht = findViewById(R.id.ivPht);
    }

    public void SurfTheWeb(View view) {
        Uri MyUri = Uri.parse(tvMyUrl.getText().toString());

        Intent SurfWeb = new Intent(Intent.ACTION_VIEW, MyUri);

        startActivity(SurfWeb);
    }

    public void MakeCall(View view) {
        Uri CllNmbr = Uri.parse("tel:" + tvPhnNmbr.getText().toString());

        Intent MkCll = new Intent(Intent.ACTION_CALL, CllNmbr);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(MkCll);
    }

    public void CapturePhoto(View view)
    {
        Intent ShtCmr = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(ShtCmr, CAMERA_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK)
        {
            Bitmap InPht = (Bitmap) data.getExtras().get("data");
            ivPht.setImageBitmap(InPht);
        }
    }

    public void ShrImg(View view)
    {
        Bitmap bm = ((android.graphics.drawable.BitmapDrawable)
                        ivPht.getDrawable()).getBitmap();

        try
        {
            java.io.File MyFile = new java.io.File(
                    getExternalCacheDir() + "/image.jpg");
            java.io.OutputStream out = new java.io.FileOutputStream(MyFile);

            bm.compress(Bitmap.CompressFormat.JPEG, 100, out);

            out.flush();
            out.close();
        }
        catch (Exception e)
        {
            Toast toast1 = Toast.makeText(getApplicationContext(),
                                            e.toString(), Toast.LENGTH_SHORT);

            toast1.show();
        }

        Intent Shr = new Intent(Intent.ACTION_SEND);
        Shr.setType("*/*");

        Shr.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(getExternalCacheDir() +
                                                                    "/image.jpg")));

        startActivity(Intent.createChooser(Shr, "Send Image"));
    }

    public void SayHello(View view)
    {
        String MailTo = "mailto:" + tvEml.getText().toString() +
                "?subject=Hello There&body=Hello Buddy";

        Intent EmailIntent = new Intent(Intent.ACTION_SENDTO);
        EmailIntent.setData(Uri.parse(MailTo));

        startActivity(EmailIntent);
    }
}
