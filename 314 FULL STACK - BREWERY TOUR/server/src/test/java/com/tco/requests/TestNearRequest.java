package com.tco.requests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.tco.misc.Distances;
import com.tco.misc.Place;
import com.tco.misc.Places;
import java.lang.Math;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class TestNearRequest {
    NearRequest n;
    Place p;

    @BeforeEach
    public void BeforeEach(){
        p = new Place();
        p.put("latitude", "10");
        p.put("longitude", "10");
        n = new NearRequest(p,10,1000.0,"vincenty",100);
    }

    @Test
    @DisplayName("colin00")
    public void doNear(){
        n.buildResponse();
    }


}
