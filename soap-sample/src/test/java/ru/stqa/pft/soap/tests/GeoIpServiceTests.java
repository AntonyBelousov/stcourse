package ru.stqa.pft.soap.tests;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("212.164.65.251");
        assertTrue(geoIp.contains("RU"));
    }
}
