package fp.flamapar.xmlread.model.nota;

import fp.flamapar.xmlread.model.nota.InfNFe;
import jakarta.xml.bind.annotation.XmlElement;

public class NFe {
    private InfNFe infNFe;

    @XmlElement(name = "infNFe", namespace = "http://www.portalfiscal.inf.br/nfe")
    public InfNFe getInfNFe() {
        return infNFe;
    }

    public void setInfNFe(InfNFe infNFe) {
        this.infNFe = infNFe;
    }
}
