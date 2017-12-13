package test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import utils.CitiesMap;


class CitiesBaseTest {
    @Test
    void createCitiesSet() throws Exception  {

        CitiesBase.createCitiesSet("test/resources/cities_1.txt");
        Assert.assertTrue(CitiesMap.isCreatedCityMapCorrect(CitiesBase.getCitiesMap()));
    }

}