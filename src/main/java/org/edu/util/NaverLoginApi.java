package org.edu.util;

import com.github.scribejava.core.builder.api.DefaultApi20;

public class NaverLoginApi extends DefaultApi20 {

	@Override
	public String getAccessTokenEndpoint() {
		//QAuth2.0 인증 체크 네이버 Rest-API 주소 : 로그인해도 되는 사람인지 체크 (관리자,일반사용자 인지 체크 안함)
		return "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		//네이버 권한 체크 하는 Rest_API 주소
		return "https://nid.naver.com/oauth2.0/authorize";
	}

}
