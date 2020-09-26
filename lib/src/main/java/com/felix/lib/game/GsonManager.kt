package com.felix.lib.game

import com.google.gson.Gson
import java.lang.reflect.Type

internal class GsonManager {
    companion object {
        val instance by lazy { GsonManager() }
    }

    val gson: Gson = Gson()
}

val GsonProxy
    get() = GsonManager.instance.gson

inline fun <T> T.toJson() = GsonProxy.toJson(this) ?: "null"

inline fun <reified T> String.fromJson(): T =
    GsonProxy.fromJson<T>(this, object : com.google.gson.reflect.TypeToken<T>() {}.type)

@Deprecated("use String.fromJson()", ReplaceWith("this.fromJson<T>()"))
inline fun <reified T> String.fromJson(type: Type) = this.fromJson<T>()