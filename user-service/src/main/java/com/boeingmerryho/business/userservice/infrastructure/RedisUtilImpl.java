package com.boeingmerryho.business.userservice.infrastructure;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.boeingmerryho.business.userservice.application.utils.RedisUtil;
import com.boeingmerryho.business.userservice.application.utils.jwt.JwtTokenProvider;
import com.boeingmerryho.business.userservice.domain.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RedisUtilImpl implements RedisUtil {
	private final RedisTemplate<String, Object> redisTemplate;
	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public void updateUserInfo(User user) {
		String userInfoKey = "user:info:" + user.getId();
		Map<String, Object> userInfoMap = new ConcurrentHashMap<>();

		userInfoMap.put("username", user.getUsername());
		userInfoMap.put("role", user.getRole());

		long expirationTime = jwtTokenProvider.getRefreshTokenExpiration();
		redisTemplate.delete(userInfoKey);
		redisTemplate.opsForHash().putAll(userInfoKey, userInfoMap);
		redisTemplate.expire(userInfoKey, expirationTime, TimeUnit.MILLISECONDS);
	}

	@Override
	public Map<String, String> updateUserJwtToken(Long id) {
		String accessToken = jwtTokenProvider.generateAccessToken(id);
		String refreshToken = jwtTokenProvider.generateRefreshToken(id);

		String tokenKey = "user:token:" + id;

		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("accessToken", accessToken);
		tokenMap.put("refreshToken", refreshToken);
		redisTemplate.delete(tokenKey);
		redisTemplate.opsForHash().putAll(tokenKey, tokenMap);

		long expirationTime = jwtTokenProvider.getRefreshTokenExpiration();
		redisTemplate.expire(tokenKey, expirationTime, TimeUnit.MILLISECONDS);

		return tokenMap;
	}

}
