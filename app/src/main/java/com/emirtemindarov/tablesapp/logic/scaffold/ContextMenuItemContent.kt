package com.emirtemindarov.tablesapp.logic.scaffold

import androidx.compose.runtime.Composable

data class ContextMenuItemContent(
    val item: @Composable () -> Unit,
    val action: () -> Unit,
    val divider: Boolean? = null
)