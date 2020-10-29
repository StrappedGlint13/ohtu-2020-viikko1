package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(5);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonArvotNegatiivinen() {
        varasto.lisaaVarastoon(11);
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
        
        Varasto vara = new Varasto(-2, -2);
        assertEquals(0, vara.paljonkoMahtuu(), vertailuTarkkuus);
        assertEquals(0, vara.getSaldo(), vertailuTarkkuus); 
        
        Varasto vara2 = new Varasto(-1);
        assertEquals(0, vara2.paljonkoMahtuu(), vertailuTarkkuus);
       
  
    }
    @Test
    public void lisaaNegis() {
        varasto.lisaaVarastoon(-1);
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoAlustustusta() {
        Varasto vara = new Varasto(10, 0);
        assertEquals(10, vara.paljonkoMahtuu(), vertailuTarkkuus);
        
        Varasto vara2 = new Varasto(1, 2);
        assertEquals(1, vara2.getSaldo(), vertailuTarkkuus);
       
    }
    
    
    @Test
    public void otaVarastostaToimii() {
        varasto.otaVarastosta(-1);
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
        
        varasto.lisaaVarastoon(3);
        varasto.otaVarastosta(5);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void tulostusToimii() {
        String merkkijonoesitys = "saldo = 0.0, vielä tilaa 10.0";
        assertEquals(merkkijonoesitys, varasto.toString());
    }


}