package com.acadgild.s8A2ParallelAsyncTask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ProgressBar pb1;
    ProgressBar pb2;
    ProgressBar pb3;
    ProgressBar pb4;

    boolean loadFirstTwoPB = true;
    boolean loadSecondTwoPB = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb1 = (ProgressBar) findViewById(R.id.pb_1);
        pb2 = (ProgressBar) findViewById(R.id.pb_2);
        pb3 = (ProgressBar) findViewById(R.id.pb_3);
        pb4 = (ProgressBar) findViewById(R.id.pb_4);

        final Button startParallelAsyncBtn = (Button) findViewById(R.id.strt_prll_async_btn);
        startParallelAsyncBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new StartLoad().execute();
            }
        });

    }

    public class StartLoad extends AsyncTask<Integer, Integer, Integer>{

        @Override
        protected Integer doInBackground(Integer... params) {
                for (int i = 0; i <= 10; i++) {
                    sleep();
                    publishProgress(i * 10);
                }
            return null;
        }

        @Override
        protected void onPreExecute() {
            if (loadFirstTwoPB) {
                pb1.setVisibility(ProgressBar.VISIBLE);
                pb2.setVisibility(ProgressBar.VISIBLE);
            } else if (loadSecondTwoPB){
                pb3.setVisibility(ProgressBar.VISIBLE);
                pb4.setVisibility(ProgressBar.VISIBLE);

            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if (loadFirstTwoPB) {
                pb1.setProgress(values[0]);
                pb2.setProgress(values[0]);
            } else if (loadSecondTwoPB) {
                pb3.setProgress(values[0]);
                pb4.setProgress(values[0]);
            }
        }

        @Override
        protected void onPostExecute(Integer integer) {
            if (loadFirstTwoPB) {
                loadFirstTwoPB = false;
                loadSecondTwoPB = true;
            }else if (loadSecondTwoPB)
                loadSecondTwoPB = false;
        }
    }

    private static void sleep(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
