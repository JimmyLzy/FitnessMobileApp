package group04.fitnessmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button startButton = (Button) findViewById(R.id.button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button1 = (Button) findViewById(R.id.ex1);
                button1.setVisibility(View.VISIBLE);
                Button button2 = (Button) findViewById(R.id.ex2);
                button2.setVisibility(View.VISIBLE);
                Button button3 = (Button) findViewById(R.id.ex3);
                button3.setVisibility(View.VISIBLE);
                Button button4 = (Button) findViewById(R.id.ex4);
                button4.setVisibility(View.VISIBLE);
                Button button5 = (Button) findViewById(R.id.ex5);
                button5.setVisibility(View.VISIBLE);
                Button button6 = (Button) findViewById(R.id.ex6);
                button6.setVisibility(View.VISIBLE);
                Button button7 = (Button) findViewById(R.id.ex7);
                button7.setVisibility(View.VISIBLE);
                Button button8 = (Button) findViewById(R.id.ex8);
                button8.setVisibility(View.VISIBLE);
                Button button9 = (Button) findViewById(R.id.ex9);
                button9.setVisibility(View.VISIBLE);
            }

        });
    }

    public void startExercise1(View view) {
        Intent intent = new Intent(this, ExerciseActivity.class);
        startActivity(intent);
    }
//    public void onButtonClick(Button view) {
//        Button button1 = (Button) findViewById(R.id.ex1);
//        button1.setVisibility("Clicked !!!");
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
