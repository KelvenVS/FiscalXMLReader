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
                    Double icms = (icmsBase != null && icmsBase.getPICMS() != null )? icmsBase.getPICMS() : 0.0;
                    Double mva = (icmsBase != null && icmsBase.getPMVAST() != null) ? icmsBase.getPMVAST() : 0.0;
                    Double pRedBC = (icmsBase != null && icmsBase.getPRedBC()!= null) ? icmsBase.getPRedBC() : 0.0;
                    Double pRedBCST = (icmsBase != null && icmsBase.getPRedBCST()!= null) ? icmsBase.getPRedBCST() : 0.0;                   
                    
                    //<ipi>
                    Double ipi = ipitrib.getpIPI();
                    
                    
                    //Calc ICMS ST
                    Double vIPI = (ipi/100 * vUnCom);
                    Double vProdNF = (vIPI + vUnCom);
                    Double vICMSproprio = (pRedBC == 0.0)  
                            ? ((vUnCom + vFrete) * icms/100) 
                            : (((vUnCom + vFrete) * icms/100) * (1 - pRedBC/100));
                            
                    Double baseICMSst = (pRedBCST == 0.0) 
                            ? ((vProdNF + vFrete)*(1+(mva/100)))
                            : ((vProdNF + vFrete)*(1+(mva/100)) * (1 - pRedBC/100));
                    
                    Double vICMSst = (baseICMSst * icms/100 - vICMSproprio);
                    
                    Double vTotalProd = (vProdNF + vICMSst);
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
                                    .icms(icms)
                                    .icmsst(vICMSst)
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
                    }
                    
