package com.example.datastorekullanimi.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.ds: DataStore<Preferences> by preferencesDataStore("userInfo")

class AppPrefs(var context: Context) {

    private val myDatastore: DataStore<Preferences> = context.ds

    companion object {
        val user_name = stringPreferencesKey("name")
        val user_surname = stringPreferencesKey("surname")
        val user_age = intPreferencesKey("age")
        val user_marital_status = booleanPreferencesKey("maritalstatus")
    }

    suspend fun saveUser(name: String, surname: String, age: Int, maritalStatus: Boolean) {
        context.ds.edit { preferences ->
            preferences[user_name] = name
            preferences[user_surname] = surname
            preferences[user_age] = age
            preferences[user_marital_status] = maritalStatus
        }
    }

    val userNameFlow: Flow<String> = myDatastore.data.map {
        it[user_name] ?: ""
    }

    val userSurnameFlow: Flow<String> = myDatastore.data.map {
        it[user_surname] ?: ""
    }

    val userAgeFlow: Flow<Int> = myDatastore.data.map {
        it[user_age] ?: 0
    }

    val userMaritalStatsFlow: Flow<Boolean> = myDatastore.data.map {
        it[user_marital_status] ?: false
    }
}



