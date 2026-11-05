package com.pedigreepair.presentation.privacypolicy.contract

data class PrivacyPolicyUiState(
    val isTermsAccepted: Boolean = false,
    val isPrivacyAccepted: Boolean = false,
    val isSubmitting: Boolean = false,
    val errorMessage: String? = null
) {
    val canProceed: Boolean
        get() = isTermsAccepted && isPrivacyAccepted && !isSubmitting
}