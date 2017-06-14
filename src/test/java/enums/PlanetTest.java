package test.java.enums;
import main.java.enums.Planet;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PlanetTest {
    @Test
    public void EnumsCanBeIterated() {
        Planet[] planetList = Planet.values();
        Assert.assertEquals(8, planetList.length);
    }

    @Test
    public void enumCanHaveMethods() {
        Planet p = Planet.EARTH;
        Double weight = 175 /p.surfaceGravity();
        Double o = p.surfaceWeight(weight);
        Assert.assertEquals(175.0, o, 1e-6);
    }
}
