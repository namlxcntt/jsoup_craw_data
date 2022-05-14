package com.lxn.appuploaddata.data.mapper

import com.lxn.appuploaddata.data.model.Category
import com.lxn.appuploaddata.data.model.CategoryCacheEntity
import javax.inject.Inject

class CategoryMapper @Inject constructor() : EntityMapper<CategoryCacheEntity, Category> {
    override fun mapFromEntity(entity: CategoryCacheEntity): Category {
        return Category(
            categoryId = entity.categoryId,
            createDate = entity.createDate,
            createBy = entity.createBy,
            updateBy = entity.updateBy,
            updateDate = entity.updateDate,
            title = entity.title,
            description = entity.description,
            image = entity.image
        )
    }

    override fun mapToEntity(domainModel: Category): CategoryCacheEntity {
        return CategoryCacheEntity(
            categoryId = domainModel.categoryId,
            createDate = domainModel.createDate,
            createBy = domainModel.createBy,
            updateBy = domainModel.updateBy,
            updateDate = domainModel.updateDate,
            title = domainModel.title,
            description = domainModel.description,
            image = domainModel.image
        )
    }

    fun mapFromEntityList(listEntity: List<CategoryCacheEntity>): List<Category> {
        return listEntity.map {
            mapFromEntity(it)
        }
    }
}