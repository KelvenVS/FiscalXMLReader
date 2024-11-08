package fp.flamapar.xmlread.model;

import jakarta.xml.bind.annotation.XmlElement;

public class Imposto {
    private ICMS icms;

    @XmlElement(name = "ICMS", namespace = "http://www.portalfiscal.inf.br/nfe")
    public ICMS getIcms() {
        return icms;
    }

    public void setIcms(ICMS icms) {
        this.icms = icms;
    }
}
