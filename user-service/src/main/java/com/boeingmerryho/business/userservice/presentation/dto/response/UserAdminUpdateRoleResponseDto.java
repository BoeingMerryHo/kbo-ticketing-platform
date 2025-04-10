package com.boeingmerryho.business.userservice.presentation.dto.response;

import com.boeingmerryho.business.userservice.domain.UserRoleType;

public record UserAdminUpdateRoleResponseDto(Long id, UserRoleType role, UserRoleType newRole) {

}
