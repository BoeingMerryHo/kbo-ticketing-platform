package com.boeingmerryho.business.userservice.application.dto.response;

import com.boeingmerryho.business.userservice.domain.UserRoleType;

public record UserAdminSearchResponseServiceDto(Long id, String userName, String slackId,
												UserRoleType role, Boolean isDeleted) {

}
