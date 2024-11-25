package fp.flamapar.xmlread;

import fp.flamapar.xmlread.model.ProductDetails;
import fp.flamapar.xmlread.model.icms.base.ICMSBase;
import fp.flamapar.xmlread.model.produto.Prod;
import fp.flamapar.xmlread.model.nota.Det;
import fp.flamapar.xmlread.model.nota.NfeProc;
import fp.flamapar.xmlread.model.produto.IPITrib;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProductProcessor {
    public List<ProductDetails> loadProdutos(File file) {
        List<ProductDetails> produtos = new ArrayList<>();
    try {
        // Configurar JAXB para a classe raiz NfeProc
        JAXBContext context = JAXBContext.newInstance(NfeProc.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        // Deserializar o XML fornecido no arquivo para NfeProc
        NfeProc nfeProc = (NfeProc) unmarshaller.unmarshal(file);

        // Acessar a lista de itens (det) dentro de infNFe
        if (nfeProc.getNfe() != null && nfeProc.getNfe().getInfNFe() != null) {
            for (Det det : nfeProc.getNfe().getInfNFe().getDet()) {

        //Object
        Prod prod = det.getProd();
        IPITrib ipitrib = det.getImposto().getIpi().getIpitrib();
        ICMSBase icmsBase = det.getImposto().getIcms().getICMSType();

        //Variaveis do XML

        //<Prod>
        String nome = prod.getXProd(); 
        String codigoProd = prod.getCProd();
        String ccodbarras = prod.getCEAN();
        String ncm =  prod.getNCM();
        String cfop = prod.getCfop();
        Double vProd = prod.getVProd();
        Double vUnCom = prod.getVUnCom();
        String uCom = prod.getUCom();                    
        Double vFrete = (prod.getVFrete() != null ? prod.getVFrete() : 0.0);
        Double qCom = (prod.getQCom() != null ? prod.getQCom() : 0.00);

        //<icms>
        String cstA = icmsBase != null ? icmsBase.getOrig() : "N/A";
        String cstB = icmsBase != null ? icmsBase.getCst() : "N/A";
        Double picms = (icmsBase != null && icmsBase.getPICMS() != null )? icmsBase.getPICMS() : 0.0;
        Double picmsst = (icmsBase != null && icmsBase.getPICMSST() != null )? icmsBase.getPICMSST() : 0.0;
        Double mva = (icmsBase != null && icmsBase.getPMVAST() != null) ? icmsBase.getPMVAST() : 0.0;
        Double pRedBC = (icmsBase != null && icmsBase.getPRedBC()!= null) ? icmsBase.getPRedBC() : 0.0;
        Double pRedBCST = (icmsBase != null && icmsBase.getPRedBCST()!= null) ? icmsBase.getPRedBCST() : 0.0;                   
        //<ipi>
        Double ipi = ipitrib.getpIPI();
                    
        //Calc ICMS ST
        Double vIPI = (ipi/100 * vUnCom);
        Double vProdNF = (vIPI + vUnCom);

        Double vICMSproprio = 0.0;
        Double baseICMSst = 0.0;

        String caso = tipoCalc(mva, picms, picmsst, pRedBCST);
        System.out.println(caso);
        processarCalculo(caso , mva, picms, picmsst, pRedBC);
        
        if (pRedBC == 0.0) {
        vICMSproprio = ((vUnCom + vFrete) * picms/100);
        baseICMSst = (vProdNF + vFrete)*(1+(mva/100));
        }else{
        vICMSproprio = ((vUnCom + vFrete) * picms/100) * (1 - pRedBC/100);
        baseICMSst = (vProdNF + vFrete)*(1+(mva/100)) * (1 - pRedBC/100);
        }

        Double vICMSst = (baseICMSst * picms/100 - vICMSproprio);
        Double vTotalProd = (vProdNF + vFrete + vICMSst);
        Double pSTsistema = (vICMSst / vUnCom) * 100;
        Double pSTprod = (vICMSst/vProdNF) * 100;

        //Se tem MVA desconsiderar esse IF
        if (mva == 0.0){ 
            baseICMSst = 0.0;
            vICMSst = 0.0;
            vTotalProd = vProdNF;
            pSTsistema = pSTprod = 0.0;
        }

//Adicionar um metodo para truncar valores

        ProductDetails produtoDetalhes = ProductDetails.builder()
                .nome(nome)
                .codigo(codigoProd)
                .codigoEAN(ccodbarras)
                .ncm(ncm)
                .csta(cstA)
                .cstb(cstB)
                .cfop(cfop)
                .uCom(uCom)
                .precoUnitario(vUnCom)
                .totalComImpostos(vTotalProd)
                .pIPI(ipi)
                .vIPI(vIPI)
                .mva(mva)
                .stsist(pSTsistema)
                .st(pSTprod)
                .picms(picms)
                .vicmsst(vICMSst)
                .vprod(vProd)
                .baseicmsst(baseICMSst)
                .vFrete(vFrete)
                .qCom(qCom)
                .build();
                produtos.add(produtoDetalhes);
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return produtos;
    }
    
public String tipoCalc(Double mva , Double picms , Double picmsst , Double pRedBC){
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

public void processarCalculo(String caso , Double mva, Double picms, Double picmsst, Double pRedBC) {
    switch (caso) {
        case "SEM_ST":
            System.out.println("Processando cálculo sem ST...");
            // Lógica específica para SEM_ST
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
                    
