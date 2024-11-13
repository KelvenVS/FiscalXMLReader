package fp.flamapar.xmlread;

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

public class XmlReadExample {
    public static void main(String[] args) {
        try {
            // Configurar JAXB para a classe raiz NfeProc
            JAXBContext context = JAXBContext.newInstance(NfeProc.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Carregar o XML e deserializar para NfeProc
            URL resource = XmlReadExample.class.getClassLoader().getResource("nfe.xml");
            if (resource == null) {
                System.out.println("Arquivo XML não encontrado.");
                return;
            }

            File file = new File(resource.getFile());
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
                    
                    Double icms = icmsBase != null ? icmsBase.getpICMS() : 0.0;
                    Double mva = icmsBase != null ? icmsBase.getpMVAST() : 0.0;
                    Double vProd = prod.getvProd();
                    Double ipi = ipitrib.getpIPI();
                    
                    //Cálculos
                    
                    //Prints
                    System.out.println("Item Número: " + det.getNItem()
                        + "\nCódigo do Fabricante: " + prod.getCProd()
                            + " Código de Barras: " + prod.getcEAN()
                        + "\nCSTa:" + cstA + " CSTb:" + cstB 
                            + " NCM:" + prod.getNCM()
                            + " ICMS:" + icms
                            + " IPI:" + ipi
                            + " MVA:" + mva
                        + "\nNome do Produto: " + prod.getXProd()
                        + "\nPreço Unit: " + vProd 
                            + " Unidade:" + prod.getuCom()
                        + "\n--------------------------------------------------------");
                }
            } else {
                System.out.println("Nenhum item encontrado na nota ou estrutura 'infNFe' está vazia.");
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
