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
 * Android concurrency Example implementation of an android activity
 * using AsyncTask mechanism.
 * @author Stephan Wagner
 */
public class MainAndroidAsyncActivity extends Activity {

    /**
     * The tag for logging.
     */
    private static final String TAG = "MainActivity";

    /**
     * The Constructor
     */
    public MainAndroidAsyncActivity() {
        Log.d(TAG, "call constructor");
    }

    /**
     * The textView of the first Output panel.
     */
    private TextView firstOutput;

    /**
     * The textView of the second output panel.
     */
    private TextView secondOutput;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle <b>Note:
     *                           Otherwise it is
     *                           null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "ACTIVITY JUST CREATED");
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        //create view with different fields
        //create button for starting with each field

        firstOutput = (TextView) findViewById(R.id.firstCalculationOutput);
        firstOutput.setText("This is the output of first calculation:\n");

        secondOutput = (TextView) findViewById(R.id.secondCalculationOutput);
        secondOutput.setText("This is the output of second calculation:\n");


    }


    @Override
    public void onDestroy()
    {
        //not needed in this example.
    }

    /**
     * Initializes the calculation. This method will be
     * triggered by e.g. a button in the gui.
     *
     * @param aView the View that triggered this method.
     */
    public void initCalculation(View aView) {

        AndroidAsyncRandomPrimeGen randomPrimeGen =
                new AndroidAsyncRandomPrimeGen(firstOutput, secondOutput);
        randomPrimeGen.execute(aView.getId());

    }


    public void clearOutput(View aView) {
        if (aView.getId() == R.id.clearOutput1) {
            firstOutput.setText("");
            firstOutput.invalidate();
        }
        if (aView.getId() == R.id.clearOutput2) {
            secondOutput.setText("");
            secondOutput.invalidate();
        }
    }

}
