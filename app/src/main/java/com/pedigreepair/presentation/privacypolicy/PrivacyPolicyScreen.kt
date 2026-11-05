package com.pedigreepair.presentation.privacypolicy


import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pedigreepair.core.designsystem.theme.PedigreePairTheme
import com.pedigreepair.core.designsystem.theme.PedigreeTheme
import com.pedigreepair.domain.model.theme.ThemePalette
import com.pedigreepair.presentation.privacypolicy.contract.PrivacyPolicyUiEffect
import com.pedigreepair.presentation.privacypolicy.contract.PrivacyPolicyUiState
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PrivacyPolicyScreen(
    viewModel: PrivacyPolicyViewModel,
    onNavigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is PrivacyPolicyUiEffect.NavigateToHome -> onNavigateToHome()
                is PrivacyPolicyUiEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    PrivacyPolicyContent(
        uiState = uiState,
        onNextClicked = viewModel::onAcceptAndContinueClicked,
        modifier = modifier
    )
}


// CONTENT
// =========================================================================

@Composable
fun PrivacyPolicyContent(
    uiState: PrivacyPolicyUiState,
    onNextClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(PedigreeTheme.colors.background)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        // Top App Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Privacy Policy",
                style = PedigreeTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = PedigreeTheme.colors.textPrimary
            )
        }

        HorizontalDivider(
            thickness = 1.dp,
            color = PedigreeTheme.colors.lineDivider
        )

        // Policy Document Scroll View
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            PolicySection(
                title = "1. Introduction & Trust",
                content = "Welcome to PedigreePair. We respect the bond between pet parents, breeders, and their companions. This policy explains how we collect, store, and utilize your account details, canine lineage documents, and shared moments."
            )

            PolicySection(
                title = "2. Pedigree Lineage & Verification Records",
                content = "To preserve the integrity of canine matchmaking and pedigree verification, lineage certificates and kennel registration numbers submitted for validation are encrypted and securely stored. We never sell your registration documents to third parties."
            )

            PolicySection(
                title = "3. Memories & Media Sharing",
                content = "Photos and milestone memories uploaded to canine profiles are visible to community members based on your visibility preferences. You retain full ownership of media uploaded to PedigreePair and can delete your media assets at any time."
            )

            PolicySection(
                title = "4. Adaptive Coat Theming Data",
                content = "When using our Magic Theme feature to adapt UI accent colors to your dog's coat, the palette calculation happens locally on your device or via secure color extraction. No biometric or raw imaging data is shared for advertising."
            )

            PolicySection(
                title = "5. Account Security & Contact Details",
                content = "Contact information between pet parents is only facilitated when an inquiry or match request is mutually accepted. You remain in control of who initiates contact."
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        HorizontalDivider(
            thickness = 1.dp,
            color = PedigreeTheme.colors.lineDivider
        )

        // Bottom Fixed Action Area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(PedigreeTheme.colors.surface)
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Button(
                onClick = onNextClicked,
                enabled = !uiState.isSubmitting,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PedigreeTheme.colors.primary,
                    contentColor = PedigreeTheme.colors.onPrimary
                ),
                shape = PedigreeTheme.shapes.medium
            ) {
                if (uiState.isSubmitting) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = PedigreeTheme.colors.onPrimary,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        text = "Next",
                        style = PedigreeTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
private fun PolicySection(
    title: String,
    content: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    ) {
        Text(
            text = title,
            style = PedigreeTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = PedigreeTheme.colors.textPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = content,
            style = PedigreeTheme.typography.bodyMedium,
            color = PedigreeTheme.colors.textSecondary,
            lineHeight = PedigreeTheme.typography.bodyMedium.lineHeight
        )
    }
}

// COMPOSE PREVIEWS
// =========================================================================

@Preview(
    name = "Light Mode",
    showBackground = true,
    backgroundColor = 0xFFF8FAFC
)
@Composable
private fun PrivacyPolicyContentLightPreview() {
    PedigreePairTheme(
        activePalette = ThemePalette.Default,
        darkTheme = false
    ) {
        PrivacyPolicyContent(
            uiState = PrivacyPolicyUiState(isSubmitting = false),
            onNextClicked = {}
        )
    }
}

@Preview(
    name = "Dark Mode",
    showBackground = true,
    backgroundColor = 0xFF0F172A,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PrivacyPolicyContentDarkPreview() {
    PedigreePairTheme(
        activePalette = ThemePalette.Default,
        darkTheme = true
    ) {
        PrivacyPolicyContent(
            uiState = PrivacyPolicyUiState(isSubmitting = false),
            onNextClicked = {}
        )
    }
}

@Preview(
    name = "Submitting / Loading State",
    showBackground = true
)
@Composable
private fun PrivacyPolicyContentLoadingPreview() {
    PedigreePairTheme(
        activePalette = ThemePalette.Default,
        darkTheme = false
    ) {
        PrivacyPolicyContent(
            uiState = PrivacyPolicyUiState(isSubmitting = true),
            onNextClicked = {}
        )
    }
}