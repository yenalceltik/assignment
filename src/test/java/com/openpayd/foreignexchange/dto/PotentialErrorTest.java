package com.openpayd.foreignexchange.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PotentialErrorTest
{
    @Test
    @DisplayName("creating potential error model")
    void creatingPotentialError()
    {
        PotentialError potentialError = new PotentialError(101, "type", "info");

        assertEquals(101, potentialError.getCode());
        assertEquals("type", potentialError.getType());
        assertEquals("info", potentialError.getInfo());
    }
}