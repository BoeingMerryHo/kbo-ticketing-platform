package com.boeingmerryho.business.membershipservice.presentation;

import org.springframework.http.HttpStatus;

import io.github.boeingmerryho.commonlibrary.response.SuccessCode;

public enum MembershipSuccessCode implements SuccessCode {
	CREATED_MEMBERSHIP("멤버십이 성공적으로 생성되었습니다", HttpStatus.CREATED),
	;

	private final String message;
	private final HttpStatus status;

	MembershipSuccessCode(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public HttpStatus getStatus() {
		return status;
	}
}
