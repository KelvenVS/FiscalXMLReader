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
        setSize(800, 400);
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
        
        
        // Linha 1
        labelNome = new JLabel("Nome: ");
        addComponent(mainPanel, labelNome, 0, 1);
        
        textNome = createTextArea();
        addComponent(mainPanel, textNome, 1, 1, 6, 1, 1.0, 0.0, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5));
        
        //Linha 2
        int linha = 2;
        labelCodigo = new JLabel("Código: ");
        addComponent(mainPanel, labelCodigo,0, linha);
        textCodigo = createTextArea();
        
        addComponent(mainPanel, textCodigo, 1, linha);
 
        labelCodigoEAN = new JLabel("Código EAN: ");
        addComponent(mainPanel, labelCodigoEAN, 2, linha);
        
        textCodigoEAN = createTextArea();
        addComponent(mainPanel, textCodigoEAN, 3, linha);
        
        
        labelNCM = new JLabel("NCM: ");
        addComponent(mainPanel, labelNCM, 4, linha);
        
        textNCM = createTextArea();
        addComponent(mainPanel, textNCM, 5, linha);
        
        
        //Linha 3
        linha = 3;
        labelCsta = new JLabel("CSTA: ");
        addComponent(mainPanel, labelCsta, 0, linha);
        
        textCsta = createTextArea();
        addComponent(mainPanel, textCsta, 1, linha);
        
        labelCstb = new JLabel("CSTB: ");
        addComponent(mainPanel, labelCstb, 2, linha);
        
        textCstb = createTextArea();
        addComponent(mainPanel, textCstb, 3, linha);
        
        labelCfop = new JLabel("CFOP: ");
        addComponent(mainPanel, labelCfop, 4, linha);
        
        textCfop = createTextArea();
        addComponent(mainPanel, textCfop, 5, linha);
        
        
        //Linha 4;
        linha = 4;
        labelpIPI = new JLabel("pIPI: ");
        addComponent(mainPanel, labelpIPI, 0, linha);
        
        textpIPI = createTextArea();        
        addComponent(mainPanel, textpIPI, 1, linha);
        
        labelvIPI = new JLabel("vIPI: ");
        addComponent(mainPanel, labelvIPI, 2, linha);
        
        textvIPI = createTextArea();
        addComponent(mainPanel, textvIPI, 3, linha);
        
        labelMva = new JLabel("MVA: ");
        addComponent(mainPanel, labelMva, 4, linha);
        
        textMva = createTextArea();
        addComponent(mainPanel, textMva, 5, linha);
        
        //Linha 5
        linha = 5;
        labelStsist = new JLabel("ST Sistema: ");
        addComponent(mainPanel, labelStsist, 0, linha);
        
        textStsist = createTextArea();
        addComponent(mainPanel, textStsist, 1, linha);
        
        labelSt = new JLabel("ST: ");
        addComponent(mainPanel, labelSt, 2, linha);
        
        textSt = createTextArea();
        addComponent(mainPanel, textSt, 3, linha);
        
        labelIcmsst = new JLabel("ICMS ST: ");        
        addComponent(mainPanel, labelIcmsst, 4, linha);
        
        textIcmsst = createTextArea();        
        addComponent(mainPanel, textIcmsst, 5, linha);
        
        //Linha 6
        linha = 6;
        labelPrecoUnit = new JLabel("Preço Unitário: ");
        addComponent(mainPanel, labelPrecoUnit, 0, linha);
        
        textPrecoUnit = createTextArea();
        addComponent(mainPanel, textPrecoUnit, 1, linha);
        
        labelTotal = new JLabel("Total com Impostos: ");
        addComponent(mainPanel, labelTotal, 2, linha);
        
        textTotal = createTextArea();
        addComponent(mainPanel, textTotal, 3, linha);
            
        labelBaseicmsst = new JLabel("Base ICMS ST: ");
        addComponent(mainPanel, labelBaseicmsst, 4, linha);
        
        textBaseicmsst = createTextArea();
        addComponent(mainPanel, textBaseicmsst, 5, linha);
        
        
        
        // Inicialização dos JLabels

        labelVprod = new JLabel("Valor Produto: ");


        // Inicialização dos JTextAreas

        textVprod = createTextArea();

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