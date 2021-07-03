package org.czh.commons.entity.example;

import lombok.Getter;
import org.junit.Assert;
import org.junit.Test;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * @author : czh
 * description :
 * date : 2021-05-02
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public class GrandsonTest extends SonTest {

    private static final long serialVersionUID = -464633035640724697L;

    @Pattern(regexp = "yyyy-MM-dd")
    @Getter
    public static String gPubSta;
    @NotEmpty
    @Getter
    protected static String gProSta;
    @NotNull
    @Getter
    static String gDefSta;
    @NotBlank
    @Getter
    private static String gPriSta;

    static {
        init();
    }

    @Pattern(regexp = "yyyy-MM-dd")
    @Getter
    public String gPub;
    @NotEmpty
    @Getter
    protected String gPro;
    @NotNull
    @Getter
    String gDef;
    @NotBlank
    @Getter
    private final String gPri;

    public GrandsonTest() {
        super();
        this.gPri = "gPriVa";
        this.gPro = "gProVa";
        this.gDef = "gDefVa";
        this.gPub = "gPubVa";
        init();
    }

    private static void init() {
        GrandsonTest.gPriSta = "gPriStaVa";
        GrandsonTest.gProSta = "gProStaVa";
        GrandsonTest.gDefSta = "gDefStaVa";
        GrandsonTest.gPubSta = "gPubStaVa";
    }

    private static String getGPriStaMet() {
        return "gPriStaMet";
    }

    protected static String getGProStaMet() {
        return "gProStaMet";
    }

    static String getGDefStaMet() {
        return "gDefStaMet";
    }

    public static String getGPubStaMet() {
        return "gPubStaMet";
    }

    @Test
    public void test() {
        Assert.assertEquals("gPriVa", new GrandsonTest().getGPri());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GrandsonTest)) return false;
        if (!super.equals(o)) return false;
        GrandsonTest that = (GrandsonTest) o;
        return Objects.equals(gPri, that.gPri)
                && Objects.equals(gPro, that.gPro)
                && Objects.equals(gDef, that.gDef)
                && Objects.equals(gPub, that.gPub);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), gPri, gPro, gDef, gPub);
    }

    @Override
    public String toString() {
        return "GrandsonTest{" +
                "gPri='" + gPri + '\'' +
                ", gPro='" + gPro + '\'' +
                ", gDef='" + gDef + '\'' +
                ", gPub='" + gPub + '\'' +
                ", pPro='" + pPro + '\'' +
                ", pDef='" + pDef + '\'' +
                ", pPub='" + pPub + '\'' +
                ", sPro='" + sPro + '\'' +
                ", sDef='" + sDef + '\'' +
                ", sPub='" + sPub + '\'' +
                '}';
    }

    private String getGPriMet() {
        return "gPriMet";
    }

    protected String getGProMet() {
        return "gProMet";
    }

    String getGDefMet() {
        return "gDefMet";
    }

    public String getGPubMet() {
        return "gPubMet";
    }
}
