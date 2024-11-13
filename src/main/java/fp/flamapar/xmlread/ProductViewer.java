package fp.flamapar.xmlread;

import fp.flamapar.xmlread.model.ProdutoDetalhes;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProductViewer extends JFrame {

    private JList<ProdutoDetalhes> productList;
    private JLabel labelNome, labelCodigo, labelCodigoEAN, labelNCM, labelPrecoUnit, labelTotal;
    private JTextArea textNome, textCodigo, textCodigoEAN, textNCM, textPrecoUnit, textTotal;

    public ProductViewer() {
        
        // Configurações da janela principal
        setTitle("Visualizador de Produtos");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        // Lista de produtos
        XmlReadExample xmlReadExample = new XmlReadExample();
        List<ProdutoDetalhes> produtos = xmlReadExample.loadProdutos();
        
        // Converte a lista para um array e cria a JList
        productList = new JList<>(produtos.toArray(new ProdutoDetalhes[0]));
        productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productList.addListSelectionListener(e -> showProductDetails(productList.getSelectedValue()));
        mainPanel.add(new JScrollPane(productList), BorderLayout.NORTH);

        // Painel de detalhes com borda e layout ajustado
        JPanel detailsPanel = new JPanel(new GridLayout(6, 2, 10, 5)); // hgap e vgap para espaçamento
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // margem ao redor do painel
        
        labelNome = new JLabel("Nome: ");
        labelCodigo = new JLabel("Código: ");
        labelCodigoEAN = new JLabel("Código EAN: ");
        labelNCM = new JLabel("NCM: ");
        labelPrecoUnit = new JLabel("Preço Unitário: ");
        labelTotal = new JLabel("Total com Impostos: ");
        
        textNome = createTextArea();
        textCodigo = createTextArea();
        textCodigoEAN = createTextArea();
        textNCM = createTextArea();
        textPrecoUnit = createTextArea();
        textTotal = createTextArea();
        

        detailsPanel.add(labelNome);
        detailsPanel.add(textNome);
        
    
        detailsPanel.add(labelCodigo);
        detailsPanel.add(textCodigo);

        detailsPanel.add(labelCodigoEAN);
        detailsPanel.add(textCodigoEAN);
        
        detailsPanel.add(labelNCM);
        detailsPanel.add(textNCM);
        
        detailsPanel.add(labelPrecoUnit);      
        detailsPanel.add(textPrecoUnit);
        
        detailsPanel.add(labelTotal);
        detailsPanel.add(textTotal);
        
        mainPanel.add(detailsPanel, BorderLayout.CENTER);
    }

    // Método auxiliar para criar JTextArea com margens internas e configuração de tamanho
    private JTextArea createTextArea() {
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(false);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // margem interna
        textArea.setBackground(new Color(255, 255, 255)); // cor de fundo para diferenciar
        textArea.setPreferredSize(new Dimension(200, 25)); // altura mínima para uniformidade
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
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductViewer viewer = new ProductViewer();
            viewer.setVisible(true);
        });
    }
}
