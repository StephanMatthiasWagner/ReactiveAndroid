package com.wagner.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.wagner.android.sampleapp.R;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Ligatus
 * Date: 26.04.15
 * Time: 12:58
 * To change this template use File | Settings | File Templates.
 */
public class MainAndroidAsyncActivity extends Activity {

    private static final String TAG = "MainActivity";

    private static final String SAVED_INSTANCE_SOME_KEY = "SOME_KEY";

    /**
     * The Saved Instance string.
     */
    private String savedInstance;

    /**
     * The Constructor
     */
    public MainAndroidAsyncActivity() {
        Log.d(TAG, "call constructor");
    }

    private TextView firstObserverOutput;
    private TextView secondObserverOutput;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this
     *                           Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is
     *                           null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "ACTIVITY JUST CREATED");
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.containsKey(SAVED_INSTANCE_SOME_KEY)) {
            savedInstance = savedInstanceState.getString(SAVED_INSTANCE_SOME_KEY);
        }

        setContentView(R.layout.activity_main);

        //create view with different fields
        //create button for starting with each field

        firstObserverOutput = (TextView) findViewById(R.id.firstCalculationOutput);
        firstObserverOutput.setText("This is the output of first calculation:\n");

        secondObserverOutput = (TextView) findViewById(R.id.secondCalculationOutput);
        secondObserverOutput.setText("This is the output of second calculation:\n");


    }


    @Override
    public void onDestroy() {
        //for later usage
    }

    public void initCalculation(View aView) {

        AndroidAsyncRandomPrimeGen randomPrimeGen =
                new AndroidAsyncRandomPrimeGen(firstObserverOutput,secondObserverOutput);
        randomPrimeGen.execute(aView.getId());

    }


    public void clearOutput(View aView) {
        if (aView.getId() == R.id.clearOutput1) {
            firstObserverOutput.setText("");
            firstObserverOutput.invalidate();
        }
        if (aView.getId() == R.id.clearOutput2) {
            secondObserverOutput.setText("");
            secondObserverOutput.invalidate();
        }
    }

}
