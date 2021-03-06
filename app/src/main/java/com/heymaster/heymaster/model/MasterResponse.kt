package com.heymaster.heymaster.model

import com.heymaster.heymaster.model.masterdetail.Authority
import com.heymaster.heymaster.model.masterdetail.Device
import com.heymaster.heymaster.model.masterdetail.Location
import com.heymaster.heymaster.model.masterdetail.Profession
import com.heymaster.heymaster.model.masterdetail.Roles

data class MasterResponse(
    val accountNonExpired: Boolean,
    val accountNonLocked: Boolean,
    val active: Boolean,
    val approximateEndTime: Any,
    val attachments: List<Any>,
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
    val phoneNumber: String,
    val professionList: List<Profession>,
    val profilePhoto: Any,
    val roles: Roles,
    val salary: Any,
    val totalMark: Int,
    val updateAt: String,
    val updatedBy: Any,
    val username: String
)
