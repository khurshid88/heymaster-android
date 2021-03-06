package com.heymaster.heymaster.role.client.repository

import com.heymaster.heymaster.data.network.ApiService
import com.heymaster.heymaster.model.auth.ClientToMasterRequest
import com.heymaster.heymaster.model.editmasterprofile.EditClientRequest
import com.heymaster.heymaster.model.editmasterprofile.EditMasterRequest
import okhttp3.RequestBody

class ClientProfileRepository(
    private val apiService: ApiService
) {

    suspend fun currentUser() = apiService.currentUser()

    suspend fun uploadAttachment(body: RequestBody) = apiService.uploadAttachment(body)

    suspend fun uploadProfilePhoto(body: RequestBody) = apiService.uploadProfilePhoto(body)

    suspend fun attachmentInfo() = apiService.attachmentInfo()

    suspend fun clientToMaster(clientToMasterRequest: ClientToMasterRequest) = apiService.clientToMaster(clientToMasterRequest)

    suspend fun clientToMasterIsAlreadyMaster() = apiService.clientToMasterIsAlreadyMaster()

    suspend fun editProfileClient(editClientRequest: EditClientRequest) = apiService.editClientProfile(editClientRequest)
}