package com.wagner.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.wagner.android.sampleapp.R;
import rx.Subscriber;
import rx.Subscription;
import rx.android.observables.AndroidObservable;
import rx.schedulers.Schedulers;

/**
 * Created with IntelliJ IDEA.
 * User: Ligatus
 * Date: 16.04.15
 * Time: 19:35
 * To change this template use File | Settings | File Templates.
 */
public class MainActivity extends Activity
{
    private static final String TAG = "HelloAndroidActivity";

    private static final String SAVED_INSTANCE_SOME_KEY = "SOME_KEY";

    /**
     * The Saved Instance string.
     */
    private String savedInstance;

    /**
     * The subscriber.
     */
    private Subscriber<String> mySubscriber;

    /**
     * The Constructor
     */
    public MainActivity()
    {
        Log.d(TAG, "call constructor");

        mySubscriber = getSubscriber();
    }

    private Subscription subscription;
    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this
     * Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is
     * null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if(savedInstanceState!=null && savedInstanceState.containsKey(SAVED_INSTANCE_SOME_KEY))
        {
            savedInstance = savedInstanceState.getString(SAVED_INSTANCE_SOME_KEY);
        }

        setContentView(R.layout.activity_main);
        RandomPrimeNumGenerator randomPrimeNumGenerator = new RandomPrimeNumGenerator();

        subscription = AndroidObservable.bindActivity(this, randomPrimeNumGenerator.getObservable())
                .subscribeOn(Schedulers.newThread()).observeOn(Schedulers.io())
                .subscribe(mySubscriber);
    }


    private Subscriber<String> getSubscriber(){

        final Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                System.out.println("                     2ndThread: itemProcessing:");
                System.out.println("                     "+s);
            }

            @Override
            public void onCompleted() {
                System.out.println("                    2nd Thred: item Completed");
            }



            @Override
            public void onError(Throwable e) {
            }
        };
        return subscriber;
    }

}