package fp.flamapar.xmlread.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "protNFe", namespace = "http://www.portalfiscal.inf.br/nfe")
public class ProtNFe {
    private String versao;

    @XmlElement(name = "versao", namespace = "http://www.portalfiscal.inf.br/nfe")
    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }
}
