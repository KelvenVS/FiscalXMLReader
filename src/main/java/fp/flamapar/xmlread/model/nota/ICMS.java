package fp.flamapar.xmlread.model.nota;

import fp.flamapar.xmlread.model.produto.ICMSBase;
import fp.flamapar.xmlread.model.produto.ICMS00;
import fp.flamapar.xmlread.model.produto.ICMS10;
import jakarta.xml.bind.annotation.XmlElement;

public class ICMS {
    private ICMS00 icms00;
    private ICMS10 icms10;

    @XmlElement(name = "ICMS00", namespace = "http://www.portalfiscal.inf.br/nfe")
    public ICMS00 getIcms00() {
        return icms00;
    }

    public void setIcms00(ICMS00 icms00) {
        this.icms00 = icms00;
    }
    
    @XmlElement(name = "ICMS10", namespace = "http://www.portalfiscal.inf.br/nfe")
    public ICMS10 getIcms10() {
        return icms10;
    }

    public void setIcms10(ICMS10 icms10) {
        this.icms10 = icms10;
    }
    
    public ICMSBase getICMSType() {
    return icms00 != null ? icms00 : icms10;
    }
}