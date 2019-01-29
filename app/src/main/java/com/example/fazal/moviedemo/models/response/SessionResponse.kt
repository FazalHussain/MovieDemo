package com.example.fazal.moviedemo.models.response

import com.google.gson.annotations.SerializedName

/**
 * Session Response Data Class
 */
data class SessionResponse(
        val success: Boolean,
        @SerializedName("guest_session_id")
        val guestSessionID: String? = "",
        @SerializedName("expires_at")
        val expireAt: String? = ""
)