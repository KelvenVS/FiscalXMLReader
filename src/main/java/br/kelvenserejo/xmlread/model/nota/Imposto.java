package br.kelvenserejo.xmlread.model.nota;

import jakarta.xml.bind.annotation.XmlElement;

public class Imposto {
    private ICMS icms;
    private IPI ipi;

    @XmlElement(name = "ICMS", namespace = "http://www.portalfiscal.inf.br/nfe")
    public ICMS getIcms() {
        return icms;
    }

    public void setIcms(ICMS icms) {
        this.icms = icms;
    }
    
    @XmlElement(name = "IPI", namespace = "http://www.portalfiscal.inf.br/nfe")
    public IPI getIpi() {
        if (ipi == null) {
            return new IPI(); // Retorna um objeto IPI vazio
        }
        return ipi;
    }

    public void setIpi(IPI ipi) {
        this.ipi = ipi;
    }
    
    
}
