package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiceTest {

    @Test
    public void testMyIp(){
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("81.9.126.140 ");
        System.out.println(ipLocation);
    }
}
