// ProdutoDetalhes.java
package fp.flamapar.xmlread.model;

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
    
    public String getNome() { return nome; }
    public String getCodigo() { return codigo; }
    public String getCodigoEAN() { return codigoEAN; }
    public String getNcm() { return ncm; }
    public Double getPrecoUnitario() { return precoUnitario; }
    public Double getTotalComImpostos() { return totalComImpostos; }
    public Double getpIPI() { return pIPI; }
    public Double getvIPI() { return vIPI; }
    public String getCsta() { return csta; }
    public String getCstb() { return cstb; }
    public String getCfop() { return cfop; }
    public String getUCom() { return uCom; }
    public Double getMva() { return mva; }
    public Double getStsist() { return stsist; }
    public Double getSt() { return st; }
    public Double getIcmsst() { return icmsst; }
    public Double getVprod() { return vprod; }
    public Double getBaseicmsst() { return baseicmsst; }
    public Double getvFrete() { return vFrete; }
    public Double getQCom() { return qCom; }
    

    @Override
    public String toString() {
        return nome + " (" + codigo + ")";
}
}