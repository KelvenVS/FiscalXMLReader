package br.kelvenserejo.xmlread.model.produto;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.Getter;

@Data
public class Prod {
    
    //Código do Produto
    @XmlElement(name = "cProd", namespace = "http://www.portalfiscal.inf.br/nfe")
    private String cProd;
    
    //Nome do produto
    @XmlElement(name = "xProd", namespace = "http://www.portalfiscal.inf.br/nfe")
    private String xProd;
    
    //Valor bruto x QTD
    @XmlElement(name = "vProd", namespace = "http://www.portalfiscal.inf.br/nfe")
    private Double vProd;
    
    //Valor bruto da Unidade
    @XmlElement(name = "vUnCom", namespace = "http://www.portalfiscal.inf.br/nfe")
    private Double vUnCom;
    
    //Frete do Produto
    @XmlElement(name = "vFrete", namespace = "http://www.portalfiscal.inf.br/nfe")
    private Double vFrete;
    
    //Código de Barras
    @XmlElement(name = "cEAN", namespace = "http://www.portalfiscal.inf.br/nfe")
    private String cEAN;
        
    //Habilitar SOMENTE Getters para evitar duplicidade
    //Código NCM do produto
    @Getter(onMethod_ = {@XmlElement(name = "NCM", namespace = "http://www.portalfiscal.inf.br/nfe")})
    private String NCM;
    
    //Tipo de Unidade
    @XmlElement(name = "uCom", namespace = "http://www.portalfiscal.inf.br/nfe")
    private String uCom;
    
    //Código CFOP
    @Getter(onMethod_ = {@XmlElement(name = "CFOP", namespace = "http://www.portalfiscal.inf.br/nfe")})
    private String cfop;
    
    //Quantidade
    @XmlElement(name = "qCom", namespace = "http://www.portalfiscal.inf.br/nfe")
    private Double qCom;

}