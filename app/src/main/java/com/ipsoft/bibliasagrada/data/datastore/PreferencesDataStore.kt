package com.ipsoft.bibliasagrada.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ipsoft.bibliasagrada.domain.common.constants.STD_FONT_SIZE
import com.ipsoft.bibliasagrada.domain.core.exception.Failure
import com.ipsoft.bibliasagrada.domain.core.function.Either
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class PreferencesDataStore(private val context: Context) {

    private val fontSize = intPreferencesKey("font_size")

    suspend fun storeFontSize(data: Int): Either<Failure, Unit> {
        context.preferencesDataStore.edit { preferences ->
            preferences[fontSize] = data
        }
        return Either.Right(Unit)
    }

    suspend fun readFontSize(): Either<Failure, Int> {
        return Either.Right(
            context.preferencesDataStore.data.map { preferences ->
                preferences[fontSize] ?: STD_FONT_SIZE
            }.first()
        )
    }
}

private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore("preferences")
