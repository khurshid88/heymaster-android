package com.heymaster.heymaster.role.master.repository

import com.heymaster.heymaster.data.network.ApiService
import com.heymaster.heymaster.model.editmasterprofile.EditMasterRequest
import okhttp3.RequestBody

class MasterProfileRepository(private val apiService: ApiService) {

    suspend fun getImages() = apiService.getImages()
    suspend fun getImage(id: Int) = apiService.getImage(id)

    suspend fun getMasterProfileInfo() = apiService.currentUser()

    suspend fun uploadAttachment(body: RequestBody) = apiService.uploadAttachment(body)

    suspend fun attachmentInfo() = apiService.attachmentInfo()

    suspend fun masterToClient() = apiService.masterToClient()

    suspend fun uploadProfilePhoto(body: RequestBody) = apiService.uploadProfilePhoto(body)
    suspend fun editProfileMaster(editMasterRequest: EditMasterRequest) = apiService.editMasterProfile(editMasterRequest)




    suspend fun getRegions() = apiService.getRegions()

    suspend fun getDistrictsFromRegion(id: Int) = apiService.getDistrictsFromRegion(id)

    suspend fun getProfessions() = apiService.getProfessions()


}