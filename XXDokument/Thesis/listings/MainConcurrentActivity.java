package com.wagner.android;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.wagner.android.sampleapp.R;

import java.math.BigInteger;
import java.util.Random;

/**
 * Java concurrency Example implementation of an
 * android activity
 * @author Stephan Wagner
 */
public class MainConcurrentActivity
        extends Activity{

    /**
     * The tag for identify logging of
     * this class.
     */
    private static final String TAG
            = "MainConcurrentActivity";

    /**
     * The key to identify the bundle of this
     * activity if it is recreated.
     */
    private static final String
            SAVED_INSTANCE_SOME_KEY = "SOME_KEY";

    /**
     * The Saved Instance string.
     */
    private String savedInstance;

    /**
     * The Constructor
     */
    public MainConcurrentActivity()
    {
        Log.d(TAG, "call constructor");
    }

    /**
     * TextView element that will show
     * the calculation output of the
     * first panel.
     */
    private static TextView firstCalculationOutput;

    /**
     * TextView element tat will show the
     * calculation output of the
     * second panel
     */
    private static TextView secondCalculationOutput;

    /**
     * The private Handler object that controls
     * the communication between the background
     * and the UI-thread where this activity is
     * running.
     */
    private final Handler HANDLER = new Handler()
    {
        /**
         * Handle incoming message and trigger
         * update of the views in this activity
         * @param msg MessageObject
         */
        @Override
        public void handleMessage(final Message msg)
        {
            updateView(msg);
        }
    };

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState ignored in this
     *                           example.
     */
    @Override
    public void onCreate(
            final Bundle savedInstanceState)
    {
        Log.d(TAG, "ACTIVITY JUST CREATED");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //create view with different fields
        //create button for starting with each field

        firstCalculationOutput =
                (TextView) findViewById(
                        R.id.firstCalculationOutput);
        firstCalculationOutput.setText(
                "This is the output of " +
                        "first Calculation:\n");

        secondCalculationOutput =
                (TextView) findViewById(
                        R.id.secondCalculationOutput);
        secondCalculationOutput.setText(
                "This is the output of" +
                        " second Calculation:\n");

    }

    /**
     * Will be called in case of destroying
     * this activity.
     */
    @Override
    public void onDestroy()
    {
        //maybe cancel background calculation
    }

    /**
     * Initialize the Calculation. This Method
     * will be called by the Start Calculation
     * Buttons from the layout.
     * @param aView the viewId of the button that.
     */
    public void initCalculation(final View aView)
    {
        RandomPrimeNumGenerator runnable =
                new RandomPrimeNumGenerator(
                        aView,HANDLER);
        Thread newThread = new Thread(runnable);
        newThread.start();
    }

    /**
     * Updates the view to display the results of
     * the calculation.
     * @param aMessage contains the result of the
     *                 calculation as bundle.
     */
    public static void updateView(
            final Message aMessage)
    {
        Log.d(TAG,"updateView");
        Bundle bundle = aMessage.getData();

        if(bundle.containsKey(
                String.valueOf(
                        R.id.startCalculation1)))
        {
            char[] firstResult =
                    (char[]) bundle.get(
                            String.valueOf(
                                    R.id.startCalculation1));

            Log.d("RandomPrimeNumGenerator",
                    "Callback view string: "
                            +String.valueOf(firstResult));

            firstCalculationOutput
                    .setText(String.valueOf(firstResult));
            firstCalculationOutput.invalidate();
        }


        if(bundle.containsKey(
                String.valueOf(R.id.startCalculation2)))
        {
            char[] secondResult =
                    (char[])bundle.get(
                            String.valueOf(
                                    R.id.startCalculation2));
            Log.d("RandomPrimeNumGenerator",
                    "Callback view string: "
                            +String.valueOf(secondResult));

            secondCalculationOutput
                    .setText(String.valueOf(secondResult));
            secondCalculationOutput.invalidate();
        }
    }

    /**
     * Clears the output field that
     * belongs to the button calling
     * this method.
     * @param aView the button viewObject
     *              that called this method.
     */
    public void clearOutput(View aView) {
        if (aView.getId() == R.id.clearOutput1) {
            firstCalculationOutput.setText("");
            firstCalculationOutput.invalidate();
        }
        if (aView.getId() == R.id.clearOutput2) {
            secondCalculationOutput.setText("");
            secondCalculationOutput.invalidate();
        }
    }


}