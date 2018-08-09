package com.yeah.ruisu.threadpoolexecutorpractice;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity
{
    private ProgressBar MyProgressBar1, MyProgressBar2,
                        MyProgressBar3, MyProgressBar4;

    private TextView MyTextView1, MyTextView2,
                        MyTextView3, MyTextView4;

    private UiHandler MyUiHandler;

    private CustomThreadPoolManager MyCstmThrdPlMngr;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BindControls();
    }

    public void BindControls ()
    {
        MyTextView1 = findViewById(R.id.tvThread1);
        MyTextView2 = findViewById(R.id.tvThread2);
        MyTextView3 = findViewById(R.id.tvThread3);
        MyTextView4 = findViewById(R.id.tvThread4);

        MyProgressBar1 = findViewById(R.id.Progress1);
        MyProgressBar2 = findViewById(R.id.Progress2);
        MyProgressBar3 = findViewById(R.id.Progress3);
        MyProgressBar4 = findViewById(R.id.Progress4);

        MyUiHandler = new UiHandler(getMainLooper(),
                                        MyTextView1, MyTextView2,
                                        MyTextView3, MyTextView4,
                                        MyProgressBar1, MyProgressBar2,
                                        MyProgressBar3, MyProgressBar4);

        MyCstmThrdPlMngr = CustomThreadPoolManager.getInstance();
        MyCstmThrdPlMngr.setHandler(MyUiHandler);
    }

    public void AddCallable(View view)
    {
        MyCstmThrdPlMngr.AddRunable();
    }

    public static class UiHandler extends Handler
    {
            private WeakReference<TextView> tvFirstThread, tvSecondThread,
                                            tvThirdThread, tvFourthThread;
            private WeakReference<ProgressBar> FirstPrgrssBr, SecondPrgrssBr,
                                                ThirdPrgrssBr, FourthPrgrssBr;

            public UiHandler(Looper looper,
                             TextView tvFirstThread, TextView tvSecondThread,
                             TextView tvThirdThread, TextView tvFourthThread,
                             ProgressBar FirstPrgrssBr, ProgressBar SecondPrgrssBr,
                             ProgressBar ThirdPrgrssBr, ProgressBar FourthPrgrssBr)
            {
                super(looper);

                this.tvFirstThread = new WeakReference<>(tvFirstThread);
                this.tvSecondThread  = new WeakReference<>(tvSecondThread);
                this.tvThirdThread = new WeakReference<>(tvThirdThread);
                this.tvFourthThread = new WeakReference<>(tvFourthThread);

                this.FirstPrgrssBr = new WeakReference<>(FirstPrgrssBr);
                this.SecondPrgrssBr = new WeakReference<>(SecondPrgrssBr);
                this.ThirdPrgrssBr = new WeakReference<>(ThirdPrgrssBr);
                this.FourthPrgrssBr = new WeakReference<>(FourthPrgrssBr);
            }

            @Override
            public void handleMessage(Message msg)
            {
                super.handleMessage(msg);

                switch (msg.what)
                {
                    case 1:
                        FirstPrgrssBr.get().setProgress(msg.getData().getInt("int"));
                        tvFirstThread.get().setText(msg.getData().getString("message"));
                        break;
                    case 2:
                        SecondPrgrssBr.get().setProgress(msg.getData().getInt("int"));
                        tvSecondThread.get().setText(msg.getData().getString("message"));
                        break;
                    case 3:
                        ThirdPrgrssBr.get().setProgress(msg.getData().getInt("int"));
                        tvThirdThread.get().setText(msg.getData().getString("message"));
                        break;
                    case 4:
                        FourthPrgrssBr.get().setProgress(msg.getData().getInt("int"));
                        tvFourthThread.get().setText(msg.getData().getString("message"));
                        break;
                }
            }
    }
}
