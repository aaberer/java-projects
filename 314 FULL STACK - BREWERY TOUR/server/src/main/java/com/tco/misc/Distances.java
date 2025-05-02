package com.tco.misc;

import java.util.ArrayList;

public class Distances extends ArrayList<Long> {

  public long total() {
    long sum = 0;
    for (long distance : this) {
      sum += distance;
    }
    return sum;
  }

  public Distances() {

  }
}
