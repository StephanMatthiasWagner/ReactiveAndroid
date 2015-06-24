package com.wagner.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.wagner.android.sampleapp.R;
import rx.Subscriber;
import rx.Subscription;
//import rx.android.observables.AndroidObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Ligatus
 * Date: 16.04.15
 * Time: 19:35
 * To change this template use File | Settings | File Templates.
 */
public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    private static final String SAVED_INSTANCE_SOME_KEY = "SOME_KEY";

    /**
     * The Saved Instance string.
     */
    private String savedInstance;

    /**
     * The subscriber.
     */
    private Subscriber<String> firstSubscriber;
    private Subscriber<String> secondSubscriber;

    /**
     * The Constructor
     */
    public MainActivity() {
        Log.d(TAG, "call constructor");
    }

    private TextView firstObserverOutput;
    private TextView secondObserverOutput;
    private Subscription subscription;

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

        firstObserverOutput = (TextView) findViewById(R.id.firstObserverOutput);
        firstObserverOutput.setText("This is the output of first observer:\n");

        secondObserverOutput = (TextView) findViewById(R.id.secondObserverOutput);
        secondObserverOutput.setText("This is the output of second observer:\n");


    }


    @Override
    public void onDestroy() {
      //for later usage
    }

    public void initSubscription(View aView) {
            StringBuilder targetString = new StringBuilder(10);
            for(int i=0; i<10; i++)
            {

                BigInteger veryBig = new BigInteger(1500, new Random());
                BigInteger randomPrimeNumber = veryBig.nextProbablePrime();
                int summe = 0;
                while (0 != randomPrimeNumber.compareTo(BigInteger.ZERO)) {
                    // addiere die letzte ziffer der uebergebenen zahl zur summe
                    summe = summe + (randomPrimeNumber.mod(BigInteger.TEN)).intValue();
                    // entferne die letzte ziffer der uebergebenen zahl
                    randomPrimeNumber = randomPrimeNumber.divide(BigInteger.TEN);
                }
                targetString.append(summe +" \n ");
            }
        if (aView.getId() == R.id.startSubscription1) {
            firstObserverOutput.setText(targetString.toString());
            firstObserverOutput.invalidate();
        }
        if (aView.getId() == R.id.startSubscription2) {
            secondObserverOutput.setText(targetString.toString());
            secondObserverOutput.invalidate();

        }
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