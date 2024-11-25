// ProdutoDetalhes.java
package fp.flamapar.xmlread.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProductDetails {
    private String nome ;
    private String codigo ;
    private String codigoEAN ;
    private String ncm ;
    private String csta ;
    private String cstb ;
    private String cfop ;
    private String uCom;

    private Double precoUnitario ;
    private Double totalComImpostos ;
    private Double pIPI ;
    private Double vIPI ;
    private Double mva ;
    private Double stsist ;
    private Double st ;
    private Double picms;
    private Double picmsst;
    private Double vicmsst ;
    private Double vprod ;
    private Double baseicmsst ;
    private Double vFrete ;
    private Double qCom;
    
    @Override
    public String toString() {
        return nome + " (" + codigo + ")";
}
}