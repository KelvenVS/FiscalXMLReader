// ProdutoDetalhes.java
package fp.flamapar.xmlread.model;

public class ProdutoDetalhes {
    private String nome;
    private String codigo;
    private String codigoEAN;
    private String ncm;
    private Double precoUnitario;
    private Double totalComImpostos;

    // Construtor e Getters e Setters

    public ProdutoDetalhes(String nome, String codigo, String codigoEAN, String ncm, Double precoUnitario, Double totalComImpostos) {
        this.nome = nome;
        this.codigo = codigo;
        this.codigoEAN = codigoEAN;
        this.ncm = ncm;
        this.precoUnitario = precoUnitario;
        this.totalComImpostos = totalComImpostos;
    }

    public String getNome() { return nome; }
    public String getCodigo() { return codigo; }
    public String getCodigoEAN() { return codigoEAN; }
    public String getNcm() { return ncm; }
    public Double getPrecoUnitario() { return precoUnitario; }
    public Double getTotalComImpostos() { return totalComImpostos; }

    @Override
    public String toString() {
        return nome + " (" + codigo + ")";
    }
}
