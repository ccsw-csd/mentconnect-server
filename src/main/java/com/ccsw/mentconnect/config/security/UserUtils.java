package com.ccsw.mentconnect.config.security;

import com.ccsw.mentconnect.security.dto.UserDetailsJWTDto;
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
