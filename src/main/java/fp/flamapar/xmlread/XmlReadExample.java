package fp.flamapar.xmlread;

import fp.flamapar.xmlread.model.ProdutoDetalhes;
import fp.flamapar.xmlread.model.produto.ICMSBase;
import fp.flamapar.xmlread.model.produto.Prod;
import fp.flamapar.xmlread.model.nota.Det;
import fp.flamapar.xmlread.model.nota.NfeProc;
import fp.flamapar.xmlread.model.produto.IPITrib;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class XmlReadExample {
    public List<ProdutoDetalhes> loadProdutos(File file) {
        List<ProdutoDetalhes> produtos = new ArrayList<>();
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

                    //Var
                    String cstA = icmsBase != null ? icmsBase.getOrig() : "N/A";
                    String cstB = icmsBase != null ? icmsBase.getCst() : "N/A";
                    Double icms = (icmsBase != null && icmsBase.getpICMS() != null )? icmsBase.getpICMS() : 0.0;
                    Double mva = (icmsBase != null && icmsBase.getpMVAST() != null) ? icmsBase.getpMVAST() : 0.0;
                    Double vProd = prod.getVProd();
                    Double vUnCom = prod.getVUnCom();
                    Double ipi = ipitrib.getpIPI();
                    String uCom = prod.getUCom();
                    Double vFrete = (prod.getVFrete() != null ? prod.getVFrete() : 0.0);
                                       
                    //Cálculos
                    DecimalFormat df = new DecimalFormat("#.00");
                    Double vIPI = (ipi/100*vUnCom);
                    Double vProdNF = (vIPI+vUnCom);
                    Double vICMSproprio = ((vUnCom+vFrete)*icms/100);
                    Double baseICMSst = ((vProdNF+vFrete)*(1+(mva/100)));
                    Double vICMSst = (baseICMSst*icms/100-vICMSproprio);
                    Double vTotalProd = (vProdNF+vICMSst);
                    Double pSTsistema = (vICMSst/vUnCom)*100;
                    Double pSTprod = (vICMSst/(vProdNF))*100;
                    
                    //adicionar um metodo para truncar valores
                    
                    ProdutoDetalhes produtoDetalhes = new ProdutoDetalhes(
                                    prod.getXProd(),       // nome
                                    prod.getCProd(),       // codigo
                                    prod.getCEAN(),        // codigoEAN
                                    prod.getNCM(),         // ncm
                                    cstA,                  // csta
                                    cstB,                  // cstb
                                    prod.getCfop(),        // cfop
                                    uCom, //Unidade Comercializada
                                    vUnCom,                // precoUnitario
                                    vTotalProd,            // totalComImpostos
                                    ipi,                   // pIPI
                                    vIPI,                  // vIPI
                                    mva,                   // mva
                                    pSTsistema,            // stsist
                                    pSTprod,               // st
                                    vICMSst,               // icmsst
                                    vProd,                 // vprod
                                    baseICMSst,             // baseicmsst
                                    vFrete                 //Valor do Frete do produto
                                    );
                                    produtos.add(produtoDetalhes);
                                    }
                                }
                            } catch (JAXBException e) {
                                e.printStackTrace();
                            }
                            return produtos;
                        }
                    }
                    
