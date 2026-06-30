package org.todo.classic.session

import android.content.SharedPreferences
import org.todo.classic.domain.model.Session

class SessionStorageImpl(private val sharedPreferences: SharedPreferences): SessionStorage {

    companion object {
        private const val ACCESS_TOKEN = "access_token"
        private const val REFRESH_TOKEN = "refresh_token"

    }

    override fun save(session: Session) {
        sharedPreferences.edit().putString(ACCESS_TOKEN, session.accessToken).putString(REFRESH_TOKEN, session.refreshToken).apply()

    }

    override fun get(): Session? {
        val accessToken = sharedPreferences.getString(ACCESS_TOKEN, null)
        val refreshToken = sharedPreferences.getString(REFRESH_TOKEN, null)

        if (accessToken == null || refreshToken == null ){
            return null
        }
        return Session(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    override fun clear() {
        sharedPreferences.edit().remove(ACCESS_TOKEN).remove(REFRESH_TOKEN).apply()
    }
}