package fp.flamapar.xmlread.model.produto;

import jakarta.xml.bind.annotation.XmlElement;

public class IPITrib {
    private Double pIPI;
    
    @XmlElement(name = "pIPI", namespace = "http://www.portalfiscal.inf.br/nfe")
    public Double getpIPI() {
        return pIPI;
    }

    public void setpIPI(Double pIPI) {
        this.pIPI = pIPI;
    }
    
    
    
}
