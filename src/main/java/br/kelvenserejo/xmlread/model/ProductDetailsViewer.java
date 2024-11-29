package br.kelvenserejo.xmlread.model;

import br.kelvenserejo.xmlread.model.ProductDetails;
import java.util.List;

public class ProductDetailsViewer {

    public static void showProducts(List<ProductDetails> produtos) {
        if (produtos == null || produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
            return;
        }

        System.out.println("Lista de Produtos:");
        for (ProductDetails produto : produtos) {
            System.out.println(produto.toString());
            System.out.println("-------------------------------------------------");
        }
    }
}

