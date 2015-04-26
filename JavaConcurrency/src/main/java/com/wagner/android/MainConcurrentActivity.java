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
 * Created with IntelliJ IDEA.
 * User: Ligatus
 * Date: 16.04.15
 * Time: 19:35
 * To change this template use File | Settings | File Templates.
 */
public class MainConcurrentActivity extends Activity{
    private static final String TAG = "MainActivity";

    private static final String SAVED_INSTANCE_SOME_KEY = "SOME_KEY";

    /**
     * The Saved Instance string.
     */
    private String savedInstance;

    /**
     * The Constructor
     */
    public MainConcurrentActivity() {
        Log.d(TAG, "call constructor");
    }

    private static TextView firstCalculationOutput;
    private static TextView secondCalculationOutput;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then
     *                           this Bundle contains the data it most recently supplied in onSaveInstanceSt
     *
     *
     *                           (Bundle). <b>Note: Otherwise it is null.</b>
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

        firstCalculationOutput = (TextView) findViewById(R.id.firstCalculationOutput);
        firstCalculationOutput.setText("This is the output of first Calculation:\n");

        secondCalculationOutput = (TextView) findViewById(R.id.secondCalculationOutput);
        secondCalculationOutput.setText("This is the output of second Calculation:\n");
        //initSubscription();

    }



    @Override
    public void onDestroy() {

    }

    private static Handler HANDLER = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            updateView(msg);
        }
    };

    public void initCalculation(View aView) {

        /*static Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                handleMessage(msg);
            }
        };
          */
        RandomPrimeNumGenerator runnable = new RandomPrimeNumGenerator(aView,HANDLER);
        Thread newThread = new Thread(runnable);
        newThread.start();

    }


    public static void updateView(Message aMessage) {
        Log.d("RandomPrimeNumGenerator","Callback handleMessage");
        Bundle bundle = aMessage.getData();

        if(bundle.containsKey(String.valueOf(R.id.startCalculation1))){
            char[] firstResult = (char[]) bundle.get(String.valueOf(R.id.startCalculation1));
            Log.d("RandomPrimeNumGenerator","Callback view string: "+String.valueOf(firstResult));

            firstCalculationOutput.setText(String.valueOf(firstResult));
            firstCalculationOutput.invalidate();
        }


        if(bundle.containsKey(String.valueOf(R.id.startCalculation2))){
            char[] secondResult = (char[])bundle.get(String.valueOf(R.id.startCalculation2));
            Log.d("RandomPrimeNumGenerator","Callback view string: "+String.valueOf(secondResult));

            secondCalculationOutput.setText(String.valueOf(secondResult));
            secondCalculationOutput.invalidate();
        }
    }


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