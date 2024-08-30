package memory.fabricators.snapfit.core.datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore(name = "snapfit_preferences")
