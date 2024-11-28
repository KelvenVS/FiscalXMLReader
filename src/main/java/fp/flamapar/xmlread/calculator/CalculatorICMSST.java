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
                    "<html>" +
                    "<b>Modo:</b> Sem Substituição Tributária (ST)<br>" +
                    "<hr>" + // Linha divisória
                    "<b>Detalhamento do Cálculo:</b><br>" +
                    "1. <b>Base do Produto:</b> Preço Unitário (vProduto) + Frete Unitário (vFrete)<br>" +
                    "&nbsp;&nbsp;&nbsp;Substituindo: %.2f + %.2f = %.2f<br>" +
                    "<hr>" +        
                    "2. <b>Valor do IPI:</b> Percentual IPI (pIPI) aplicado à Base do Produto<br>" +
                    "&nbsp;&nbsp;&nbsp;Substituindo: %.2f%% x (%.2f + %.2f) = %.2f<br>" +
                    "<hr>" +
                    "3. <b>Total com Imposto:</b> Base do Produto + Valor do IPI<br>" +
                    "&nbsp;&nbsp;&nbsp;Substituindo: %.2f + %.2f = %.2f<br>" +
                    "<hr>" + // Linha divisória
                    "<b>Observação:</b> ICMS Próprio e ICMS ST não aplicáveis neste caso.<br>" +
                    "</html>",
                    vProduto, vFrete, (vProduto + vFrete),
                    pIPI * 100, vProduto, vFrete, vIPI,
                    (vProduto + vFrete), vIPI, vTotalComImposto
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
                "<html>" +
                "<b>Modo:</b> Estadual sem Redução<br>" +
                "<hr>" +
                "<b>Detalhamento do Cálculo:</b><br>" +
                "1. <b>ICMS Próprio:</b> (vProduto + vFrete) * pICMS / 100<br>" +
                "&nbsp;&nbsp;&nbsp;Substituindo: (%.2f + %.2f) * %.2f%% = R$ %.2f<br>" +
                "<hr>" +        
                "2. <b>Base ICMS ST:</b> (vProduto + vIPI + vFrete) * (1 + pMVA / 100)<br>" +
                "&nbsp;&nbsp;&nbsp;Substituindo: (%.2f + %.2f + %.2f) * (1 + %.2f%%) = R$ %.2f<br>" +
                "<hr>" +        
                "3. <b>ICMS ST:</b> Base ICMS ST * pICMS / 100 - ICMS Próprio<br>" +
                "&nbsp;&nbsp;&nbsp;Substituindo: %.2f * %.2f%% - %.2f = R$ %.2f<br>" +
                "<hr>" +        
                "4. <b>Total com Imposto:</b> vProduto + vIPI + ICMS ST + vFrete<br>" +
                "&nbsp;&nbsp;&nbsp;Substituindo: %.2f + %.2f + %.2f + %.2f = R$ %.2f<br>" +
                "<hr>" +        
                "5. <b>ST Produto:</b> ICMS ST / (vProduto + vIPI) * 100<br>" +
                "&nbsp;&nbsp;&nbsp;Substituindo: %.2f / (%.2f + %.2f) * 100 = %.2f%%<br>" +
                "<hr>" +        
                "6. <b>ST Sistema:</b> ICMS ST / vProduto * 100<br>" +
                "&nbsp;&nbsp;&nbsp;Substituindo: %.2f / %.2f * 100 = %.2f%%<br>" +
                "<hr>" +
                "</html>",
                vProduto, vFrete, pICMS, vICMSproprio,
                vProduto, vIPI, vFrete, pMVA, baseICMSST,
                baseICMSST, pICMS, vICMSproprio, vICMSST,
                vProduto, vIPI, vICMSST, vFrete, vTotalComImposto,
                vICMSST, vProduto, vIPI, pSTprod,
                vICMSST, vProduto, pSTsist
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
                    "<html>" +
                    "<b>Modo:</b> Estadual com Redução<br>" +
                    "<hr>" +
                    "<b>Detalhamento do Cálculo:</b><br>" +
                    "1. <b>ICMS Próprio:</b> ((vProduto + vFrete) * pICMS / 100) * (1 - pRedBC / 100)<br>" +
                    "&nbsp;&nbsp;&nbsp;Substituindo: ((%.2f + %.2f) * %.2f%%) * (1 - %.2f%%) = R$ %.2f<br>" +
                    "<hr>" +        
                    "2. <b>Base ICMS ST:</b> ((vProduto + vIPI + vFrete) * (1 + pMVA / 100)) * (1 - pRedBCST / 100)<br>" +
                    "&nbsp;&nbsp;&nbsp;Substituindo: ((%.2f + %.2f + %.2f) * (1 + %.2f%%)) * (1 - %.2f%%) = R$ %.2f<br>" +
                    "<hr>" +        
                    "3. <b>ICMS ST:</b> Base ICMS ST * pICMS / 100 - ICMS Próprio<br>" +
                    "&nbsp;&nbsp;&nbsp;Substituindo: %.2f * %.2f%% - %.2f = R$ %.2f<br>" +
                    "<hr>" +        
                    "4. <b>Total com Imposto:</b> vProduto + vIPI + ICMS ST + vFrete<br>" +
                    "&nbsp;&nbsp;&nbsp;Substituindo: %.2f + %.2f + %.2f + %.2f = R$ %.2f<br>" +
                    "<hr>" +        
                    "5. <b>ST Produto:</b> ICMS ST / (vProduto + vIPI) * 100<br>" +
                    "&nbsp;&nbsp;&nbsp;Substituindo: %.2f / (%.2f + %.2f) * 100 = %.2f%%<br>" +
                    "<hr>" +        
                    "6. <b>ST Sistema:</b> ICMS ST / vProduto * 100<br>" +
                    "&nbsp;&nbsp;&nbsp;Substituindo: %.2f / %.2f * 100 = %.2f%%<br>" +
                    "<hr>" +
                    "</html>",
                    vProduto, vFrete, pICMS, pRedBC, vICMSproprio,
                    vProduto, vIPI, vFrete, pMVA, pRedBCST, baseICMSST,
                    baseICMSST, pICMS, vICMSproprio, vICMSST,
                    vProduto, vIPI, vICMSST, vFrete, vTotalComImposto,
                    vICMSST, vProduto, vIPI, pSTprod,
                    vICMSST, vProduto, pSTsist
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
                
                
                // Explicação "à mão" para INTERESTADUAL_SEM_REDUCAO
                tooltipText = String.format(
                    "<html>" +
                    "<b>Modo:</b> Interestadual sem Redução<br>" +
                    "<hr>" +
                    "<b>Detalhamento do Cálculo:</b><br>" +
                    "1. <b>ICMS Próprio:</b> (vProduto + vFrete) * pICMS / 100<br>" +
                    "&nbsp;&nbsp;&nbsp;Substituindo: (%.2f + %.2f) * %.2f%% = R$ %.2f<br>" +
                    "<hr>" +
                    "2. <b>Base ICMS ST:</b> ((vProduto + vIPI + vFrete) * pMVA / 100) + (vProduto + vIPI)<br>" +
                    "&nbsp;&nbsp;&nbsp;Substituindo: ((%.2f + %.2f + %.2f) * %.2f%%) + (%.2f + %.2f) = R$ %.2f<br>" +
                    "<hr>" +
                    "3. <b>ICMS ST:</b> Base ICMS ST * pICMSST / 100 - ICMS Próprio<br>" +
                    "&nbsp;&nbsp;&nbsp;Substituindo: %.2f * %.2f%% - %.2f = R$ %.2f<br>" +
                    "<hr>" +
                    "4. <b>Total com Imposto:</b> vProduto + vIPI + ICMS ST + vFrete<br>" +
                    "&nbsp;&nbsp;&nbsp;Substituindo: %.2f + %.2f + %.2f + %.2f = R$ %.2f<br>" +
                    "<hr>" +
                    "5. <b>ST Produto:</b> ICMS ST / (vProduto + vIPI) * 100<br>" +
                    "&nbsp;&nbsp;&nbsp;Substituindo: %.2f / (%.2f + %.2f) * 100 = %.2f%%<br>" +
                    "<hr>" +
                    "6. <b>ST Sistema:</b> ICMS ST / vProduto * 100<br>" +
                    "&nbsp;&nbsp;&nbsp;Substituindo: %.2f / %.2f * 100 = %.2f%%<br>" +
                    "<hr>" +
                    "</html>",
                    vProduto, vFrete, pICMS, vICMSproprio,
                    vProduto, vIPI, vFrete, pMVA, vProduto, vIPI, baseICMSST,
                    baseICMSST, pICMSST, vICMSproprio, vICMSST,
                    vProduto, vIPI, vICMSST, vFrete, vTotalComImposto,
                    vICMSST, vProduto, vIPI, pSTprod,
                    vICMSST, vProduto, pSTsist
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
                    "<html>" +
                    "<b>Modo:</b> Interestadual com Redução<br>" +
                    "<hr>" +
                    "<b>Detalhamento do Cálculo:</b><br>" +
                    "1. <b>Base ICMS:</b> (vProduto + vFrete) * (1 - pRedBC / 100)<br>" +
                    "&nbsp;&nbsp;&nbsp;Substituindo: (%.2f + %.2f) * (1 - %.2f / 100) = R$ %.2f<br>" +
                    "<hr>" +
                    "2. <b>ICMS Próprio:</b> Base ICMS * pICMS / 100<br>" +
                    "&nbsp;&nbsp;&nbsp;Substituindo: %.2f * %.2f / 100 = R$ %.2f<br>" +
                    "<hr>" +
                    "3. <b>Base ICMS ST:</b> ((vProduto + vIPI + vFrete) * (1 + pMVA / 100)) * (1 - pRedBCST / 100)<br>" +
                    "&nbsp;&nbsp;&nbsp;Substituindo: ((%.2f + %.2f + %.2f) * (1 + %.2f / 100)) * (1 - %.2f / 100) = R$ %.2f<br>" +
                    "<hr>" +
                    "4. <b>ICMS ST:</b> Base ICMS ST * pICMSST / 100 - ICMS Próprio<br>" +
                    "&nbsp;&nbsp;&nbsp;Substituindo: %.2f * %.2f / 100 - %.2f = R$ %.2f<br>" +
                    "<hr>" +
                    "5. <b>Total com Imposto:</b> vProduto + vIPI + ICMS ST + vFrete<br>" +
                    "&nbsp;&nbsp;&nbsp;Substituindo: %.2f + %.2f + %.2f + %.2f = R$ %.2f<br>" +
                    "<hr>" +
                    "6. <b>ST Produto:</b> ICMS ST / (vProduto + vIPI) * 100<br>" +
                    "&nbsp;&nbsp;&nbsp;Substituindo: %.2f / (%.2f + %.2f) * 100 = %.2f%%<br>" +
                    "<hr>" +
                    "7. <b>ST Sistema:</b> ICMS ST / vProduto * 100<br>" +
                    "&nbsp;&nbsp;&nbsp;Substituindo: %.2f / %.2f * 100 = %.2f%%<br>" +
                    "<hr>" +
                    "</html>",
                    vProduto, vFrete, pRedBC, baseICMS,
                    baseICMS, pICMS, vICMSproprio,
                    vProduto, vIPI, vFrete, pMVA, pRedBCST, baseICMSST,
                    baseICMSST, pICMSST, vICMSproprio, vICMSST,
                    vProduto, vIPI, vICMSST, vFrete, vTotalComImposto,
                    vICMSST, vProduto, vIPI, pSTprod,
                    vICMSST, vProduto, pSTsist
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
