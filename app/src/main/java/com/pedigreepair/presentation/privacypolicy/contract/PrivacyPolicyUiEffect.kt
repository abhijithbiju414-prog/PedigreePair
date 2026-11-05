package com.pedigreepair.presentation.privacypolicy.contract

sealed interface PrivacyPolicyUiEffect {
    data object NavigateToHome : PrivacyPolicyUiEffect
    data class ShowToast(val message: String) : PrivacyPolicyUiEffect
}