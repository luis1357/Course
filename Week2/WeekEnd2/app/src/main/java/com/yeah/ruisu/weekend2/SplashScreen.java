package com.yeah.ruisu.weekend2;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends Activity {

    /* Duration of the Splash Screen. */
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        /* New Handler to start the MainActivity and close the *
         *  splash screen after the delay.                     */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                /* We create an intent to display the Main Activity. */
                Intent MainIntent = new Intent (SplashScreen.this,
                                                                MainActivity.class);
                SplashScreen.this.startActivity(MainIntent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

}
