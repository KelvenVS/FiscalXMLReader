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
    //Var do Objeto
    Double vProduto = produto.getPrecoUnitario();
    Double vFrete =  produto.getVFrete()/produto.getQCom();
    Double pRedBC = produto.getPRedBC();
    Double pRedBCST = produto.getPRedBCST();
    Double pMVA = produto.getMva();
    Double pIPI = produto.getPIPI()/100;
    Double vIPI =  pIPI * (vProduto + vFrete);
    Double pICMS = produto.getPicms();
    Double pICMSST = produto.getPicmsst();
    
    pRedBCST = (pRedBCST == 0 ? pRedBCST = pRedBC : pRedBCST);
    
    String tooltipText;
    
    //Variáveis cálculo
    Double vTotalComImposto;
    Double vICMSproprio;
    Double baseICMSST;
    Double vICMSST;
    Double pSTsist;
    Double pSTprod;
    Double vICMSdestino;
    Double baseICMS;
    
    switch (caso) {
        case "SEM_ST":
                vTotalComImposto = (vProduto + vIPI + vFrete);
                produto.setTotalComImpostos(vTotalComImposto);
                produto.setVIPI(vIPI);
                produto.setBaseicmsst(0.0);
                produto.setVicmsst(0.0);
                produto.setPicmsst(0.0);
                produto.setPicms(0.0);
            
            // Tooltip para SEM_ST    
            tooltipText = String.format(
            "<html>Modo: Sem Substituição Tributária (ST)<br>" +
            "Total com Imposto: R$ %.2f<br>" +
            "ICMS Próprio: R$ 0.00<br>" +
            "Base ICMS ST: R$ 0.00<br>" +
            "ICMS ST: R$ 0.00</html>",
            vTotalComImposto
            );
            produto.setExplicacao(tooltipText);
            
            break;
        
        case "ESTADUAL_SEM_REDUCAO":
                // Lógica específica para ESTADUAL_SEM_REDUCAO
                //System.out.println("Processando cálculo estadual sem redução...");
                produto.setVIPI(vIPI);
                
                vICMSproprio = (vProduto + vFrete) * pICMS/100;
                
                //Verificar alterações de frete em todas basesde ICMS
                baseICMSST = (vProduto + vIPI + vFrete) * (1+(pMVA/100));
                produto.setBaseicmsst(baseICMSST);
                
                vICMSST = baseICMSST * pICMS/100 - vICMSproprio;
                produto.setVicmsst(vICMSST);
                
                vTotalComImposto = (vProduto + vIPI + vICMSST + vFrete);
                produto.setTotalComImpostos(vTotalComImposto);
                
                pSTprod = (vICMSST/(vProduto + vIPI))*100;
                produto.setSt(pSTprod);
                
                pSTsist = (vICMSST/vProduto)*100;
                produto.setStsist(pSTsist);
                
            // Tooltip para ESTADUAL_SEM_REDUCAO
            tooltipText = String.format(
                "<html>Modo: Estadual sem Redução<br>" +
                "ICMS Próprio: R$ %.2f<br>" +
                "Base ICMS ST: R$ %.2f<br>" +
                "ICMS ST: R$ %.2f<br>" +
                "Total com Imposto: R$ %.2f<br>" +
                "ST Produto: %.2f%%<br>" +
                "ST Sistema: %.2f%%</html>",
                vICMSproprio, baseICMSST, vICMSST, vTotalComImposto, pSTprod, pSTsist
            );
            produto.setExplicacao(tooltipText);
            break;

        case "ESTADUAL_COM_REDUCAO":
                // Lógica específica para ESTADUAL_COM_REDUCAO
                //System.out.println("Processando cálculo estadual com redução...");
                produto.setVIPI(vIPI);
                
                vICMSproprio = ((vProduto + vFrete) * pICMS/100 ) * ( 1 - pRedBC/100);
                
                baseICMSST = ((vProduto + vIPI + vFrete) * (1+(pMVA/100) ) * ( 1 - pRedBCST/100));
                produto.setBaseicmsst(baseICMSST);
                
                vICMSST = baseICMSST * pICMS/100 - vICMSproprio;
                produto.setVicmsst(vICMSST);
                
                vTotalComImposto = (vProduto + vIPI + vICMSST + vFrete);
                produto.setTotalComImpostos(vTotalComImposto);
                
                pSTprod = (vICMSST/(vProduto + vIPI))*100;
                produto.setSt(pSTprod);
                
                pSTsist = (vICMSST/vProduto)*100;
                produto.setStsist(pSTsist);
                
                // Tooltip para ESTADUAL_COM_REDUCAO
                tooltipText = String.format(
                    "<html>Modo: Estadual com Redução<br>" +
                    "Redução Base ICMS: %.2f%%<br>" +
                    "ICMS Próprio: R$ %.2f<br>" +
                    "Base ICMS ST: R$ %.2f<br>" +
                    "ICMS ST: R$ %.2f<br>" +
                    "Total com Imposto: R$ %.2f<br>" +
                    "ST Produto: %.2f%%<br>" +
                    "ST Sistema: %.2f%%</html>",
                    pRedBC, vICMSproprio, baseICMSST, vICMSST, vTotalComImposto, pSTprod, pSTsist
                );
                produto.setExplicacao(tooltipText);
                break; 
            
            
        case "INTERESTADUAL_SEM_REDUCAO":
                //System.out.println("Processando cálculo interestadual sem redução...");
                // Lógica específica para INTERESTADUAL_SEM_REDUCAO

                produto.setVIPI(vIPI);

                vICMSproprio = (vProduto + vFrete) * pICMS/100;

                baseICMSST = ( (vProduto + vIPI + vFrete) * pMVA/100 ) + (vProduto + vIPI);
                produto.setBaseicmsst(baseICMSST);

                vICMSdestino = baseICMSST * pICMSST/100;

                vICMSST = vICMSdestino - vICMSproprio;
                produto.setVicmsst(vICMSST);

                vTotalComImposto = (vProduto + vIPI + vICMSST + vFrete);
                produto.setTotalComImpostos(vTotalComImposto);

                pSTprod = (vICMSST/(vProduto + vIPI))*100;
                produto.setSt(pSTprod);

                pSTsist = (vICMSST/vProduto)*100;
                produto.setStsist(pSTsist);
                
                
                // Tooltip para INTERESTADUAL_SEM_REDUCAO
                tooltipText = String.format(
                    "<html>Modo: Interestadual sem Redução<br>" +
                    "ICMS Próprio: R$ %.2f<br>" +
                    "Base ICMS ST: R$ %.2f<br>" +
                    "ICMS ST: R$ %.2f<br>" +
                    "Total com Imposto: R$ %.2f<br>" +
                    "ST Produto: %.2f%%<br>" +
                    "ST Sistema: %.2f%%</html>",
                    vICMSproprio, baseICMSST, vICMSST, vTotalComImposto, pSTprod, pSTsist
                );
                produto.setExplicacao(tooltipText);
                break;
        
        case "INTERESTADUAL_COM_REDUCAO":
                //System.out.println("Processando cálculo interestadual com redução...");
                // Lógica específica para INTERESTADUAL_COM_REDUCAO

                produto.setVIPI(vIPI);

                baseICMS = (vProduto + vFrete)*(1-(pRedBC/100));
                vICMSproprio = baseICMS * pICMS/100;

                baseICMSST = ( (vProduto + vIPI + vFrete) * (1 + pMVA/100) ) * (1 - (pRedBCST/100));
                Double ICMSsobreST = (baseICMSST * pICMSST/100);
                produto.setBaseicmsst(baseICMSST);
                vICMSST = (ICMSsobreST - vICMSproprio);
                produto.setVicmsst(vICMSST);


                vTotalComImposto = (vProduto + vIPI + vICMSST + vFrete);
                produto.setTotalComImpostos(vTotalComImposto);

                pSTprod = (vICMSST/(vProduto + vIPI))*100;
                produto.setSt(pSTprod);

                pSTsist = (vICMSST/vProduto)*100;
                produto.setStsist(pSTsist);
                
                // Tooltip para INTERESTADUAL_COM_REDUCAO
                tooltipText = String.format(
                    "<html>Modo: Interestadual com Redução<br>" +
                    "Redução Base ICMS: %.2f%%<br>" +
                    "ICMS Próprio: R$ %.2f<br>" +
                    "Base ICMS ST: R$ %.2f<br>" +
                    "ICMS ST: R$ %.2f<br>" +
                    "Total com Imposto: R$ %.2f<br>" +
                    "ST Produto: %.2f%%<br>" +
                    "ST Sistema: %.2f%%</html>",
                    pRedBC, vICMSproprio, baseICMSST, vICMSST, vTotalComImposto, pSTprod, pSTsist
                );
                produto.setExplicacao(tooltipText);
                break;
        

        
        default:
            System.out.println("Caso não identificado.");
            // Lógica padrão, se necessário
            break;
        }
    }
}
