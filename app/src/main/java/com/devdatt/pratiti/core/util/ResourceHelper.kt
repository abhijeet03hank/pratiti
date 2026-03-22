package com.devdatt.pratiti.core.util

import android.content.Context

fun getRawResId(context: Context, fileName: String): Int {
    return context.resources.getIdentifier(
        fileName.substringBefore("."),
        "raw",
        context.packageName
    )
}