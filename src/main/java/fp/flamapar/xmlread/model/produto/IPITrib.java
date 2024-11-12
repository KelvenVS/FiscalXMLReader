package fp.flamapar.xmlread.model.produto;

import jakarta.xml.bind.annotation.XmlElement;

public class IPITrib {
    private String pIPI;
    
    @XmlElement(name = "pIPI", namespace = "http://www.portalfiscal.inf.br/nfe")
    public String getpIPI() {
        return pIPI;
    }

    public void setpIPI(String pIPI) {
        this.pIPI = pIPI;
    }
    
    
    
}
