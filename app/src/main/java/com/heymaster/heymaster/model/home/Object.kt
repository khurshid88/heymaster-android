package com.heymaster.heymaster.model.home

import com.heymaster.heymaster.model.AttachmentInfo

data class Object(
    val accountNonExpired: Boolean,
    val accountNonLocked: Boolean,
    val active: Boolean,
    val approximateEndTime: Any,
    val attachments: List<AttachmentInfo>,
    val authorities: List<Authority>,
    val birthDate: String,
    val busy: Boolean,
    val createAt: String,
    val createdBy: Any,
    val credentialsNonExpired: Boolean,
    val device: Device,
    val enabled: Boolean,
    val experienceYear: Int,
    val fullName: String,
    val gender: Boolean,
    val generatePassword: String,
    val id: Int,
    val location: Location,
    val password: String,
    val peopleReitedCount: Int,
    val phoneNumber: String?,
    val professionList: List<Profession>,
    val profilePhoto: AttachmentInfo?,
    val roles: Roles,
    val salary: Any,
    val totalMark: Int,
    val updateAt: String,
    val updatedBy: Any,
    val username: String
)