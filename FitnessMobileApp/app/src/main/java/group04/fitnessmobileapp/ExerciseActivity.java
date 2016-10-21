package group04.fitnessmobileapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yd on 2016/10/16.
 */
public class ExerciseActivity extends AppCompatActivity {

    Button exercise_button;
    int i = 0;
    private int min = 0;
    private int sec = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        exercise_button = (Button) findViewById(R.id.count_exercise);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable(){
                    @Override
                    public void run(){

                        TextView txtClicks = (TextView) findViewById(R.id.timer);
                        // task to be done every 1000 milliseconds
                        sec += 1;
                        if (sec == 60){
                            sec = 0;
                            min += 1;
                            if (min == 100){
                                min = 0;
                            }
                        }
                        String secstr;
                        String minstr;
                        if (sec < 10){
                            secstr = "0" + String.valueOf(sec);
                        } else {
                            secstr = String.valueOf(sec);
                        }
                        if (min < 10){
                            minstr = "0" + String.valueOf(min);
                        } else {
                            minstr = String.valueOf(min);
                        }
                        txtClicks.setText(minstr + ":" + secstr);
                    }
                });

}
}, 0, 1000);
        }

}
