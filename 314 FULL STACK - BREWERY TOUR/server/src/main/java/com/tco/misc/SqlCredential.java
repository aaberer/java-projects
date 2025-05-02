package com.tco.misc;

public class SqlCredential {

  // shared user with read-only access
  final static String USER = "cs314-db";
  final static String PASSWORD = "eiK5liet1uej";

  //Local host url
  //use ssh -L 56247:faure.cs.colostate.edu:3306 <NETID>@char.cs.colostate.edu in your terminal to portforward if using this URL
  //final static String URL = "jdbc:mariadb://127.0.0.1:56247/cs314";

  //Production URL
  final static String URL = "jdbc:mariadb://faure.cs.colostate.edu/cs314";

  static String url() {
    return URL;
  }
}
