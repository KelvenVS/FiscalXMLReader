package fp.flamapar.xmlread.model.nota;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlAttribute;
import java.util.List;

public class InfNFe {
    private String versao;
    private String id;
    private List<Det> det;

    @XmlAttribute(name = "versao")
    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    @XmlAttribute(name = "Id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "det", namespace = "http://www.portalfiscal.inf.br/nfe")
    public List<Det> getDet() {
        return det;
    }

    public void setDet(List<Det> det) {
        this.det = det;
    }
}
