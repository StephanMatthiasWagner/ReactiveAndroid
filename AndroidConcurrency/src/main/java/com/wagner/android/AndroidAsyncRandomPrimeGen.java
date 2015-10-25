package com.wagner.android;

import android.os.AsyncTask;
import android.widget.TextView;
import com.wagner.android.sampleapp.R;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Ligatus
 * Date: 26.04.15
 * Time: 12:59
 * To change this template use File | Settings | File Templates.
 */
public class AndroidAsyncRandomPrimeGen extends AsyncTask<Integer, String, AsyncTaskResult<String>> {


    AndroidAsyncRandomPrimeGen(TextView aFirstOutputView, TextView aSecondOutputView) {
        firstOutputView = aFirstOutputView;
        secondOutputView = aSecondOutputView;
    }

    TextView firstOutputView;
    TextView secondOutputView;
    int triggerViewId;

    @Override
    protected AsyncTaskResult<String> doInBackground(Integer... params) {

        if (params == null || params.length != 1) {
            return new AsyncTaskResult<String>(new IllegalArgumentException("Not the rights params:" + params));
        }
        triggerViewId = params[0].intValue();

        publishProgress("Init Calculation");
        StringBuilder targetString = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {

            BigInteger veryBig = new BigInteger(1500, new Random());
            BigInteger randomPrimeNumber = veryBig.nextProbablePrime();
            int summe = 0;
            while (0 != randomPrimeNumber.compareTo(BigInteger.ZERO)) {
                // addiere die letzte ziffer der uebergebenen zahl zur summe
                summe = summe + (randomPrimeNumber.mod(BigInteger.TEN)).intValue();
                // entferne die letzte ziffer der uebergebenen zahl
                randomPrimeNumber = randomPrimeNumber.divide(BigInteger.TEN);
            }
            targetString.append(summe + " \n ");
        }
        return new AsyncTaskResult<String>(targetString.toString());
    }


    protected void onPreExecute() {
        // Perform setup - runs on user interface thread
    }

    protected void onProgressUpdate(String values) {
        // Update user with progress bar or similar - runs on user interface thread
    }

    @Override
    protected void onPostExecute(AsyncTaskResult<String> result) {
        if (result.getError() != null) {
            // error handling here
        } else if (isCancelled()) {
            // cancel handling here
        } else {

            //update user interface
            String realResult = result.getResult();

            if (triggerViewId == R.id.startCalculation1) {
                firstOutputView.setText(realResult);
                firstOutputView.invalidate();
            }
            if (triggerViewId == R.id.startCalculation2) {
                secondOutputView.setText(realResult);
                secondOutputView.invalidate();
            }

        }


    }
}


