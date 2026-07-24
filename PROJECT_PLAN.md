# Pratiti — Project Development Plan

Devotional music player (Kotlin, Jetpack Compose, Clean Architecture + ViewModel, Hilt).
Catalog size target: ~50–200 tracks. Cloudflare backend later; local `res/raw` for MVP testing.

## Goals

- Screens: Home, Category, Player
- Compose-only UI
- Clean Architecture with ViewModels
- Hilt for DI
- Support Android 10–17 (`minSdk` 29, modern `targetSdk`/`compileSdk`)
- Swap local catalog for Cloudflare without rewriting UI

## Current baseline (as of plan authoring)

| Area | Status |
|------|--------|
| Home / Category / Player screens | Present (mostly UI) |
| Navigation | String routes in `AppNavigation` |
| Media3 / `PlayerManager` | Basic play/pause from `raw` |
| Repository | Hardcoded `TrackRepository`, no interface |
| ViewModels | Manual repo construction — no Hilt |
| Hilt | Not wired |
| `minSdk` | 24 (target: 29) |

## Target package structure

```
app/src/main/java/com/devdatt/pratiti/
├── PratitiApp.kt                 # @HiltAndroidApp
├── MainActivity.kt               # @AndroidEntryPoint
├── di/                           # Hilt modules
├── core/
│   ├── navigation/               # Routes, NavHost
│   ├── player/                   # PlayerManager / MediaSession later
│   └── util/
├── data/
│   ├── model/                    # DTOs
│   ├── local/                    # Raw / assets catalog (Phase 1)
│   ├── remote/                   # Cloudflare (later)
│   └── repository/               # TrackRepositoryImpl
├── domain/
│   ├── model/                    # Track, Category
│   ├── repository/               # TrackRepository interface
│   └── usecase/
└── feature/
    ├── home/
    ├── category/
    └── player/
```

**Dependency rule:** UI → ViewModel → UseCase → Repository interface → DataSource.

## Phase 0 — Project foundation

1. Set `minSdk = 29` (Android 10); keep current `targetSdk` / `compileSdk`.
2. Add dependencies: Hilt + KSP, `hilt-navigation-compose`, Lifecycle ViewModel Compose; keep Media3.
3. Add `PratitiApp` (`@HiltAndroidApp`), register in manifest; `@AndroidEntryPoint` on `MainActivity`.
4. Apply `PratitiTheme` in `setContent`.
5. Reorganize packages to match target structure.

**Exit criteria:** App builds with Hilt; `minSdk` 29.

## Phase 1 — Domain + local data (`raw` / assets)

1. Domain models: `Track`, `Category` (prefer data-driven categories over hard enum if API-bound later).
2. Repository contract:

```kotlin
interface TrackRepository {
    suspend fun getCategories(): List<Category>
    suspend fun getTracksByCategory(categoryId: String): List<Track>
    suspend fun getTrackById(id: Int): Track?
}
```

3. Local data source (`RawTrackDataSource` or `assets/catalog.json` + mapper).
4. Resolve `fileName` → `raw` res id via existing helper.
5. Use cases: GetCategories, GetTracksByCategory, GetTrackById.
6. Hilt: bind repository; provide data source / `PlayerManager` as needed (`@Singleton` for player).

**Exit criteria:** ViewModels load data via use cases; no Cloudflare code required.

**Catalog preference:** `assets/catalog.json` over Kotlin lists (scales to 200 tracks; easier Cloudflare handoff).

## Phase 2 — Navigation + feature ViewModels

1. Type-safe routes: `home` → `category/{categoryId}` → `player/{trackId}` (use ids, not display names).
2. HomeViewModel → categories UiState; navigate by id.
3. CategoryViewModel → tracks for category; open player.
4. PlayerViewModel + injected playback controller; UiState (track, playing, position, duration).
5. App-wide singleton player (do not create `PlayerManager` only inside screen `remember`).

**Exit criteria:** Browse → list → play works with local files.

## Phase 3 — Player polish (still local)

1. Real seek + position/duration updates from ExoPlayer.
2. Next / previous within category (or queue).
3. Optional mini-player on Home/Category.
4. Lifecycle: safe pause/release; no leaks on rotation/back.
5. Phase 3b: Media3 Session + notification + lock screen controls.

**Exit criteria:** Reliable playback for local catalog.

## Phase 4 — UI / UX

1. Devotional-aligned theme (colors, type).
2. Loading / empty / error states.
3. Category artwork placeholders (local drawables first).
4. Accessibility + consistent edge-to-edge / system bars.

## Phase 5 — Cloudflare readiness (backend later)

| Abstraction | Phase 1 | Phase 5 |
|-------------|---------|---------|
| Track source | `raw` file name | HTTPS URL (R2/CDN) |
| Data source | Local / assets | Cloudflare API or static JSON |
| Networking | None | Retrofit/Ktor + OkHttp, HTTPS only |

Prep: keep repository choosing data source via Hilt; add internet permission only when remote is enabled.

## Build order

| Step | Focus | Outcome |
|------|--------|---------|
| 1 | Phase 0 | Hilt + SDK + packages |
| 2 | Phase 1 | Domain/repo/local catalog |
| 3 | Phase 2 Home + Category | Navigation + lists |
| 4 | Phase 2 Player | End-to-end local playback |
| 5 | Phase 3 | Seek, next/prev, lifecycle |
| 6 | Phase 4 | Visual polish |
| 7 | Phase 5 | Remote stub / Cloudflare swap |

## Decisions locked for MVP

1. Catalog: prefer `assets/catalog.json`.
2. Player: app-wide `@Singleton`.
3. Background audio / MediaSession: Phase 3b (after core UI + playback).
4. Categories: data-driven.
5. Queue: treat category as playlist when possible.

## Out of scope for structure / early phases

- Cloudflare networking implementation
- Large production libraries in `res/raw` (use a few short test mp3s only)
- ExoPlayer/Context inside ViewModels (wrap in player layer)
- Manual `TrackRepository()` inside ViewModels

## Immediate next action

Phase 0 + Phase 1 skeleton: Hilt setup, package folders, repository interface + local impl, inject ViewModels.
