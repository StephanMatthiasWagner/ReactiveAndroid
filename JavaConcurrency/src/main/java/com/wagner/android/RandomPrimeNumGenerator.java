package com.wagner.android;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Ligatus
 * Date: 16.04.15
 * Time: 19:12
 * To change this template use File | Settings | File Templates.
 */
public class RandomPrimeNumGenerator implements Runnable {

    private static final String TAG = "RandomPrimeNumGenerator";


    /**
     *
     */
    private View targetView;

    /**
     *
     */
    private Handler handler;

    /**
     * The constructor.
     * @param aView
     * @param aCallbackHandler
     */
    public RandomPrimeNumGenerator(final View aView, final Handler aCallbackHandler) {
        Log.d(TAG,"Call Constructor");
        targetView = aView;
        handler = aCallbackHandler;
    }

    @Override
    public void run() {
        Log.d(TAG,"Call run");
        String result = startCalculation();
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putCharArray(String.valueOf(targetView.getId()), result.toCharArray());
        message.setData(bundle);
        Log.d(TAG,"Call handler");
        handler.sendMessage(message);
    }



    private String startCalculation() {
        Log.d(TAG," startCalculation");

        StringBuilder targetString = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {

            Log.d(TAG," calculation iteration: "+i);
            BigInteger veryBig = new BigInteger(500, new Random());
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
        return targetString.toString();
    }

}
