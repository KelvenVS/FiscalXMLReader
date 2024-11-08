package fp.flamapar.xmlread.model;

import jakarta.xml.bind.annotation.XmlElement;

public class ICMS {
    private ICMS00 icms00;

    @XmlElement(name = "ICMS00", namespace = "http://www.portalfiscal.inf.br/nfe")
    public ICMS00 getIcms00() {
        return icms00;
    }

    public void setIcms00(ICMS00 icms00) {
        this.icms00 = icms00;
    }
}
