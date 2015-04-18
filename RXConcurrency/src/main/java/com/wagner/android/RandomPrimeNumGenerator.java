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


   private Observable<String> primMessageObservable;
   private Observable<Integer> primIntObservable;

    /**
     * The Constructor.
     */
    public RandomPrimeNumGenerator()
    {

    primMessageObservable = Observable.create(
            new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> sub) {
                    for(int i=0; i<10; i++)
                    {

                        BigInteger veryBig = new BigInteger(500, new Random());
                        BigInteger randomPrimeNumber = veryBig.nextProbablePrime();

                       sub.onNext("Observable emits item number: " + i );
                       sub.onNext(randomPrimeNumber.toString());

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
       primIntObservable = Observable.create(
       new Observable.OnSubscribe<Integer>() {
          @Override
          public void call(Subscriber<? super Integer> sub) {

             sub.onNext( );
             sub.onNext(randomPrimeNumber.toString());

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
