package eu.kanade.presentation.library.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tachiyomi.presentation.core.components.FastScrollLazyVerticalGrid
import tachiyomi.presentation.core.util.LocalIsTvUi
import tachiyomi.presentation.core.util.plus

/**
 * CompositionLocal that carries a callback grid items can invoke with their
 * index when they receive focus, allowing [LazyLibraryGrid] to auto-scroll
 * the focused item into view in TV mode.  Defaults to a no-op.
 */
internal val LocalLibraryGridItemFocused = staticCompositionLocalOf<(Int) -> Unit> { {} }

@Composable
internal fun LazyLibraryGrid(
    modifier: Modifier = Modifier,
    columns: Int,
    contentPadding: PaddingValues,
    lazyGridState: LazyGridState = rememberLazyGridState(),
    content: LazyGridScope.() -> Unit,
) {
    val isTvUi = LocalIsTvUi.current
    var focusedIndex by remember { mutableIntStateOf(-1) }

    LaunchedEffect(focusedIndex) {
        if (isTvUi && focusedIndex >= 0) {
            lazyGridState.animateScrollToItem(focusedIndex)
        }
    }

    val onItemFocused: (Int) -> Unit = remember { { index -> focusedIndex = index } }

    androidx.compose.runtime.CompositionLocalProvider(LocalLibraryGridItemFocused provides onItemFocused) {
        FastScrollLazyVerticalGrid(
            columns = if (columns == 0) GridCells.Adaptive(128.dp) else GridCells.Fixed(columns),
            modifier = modifier,
            state = lazyGridState,
            contentPadding = contentPadding + PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(CommonEntryItemDefaults.GridVerticalSpacer),
            horizontalArrangement = Arrangement.spacedBy(CommonEntryItemDefaults.GridHorizontalSpacer),
            content = content,
        )
    }
}

fun LazyGridScope.globalSearchItem(
    searchQuery: String?,
    onGlobalSearchClicked: () -> Unit,
) {
    if (!searchQuery.isNullOrEmpty()) {
        item(
            span = { GridItemSpan(maxLineSpan) },
            contentType = { "library_global_search_item" },
        ) {
            GlobalSearchItem(
                searchQuery = searchQuery,
                onClick = onGlobalSearchClicked,
            )
        }
    }
}
