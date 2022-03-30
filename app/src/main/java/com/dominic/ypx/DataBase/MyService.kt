package com.dominic.ypx.DataBase

import com.dominic.ypx.Target.Target

interface MyService {

    fun addTarget(target: Target)
    fun getAllTargets():ArrayList<Target>
    fun editTarget(target: Target):Int
    fun deleteTarget(target: Target)
}