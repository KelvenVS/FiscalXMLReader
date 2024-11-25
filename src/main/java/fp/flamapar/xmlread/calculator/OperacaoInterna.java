package fp.flamapar.xmlread.calculator;

import fp.flamapar.xmlread.model.ProductDetails;


public class OperacaoInterna implements ICMSCalculator {
    
    @Override
    public ProductDetails calcularICMS(ProductDetails productDetails) {
        // Calcular ICMS-ST sem redução
        double vIPI = (productDetails.getPIPI() / 100) * productDetails.getPrecoUnitario();
        double vProdNF = vIPI + productDetails.getPrecoUnitario();

        double baseICMSST = (vProdNF + productDetails.getVFrete()) * (1 + (productDetails.getMva() / 100));
        double vICMSST = (baseICMSST * productDetails.getIcms() / 100);

        productDetails.setBaseicmsst(baseICMSST);
        productDetails.setIcmsst(vICMSST);
        productDetails.setTotalComImpostos(vProdNF + vICMSST);

        return productDetails;
    }
    
}
