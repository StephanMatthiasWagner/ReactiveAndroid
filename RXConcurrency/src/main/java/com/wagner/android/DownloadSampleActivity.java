package com.wagner.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.wagner.android.sampleapp.R;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.observables.AndroidObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.Observable;
import rx.android.app.AppObservable;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

import java.io.File;

/**
 * Created by Little on 20.03.2015.
 */
public class DownloadSampleActivity extends Activity implements Observer<File> {

    private static final String TAG = "HelloAndroidActivity";

    private static final String SAVED_INSTANCE_SOME_KEY = "SOME_KEY";

    /**
     * The Saved Instance string.
     */
    private String savedInstance;


    public DownloadSampleActivity() {
        Log.d(TAG, "call constructor");
    }


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
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.containsKey(SAVED_INSTANCE_SOME_KEY)) {
            savedInstance = savedInstanceState.getString(SAVED_INSTANCE_SOME_KEY);
        }

        setContentView(R.layout.activity_main);
     /* Observable.from(new String[]{"one", "two", "three", "four", "five"})
               .subscribeOn(Schedulers.newThread())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(/* an Observer );*/


/*      LayoutInflater inflater = getLayoutInflater();
      final View l  = (View) findViewById(android.R.id.content);

      final Button button = (Button)findViewById(R.id.refreshButton);
      button.setText("Refresh");
      button.setOnClickListener(new View.OnClickListener() {

                                   @Override
                                   public void onClick(View v) {
                                      zoom(l, 2f, 2f, new PointF(0, 0));
                                   }
                                });

      final TextView textView = (TextView) findViewById(R.id.textView);
      textView.setText(savedInstance);

      final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
         .findViewById(android.R.id.content)).getChildAt(0);


      final DummyLibView dummyLibView = new DummyLibView();
      dummyLibView.addDummyTextView(viewGroup, R.id.refreshButton);

      Log.d(TAG, "onCreate called" );

      final DummyLib gestureLib = new DummyLib();
      gestureLib.callDummyLib("This is the call Message for GestureLib");
*/

     /*   subscription = AndroidObservable.bindActivity(this, downloadFileObservable())
                .subscribeOn(Schedulers.newThread())
                .subscribe(this);
 */
    }

  /*  private Observable<File> downloadFileObservable() {
        return Observable.create(new Observable.OnSubscribe<File>() {
            @Override
            public Subscription call(Subscriber<? super String> sub) {
                try {
                    byte[] fileContent = downloadFile();
                    File file = writeToFile(fileContent);
                    fileObserver.onNext(file);
                    fileObserver.onCompleted();
                } catch (Exception e) {
                    fileObserver.onError(e);
                }
                return Subscriptions.empty();
            }
        });
    }
    */
    private byte[] downloadFile() {
        return null;
    }

    private File writeToFile(byte[] aByteArray) {
        return new File("bla.txt");
    }

    @Override
    protected void onDestroy() {
        subscription.unsubscribe();
    }

    public void onNext(File file) {
        Toast.makeText(this,
                "Downloaded: " + file.getAbsolutePath(),
                Toast.LENGTH_SHORT)
                .show();
    }

    public void onCompleted() {
    }

    public void onError(Throwable error) {
        Toast.makeText(this,
                "Download failed: " + error.getMessage(),
                Toast.LENGTH_SHORT)
                .show();
    }

}


//}
