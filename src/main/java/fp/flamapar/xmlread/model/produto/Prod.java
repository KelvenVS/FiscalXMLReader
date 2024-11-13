package fp.flamapar.xmlread.model.produto;

import jakarta.xml.bind.annotation.XmlElement;

public class Prod {
    private String cProd;
    private String xProd;
    private Double vProd;
    private Double vUnCom;
    private String cEAN;
    private String NCM;
    private String uCom;

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
    
    @XmlElement(name = "vProd", namespace = "http://www.portalfiscal.inf.br/nfe")
    public Double getvProd() {
        return vProd;
    }

    public void setvProd(Double vProd) {
        this.vProd = vProd;
    }
    
    @XmlElement(name = "cEAN", namespace = "http://www.portalfiscal.inf.br/nfe")
    public String getcEAN() {
        return cEAN;
    }

    public void setcEAN(String cEAN) {
        this.cEAN = cEAN;
    }
    
    @XmlElement(name = "NCM", namespace = "http://www.portalfiscal.inf.br/nfe")
    public String getNCM() {
        return NCM;
    }
    
    public void setNCM(String NCM) {
        this.NCM = NCM;
    }
    
    @XmlElement(name = "uCom", namespace = "http://www.portalfiscal.inf.br/nfe")
    public String getuCom() {
        return uCom;
    }

    public void setuCom(String uCom) {
        this.uCom = uCom;
    }
    
    @XmlElement(name = "vUnCom", namespace = "http://www.portalfiscal.inf.br/nfe")
    public Double getvUnCom() {
        return vUnCom;
    }

    public void setvUnCom(Double vUnCom) {
        this.vUnCom = vUnCom;
    }
}
