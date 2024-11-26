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
        String caso = tipoCalc(produto.getMva(), produto.getPicms(),produto.getPicmsst(), produto.getPRedBC());
        System.out.println(produto +" "+ caso);
        
        processarCalculo(caso , produto);
        }
    }
    
public static String tipoCalc(Double mva , Double picms , Double picmsst , Double pRedBC){
    String caso;
    
    int nova_mva = mva.intValue();
    int nova_picms = picms.intValue();
    int nova_picmsst = picmsst.intValue();
    int nova_predbc = pRedBC.intValue();
    
    // Determina o caso
    if (nova_mva == 0) {
        caso = "SEM_ST"; // Sem MVA
    
    } else if (nova_picms != nova_picmsst) {
        caso = (nova_predbc == 0) ? "INTERESTADUAL_SEM_REDUCAO" : "INTERESTADUAL_COM_REDUCAO";
        
    } else {
        caso = (nova_predbc == 0) ? "ESTADUAL_SEM_REDUCAO" : "ESTADUAL_COM_REDUCAO";
        
    }
    return caso;
}    
    
public static void processarCalculo(String caso , ProductDetails produto) {
    switch (caso) {
        case "SEM_ST":
            System.out.println("Processando cálculo sem ST...");
            String ccodbarra = produto.getCodigoEAN();
            System.out.println(ccodbarra);
            produto.setCodigoEAN("12345678");
            ccodbarra = produto.getCodigoEAN();
            System.out.println(ccodbarra);
            break;
        
        case "INTERESTADUAL_SEM_REDUCAO":
            System.out.println("Processando cálculo interestadual sem redução...");
            // Lógica específica para INTERESTADUAL_SEM_REDUCAO
            break;
        
        case "INTERESTADUAL_COM_REDUCAO":
            System.out.println("Processando cálculo interestadual com redução...");
            // Lógica específica para INTERESTADUAL_COM_REDUCAO
            break;
        
        case "ESTADUAL_SEM_REDUCAO":
            System.out.println("Processando cálculo estadual sem redução...");
            // Lógica específica para ESTADUAL_SEM_REDUCAO
            break;
        
        case "ESTADUAL_COM_REDUCAO":
            System.out.println("Processando cálculo estadual com redução...");
            // Lógica específica para ESTADUAL_COM_REDUCAO
            break;
        
        default:
            System.out.println("Caso não identificado.");
            // Lógica padrão, se necessário
            break;
        }
    }
}
