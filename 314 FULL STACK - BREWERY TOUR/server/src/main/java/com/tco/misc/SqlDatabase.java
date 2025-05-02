package com.tco.misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlDatabase {

  private final static String TABLE = "world";
  private final static String COLUMNS = "world.id,world.name,world.municipality,world.type,world.iso_region,world.iso_country,world.latitude,world.longitude,world.altitude";

  private Places places;
  private SqlCredential creds;
  private SqlSelect Select;

  public SqlDatabase() {
    this.creds = new SqlCredential();
    this.Select = new SqlSelect(TABLE, COLUMNS);
  }

  // New helper method for executing SQL queries
  private ResultSet executeQuery(String sql) throws Exception {
    Connection conn = DriverManager.getConnection(creds.url(), creds.USER, creds.PASSWORD);
    Statement query = conn.createStatement();
    return query.executeQuery(sql);
  }

  public Integer found(String match, Integer limit) throws Exception {
    try {
      return found(match, null, null);
    } catch (Exception e) {
      throw e;
    }
  }

  public Integer found(String match, List<String> type, List<String> where) throws Exception {
    String sql = "";
    sql = Select.found(match, type, where);
    try (
        ResultSet results = executeQuery(sql)
    ) {
      return count(results);
    } catch (Exception e) {
      throw e;
    }
  }

  private static Integer count(ResultSet results) throws Exception {
    if (results.next()) {
      return results.getInt("count");
    }
    throw new Exception("No count results in found query.");
  }

  // Calls places with updated null
  public Places places(String match, Integer limit) throws Exception {
    try {
      return places(match, limit, null, null);
    } catch (Exception e) {
      throw e;
    }
  }

  public Places places(String match, Integer limit, List<String> type, List<String> where) throws Exception {
    String sql = "";
    sql = Select.matchTypeWhere(match, limit, type, where);
    try (
        ResultSet results = executeQuery(sql)
    ) {
      return convertQueryResultsToPlaces(results);
    } catch (Exception e) {
      throw e;
    }
  }

  public Places near(Integer limit, double boxTop, double boxBottom, double boxLeft, double boxRight) throws Exception {
    String nearColumns = "world.id, world.name, world.municipality, world.type, world.iso_region AS region, world.iso_country AS country, world.latitude, world.longitude, world.altitude";
    String sql = Select.near(nearColumns, limit, boxTop, boxBottom, boxLeft, boxRight);
    try (
        ResultSet results = executeQuery(sql)
    ) {
      return convertQueryResultsToPlaces(results);
    } catch (Exception e) {
      throw e;
    }
  }

  public static Places convertQueryResultsToPlaces(ResultSet results) throws Exception {
    int count = 0;
    String[] cols = ("name,latitude,longitude,altitude,type,municipality").split(",");
    Places places = new Places();
    while (results.next()) {
      Place place = new Place();
      for (String col : cols) {
        place.put(col, results.getString(col));
      }
      places.add(place);
    }
    return places;
  }

  // Function to get all country names and add them to an arraylist, this is used in config
  public ArrayList<String> getAllCountryNames() {
    String sql = Select.getAllCountries();
    ArrayList<String> outArrayList = new ArrayList<>();
    try (
        ResultSet results = executeQuery(sql)
    ) {
      // Function that loops results and prints
      while (results.next()) {
        outArrayList.add(results.getString("country.name"));
      }
    } catch (Exception e) {
      // Optionally log the error
    }
    return outArrayList;
  }
}
