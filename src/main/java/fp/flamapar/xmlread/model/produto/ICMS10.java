package fp.flamapar.xmlread.model.produto;

import jakarta.xml.bind.annotation.XmlElement;

public class ICMS10 implements ICMSBase {
    private String orig;
    private String cst;
    private Double pICMS;
    private Double pMVAST;
        
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
    public Double getpICMS() {
        return pICMS;
    }

    public void setpICMS(Double pICMS) {
        this.pICMS = pICMS;
    }
    
    public Double getpMVAST() {
        return pMVAST;
    }
    
    @XmlElement(name = "pMVAST", namespace = "http://www.portalfiscal.inf.br/nfe")
    public void setpMVAST(Double pMVAST) {
        this.pMVAST = pMVAST;
    }
}