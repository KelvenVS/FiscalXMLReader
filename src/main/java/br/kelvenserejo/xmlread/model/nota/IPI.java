package br.kelvenserejo.xmlread.model.nota;

import br.kelvenserejo.xmlread.model.produto.IPITrib;
import jakarta.xml.bind.annotation.XmlElement;

public class IPI {
    private IPITrib ipitrib;
    
    @XmlElement(name = "IPITrib", namespace = "http://www.portalfiscal.inf.br/nfe")
    public IPITrib getIpitrib() {
        // Retorna uma instância padrão de IPITrib se ipitrib for nulo
        if (ipitrib == null) {
            IPITrib defaultIpiTrib = new IPITrib();
            defaultIpiTrib.setpIPI(0.0);
            return defaultIpiTrib;
        }
        return ipitrib;
    }

    public void setIpitrib(IPITrib ipitrib) {
        this.ipitrib = ipitrib;
    }
    
    
    
}
