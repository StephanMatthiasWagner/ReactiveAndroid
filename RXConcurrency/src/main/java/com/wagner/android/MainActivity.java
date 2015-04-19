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

/**
 * Created with IntelliJ IDEA.
 * User: Ligatus
 * Date: 16.04.15
 * Time: 19:35
 * To change this template use File | Settings | File Templates.
 */
public class MainActivity extends Activity
{
    private static final String TAG = "MainActivity";

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
    }

    private TextView firstObserverOutput;
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
        Log.d(TAG, "ACTIVITY JUST CREATED");
        super.onCreate(savedInstanceState);

        if(savedInstanceState!=null && savedInstanceState.containsKey(SAVED_INSTANCE_SOME_KEY))
        {
            savedInstance = savedInstanceState.getString(SAVED_INSTANCE_SOME_KEY);
        }

        setContentView(R.layout.activity_main);

        //create view with different fields
        //create button for starting with each field

        firstObserverOutput = (TextView)findViewById(R.id.firstObserverOutput);
        firstObserverOutput.setText("blablub");

        //initSubscription();

    }


    @Override
    public void onDestroy()
    {
        if(subscription!=null)
        {
            subscription.unsubscribe();
        }
    }

    public void initSubscription(View aView){
        mySubscriber = getSubscriber();
        RandomPrimeNumGenerator randomPrimeNumGenerator = new RandomPrimeNumGenerator();

        subscription = randomPrimeNumGenerator.getObservable().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String item) {
                        try{
                            Integer.valueOf(item);
                        }catch (NumberFormatException e)
                        {
                            return false;
                        }
                        return true;
                    }
                }
                )
                .subscribe(mySubscriber);

    }


    private Subscriber<String> getSubscriber(){

        return new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                //Actualize the view + setting value
                String lastOutput = firstObserverOutput.getText().toString();
                firstObserverOutput.setText(lastOutput + " \n " +s);

                firstObserverOutput.invalidate();
            }

            @Override
            public void onCompleted() {
                //show in view that observer is ready
            }

            @Override
            public void onError(Throwable e)
            {
                //show in view that observer ran in an error
            }
        };
    }

}