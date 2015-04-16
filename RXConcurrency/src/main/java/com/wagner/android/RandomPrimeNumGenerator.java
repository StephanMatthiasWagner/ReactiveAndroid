package com.wagner.android;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Ligatus
 * Date: 16.04.15
 * Time: 19:12
 * To change this template use File | Settings | File Templates.
 */
public class RandomPrimeNumGenerator {


    int index = 0;
    Observable<String> myObservable;

    /**
     * The Constructor.
     */
    public RandomPrimeNumGenerator()
    {

    myObservable = Observable.create(
            new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> sub) {
                    for(int i=0; i<10; i++)
                    {
                        index++;
                        sub.onNext("ObserverThread: Hello, world!" + i +" index="+index);


                        BigInteger veryBig = new BigInteger(500, new Random());
                        veryBig.nextProbablePrime();

                        //in case of an error: you can call onError
                       /* try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }*/
                    }
                    sub.onCompleted();
                }
            }
    );
    }

    /**
     * Returns the Observable.
     * @return Observable<String> instance.
     */
    public Observable<String> getObservable(){
        return myObservable;
    }

}
