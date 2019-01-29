package com.example.fazal.moviedemo.communication;

/**
 * Interface definition for response callback
 */
public interface IResponseCallback {

    /**
     * Invoked this method when response successfully received
     *
     * @param object The {@linkplain Object} of any type.
     */
    void onSuccess(Object object);

    /**
     * Invoked this method when facing an error while getting the response from server.
     *
     * @param error The error message.
     * @param errorCode The error code.
     */
    void onError(String error, int errorCode);

}