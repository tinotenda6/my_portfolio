package com.google.sps.data;

/** A message received from contactme form */
public final class Message {

  private final long id;
  private final String firstname;
  private final String lastname;
  private final String email;
  private final String message;

  public Message(long id,String firstname, String lastname, String email, String message) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.message = message;   
  }
}