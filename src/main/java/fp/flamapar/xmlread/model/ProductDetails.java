// ProdutoDetalhes.java
package fp.flamapar.xmlread.model;

import lombok.Data;

@Data
public class ProductDetails {
    
    private String nome , codigo , codigoEAN , ncm , csta , cstb , cfop , uCom;

    private Double precoUnitario ,totalComImpostos , pIPI , vIPI , mva , stsist , st , icmsst , vprod ,baseicmsst , vFrete, qCom;

    
    // Construtor com todos os atributos
    public ProductDetails(String nome, String codigo, String codigoEAN, String ncm, String csta, String cstb, String cfop,String uCom, Double precoUnitario, Double totalComImpostos, Double pIPI, Double vIPI, Double mva, Double stsist, Double st, Double icmsst, Double vprod, Double baseicmsst , Double vFrete, Double qCom) {
        this.nome = nome;
        this.codigo = codigo;
        this.codigoEAN = codigoEAN;
        this.ncm = ncm;
        this.csta = csta;
        this.cstb = cstb;
        this.cfop = cfop;
        this.uCom = uCom;
        this.precoUnitario = precoUnitario;
        this.totalComImpostos = totalComImpostos;
        this.pIPI = pIPI;
        this.vIPI = vIPI;
        this.mva = mva;
        this.stsist = stsist;
        this.st = st;
        this.icmsst = icmsst;
        this.vprod = vprod;
        this.baseicmsst = baseicmsst;
        this.vFrete = vFrete;
        this.qCom = qCom;
 }
    
    @Override
    public String toString() {
        return nome + " (" + codigo + ")";
}
}