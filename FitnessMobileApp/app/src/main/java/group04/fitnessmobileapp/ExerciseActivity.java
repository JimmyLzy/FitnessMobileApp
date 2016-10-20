package group04.fitnessmobileapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * Created by yd on 2016/10/16.
 */
public class ExerciseActivity extends AppCompatActivity {

    Button exercise_button;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        exercise_button = (Button) findViewById(R.id.count_exercise);
    }

}
