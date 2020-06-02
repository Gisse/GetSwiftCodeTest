package com.djevtic.myswifttestcode.utils

import com.google.gson.Gson

object Serializer {

    private val typeFactory = TypeFactory()
    private val gson = Gson()

    fun serialize(model: Any?): String {
        return gson.toJson(model)
    }

    fun <T> deserialize(string: String, objectType: TypeFactory.ObjectType): T {
        return gson.fromJson(string, typeFactory.getType(objectType)) as T
    }

}