package com.wagner.playground;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
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
public class ReactiveDemo {

    static Observable<String> myObservable = Observable.create(
            new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> sub) {
                    for (int i = 0; i < 10; i++) {
                        sub.onNext("ObserverThread: Hello, world!" + i);


                        BigInteger veryBig = new BigInteger(500, new Random());
                        veryBig.nextProbablePrime();

                    }
                    sub.onCompleted();
                }
            }
    );


    static Subscriber<String> mySubscriber = new Subscriber<String>() {

        @Override
        public void onNext(String s) {
            System.out.println("1rst Thread itemProcessing:");
            System.out.println(s);
        }

        @Override
        public void onCompleted() {
            firstObservableHasFinished=true;
            System.out.println("1rst item Completed");
        }


        @Override
        public void onError(Throwable e) {
            System.out.println("There was an error on first subscriber:" + e);
        }
    };

    static Subscriber<String> mySndSubscriber = new Subscriber<String>() {
        @Override
        public void onNext(String s) {
            System.out.println("                                2ndThread: itemProcessing:");
            System.out.println("                                " + s);
        }

        @Override
        public void onCompleted() {
            secondObservableHasFinished = true;
            System.out.println("                                2nd Thred: item Completed");
        }


        @Override
        public void onError(Throwable e) {
        }
    };


    public static boolean firstObservableHasFinished = false;
    public static boolean secondObservableHasFinished = false;


    public static void main(String[] args) {

        Subscription subscription = myObservable.subscribeOn(Schedulers.newThread()).observeOn(Schedulers.io()).subscribe
                                   (mySubscriber);
        myObservable.subscribeOn(Schedulers.newThread()).subscribe(mySndSubscriber);

        while(!firstObservableHasFinished || !secondObservableHasFinished){
        }

        System.out.println("firstObservableHasFinished:"+firstObservableHasFinished);
        System.out.println("secondObservableHasFinished:"+secondObservableHasFinished);

  /*      for(int i = 0; i<10; i++){  index++;
            System.out.println("                                                       Main Thread output. Iteration:"+i+ "index="+index);
            if(i==2){
                System.out.println("                                                   Subscription state:"+subscription.isUnsubscribed());
             System.out.println("unsubscribing Thread1");
              subscription.unsubscribe();
            System.out.println("                                                       Subscription state:"+subscription.isUnsubscribed());
            }
            BigInteger veryBig = new BigInteger(500, new Random());
            veryBig.nextProbablePrime();
*/            /*try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } */
    }
// Outputs "Hello, world!"
//}


}
