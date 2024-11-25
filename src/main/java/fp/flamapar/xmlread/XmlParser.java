package fp.flamapar.xmlread;

import fp.flamapar.xmlread.model.ProductDetailsViewer;
import fp.flamapar.xmlread.model.ProductDetails;
import fp.flamapar.xmlread.model.icms.base.ICMSBase;
import fp.flamapar.xmlread.model.nota.Det;
import fp.flamapar.xmlread.model.nota.NfeProc;
import fp.flamapar.xmlread.model.produto.IPITrib;
import fp.flamapar.xmlread.model.produto.Prod;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {

    public List<ProductDetails> parseXml(File file) {
        List<ProductDetails> produtos = new ArrayList<>();
        try {
            JAXBContext context = JAXBContext.newInstance(NfeProc.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            NfeProc nfeProc = (NfeProc) unmarshaller.unmarshal(file);

            if (nfeProc.getNfe() != null && nfeProc.getNfe().getInfNFe() != null) {
                for (Det det : nfeProc.getNfe().getInfNFe().getDet()) {
                    Prod prod = det.getProd();
                    IPITrib ipitrib = det.getImposto().getIpi().getIpitrib();
                    ICMSBase icmsBase = det.getImposto().getIcms().getICMSType();

                    // Cria um objeto ProductDetails básico, apenas com dados extraídos
                    ProductDetails produtoDetalhes = ProductDetails.builder()
                            .nome(prod.getXProd())
                            .codigo(prod.getCProd())
                            .codigoEAN(prod.getCEAN())
                            .ncm(prod.getNCM())
                            .csta(icmsBase != null ? icmsBase.getOrig() : "N/A")
                            .cstb(icmsBase != null ? icmsBase.getCst() : "N/A")
                            .cfop(prod.getCfop())
                            .uCom(prod.getUCom())
                            .precoUnitario(prod.getVUnCom())
                            .totalComImpostos(0.0)
                            .pIPI(ipitrib.getpIPI())
                            .vIPI(99.99)
                            .mva((icmsBase != null && icmsBase.getPMVAST() != null) ? icmsBase.getPMVAST() : 0.0)
                            .pRedBC((icmsBase != null && icmsBase.getPRedBC()!= null) ? icmsBase.getPRedBC() : 0.0)
                            .pRedBCST((icmsBase != null && icmsBase.getPRedBCST()!= null) ? icmsBase.getPRedBCST() : 0.0)
                            .stsist(0.0)
                            .st(0.0)
                            .picms((icmsBase != null && icmsBase.getPICMS() != null )? icmsBase.getPICMS() : 0.0)
                            .picmsst(0.0)
                            .vicmsst(0.0)
                            .vprod(prod.getVProd())
                            .baseicmsst(0.0)
                            .vFrete((prod.getVFrete() != null ? prod.getVFrete() : 0.0))
                            .qCom((prod.getQCom() != null ? prod.getQCom() : 0.00))
                            .build();

                    produtos.add(produtoDetalhes);
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        ProductDetailsViewer.showProducts(produtos);
        return produtos;
    }
}
