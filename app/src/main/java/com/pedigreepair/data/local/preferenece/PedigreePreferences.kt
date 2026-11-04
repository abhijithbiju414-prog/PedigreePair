package com.pedigreepair.data.local.preferenece


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.pedigreeDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "pedigree_preferences"
)

@Singleton
class PedigreePreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {

    // --- Onboarding Operations ---

    val isTutorialCompleted: Flow<Boolean> = context.pedigreeDataStore.data.map { prefs ->
        prefs[PedigreePreferenceKeys.IS_TUTORIAL_COMPLETED] ?: false
    }

    val isPolicyAccepted: Flow<Boolean> = context.pedigreeDataStore.data.map { prefs ->
        prefs[PedigreePreferenceKeys.IS_POLICY_ACCEPTED] ?: false
    }

    suspend fun setTutorialCompleted(completed: Boolean) {
        context.pedigreeDataStore.edit { prefs ->
            prefs[PedigreePreferenceKeys.IS_TUTORIAL_COMPLETED] = completed
        }
    }

    suspend fun setPolicyAccepted(accepted: Boolean) {
        context.pedigreeDataStore.edit { prefs ->
            prefs[PedigreePreferenceKeys.IS_POLICY_ACCEPTED] = accepted
        }
    }

    // --- Magic Theme Operations ---

    val isMagicThemeActive: Flow<Boolean> = context.pedigreeDataStore.data.map { prefs ->
        prefs[PedigreePreferenceKeys.IS_MAGIC_THEME_ACTIVE] ?: false
    }

    val magicPrimaryHex: Flow<Long?> = context.pedigreeDataStore.data.map { prefs ->
        prefs[PedigreePreferenceKeys.MAGIC_PRIMARY_HEX]
    }

    val magicSecondaryHex: Flow<Long?> = context.pedigreeDataStore.data.map { prefs ->
        prefs[PedigreePreferenceKeys.MAGIC_SECONDARY_HEX]
    }

    val magicLineDividerHex: Flow<Long?> = context.pedigreeDataStore.data.map { prefs ->
        prefs[PedigreePreferenceKeys.MAGIC_LINE_DIVIDER_HEX]
    }

    val magicIconTintHex: Flow<Long?> = context.pedigreeDataStore.data.map { prefs ->
        prefs[PedigreePreferenceKeys.MAGIC_ICON_TINT_HEX]
    }

    suspend fun saveMagicThemePalette(
        primaryHex: Long,
        secondaryHex: Long,
        lineDividerHex: Long,
        iconTintHex: Long
    ) {
        context.pedigreeDataStore.edit { prefs ->
            prefs[PedigreePreferenceKeys.MAGIC_PRIMARY_HEX] = primaryHex
            prefs[PedigreePreferenceKeys.MAGIC_SECONDARY_HEX] = secondaryHex
            prefs[PedigreePreferenceKeys.MAGIC_LINE_DIVIDER_HEX] = lineDividerHex
            prefs[PedigreePreferenceKeys.MAGIC_ICON_TINT_HEX] = iconTintHex
            prefs[PedigreePreferenceKeys.IS_MAGIC_THEME_ACTIVE] = true
        }
    }

    suspend fun clearMagicThemePalette() {
        context.pedigreeDataStore.edit { prefs ->
            prefs.remove(PedigreePreferenceKeys.MAGIC_PRIMARY_HEX)
            prefs.remove(PedigreePreferenceKeys.MAGIC_SECONDARY_HEX)
            prefs.remove(PedigreePreferenceKeys.MAGIC_LINE_DIVIDER_HEX)
            prefs.remove(PedigreePreferenceKeys.MAGIC_ICON_TINT_HEX)
            prefs[PedigreePreferenceKeys.IS_MAGIC_THEME_ACTIVE] = false
        }
    }
}