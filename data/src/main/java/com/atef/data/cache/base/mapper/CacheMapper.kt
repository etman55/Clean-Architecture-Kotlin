package com.atef.data.cache.base.mapper

interface CacheMapper<C, E> {

    fun mapFromCached(type: C): E

    fun mapToCached(entity: E): C

    fun mapFromEntityList(types: List<C>): List<E> {
        return types.mapTo(mutableListOf(), ::mapFromCached)
    }

    fun mapFromDomainList(entityModels: List<E>): List<C> {
        return entityModels.mapTo(mutableListOf(), ::mapToCached)
    }
}
