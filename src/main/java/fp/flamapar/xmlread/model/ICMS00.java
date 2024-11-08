package fp.flamapar.xmlread.model;

import jakarta.xml.bind.annotation.XmlElement;

public class ICMS00 {
    private String orig;
    private String cst;

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
}
