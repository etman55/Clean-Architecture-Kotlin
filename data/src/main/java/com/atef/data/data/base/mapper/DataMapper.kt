package com.atef.data.data.base.mapper

interface DataMapper<E, D> {

    fun mapFromData(data: E): D

    fun mapToData(domain: D): E

    fun mapFromEntityList(dataModels: List<E>): List<D> {
        return dataModels.mapTo(mutableListOf(), ::mapFromData)
    }

    fun mapFromDomainList(domainModels: List<D>): List<E> {
        return domainModels.mapTo(mutableListOf(), ::mapToData)
    }
}
