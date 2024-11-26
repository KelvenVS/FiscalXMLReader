package fp.flamapar.xmlread.calculator;

import fp.flamapar.xmlread.model.ProductDetails;
import java.util.List;

public class CalculatorICMSST {
    
    public static void Calculator(List<ProductDetails> produtos) {
        if (produtos == null || produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
            return;
        }

        System.out.println("Lista de Produtos:");
        for (ProductDetails produto : produtos) {
            Double IPI = 2.4;
            
            produto.setVIPI(IPI);

            System.out.println(produto.toString());
            System.out.println(produto.getPIPI());
            System.out.println(produto.getNome());
            System.out.println("-------------------------------------------------");
        }
    }
}
