package com.pedigreepair.data.local.preferenece
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
/**
 * Dedicated storage keys for DataStore preferences.
 * Isolated from business logic to support clean migrations and testing.
 */
object PedigreePreferenceKeys {
    // Onboarding Keys
    val IS_TUTORIAL_COMPLETED = booleanPreferencesKey("key_is_tutorial_completed")
    val IS_POLICY_ACCEPTED = booleanPreferencesKey("key_is_policy_accepted")

    // Magic Theme Keys
    val IS_MAGIC_THEME_ACTIVE = booleanPreferencesKey("key_is_magic_theme_active")
    val MAGIC_PRIMARY_HEX = longPreferencesKey("key_magic_primary_hex")
    val MAGIC_SECONDARY_HEX = longPreferencesKey("key_magic_secondary_hex")
    val MAGIC_LINE_DIVIDER_HEX = longPreferencesKey("key_magic_line_divider_hex")
    val MAGIC_ICON_TINT_HEX = longPreferencesKey("key_magic_icon_tint_hex")
}