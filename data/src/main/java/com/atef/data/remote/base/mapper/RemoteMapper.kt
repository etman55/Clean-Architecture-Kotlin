package com.atef.data.remote.base.mapper

/**
 * @author Atef Etman.
 * @email etman850@gmail.com.
 * @phone +201090705106.
 */

interface RemoteMapper<M, E> {

    fun mapFromModel(model: M): E

    fun mapToModel(entity: E): M

    fun mapModelList(models: List<M>): List<E> {
        return models.mapTo(mutableListOf(), ::mapFromModel)
    }

    fun mapEntityList(entities: List<E>): List<M> {
        return entities.mapTo(mutableListOf(), ::mapToModel)
    }
}
