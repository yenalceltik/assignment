package com.openpayd.foreignexchange.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "conversion_detail")
@Entity
@Data
public class ConversionDetail
{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", unique = true)
    @JsonProperty(value = "transactionId")
    private String id;

    private String sourceCurrency;

    private String targetCurrency;

    private BigDecimal amountConverted;

    private String createTime;


}
