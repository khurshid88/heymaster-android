package com.heymaster.heymaster.model.auth

import com.heymaster.heymaster.model.*

data class CurrentUser(
    val accountNonExpired: Boolean,
    val accountNonLocked: Boolean,
    val active: Boolean,
    val approximateEndTime: Any,
    val attachments: List<AttachmentInfo>,
    val authorities: List<Authority>,
    val birthDate: String?,
    val busy: Boolean,
    val createAt: String,
    val createdBy: Any,
    val credentialsNonExpired: Boolean,
    val enabled: Boolean,
    val experienceYear: Int,
    val fullName: String,
    val gender: Boolean,
    val generatePassword: Any,
    val id: Int,
    val location: Location,
    val password: Any,
    val peopleReitedCount: Int,
    val phoneNumber: String,
    val professionList: List<Any>,
    val profilePhoto: AttachmentInfo? = null,
    val roles: Roles,
    val salary: Any,
    val totalMark: Int,
    val updateAt: String,
    val updatedBy: Any,
    val username: String,
    val device: Device,
    val alreadyIsMaster: Boolean
)


data class Location(
    val region: Region,
    val district: District
)

data class Roles(
    val authority: String,
    val id: Int,
    val roleName: String
)

data class Authority(
    val authority: String,
    val id: Int,
    val roleName: String
)