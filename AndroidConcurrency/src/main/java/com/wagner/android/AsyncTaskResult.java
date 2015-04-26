package com.wagner.android;

/**
 * Created with IntelliJ IDEA.
 * User: Ligatus
 * Date: 26.04.15
 * Time: 14:45
 * To change this template use File | Settings | File Templates.
 */
public class AsyncTaskResult<T> {
    private T result;
    private Exception error;



    public T getResult() {
        return result;
    }
    public Exception getError() {
        return error;
    }


    public AsyncTaskResult(T result) {
        super();
        this.result = result;
    }


    public AsyncTaskResult(Exception error) {
        super();
        this.error = error;
    }
}