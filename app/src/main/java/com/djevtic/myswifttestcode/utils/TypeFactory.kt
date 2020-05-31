package com.djevtic.myswifttestcode.utils

import com.djevtic.myswifttestcode.network.models.standing.Standing
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class TypeFactory {

    enum class ObjectType {
        TEAM,
    }

    fun getType(objectType: ObjectType): Type {

        return when (objectType) {
            ObjectType.TEAM -> return object : TypeToken<Standing>() {
            }.type
        }

    }

}