package com.devdatt.pratiti.feature.home.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.devdatt.pratiti.ui.theme.BottomBarBackground
import com.devdatt.pratiti.ui.theme.BottomBarSelected
import com.devdatt.pratiti.ui.theme.BottomBarUnselected

/**
 * Main bottom tabs shown on the Home shell.
 * Selection is UI-only for now; Search / Library / Account are placeholders.
 */
enum class MainTab(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    Home("Home", Icons.Filled.Home, Icons.Outlined.Home),
    Search("Search", Icons.Filled.Search, Icons.Outlined.Search),
    Library("Library", Icons.Filled.LibraryMusic, Icons.Outlined.LibraryMusic),
    Account("Account", Icons.Filled.AccountCircle, Icons.Outlined.AccountCircle)
}

/**
 * Bottom navigation bar (Home, Search, Library, Account).
 * Does not navigate between NavHost destinations yet — only updates [selectedTab].
 */
@Composable
fun PratitiBottomBar(
    selectedTab: MainTab,
    onTabSelected: (MainTab) -> Unit
) {
    NavigationBar(
        containerColor = BottomBarBackground,
        contentColor = BottomBarUnselected,
        tonalElevation = 0.dp
    ) {
        MainTab.entries.forEach { tab ->
            val selected = tab == selectedTab
            NavigationBarItem(
                selected = selected,
                onClick = { onTabSelected(tab) },
                icon = {
                    Icon(
                        imageVector = if (selected) tab.selectedIcon else tab.unselectedIcon,
                        contentDescription = tab.label
                    )
                },
                label = { Text(text = tab.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = BottomBarSelected,
                    selectedTextColor = BottomBarSelected,
                    unselectedIconColor = BottomBarUnselected,
                    unselectedTextColor = BottomBarUnselected,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
