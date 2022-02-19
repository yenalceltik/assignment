package com.openpayd.foreignexchange.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponseModel
{
    private boolean success;
    private PotentialError error;

}