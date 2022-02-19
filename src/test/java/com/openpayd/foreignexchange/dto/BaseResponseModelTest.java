package com.openpayd.foreignexchange.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseResponseModelTest
{

    @Test
    @DisplayName("creating base response model")
    void creatingBaseResponseModel() {

        BaseResponseModel responseModel = new BaseResponseModel(true, new PotentialError(101, "type", "info"));

        assertTrue(responseModel.isSuccess());
        assertEquals("info", responseModel.getError().getInfo());
        assertEquals("type", responseModel.getError().getType());
        assertEquals(101, responseModel.getError().getCode());
    }
}