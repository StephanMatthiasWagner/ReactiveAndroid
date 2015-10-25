package com.wagner.android.gesturelib.gestureutils;

import android.graphics.PointF;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

/**
 * This Class offers a generic Zoom and Drag
 * functionality that is controlled by using
 * Touch Gestures
 *
 * @author Stephan Wagner
 * @version 1.1.1.5
 *          Time: 22:06
 */
public class Touch implements View.OnTouchListener {

/**
 * The List of views that will be scaled
 * by using touch gestures.
 */
private List<View> transformableViews;

/**
 * The Tag for logging to this Listener.
 */
private final String TAG = getClass().getName();

/**
 * The initial coordinates of the first touch.
 */
private PointF start;

/**
 * The flag for defining the touch mode.
 */
private int mode;

/**
 *  Touch mode as functionality selector.
 */
private enum Mode{NONE, DRAG, ZOOM}

/**
 * The initial Distance for the
 * calculation of zoom.
 */
private float initDist;

/**
 * The initial Distance for the
 * calculation of zoom.
 */
private float newDist;

/**
 * The current scale factor of the zoom.
 */
private float scaleFactor;

/**
 * The maximal scale factor;
 */
private float maxScale;

/**
 * The minimal scale factor;
 */
private float minScale;

/**
 * The latency of the scale. Makes the zoom slower.
 */
private float latency;

/**
 * The Constructor of the touchListener
 * Implementation sets the initial not null parameters.
 *
 * @param aTransformableViewList The list of View
 *                               Instances that should
 *                               be transformed.
 * @param aMaxScaleFactor        the maximal scale factor.
 * @param aMinScaleFactor        the minimal scale factor.
 * @param aLatency               the latency for the
 *                               transformation,that is
 *                               used to make it slower.
 */
public Touch(final List<View> aTransformableViewList,
             final float aMaxScaleFactor,
             final float aMinScaleFactor,
             final float aLatency) {

    transformableViews = aTransformableViewList;
    start = new PointF();

    //Setting initial Values
    mode = Mode.NONE.ordinal();
    initDist = 1;
    newDist = 1;
    scaleFactor = 1;

    //setting scale
    maxScale = aMaxScaleFactor;
    minScale = aMinScaleFactor;

    //setting latency
    latency = aLatency;
}

/**
 * The implementation of TouchListener Interface,
 * that realizes drag and zoom functionality controlled
 * by multi-touch-gestures
 * @param touchView gives the base for the
 *                  registration of each touch.
 * @param anEvent holds the type and the
 *                coordinates of each touch.
 * @return returns true if listener has consumed the event.
 */
@Override
public boolean onTouch(final View touchView,
                       final MotionEvent anEvent) {

switch (anEvent.getActionMasked()) {
//first finger down only
case MotionEvent.ACTION_DOWN:
    //getting position
    start.set(anEvent.getX(), anEvent.getY());

    Log.d(TAG, "mode=DRAG");
    mode = Mode.DRAG.ordinal();
    break;

//first finger lifted
case MotionEvent.ACTION_UP:
    break;

//second finger lifted
case MotionEvent.ACTION_POINTER_UP:
    //new initial Distance is the last calculated distance
    //e.g. for more than one following pinch open gestures
    //and the connected zoom in function.
    initDist = newDist;

    mode = Mode.NONE.ordinal();
    Log.d(TAG, "mode=NONE");
    break;

//second finger down
case MotionEvent.ACTION_POINTER_DOWN:
    initDist = spacing(anEvent);
    mode = Mode.ZOOM.ordinal();
    Log.d(TAG, "mode=ZOOM");
    break;

case MotionEvent.ACTION_MOVE:
    if (mode == Mode.DRAG.ordinal()) {
    //movement of first finger

    float newX = anEvent.getX();
    float newY = anEvent.getY();

    float distanceX = (start.x - newX) / latency;
    float distanceY = (start.y - newY) / latency;

    for (final View draggableView : transformableViews) {
        draggableView.setTranslationX(
                draggableView.getTranslationX() - distanceX);
        draggableView.setTranslationY(
                draggableView.getTranslationY() - distanceY);
    }

    } else if (mode == Mode.ZOOM.ordinal()) { //pinch zooming
    float newDist = spacing(anEvent);

    if (newDist > initDist || newDist < initDist) {
        float factor = newDist / initDist;

        if (newDist > initDist && scaleFactor < maxScale)
        //pinch open --> zoom in
        {
            factor = factor / latency; //latency
            scaleFactor = scaleFactor + factor;
        }
        if (newDist < initDist &&
                scaleFactor > minScale &&
                factor < scaleFactor)
        //pinch close --> zoom out
        {
            factor = factor / latency; //latency
            scaleFactor = scaleFactor - factor;
        }

        for (final View scalableView : transformableViews) {
            scalableView.setScaleX(scaleFactor);
            scalableView.setScaleY(scaleFactor);
        }

        }
    }
    break;
}


return true;
}


/**
 * Calculates the distance between first
 * two touch points on touch screen.
 *
 * @param anEvent that contains the coordinates
 *              of the touch points.
 * @return the distance as float.
 */
private float spacing(final MotionEvent anEvent) {
    final float x = anEvent.getX(0) - anEvent.getX(1);
    final float y = anEvent.getY(0) - anEvent.getY(1);
    return FloatMath.sqrt(x * x + y * y);
}


}
