package com.example.sotw.donationtracker;

import org.junit.Test;

import static org.junit.Assert.*;
import com.example.sotw.donationtracker.model.Location;
/**
 * unit test for the class
 */
public class GreenwaldTest {
    @Test
    public void test_constructor(){
        Location newLocation = new Location("2","Starbucks",
                "2000 Peachtree Street",33,-88,
                "Drop Off","888-999-2000","http://sbucks.com");
        assertEquals(newLocation.getName(), "Starbucks");

    }
}
