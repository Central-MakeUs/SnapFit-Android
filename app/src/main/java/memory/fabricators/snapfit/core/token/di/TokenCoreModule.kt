package memory.fabricators.snapfit.core.token.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import memory.fabricators.snapfit.core.token.TokenManager
import memory.fabricators.snapfit.core.token.TokenManagerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val tokenCoreModule: Module
    get() = module {
        single<DataStore<Preferences>> { androidContext().dataStore }
        single<TokenManager> {
            TokenManagerImpl(
                httpClient = get(),
                dataStore = get(),
            )
        }
    }

private val Context.dataStore by preferencesDataStore(
    name = "token_preferences",
)
