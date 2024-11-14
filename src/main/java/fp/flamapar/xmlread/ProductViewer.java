package fp.flamapar.xmlread;

import fp.flamapar.xmlread.model.ProdutoDetalhes;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProductViewer extends JFrame {

    private JList<ProdutoDetalhes> productList;
    private JLabel labelNome, labelCodigo, labelCodigoEAN, labelNCM, labelPrecoUnit, labelTotal, labelpIPI, labelvIPI;
    private JTextArea textNome, textCodigo, textCodigoEAN, textNCM, textPrecoUnit, textTotal ,textpIPI , textvIPI;

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

        // Define o layout para os detalhes do produto
        labelNome = new JLabel("Nome: ");
        labelCodigo = new JLabel("Código: ");
        labelCodigoEAN = new JLabel("Código EAN: ");
        labelNCM = new JLabel("NCM: ");
        labelpIPI = new JLabel("pIPI: ");
        labelvIPI = new JLabel("vIPI: ");
        labelPrecoUnit = new JLabel("Preço Unitário: ");
        labelTotal = new JLabel("Total com Impostos: ");

        textNome = createTextArea();
        textCodigo = createTextArea();
        textCodigoEAN = createTextArea();
        textNCM = createTextArea();
        textpIPI = createTextArea();
        textvIPI = createTextArea();
        textPrecoUnit = createTextArea();
        textTotal = createTextArea();

        // Adiciona os rótulos e campos de texto no painel com GridBagLayout
        addLabelAndField(mainPanel, gbc, labelNome, textNome, 1);
        addLabelAndField(mainPanel, gbc, labelCodigo, textCodigo, 2);
        addLabelAndField(mainPanel, gbc, labelCodigoEAN, textCodigoEAN, 3);
        addLabelAndField(mainPanel, gbc, labelNCM, textNCM, 4);
        
        // Adiciona labelpIPI e textpIPI na mesma linha que labelvIPI e textvIPI
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.weightx = 0.2;
        mainPanel.add(labelpIPI, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.2;
        mainPanel.add(textpIPI, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0.2;
        mainPanel.add(labelvIPI, gbc);
        gbc.gridx = 3;
        gbc.weightx = 0.2;
        mainPanel.add(textvIPI, gbc);
        
        addLabelAndField(mainPanel, gbc, labelPrecoUnit, textPrecoUnit, 6);
        addLabelAndField(mainPanel, gbc, labelTotal, textTotal, 7);
    }

    // Método auxiliar para adicionar rótulos e campos de texto no GridBagLayout
    private void addLabelAndField(JPanel panel, GridBagConstraints gbc, JLabel label, JTextArea textArea, int row) {
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);

        // Adiciona o rótulo
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0.1;
        panel.add(label, gbc);

        // Adiciona o campo de texto
        gbc.gridx = 1;
        gbc.weightx = 0.9;
        panel.add(textArea, gbc);
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
            textCodigo.setText(produto.getCodigo());
            textCodigoEAN.setText(produto.getCodigoEAN());
            textNCM.setText(produto.getNcm());
            textPrecoUnit.setText("" + produto.getPrecoUnitario());
            textTotal.setText("" + produto.getTotalComImpostos());
            textpIPI.setText("" + produto.getpIPI());
            textvIPI.setText("" + produto.getvIPI());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductViewer viewer = new ProductViewer();
            viewer.setVisible(true);
        });
    }
}
