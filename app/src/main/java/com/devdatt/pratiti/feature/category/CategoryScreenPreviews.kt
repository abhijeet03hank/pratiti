package com.devdatt.pratiti.feature.category

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.devdatt.pratiti.domain.model.Category
import com.devdatt.pratiti.domain.model.Track
import com.devdatt.pratiti.ui.theme.PratitiTheme

private val previewCategory = Category(
    id = "pooja_avsar",
    name = "Pooja Avsar",
    displayName = "Pooja Avsar"
)

private val previewTracks = listOf(
    Track(
        id = 1,
        title = "Sakal Pooja Avsar",
        categoryId = "pooja_avsar",
        fileName = "sakal_pooja_avsar.mp3"
    ),
    Track(
        id = 2,
        title = "Sayankal Pooja Avsar",
        categoryId = "pooja_avsar",
        fileName = "sayankal_pooja_avsar.mp3"
    )
)

@Preview(name = "Category – tracks", showBackground = true, showSystemUi = true)
@Composable
private fun CategoryScreenPreview() {
    PratitiTheme(dynamicColor = false) {
        CategoryScreenContent(
            categoryId = previewCategory.id,
            uiState = CategoryUiState.Success(
                category = previewCategory,
                tracks = previewTracks
            ),
            onTrackClick = {}
        )
    }
}

@Preview(name = "Category – empty", showBackground = true)
@Composable
private fun CategoryScreenEmptyPreview() {
    PratitiTheme(dynamicColor = false) {
        CategoryScreenContent(
            categoryId = previewCategory.id,
            uiState = CategoryUiState.Success(
                category = previewCategory,
                tracks = emptyList()
            ),
            onTrackClick = {}
        )
    }
}

@Preview(name = "Category – loading", showBackground = true)
@Composable
private fun CategoryScreenLoadingPreview() {
    PratitiTheme(dynamicColor = false) {
        CategoryScreenContent(
            categoryId = previewCategory.id,
            uiState = CategoryUiState.Loading,
            onTrackClick = {}
        )
    }
}
