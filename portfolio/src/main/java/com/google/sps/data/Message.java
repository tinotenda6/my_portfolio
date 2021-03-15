package com.google.sps.data;

/** A message received from contactme form */
public final class Message {

  private final long id;
  private final String email;
  private final String message;
  ;

  public Message(long id, String email, String message) {
    this.id = id;
    this.email = email;
    this.message = message;   
  }
}