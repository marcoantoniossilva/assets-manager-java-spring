package io.github.marcoantoniossilva.assets_manager.common;

import io.github.marcoantoniossilva.assets_manager.domain.model.User;

public class LoggedUser {

  private static final ThreadLocal<User> currentUser = ThreadLocal.withInitial(() -> null);

  public static User getUser() {
    return currentUser.get();
  }

  public static void setUser(User user) {
    currentUser.set(user);
  }

  public static void clear(){
    currentUser.remove();
  }

}
