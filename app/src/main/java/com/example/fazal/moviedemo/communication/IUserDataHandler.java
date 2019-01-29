package com.example.fazal.moviedemo.communication;

import com.example.fazal.moviedemo.models.response.SessionResponse;

/**
 * Interface definition for Callback to be invoked when data received from server
 */
public interface IUserDataHandler {

    /**
     * Callback to be invoked when {@linkplain SessionResponse} received from server
     *
     * @param response The {@linkplain SessionResponse} object.
     */
    void onSessionCreated(SessionResponse response);

    /**
     * Callback to be invoked when error occur.
     *
     * @param error The error message
     */
    void onError(String error, int errorCode);
}
