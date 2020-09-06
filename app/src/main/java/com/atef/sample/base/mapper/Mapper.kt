package com.atef.sample.base.mapper

interface Mapper<out V, in D> {

    fun mapToView(type: D): V
}
