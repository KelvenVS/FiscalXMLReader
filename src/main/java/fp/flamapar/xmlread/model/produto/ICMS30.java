package fp.flamapar.xmlread.model.produto;


import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.Getter;

@Data
public class ICMS30 implements ICMSBase {
    
    @Getter(onMethod_ = {@XmlElement(name = "orig", namespace = "http://www.portalfiscal.inf.br/nfe")})
    private String orig;
    
    @Getter(onMethod_ = {@XmlElement(name = "CST", namespace = "http://www.portalfiscal.inf.br/nfe")})
    private String cst;
    
    @Getter(onMethod_ = {@XmlElement(name = "pICMS", namespace = "http://www.portalfiscal.inf.br/nfe")})
    private Double pICMS;
    
    @Getter(onMethod_ = {@XmlElement(name = "pMVAST", namespace = "http://www.portalfiscal.inf.br/nfe")})
    private Double pMVAST;
        
}
