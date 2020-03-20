package com.omertex.test.app.data.model


import com.omertex.mvprx.domain.global.mappers.BaseMapperPair
import com.omertex.test.app.data.model.placeholder.User
import com.omertex.test.app.data.model.unsplash.Photo

class MergeModelMapper : BaseMapperPair<User, Photo, MergeModel> {
    override fun map(fromFirst: User, fromSecond: Photo): MergeModel {
        return MergeModel(
            address = fromFirst.address,
            company = fromFirst.company,
            email = fromFirst.email,
            id = fromFirst.id,
            name = fromFirst.name,
            phone = fromFirst.phone,
            username = fromFirst.username,
            website =fromFirst.website ,
            urls = fromSecond.urls
        )
    }

}