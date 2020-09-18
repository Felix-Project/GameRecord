package com.felix.lib.game

import com.google.gson.Gson
import java.lang.reflect.Type

internal class GsonManager {
    companion object {
        val instance by lazy { GsonManager() }
    }

    val gson: Gson

    init {
        gson = Gson()
    }
}

internal val GsonProxy
    get() = GsonManager.instance.gson

fun <T> T.toJson() = GsonProxy.toJson(this)

fun <T> String.fromJson(clazz: Class<T>) = GsonProxy.fromJson<T>(this, clazz)
fun <T> String.fromJson(type: Type) = GsonProxy.fromJson<T>(this, type)