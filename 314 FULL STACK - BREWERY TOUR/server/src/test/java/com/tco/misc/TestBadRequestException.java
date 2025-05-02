package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import com.tco.misc.BadRequestException;


public class TestBadRequestException {
    @Test
    @DisplayName("colin00:Test BadRequestException")
    public void test() {
        BadRequestException exception = new BadRequestException();
    }
}
