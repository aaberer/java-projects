package com.tco.requests;


import com.tco.misc.BadRequestException;
import com.tco.misc.Places;
import com.tco.misc.SqlDatabase;
import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindRequest extends Request {

  public static final transient Logger log = LoggerFactory.getLogger(DistancesRequest.class);

  private String match;
  private List<String> type;
  private List<String> where;
  private Integer limit;
  private Integer found;
  private Places places;

  //constructor for testing
  public FindRequest(String match, List<String> type, List<String> where, Integer limit) {
    this.match = match;
    this.type = type;
    this.where = where;
    this.limit = limit;
  }

  @Override
  public void buildResponse() throws BadRequestException {
    find();
    log.trace("buildResponse -> {}", this);
  }

  private void find() throws BadRequestException {
    //Make new database and pull from creds
    SqlDatabase database = new SqlDatabase();
    //These are defaults if the data is not set or set wrong, see the spec guide for info
    Integer nonReturnLimit = (limit != null && limit != 0) ? limit : 200; // changes to limit or default to 105 spec
    if (limit > 200) {
      nonReturnLimit = 200;
    }
    try {
      //This is used to check if the countries provided are provided in the config
      if (!(where == null)) {
        ArrayList<String> possibleCountries = database.getAllCountryNames();
        for (String country : where) {
          if (!(possibleCountries.contains(country))) {
            throw new BadRequestException();
          }
        }
      }
      //pull data and set interal values to be sent back to user
      this.places = database.places(this.match, nonReturnLimit, this.type, this.where);
      this.found = database.found(this.match, this.type, this.where);


    } catch (BadRequestException e) {
      throw e;
    } catch (Exception e) {
      log.error("Error on find request", e);
    }
  }
}
