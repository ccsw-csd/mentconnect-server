package com.ccsw.mentconnect.security.dto;

/**
 * @author amirzoya
 *
 */
public class LoginDto {

  private String username;

  private String password;

  /**
   * @return username
   */
  public String getUsername() {

    return this.username;
  }

  /**
   * @param username new value of {@link #getUsername}.
   */
  public void setUsername(String username) {

    this.username = username;
  }

  /**
   * @return password
   */
  public String getPassword() {

    return this.password;
  }

  /**
   * @param password new value of {@link #getPassword}.
   */
  public void setPassword(String password) {

    this.password = password;
  }

}
