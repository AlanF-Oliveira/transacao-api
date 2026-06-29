package com.alan.transacao_api.infrastructure.exception;

public class UnprocessableEntity extends RuntimeException {
  public UnprocessableEntity(String message) {
    super(message);
  }
}
