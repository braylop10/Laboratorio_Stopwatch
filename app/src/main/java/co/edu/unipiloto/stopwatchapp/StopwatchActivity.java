package co.edu.unipiloto.stopwatchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.TextView;
import java.util.Locale;

public class StopwatchActivity extends AppCompatActivity {

    private int seconds= 0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if (savedInstanceState != null){
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
        }
        runTimer();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    }

    public void onClickStart(View View){
        running=true;
    }
    public void onClickStop(View View){
        running=false;
    }
    public void onClickReset(View View){
        running=false;
        seconds=0;
    }

    private void runTimer() {
        final TextView timeView= (TextView)findViewById(R.id.time_view);
        final Handler handler= new Handler();
       // final HandlerThread handlerThread = new HandlerThread("MyThread");
        handler.post(new Runnable() {
            @Override
            public void run () {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        }) ;
    }

}