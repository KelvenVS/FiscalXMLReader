package br.kelvenserejo.xmlread.model.nota;

import br.kelvenserejo.xmlread.model.produto.Prod;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlAttribute;

public class Det {
    private String nItem;
    private Prod prod;
    private Imposto imposto;

    @XmlAttribute(name = "nItem")
    public String getNItem() {
        return nItem;
    }

    public void setNItem(String nItem) {
        this.nItem = nItem;
    }

    @XmlElement(name = "prod", namespace = "http://www.portalfiscal.inf.br/nfe")
    public Prod getProd() {
        return prod;
    }

    public void setProd(Prod prod) {
        this.prod = prod;
    }
    
    @XmlElement(name = "imposto", namespace = "http://www.portalfiscal.inf.br/nfe")
    public Imposto getImposto() {
        return imposto;
    }

    public void setImposto(Imposto imposto) {
        this.imposto = imposto;
    }
    
    
}
