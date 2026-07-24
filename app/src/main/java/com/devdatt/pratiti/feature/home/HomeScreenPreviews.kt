package com.devdatt.pratiti.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.devdatt.pratiti.domain.model.Category
import com.devdatt.pratiti.feature.home.component.MainTab
import com.devdatt.pratiti.ui.theme.PratitiTheme

private val previewCategories = listOf(
    Category(id = "aarti", name = "Aarti", displayName = "Aarti"),
    Category(id = "bhajan", name = "Bhajan", displayName = "Bhajan"),
    Category(id = "stavan", name = "Stavan", displayName = "Stavan"),
    Category(id = "pooja_avsar", name = "Pooja Avsar", displayName = "Pooja Avsar")
)

@Preview(name = "Home – categories", showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    PratitiTheme(dynamicColor = false) {
        HomeScreenContent(
            selectedTab = MainTab.Home,
            onTabSelected = {},
            homeUiState = HomeUiState.Success(previewCategories),
            onRetry = {},
            onCategoryClick = {}
        )
    }
}

@Preview(name = "Home – loading", showBackground = true)
@Composable
private fun HomeScreenLoadingPreview() {
    PratitiTheme(dynamicColor = false) {
        HomeScreenContent(
            selectedTab = MainTab.Home,
            onTabSelected = {},
            homeUiState = HomeUiState.Loading,
            onRetry = {},
            onCategoryClick = {}
        )
    }
}

@Preview(name = "Home – search tab", showBackground = true)
@Composable
private fun HomeScreenSearchTabPreview() {
    PratitiTheme(dynamicColor = false) {
        HomeScreenContent(
            selectedTab = MainTab.Search,
            onTabSelected = {},
            homeUiState = HomeUiState.Success(previewCategories),
            onRetry = {},
            onCategoryClick = {}
        )
    }
}
