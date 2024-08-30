package memory.fabricators.snapfit.core.datastore.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import memory.fabricators.snapfit.core.datastore.dataStore
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val dataStoreCoreModule: Module
    get() = module {
        single<DataStore<Preferences>> { androidContext().dataStore }
    }
