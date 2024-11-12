package fp.flamapar.xmlread.model.produto;

import jakarta.xml.bind.annotation.XmlElement;

public class ICMS00 implements ICMSBase {
    private String orig;
    private String cst;
    private String pICMS;

    @XmlElement(name = "orig", namespace = "http://www.portalfiscal.inf.br/nfe")
    public String getOrig() {
        return orig;
    }

    public void setOrig(String orig) {
        this.orig = orig;
    }

    @XmlElement(name = "CST", namespace = "http://www.portalfiscal.inf.br/nfe")
    public String getCst() {
        return cst;
    }

    public void setCst(String cst) {
        this.cst = cst;
    }
    
    @XmlElement(name = "pICMS", namespace = "http://www.portalfiscal.inf.br/nfe")
    public String getpICMS() {
        return pICMS;
    }

    public void setpICMS(String pICMS) {
        this.pICMS = pICMS;
    }
}
