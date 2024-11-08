package fp.flamapar.xmlread.model;

import jakarta.xml.bind.annotation.XmlElement;

public class Prod {
    private String cProd;
    private String xProd;

    @XmlElement(name = "cProd", namespace = "http://www.portalfiscal.inf.br/nfe")
    public String getCProd() {
        return cProd;
    }

    public void setCProd(String cProd) {
        this.cProd = cProd;
    }

    @XmlElement(name = "xProd", namespace = "http://www.portalfiscal.inf.br/nfe")
    public String getXProd() {
        return xProd;
    }

    public void setXProd(String xProd) {
        this.xProd = xProd;
    }
}
