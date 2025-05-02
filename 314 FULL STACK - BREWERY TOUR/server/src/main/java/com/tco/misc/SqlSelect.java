package com.tco.misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.Exception;
import java.util.StringJoiner;
import java.util.Collections;


public class SqlSelect {

  private String TABLE;
  private String COLUMNS;

  public SqlSelect(String TABLE, String COLUMNS) {
    this.TABLE = TABLE;
    this.COLUMNS = COLUMNS;
  }

  public String found(String match, List<String> typeList, List<String> whereList) {
    if (typeList == null) {
      typeList = Collections.<String>emptyList();
    }
    if (whereList == null) {
      whereList = Collections.<String>emptyList();
    }
    return statement(match, "COUNT(*) AS count ", typeList, whereList, "");
  }

  public String statement(String match, String data, String limit) {
    return "SELECT "
        + data
        + " FROM " + TABLE
        + " WHERE name LIKE \"%" + match + "%\" "
        + limit
        + " ;";
  }

  public String matchTypeWhere(String match, int limit, List<String> typeList, List<String> whereList) {
    return statement(match, "DISTINCT " + this.COLUMNS + (",country.name,country.id"), typeList, whereList,
        "LIMIT " + limit);
  }

  //world.name,world.id,country.name,region.name,world.municipality
  public String statement(String match, String data, List<String> types, List<String> wheres, String limit) {
    //this sets null to emtpy not have errors accessing null pointer
    if (types == null) {
      types = Collections.<String>emptyList();
    }
    if (wheres == null) {
      wheres = Collections.<String>emptyList();
    }
    StringBuilder query = new StringBuilder();
    query.append("SELECT ")
        .append(data)
        .append(" FROM world ")
        .append("INNER JOIN country ON world.iso_country = country.id ")
        .append("INNER JOIN region ON world.iso_region = region.id ")
        .append("WHERE (")
        .append("world.id LIKE \"%").append(match).append("%\" OR ")
        .append("world.name LIKE \"%").append(match).append("%\" OR ")
        .append("world.municipality LIKE \"%").append(match).append("%\" OR ")
        .append("country.name LIKE \"%").append(match).append("%\" OR ")
        .append("region.name LIKE \"%").append(match).append("%\") ");

    if (!types.isEmpty()) {
      query = addTypes(query, types);
    }

    if (!wheres.isEmpty()) {
      query = addWheres(query, wheres);
    }

    if (match.isEmpty()) {
      query.append(" ORDER BY RAND() ");
    }

    query.append(limit).append(";");
    return query.toString();
  }

  private StringBuilder addTypes(StringBuilder query, List<String> types) {
    query.append("AND (");
    for (int i = 0; i < types.size(); i++) {
      if (!types.get(i).equals("other")) {
        query.append("world.type LIKE \"%")
            .append(types.get(i))
            .append("%\"");
        if (i < types.size() - 1) {
          query.append(" OR ");
        }
      } else {
        query.append("world.type LIKE \"%seaplane_base%\" OR world.type LIKE \"%closed%\"");
        if (i < types.size() - 1) {
          query.append(" OR ");
        }
      }
    }
    query.append(") ");
    return query;
  }

  private StringBuilder addWheres(StringBuilder query, List<String> wheres) {
    query.append("AND (");
    for (int i = 0; i < wheres.size(); i++) {
      query.append("country.name LIKE \"%")
          .append(wheres.get(i))
          .append("%\"");
      if (i < wheres.size() - 1) {
        query.append(" OR ");
      }
    }
    query.append(") ");
    return query;
  }

  public String near(String columns, Integer limit, double boxTop, double boxBottom, double boxLeft, double boxRight) {
    String where =
        "WHERE latitude BETWEEN " + boxTop + " AND " + boxBottom + " AND longitude BETWEEN " + boxLeft + " AND "
            + boxRight;
    String sqlReturn = "SELECT " + columns + " FROM world " + where + " LIMIT " + limit + ";";
    return sqlReturn;
  }

  public String getAllCountries() {
    return "SELECT DISTINCT name FROM country;";
  }
}
