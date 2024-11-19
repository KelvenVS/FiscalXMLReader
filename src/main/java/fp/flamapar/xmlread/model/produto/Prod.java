package fp.flamapar.xmlread.model.produto;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.Getter;

@Data
public class Prod {
    
    @XmlElement(name = "cProd", namespace = "http://www.portalfiscal.inf.br/nfe")
    private String cProd;
    
    @XmlElement(name = "xProd", namespace = "http://www.portalfiscal.inf.br/nfe")
    private String xProd;
    
    @XmlElement(name = "vProd", namespace = "http://www.portalfiscal.inf.br/nfe")
    private Double vProd;
    
    @XmlElement(name = "vUnCom", namespace = "http://www.portalfiscal.inf.br/nfe")
    private Double vUnCom;
    
    @XmlElement(name = "vFrete", namespace = "http://www.portalfiscal.inf.br/nfe")
    private Double vFrete;
    
    @XmlElement(name = "cEAN", namespace = "http://www.portalfiscal.inf.br/nfe")
    private String cEAN;
        
    //Habilitar SOMENTE Getters para evitar duplicidade
    @Getter(onMethod_ = {@XmlElement(name = "NCM", namespace = "http://www.portalfiscal.inf.br/nfe")})
    private String NCM;
    
    @XmlElement(name = "uCom", namespace = "http://www.portalfiscal.inf.br/nfe")
    private String uCom;
    
    @Getter(onMethod_ = {@XmlElement(name = "CFOP", namespace = "http://www.portalfiscal.inf.br/nfe")})
    private String cfop;

}