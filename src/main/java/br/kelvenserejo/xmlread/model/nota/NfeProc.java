package br.kelvenserejo.xmlread.model.nota;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "nfeProc", namespace = "http://www.portalfiscal.inf.br/nfe")
public class NfeProc {
    private NFe nfe;
    private ProtNFe protNFe;

    @XmlElement(name = "NFe", namespace = "http://www.portalfiscal.inf.br/nfe")
    public NFe getNfe() {
        return nfe;
    }

    public void setNfe(NFe nfe) {
        this.nfe = nfe;
    }

    @XmlElement(name = "protNFe", namespace = "http://www.portalfiscal.inf.br/nfe")
    public ProtNFe getProtNFe() {
        return protNFe;
    }

    public void setProtNFe(ProtNFe protNFe) {
        this.protNFe = protNFe;
    }
}
