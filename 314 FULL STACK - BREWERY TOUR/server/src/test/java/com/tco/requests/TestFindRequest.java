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
import java.util.ArrayList;
import java.util.List;


public class TestFindRequest {
    @Test
    @DisplayName("aknott: Test Basic")
    public void basicFind() {
        FindRequest req  = new FindRequest("",null,null,10);
    }

    @Test
    @DisplayName("colin00: Test Basic")
    public void buildReq() {
        FindRequest req  = new FindRequest("d",null,null,10);
        try{
            req.buildResponse();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }

    @Test
    @DisplayName("colin00: Test with where good")
    public void testWhere() {
        List<String> countries = new ArrayList<>();
        countries.add("Canada");
        FindRequest req  = new FindRequest("d",null,countries,10);
        try{
            req.buildResponse();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }

    @Test
    @DisplayName("colin00: Test with where bad")
    public void testBadWhere() {
        List<String> countries = new ArrayList<>();
        countries.add("not a real country");
        FindRequest req  = new FindRequest("d",null,countries,10);
        try{
            req.buildResponse();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }

    @Test
    @DisplayName("colin00: Test with big limit")
    public void testBigLimit() {
        FindRequest req  = new FindRequest("d",null,null,10000);
        try{
            req.buildResponse();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }
    
}
  

