package com.rubypaper.shopping.biz.domain;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {

    private String city;

    private String roadName;

    private String zipCode;
}
