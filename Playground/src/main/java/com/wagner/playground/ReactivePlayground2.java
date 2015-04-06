package com.wagner.playground;

import rx.*;
import rx.schedulers.Schedulers;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Ligatus
 * Date: 06.04.15
 * Time: 08:59
 * To change this template use File | Settings | File Templates.
 */
public class ReactivePlayground2 {

    static int index = 0;
    static Observer observer;
    static Observable<String> myObservable = Observable.create(
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


    static Subscriber<String> mySubscriber = new Subscriber<String>() {
        @Override
        public void onNext(String s) {
            System.out.println("itemProcessing:");
            System.out.println(s);
        }

        @Override
        public void onCompleted() {
            System.out.println("item Completed");
        }



        @Override
        public void onError(Throwable e) {
            System.out.println("There was an error on first subscriber:"+e);
        }
    };

    static Subscriber<String> mySndSubscriber = new Subscriber<String>() {
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


    public static void main (String [] args){
        Subscription subscription = myObservable.subscribeOn(Schedulers.newThread()).subscribe(mySubscriber);
        myObservable.subscribeOn(Schedulers.newThread()).subscribe(mySndSubscriber);

        for(int i = 0; i<10; i++){  index++;
            System.out.println("                                                       Main Thread output. Iteration:"+i+ "index="+index);
            if(i==2){
                System.out.println("                                                   Subscription state:"+subscription.isUnsubscribed());
                System.out.println("                                                       unsubscribing Thread1");
              subscription.unsubscribe();
            System.out.println("                                                       Subscription state:"+subscription.isUnsubscribed());
            }
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
// Outputs "Hello, world!"
    }


}
