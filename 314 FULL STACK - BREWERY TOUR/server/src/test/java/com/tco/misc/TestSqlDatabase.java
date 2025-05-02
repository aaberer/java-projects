package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static java.lang.Math.toRadians;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

public class TestSqlDatabase {
    String match;
    Integer limit;
    SqlDatabase database;
    List<String> type;
    List<String> where;

    @BeforeEach
    public void setUp() {
        match = "d";
        limit = 5;
        database = new SqlDatabase();
        type = List.of("airport");
        where = List.of("Canada");

    }

    @Test
    @DisplayName("colin00")
    public void testFound() {
        try{
            database.found(match,limit);
            database.found(" ANDor AND or )()\"",limit);
            database.found(match,type,where);
        }
        catch (Exception e) {
        }
    }

    @Test
    @DisplayName("colin00")
    public void testPlaces() {
        try{
            database.places(match,limit);
            database.places(" ANDor AND or )()\"",limit);
            database.places(match,limit,type,where);
        }
        catch (Exception e) {
        }
    }

    @Test
    @DisplayName("aknott")
    public void testBasicInitialization() {
        assertNotNull(database);
    }
}

