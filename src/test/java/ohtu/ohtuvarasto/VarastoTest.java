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
    Varasto varasto2;
    Varasto tyhja;
    Varasto tyhja2;
    Varasto taysi;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto2 = new Varasto(10,4);
        tyhja = new Varasto(0);
        tyhja2 = new Varasto(0,-2);
        taysi = new Varasto(6,8);
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
    public void tyhjaVarastoOnTyhja() {
        assertEquals(0, tyhja.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void tyhjaVarasto2OnTyhja() {
        assertEquals(0, tyhja2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastolla2OikeaTilavuus() {
        assertEquals(10, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenSaldoOnNolla() {
        assertEquals(0, tyhja.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void ylimenevaVarastoOnTaysi() {
        assertEquals(6, taysi.getSaldo(), vertailuTarkkuus);
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
    public void ylimaaraMeneeHukkaanLisattaessa() {
        varasto.lisaaVarastoon(14);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisattaessaNegatiivinenEiMuutosta() {
        varasto.lisaaVarastoon(-4);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
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
    public void otetaanTyhjäksi() {
        varasto.lisaaVarastoon(6);
        
        double saatuMaara = varasto.otaVarastosta(8);
        
        assertEquals(6, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void otetaanNegatiivinen() {
        varasto.lisaaVarastoon(6);
        
        double saatuMaara = varasto.otaVarastosta(-2);
        
        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }
    
    
    
    
}