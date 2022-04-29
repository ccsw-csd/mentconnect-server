package com.capgemini.mentconnect.config.security;

import com.capgemini.mentconnect.security.dto.UserDetailsJWTDto;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author mvallsal
 *
 */
public class UserUtils {

  /**
   * @return UserDetailsJWTDto
   */
  public static UserDetailsJWTDto getUserDetails() {

    return (UserDetailsJWTDto) SecurityContextHolder.getContext().getAuthentication().getDetails();
  }

}
