package com.heymaster.heymaster.role.client.repository

import com.heymaster.heymaster.data.network.ApiService

class ClientHomeRepository(
    private val apiService: ApiService
) {
    suspend fun getServices() = apiService.getServices()

    suspend fun getService(id: Int) = apiService.getService(id)

    suspend fun getAds() = apiService.getAds()

    suspend fun getHome() = apiService.getHome()

    suspend fun getProfessions() = apiService.getProfessions()

    suspend fun getProfessionFromCategory(id: String) = apiService.getProfessionFromCategory(id)

    suspend fun getActiveMasters() = apiService.getActiveMasters()

    suspend fun getMasterFromProfessionId(id: Int) = apiService.getMasterFromProfession(id)

    suspend fun getAllCategory() = apiService.getAllCategory()


}