package com.wagner.playground;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created with IntelliJ IDEA.
 * User: Ligatus
 * Date: 29.03.15
 * Time: 14:32
 * To change this template use File | Settings | File Templates.
 */
public class ReactivePlayground {
    public static void main(String [] args){

        String s1 = "Hallo";
        String s2 = "Du";
        String s3 = "Penis!!!!";

          observerHallo(s1, s2, s3);

           }


    public static void observerHallo(String... names){
        Observable.from(names).subscribe(new Action1<String>() {

            @Override
            public void call(String s) {
                System.out.println("Hello " + s + "!");
            }

        });

    }
}
