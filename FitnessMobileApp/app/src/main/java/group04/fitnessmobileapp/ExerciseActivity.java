package group04.fitnessmobileapp;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by yd on 2016/10/16.
 */
public class ExerciseActivity extends FragmentActivity {

    ViewPager viewPager;
    ExercisePagerAdapter exercisePagerAdapter;

    Button exercise_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        Intent intent = getIntent();
        ArrayList<CharSequence> exerciseList = intent.getCharSequenceArrayListExtra("exerciseList");

        exercisePagerAdapter = new ExercisePagerAdapter(getSupportFragmentManager());

        exercisePagerAdapter.setCount(exerciseList.size());

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(exercisePagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        //exercise_button = (Button) findViewById(R.id.count_exercise);
    }

}
