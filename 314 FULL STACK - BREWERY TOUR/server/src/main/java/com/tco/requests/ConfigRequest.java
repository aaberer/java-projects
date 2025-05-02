package com.tco.requests;

import java.util.List;
import java.util.ArrayList;
import com.tco.misc.SqlDatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigRequest extends Request {

  private static final transient Logger log = LoggerFactory.getLogger(ConfigRequest.class);

  private String serverName;
  private List<String> features;
  private List<String> formulae;
  private List<String> type;
  private List<String> where;


  @Override
  public void buildResponse() {
    serverName = "t46 Software Samurais";
    features = new ArrayList<>();
    formulae = new ArrayList<>();
    type = new ArrayList<>();
    where = new ArrayList<>();

    //Features
    features.add("config");
    features.add("distances");
    features.add("tour");
    features.add("near");
    features.add("find");
    features.add("type");
    features.add("where");

    //Fomrulas
    formulae.add("vincenty");
    formulae.add("haversine");
    formulae.add("cosines");

    //Types
    type.add("airport");
    type.add("heliport");
    type.add("balloonport");
    type.add("other");

    //Where
    SqlDatabase database = new SqlDatabase();
    where = database.getAllCountryNames();

    log.trace("buildResponse -> {}", this);
  }

  /* The following methods exist only for testing purposes and are not used
  during normal execution, including the constructor. */

  public ConfigRequest() {
    this.requestType = "config";
  }

  public String getServerName() {
    return serverName;
  }

  public boolean validFeature(String feature) {
    return features.contains(feature);
  }
}
