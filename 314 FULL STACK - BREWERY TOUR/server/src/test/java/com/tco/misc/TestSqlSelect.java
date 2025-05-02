package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class TestSqlSelect {

    @Test
    @DisplayName(" nlrohr: Get all countries")
    public void testGetAllCountries() {
        SqlSelect sqlSelect = new SqlSelect("world", "name, id");
        String result = sqlSelect.getAllCountries();
        String expected = "SELECT DISTINCT name FROM country;";
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("wwardlow: Test near")
    public void testNear() {
        SqlSelect sqlSelect = new SqlSelect("world", "name, id");
        String result = sqlSelect.near("country", 10, 10.0, 0.0, 10.0, 0.0);
        String expected = "SELECT country FROM world WHERE latitude BETWEEN 10.0 AND 0.0 AND longitude BETWEEN 10.0 AND 0.0 LIMIT 10;";
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("wwardlow: Test found multiple args")
    public void testFoundArgs() {
        SqlSelect sqlSelect = new SqlSelect("world", "name, id");
        String result = sqlSelect.statement("Chicago", null, null);
        String expected = "SELECT null FROM world WHERE name LIKE \"%Chicago%\" null ;";
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("wwardlow: Test MatchTypeWhere")
    public void testMatchTypeWhere() {
        SqlSelect sqlSelect = new SqlSelect("world", "name, id");
        String result = sqlSelect.matchTypeWhere("Chicago", 10, null, null);
        String expected = "SELECT DISTINCT name, id,country.name,country.id FROM world INNER JOIN country ON world.iso_country = country.id INNER JOIN region ON world.iso_region = region.id WHERE (world.id LIKE \"%Chicago%\" OR world.name LIKE \"%Chicago%\" OR world.municipality LIKE \"%Chicago%\" OR country.name LIKE \"%Chicago%\" OR region.name LIKE \"%Chicago%\") LIMIT 10;";
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("wwardlow: Test found multiple args no null")
    public void testFoundNoNull() {
        ArrayList<String> typeList = new ArrayList<>();
        typeList.add("California");
        ArrayList<String> whereList = new ArrayList<>();
        whereList.add("Denver");
        SqlSelect sqlSelect = new SqlSelect("world", "name, id");
        String result = sqlSelect.found("Chicago", typeList, whereList);
        String expected = "SELECT COUNT(*) AS count  FROM world INNER JOIN country ON world.iso_country = country.id INNER JOIN region ON world.iso_region = region.id WHERE (world.id LIKE \"%Chicago%\" OR world.name LIKE \"%Chicago%\" OR world.municipality LIKE \"%Chicago%\" OR country.name LIKE \"%Chicago%\" OR region.name LIKE \"%Chicago%\") AND (world.type LIKE \"%California%\") AND (country.name LIKE \"%Denver%\") ;";
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("wwardlow: Test found type Others")
    public void testFoundOthers() {
        ArrayList<String> typeList = new ArrayList<>();
        typeList.add("other");
        ArrayList<String> whereList = new ArrayList<>();
        whereList.add("Denver");
        SqlSelect sqlSelect = new SqlSelect("world", "name, id");
        String result = sqlSelect.found("Chicago", typeList, whereList);
        String expected = "SELECT COUNT(*) AS count  FROM world INNER JOIN country ON world.iso_country = country.id INNER JOIN region ON world.iso_region = region.id WHERE (world.id LIKE \"%Chicago%\" OR world.name LIKE \"%Chicago%\" OR world.municipality LIKE \"%Chicago%\" OR country.name LIKE \"%Chicago%\" OR region.name LIKE \"%Chicago%\") AND (world.type LIKE \"%seaplane_base%\" OR world.type LIKE \"%closed%\") AND (country.name LIKE \"%Denver%\") ;";
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("wwardlow: Test found Empty Match")
    public void testFoundEmptyMatch() {
        SqlSelect sqlSelect = new SqlSelect("world", "name, id");
        String result = sqlSelect.found("", null, null);
        String expected = "SELECT COUNT(*) AS count  FROM world INNER JOIN country ON world.iso_country = country.id INNER JOIN region ON world.iso_region = region.id WHERE (world.id LIKE \"%%\" OR world.name LIKE \"%%\" OR world.municipality LIKE \"%%\" OR country.name LIKE \"%%\" OR region.name LIKE \"%%\")  ORDER BY RAND() ;";
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("wwardlow: Test found type Others w/ Additional")
    public void testFoundOthersAdditional() {
        ArrayList<String> typeList = new ArrayList<>();
        typeList.add("other");
        typeList.add("other");
        ArrayList<String> whereList = new ArrayList<>();
        whereList.add("Denver");
        SqlSelect sqlSelect = new SqlSelect("world", "name, id");
        String result = sqlSelect.found("Chicago", typeList, whereList);
        String expected = "SELECT COUNT(*) AS count  FROM world INNER JOIN country ON world.iso_country = country.id INNER JOIN region ON world.iso_region = region.id WHERE (world.id LIKE \"%Chicago%\" OR world.name LIKE \"%Chicago%\" OR world.municipality LIKE \"%Chicago%\" OR country.name LIKE \"%Chicago%\" OR region.name LIKE \"%Chicago%\") AND (world.type LIKE \"%seaplane_base%\" OR world.type LIKE \"%closed%\" OR world.type LIKE \"%seaplane_base%\" OR world.type LIKE \"%closed%\") AND (country.name LIKE \"%Denver%\") ;";
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("wwardlow: Test found multiple args no null Additional")
    public void testFoundAdditional() {
        ArrayList<String> typeList = new ArrayList<>();
        typeList.add("California");
        typeList.add("Denver");
        ArrayList<String> whereList = new ArrayList<>();
        whereList.add("Denver");
        whereList.add("California");
        SqlSelect sqlSelect = new SqlSelect("world", "name, id");
        String result = sqlSelect.found("Chicago", typeList, whereList);
        String expected = "SELECT COUNT(*) AS count  FROM world INNER JOIN country ON world.iso_country = country.id INNER JOIN region ON world.iso_region = region.id WHERE (world.id LIKE \"%Chicago%\" OR world.name LIKE \"%Chicago%\" OR world.municipality LIKE \"%Chicago%\" OR country.name LIKE \"%Chicago%\" OR region.name LIKE \"%Chicago%\") AND (world.type LIKE \"%California%\" OR world.type LIKE \"%Denver%\") AND (country.name LIKE \"%Denver%\" OR country.name LIKE \"%California%\") ;";
        assertEquals(expected, result);
    }
}