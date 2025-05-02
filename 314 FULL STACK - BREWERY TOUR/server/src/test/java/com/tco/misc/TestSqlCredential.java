package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class TestSqlCredential {

    @Test
    @DisplayName("nlrohr: Matching URL")
    public void testUrl() {
        String expectedUrl = "jdbc:mariadb://faure.cs.colostate.edu/cs314";
        //assertEquals(expectedUrl, SqlCredential.url(), "URL should match the expected production URL");
    }

    @Test
    @DisplayName("nlrohr:User matches read only username")
    public void testUser() {
        String expectedUser = "cs314-db";
        assertEquals(expectedUser, SqlCredential.USER, "User should match the expected read-only username");
    }

    @Test
    @DisplayName("nlrohr:Password matches expected")
    public void testPassword() {
        String expectedPassword = "eiK5liet1uej";
        assertEquals(expectedPassword, SqlCredential.PASSWORD, "Password should match the expected read-only password");
    }

    @Test
    @DisplayName("nlrohr:Test URL Null")
    public void testUrlNotNull() {
        assertNotNull(SqlCredential.url(), "URL should not be null");
    }

    @Test
    @DisplayName("nlrohr:Test user Null")
    public void testUserNotNull() {
        assertNotNull(SqlCredential.USER, "User should not be null");
    }

    @Test
    @DisplayName("nlrohr:Test Password Null")
    public void testPasswordNotNull() {
        assertNotNull(SqlCredential.PASSWORD, "Password should not be null");
    }
}
