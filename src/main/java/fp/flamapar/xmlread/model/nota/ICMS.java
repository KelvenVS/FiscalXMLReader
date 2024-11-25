package fp.flamapar.xmlread.model.nota;

import fp.flamapar.xmlread.model.icms.base.ICMSBase;
import fp.flamapar.xmlread.model.icms.impl.ICMS00;
import fp.flamapar.xmlread.model.icms.impl.ICMS10;
import fp.flamapar.xmlread.model.icms.impl.ICMS20;
import fp.flamapar.xmlread.model.icms.impl.ICMS30;
import fp.flamapar.xmlread.model.icms.impl.ICMS40;
import fp.flamapar.xmlread.model.icms.impl.ICMS51;
import fp.flamapar.xmlread.model.icms.impl.ICMS60;
import fp.flamapar.xmlread.model.icms.impl.ICMS70;
import fp.flamapar.xmlread.model.icms.impl.ICMS90;

import jakarta.xml.bind.annotation.XmlElement;

public class ICMS {
    private ICMS00 icms00;
    private ICMS10 icms10;
    private ICMS20 icms20;
    private ICMS30 icms30;
    private ICMS40 icms40;
    private ICMS51 icms51;
    private ICMS60 icms60;
    private ICMS70 icms70;
    private ICMS90 icms90;


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

    @XmlElement(name = "ICMS20", namespace = "http://www.portalfiscal.inf.br/nfe")
    public ICMS20 getIcms20() {
        return icms20;
    }

    public void setIcms20(ICMS20 icms20) {
        this.icms20 = icms20;
    }

    @XmlElement(name = "ICMS30", namespace = "http://www.portalfiscal.inf.br/nfe")
    public ICMS30 getIcms30() {
        return icms30;
    }

    public void setIcms30(ICMS30 icms30) {
        this.icms30 = icms30;
    }

    @XmlElement(name = "ICMS40", namespace = "http://www.portalfiscal.inf.br/nfe")
    public ICMS40 getIcms40() {
        return icms40;
    }

    public void setIcms40(ICMS40 icms40) {
        this.icms40 = icms40;
    }

    @XmlElement(name = "ICMS51", namespace = "http://www.portalfiscal.inf.br/nfe")
    public ICMS51 getIcms51() {
        return icms51;
    }

    public void setIcms51(ICMS51 icms51) {
        this.icms51 = icms51;
    }

    @XmlElement(name = "ICMS60", namespace = "http://www.portalfiscal.inf.br/nfe")
    public ICMS60 getIcms60() {
        return icms60;
    }

    public void setIcms60(ICMS60 icms60) {
        this.icms60 = icms60;
    }

    @XmlElement(name = "ICMS70", namespace = "http://www.portalfiscal.inf.br/nfe")
    public ICMS70 getIcms70() {
        return icms70;
    }

    public void setIcms70(ICMS70 icms70) {
        this.icms70 = icms70;
    }

    @XmlElement(name = "ICMS90", namespace = "http://www.portalfiscal.inf.br/nfe")
    public ICMS90 getIcms90() {
        return icms90;
    }

    public void setIcms90(ICMS90 icms90) {
        this.icms90 = icms90;
    }

    public ICMSBase getICMSType() {
        if (icms00 != null) return icms00;
        if (icms10 != null) return icms10;
        if (icms20 != null) return icms20;
        if (icms30 != null) return icms30;
        if (icms40 != null) return icms40;
        if (icms51 != null) return icms51;
        if (icms60 != null) return icms60;
        if (icms70 != null) return icms70;
        if (icms90 != null) return icms90;
        return null;
    }
}