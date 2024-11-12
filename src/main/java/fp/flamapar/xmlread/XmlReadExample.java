package fp.flamapar.xmlread;

import fp.flamapar.xmlread.model.produto.ICMS00;
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
                    Prod prod = det.getProd();
                    ICMS00 icms00 = det.getImposto().getIcms().getIcms00();
                    IPITrib ipitrib = det.getImposto().getIpi().getIpitrib();
                    Double vProd = prod.getvProd();
                    
                    System.out.println("Item Número: " + det.getNItem());
                    System.out.println("Código do Fabricante: " + prod.getCProd() 
                            + " Código de Barras: " + prod.getcEAN());
                    System.out.println("CSTa:" + icms00.getOrig() + " CSTb:" + icms00.getCst() 
                    + " NCM:" + prod.getNCM()
                    + " IPI:" + (ipitrib != null ? ipitrib.getpIPI() : "N/A"));
                    
                    System.out.println("Nome do Produto: " + prod.getXProd());
                    System.out.println("Preço Unit: " + vProd);
                    System.out.println("--------------------------------");
                }
            } else {
                System.out.println("Nenhum item encontrado na nota ou estrutura 'infNFe' está vazia.");
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
