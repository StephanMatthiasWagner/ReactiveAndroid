package com.wagner.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.wagner.android.sampleapp.R;

/**
 * Created by Little on 20.03.2015.
 */
public class DownloadSampleActivity extends Activity
{

   private static final String TAG = "HelloAndroidActivity";

   private static final String SAVED_INSTANCE_SOME_KEY = "SOME_KEY";

   /**
    * The Saved Instance string.
    */
   private String savedInstance;


   public DownloadSampleActivity()
   {
      Log.d(TAG, "call constructor");
   }

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

   }


}
