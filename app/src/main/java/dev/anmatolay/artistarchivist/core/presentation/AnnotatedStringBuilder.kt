package dev.anmatolay.artistarchivist.core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import dev.anmatolay.artistarchivist.util.Contant.URL_ANNOTATION
import dev.anmatolay.artistarchivist.util.Contant.URL_ISNI

@Composable
fun annotatedStringBuilder(isniCode: String) = buildAnnotatedString {
    append(isniCode)
    addStyle(
        style = SpanStyle(
            color = MaterialTheme.colorScheme.primary,
            fontSize = 16.sp,
            textDecoration = TextDecoration.Underline
        ), start = 0, end = isniCode.length
    )
    addStringAnnotation(
        tag = URL_ANNOTATION,
        annotation = "$URL_ISNI$isniCode",
        start = 0,
        end = isniCode.length
    )
}