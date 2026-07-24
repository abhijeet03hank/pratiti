package com.devdatt.pratiti.core.util

import android.content.Context

/**
 * Resolves a raw resource id from a catalog file name.
 *
 * Example: `"sakal_pooja_avsar.mp3"` → `R.raw.sakal_pooja_avsar`.
 * Returns `0` if the resource does not exist.
 */
fun getRawResId(context: Context, fileName: String): Int {
    return context.resources.getIdentifier(
        fileName.substringBefore("."),
        "raw",
        context.packageName
    )
}
