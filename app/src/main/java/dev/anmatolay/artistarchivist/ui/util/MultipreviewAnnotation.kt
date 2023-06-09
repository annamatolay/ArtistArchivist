package dev.anmatolay.artistarchivist.ui.util

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Dark",
    group = "UI Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    name = "Light",
    group = "UI Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
annotation class UIModePreviews

@Preview(
    name = "Small",
    group = "Font Scale",
    fontScale = 0.5f,
)
@Preview(
    name = "Small",
    group = "Font Scale",
    fontScale = 1.5f,
)
annotation class FontScalePreviews
