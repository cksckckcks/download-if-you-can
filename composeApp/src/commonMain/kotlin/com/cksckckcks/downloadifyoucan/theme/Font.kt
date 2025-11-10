package com.cksckckcks.downloadifyoucan.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import downloadifyoucan.composeapp.generated.resources.*
import org.jetbrains.compose.resources.Font

@Composable
fun pretendard() = FontFamily(
    Font(Res.font.Pretendard_Thin, FontWeight.Thin),
    Font(Res.font.Pretendard_ExtraLight, FontWeight.ExtraLight),
    Font(Res.font.Pretendard_Light, FontWeight.Light),
    Font(Res.font.Pretendard_Regular, FontWeight.Normal),
    Font(Res.font.Pretendard_Medium, FontWeight.Medium),
    Font(Res.font.Pretendard_SemiBold, FontWeight.SemiBold),
    Font(Res.font.Pretendard_Bold, FontWeight.Bold),
    Font(Res.font.Pretendard_ExtraBold, FontWeight.ExtraBold),
    Font(Res.font.Pretendard_Black, FontWeight.Black)
)