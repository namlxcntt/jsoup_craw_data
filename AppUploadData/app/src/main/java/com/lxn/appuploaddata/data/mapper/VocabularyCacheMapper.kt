package com.lxn.appuploaddata.data.mapper

import com.lxn.appuploaddata.data.model.Vocabulary
import com.lxn.appuploaddata.data.model.VocabularyCacheEntity
import javax.inject.Inject

class VocabularyCacheMapper @Inject constructor() : EntityMapper<VocabularyCacheEntity, Vocabulary> {
    override fun mapFromEntity(entity: VocabularyCacheEntity): Vocabulary {
        return Vocabulary(
            id = entity.id,
            topic = entity.topic,
            id_temp = entity.id_temp,
            vocabulary = entity.vocabulary,
            vocalization = entity.vocalization,
            explanation = entity.explanation,
            transalte = entity.transalte,
            example = entity.example,
            exampleTranslate = entity.exampleTranslate
        )
    }

    override fun mapToEntity(domainModel: Vocabulary): VocabularyCacheEntity {
        return VocabularyCacheEntity(
            id = domainModel.id,
            topic = domainModel.topic,
            id_temp = domainModel.id_temp,
            vocabulary = domainModel.vocabulary,
            vocalization = domainModel.vocalization,
            explanation = domainModel.explanation,
            transalte = domainModel.transalte,
            example = domainModel.example,
            exampleTranslate = domainModel.exampleTranslate
        )
    }

    fun mapFromEntityList(listEntity: List<VocabularyCacheEntity>): List<Vocabulary> {
        return listEntity.map {
            mapFromEntity(it)
        }
    }
}