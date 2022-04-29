package com.capgemini.mentconnect.security.dto;

/**
 * @author amirzoya
 *
 */
public class SecurityCredencialDto {

  private String accessToken;

  private Long expireTime;

  /**
   * @return accessToken
   */
  public String getAccessToken() {

    return this.accessToken;
  }

  /**
   * @param accessToken new value of {@link #getAccessToken}.
   */
  public void setAccessToken(String accessToken) {

    this.accessToken = accessToken;
  }

  /**
   * @return expireTime
   */
  public Long getExpireTime() {

    return this.expireTime;
  }

  /**
   * @param expireTime new value of {@link #getExpireTime}.
   */
  public void setExpireTime(Long expireTime) {

    this.expireTime = expireTime;
  }

}
