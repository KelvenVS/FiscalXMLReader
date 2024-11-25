package fp.flamapar.xmlread.model.icms.impl;


import fp.flamapar.xmlread.model.icms.base.ICMSBase;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.Getter;

@Data
public class ICMS60 implements ICMSBase {
    
    @Getter(onMethod_ = {@XmlElement(name = "orig", namespace = "http://www.portalfiscal.inf.br/nfe")})
    private String orig;
    
    @Getter(onMethod_ = {@XmlElement(name = "CST", namespace = "http://www.portalfiscal.inf.br/nfe")})
    private String cst;
    
    @Getter(onMethod_ = {@XmlElement(name = "pICMS", namespace = "http://www.portalfiscal.inf.br/nfe")})
    private Double pICMS;
    
    @Getter(onMethod_ = {@XmlElement(name = "pICMSST", namespace = "http://www.portalfiscal.inf.br/nfe")})
    private Double pICMSST;
        
    @Getter(onMethod_ = {@XmlElement(name = "pMVAST", namespace = "http://www.portalfiscal.inf.br/nfe")})
    private Double pMVAST;
            
    @Getter(onMethod_ = {@XmlElement(name = "pRedBC", namespace = "http://www.portalfiscal.inf.br/nfe")})
    private Double pRedBC;
    
    @Getter(onMethod_ = {@XmlElement(name = "pRedBCST", namespace = "http://www.portalfiscal.inf.br/nfe")})
    private Double pRedBCST;
}
