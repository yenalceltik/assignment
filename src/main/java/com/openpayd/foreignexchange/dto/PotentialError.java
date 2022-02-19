package com.openpayd.foreignexchange.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PotentialError
{
    private int code;
    private String type;
    private String info;
}
