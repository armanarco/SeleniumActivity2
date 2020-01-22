package com.CommonFunctions;

public class Assertion {
    public boolean equals(String expectedURL, String actualURL){

        boolean result = false;

        if(expectedURL.equalsIgnoreCase(actualURL)){
            result = true;
        }
        return result;
    }
}
