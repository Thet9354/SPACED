package com.example.spaced.CountryInventory;

import java.io.Serializable;

public class Country implements Serializable {

    private String name;

    public Country()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
