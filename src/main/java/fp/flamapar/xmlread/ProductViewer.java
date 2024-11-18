package fp.flamapar.xmlread;

import fp.flamapar.xmlread.model.ProdutoDetalhes;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProductViewer extends JFrame {

    private JList<ProdutoDetalhes> productList;
    // Declaração dos JLabels para cada atributo de ProdutoDetalhes
    private JLabel labelNome, labelCodigo, labelCodigoEAN, labelNCM, labelPrecoUnit, labelTotal, labelpIPI, labelvIPI,labelCsta, labelCstb, labelCfop, labelMva, labelStsist, labelSt, labelIcmsst, labelVprod, labelBaseicmsst;
    
    private JTextArea textNome, textCodigo, textCodigoEAN, textNCM, textPrecoUnit, textTotal, textpIPI, textvIPI,textCsta, textCstb, textCfop, textMva, textStsist, textSt, textIcmsst, textVprod, textBaseicmsst;


    public ProductViewer() {
        
        // Configurações da janela principal
        setTitle("Visualizador de Produtos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Define o layout principal como GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        add(mainPanel);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Carrega a lista de produtos
        XmlReadExample xmlReadExample = new XmlReadExample();
        List<ProdutoDetalhes> produtos = xmlReadExample.loadProdutos();

        // Configura a JList de produtos
        productList = new JList<>(produtos.toArray(new ProdutoDetalhes[0]));
        productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productList.addListSelectionListener(e -> showProductDetails(productList.getSelectedValue()));
        
        // Adiciona a lista ao painel principal com rolagem, ocupando toda a largura
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 6;   // Ocupa seis colunas
        gbc.insets = new Insets(5, 10, 5, 5);
        gbc.weightx = 1;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(new JScrollPane(productList), gbc);
        
        
        
        labelNome = new JLabel("Nome: ");
        addComponent(mainPanel, labelNome, 0, 1);
        
        textNome = createTextArea();
        addComponent(mainPanel, textNome, 1, 1);
        
        labelCodigo = new JLabel("Código: ");
        addComponent(mainPanel, labelCodigo, 2, 1);
        
        textCodigo = createTextArea();
        addComponent(mainPanel, textCodigo, 3, 1);
        
        labelCodigoEAN = new JLabel("Código EAN: ");
        addComponent(mainPanel, labelCodigoEAN, 4, 1);
        
        textCodigoEAN = createTextArea();
        addComponent(mainPanel, textCodigoEAN, 5, 1);
        
        
        
        
        // Inicialização dos JLabels
        
        labelNCM = new JLabel("NCM: ");
        labelPrecoUnit = new JLabel("Preço Unitário: ");
        labelTotal = new JLabel("Total com Impostos: ");
        labelpIPI = new JLabel("pIPI: ");
        labelvIPI = new JLabel("vIPI: ");
        labelCsta = new JLabel("CSTA: ");
        labelCstb = new JLabel("CSTB: ");
        labelCfop = new JLabel("CFOP: ");
        labelMva = new JLabel("MVA: ");
        labelStsist = new JLabel("ST Sistema: ");
        labelSt = new JLabel("ST: ");
        labelIcmsst = new JLabel("ICMS ST: ");
        labelVprod = new JLabel("Valor Produto: ");
        labelBaseicmsst = new JLabel("Base ICMS ST: ");

        // Inicialização dos JTextAreas
        
        
        textNCM = createTextArea();
        textPrecoUnit = createTextArea();
        textTotal = createTextArea();
        textpIPI = createTextArea();
        textvIPI = createTextArea();
        textCsta = createTextArea();
        textCstb = createTextArea();
        textCfop = createTextArea();
        textMva = createTextArea();
        textStsist = createTextArea();
        textSt = createTextArea();
        textIcmsst = createTextArea();
        textVprod = createTextArea();
        textBaseicmsst = createTextArea();
    }
    
    // Método auxiliar para criar JTextArea com margens internas e configuração de tamanho
    private JTextArea createTextArea() {
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createEmptyBorder(5,20, 5, 5));
        textArea.setBackground(new Color(255, 255, 255));
        //textArea.setPreferredSize(new Dimension(50, 25));
        return textArea;
    }
    
    // Atualiza o Produto ao clicar
    private void showProductDetails(ProdutoDetalhes produto) {
        if (produto != null) {
            textNome.setText(produto.getNome());
            System.out.println(produto.getNome());
            textCodigo.setText(produto.getCodigo());
            textCodigoEAN.setText(produto.getCodigoEAN());
            textNCM.setText(produto.getNcm());
            textPrecoUnit.setText(String.valueOf(produto.getPrecoUnitario()));
            textTotal.setText(String.valueOf(produto.getTotalComImpostos()));
            textpIPI.setText(String.valueOf(produto.getpIPI()));
            textvIPI.setText(String.valueOf(produto.getvIPI()));
            textCsta.setText(produto.getCsta());
            textCstb.setText(produto.getCstb());
            textCfop.setText(produto.getCfop());
            textMva.setText(String.valueOf(produto.getMva()));
            textStsist.setText(String.valueOf(produto.getStsist()));
            textSt.setText(String.valueOf(produto.getSt()));
            textIcmsst.setText(String.valueOf(produto.getIcmsst()));
            textVprod.setText(String.valueOf(produto.getVprod()));
            textBaseicmsst.setText(String.valueOf(produto.getBaseicmsst()));
        }
    }
    
        private void addComponent(JPanel panel, Component component, int gridx, int gridy) {
            addComponent(panel, component, gridx, gridy, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, new Insets(5, 5, 5, 5));
        }

        private void addComponent(JPanel panel, Component component, int gridx, int gridy, int gridwidth, int gridheight) {
            addComponent(panel, component, gridx, gridy, gridwidth, gridheight, 0.0, 0.0, GridBagConstraints.NONE, new Insets(5, 5, 5, 5));
        }

        private void addComponent(
            JPanel panel,
            Component component,
            int gridx,
            int gridy,
            int gridwidth,
            int gridheight,
            double weightx,
            double weighty,
            int fill,
            Insets insets
        ) {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = gridx;
            gbc.gridy = gridy;
            gbc.gridwidth = gridwidth;
            gbc.gridheight = gridheight;
            gbc.weightx = weightx;
            gbc.weighty = weighty;
            gbc.fill = fill;
            gbc.insets = insets;
            panel.add(component, gbc);
        }


    
    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        ProductViewer viewer = new ProductViewer();
        viewer.setVisible(true);
    });
    }
}