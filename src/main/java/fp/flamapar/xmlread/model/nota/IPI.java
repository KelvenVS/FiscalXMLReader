package fp.flamapar.xmlread.model.nota;

import fp.flamapar.xmlread.model.produto.IPITrib;
import jakarta.xml.bind.annotation.XmlElement;

public class IPI {
    private IPITrib ipitrib;
    
    @XmlElement(name = "IPITrib", namespace = "http://www.portalfiscal.inf.br/nfe")
    public IPITrib getIpitrib() {
        return ipitrib;
    }

    public void setIpitrib(IPITrib ipitrib) {
        this.ipitrib = ipitrib;
    }
    
    
    
}
