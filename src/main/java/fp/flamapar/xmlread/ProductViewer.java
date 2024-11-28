package fp.flamapar.xmlread;

import fp.flamapar.xmlread.model.ProductDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.*; // Para Drag-and-Drop
import java.awt.datatransfer.DataFlavor; // Para Transferência de Dados
import java.text.DecimalFormat; // Para Formatar os Doubles
import java.io.File;
import java.util.List; // Para manipular múltiplos arquivos

public class ProductViewer extends JFrame {

    private JList<ProductDetails> productList;   
    // Declaração dos JLabels para cada atributo de ProdutoDetalhes
    private JLabel labelNome, labelCodigo, labelCodigoEAN, labelNCM, labelPrecoUnit, labelPrecoComImpostos, labelpIPI, labelvIPI,labelCsta, labelCstb, labelCfop, labelMva, labelStsist, labelSt, labelIcmsst, labelUcom, labelBaseicmsst, labelTotalProd, labelTotaldaNota , labelvFrete;
    
    private JTextArea textNome, textCodigo, textCodigoEAN, textNCM, textPrecoUnit, textTotal, textpIPI, textvIPI,textCsta, textCstb, textCfop, textMva, textStsist, textSt, textIcmsst, textUcom, textBaseicmsst , textTotalProd, textTotaldaNota , textvFrete;


    public ProductViewer() {
        
        productList = new JList<>();
        
        // Adiciona um listener à lista para atualizar os detalhes ao clicar
        productList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Evita múltiplas notificações durante uma única seleção
                ProductDetails selectedProduct = productList.getSelectedValue();
                showProductDetails(selectedProduct);
            }
        });
        
        // Configurações da janela principal
        setTitle("Visualizador de Produtos");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Define o layout principal como GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        add(mainPanel);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        
        JLabel dragDropArea = new JLabel("Arraste o arquivo XML aqui", SwingConstants.CENTER);
        dragDropArea.setPreferredSize(new Dimension(600, 100));
        dragDropArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        dragDropArea.setOpaque(true);
        dragDropArea.setBackground(Color.LIGHT_GRAY);
        dragDropArea.setForeground(Color.BLACK);

        // Configura o Drag-and-Drop para o dragDropArea
        new DropTarget(dragDropArea, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent dtde) {
                try {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles = (List<File>) dtde.getTransferable()
                            .getTransferData(DataFlavor.javaFileListFlavor);

                    for (File file : droppedFiles) {
                        if (file.getName().endsWith(".xml")) {
                            processXmlFile(file); // Processa o arquivo XML
                        } else {
                            JOptionPane.showMessageDialog(dragDropArea, "Por favor, insira apenas arquivos XML.");
                        }
                    }

                    dtde.dropComplete(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dragDropArea, "Erro ao processar o arquivo: " + ex.getMessage());
                    dtde.dropComplete(false);
                }
            }
        });
        
        // Adiciona a lista ao painel principal com rolagem, ocupando toda a largura
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 6;   // Ocupa seis colunas
        gbc.gridheight = 3;  // Ocupa 3 linhas
        gbc.insets = new Insets(5, 10, 5, 5);
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(new JScrollPane(productList), gbc);
        
        
              
        
        // Linha 1
        int linha = 4;
        labelNome = new JLabel("Nome: ");
        addComponent(mainPanel, labelNome, 0, linha);
        
        textNome = createTextArea();
        addComponent(mainPanel, textNome, 1, linha, 3, 1, 1.0, 0.0, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5));
        
        labelUcom = new JLabel("Unidade: ");
        addComponent(mainPanel, labelUcom, 4, linha);
        
        textUcom = createTextArea();
        addComponent(mainPanel, textUcom, 5, linha);
        
        
        
        //Linha 2
        linha = linha + 1;
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
        linha = linha + 1;
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
        linha = linha + 1;
        labelpIPI = new JLabel("% IPI: ");
        addComponent(mainPanel, labelpIPI, 0, linha);
        
        textpIPI = createTextArea();        
        addComponent(mainPanel, textpIPI, 1, linha);
        
        labelvIPI = new JLabel("IPI: ");
        addComponent(mainPanel, labelvIPI, 2, linha);
        
        textvIPI = createTextArea();
        addComponent(mainPanel, textvIPI, 3, linha);
        
        labelMva = new JLabel("% MVA: ");
        addComponent(mainPanel, labelMva, 4, linha);
        
        textMva = createTextArea();
        addComponent(mainPanel, textMva, 5, linha);
        
        //Linha 5
        linha = linha + 1;
        labelStsist = new JLabel("% ST Sistema: ");
        addComponent(mainPanel, labelStsist, 0, linha);
        
        textStsist = createTextArea();
        addComponent(mainPanel, textStsist, 1, linha);
        
        labelSt = new JLabel("% ST: ");
        addComponent(mainPanel, labelSt, 2, linha);
        
        textSt = createTextArea();
        addComponent(mainPanel, textSt, 3, linha);
        
        labelIcmsst = new JLabel("ICMS ST: ");        
        addComponent(mainPanel, labelIcmsst, 4, linha);
        
        textIcmsst = createTextArea();        
        addComponent(mainPanel, textIcmsst, 5, linha);
        
        //Linha 6
        linha = linha + 1;
        labelPrecoUnit = new JLabel("Preço Unitário: ");
        addComponent(mainPanel, labelPrecoUnit, 0, linha);
        
        textPrecoUnit = createTextArea();
        addComponent(mainPanel, textPrecoUnit, 1, linha);
        
        labelPrecoComImpostos = new JLabel("Preço Final: ");
        addComponent(mainPanel, labelPrecoComImpostos, 2, linha);
        
        textTotal = createTextArea();
        addComponent(mainPanel, textTotal, 3, linha);
            
        labelBaseicmsst = new JLabel("Base ICMS ST: ");
        addComponent(mainPanel, labelBaseicmsst, 4, linha);
        
        textBaseicmsst = createTextArea();
        addComponent(mainPanel, textBaseicmsst, 5, linha);
        
        //Linha 7 
        linha = linha + 1;
        labelTotalProd = new JLabel("Total do Produto:");
        addComponent(mainPanel, labelTotalProd, 0, linha);
        
        textTotalProd = createTextArea();
        addComponent(mainPanel, textTotalProd, 1, linha);
        
        labelTotaldaNota = new JLabel("Total da Nota :");
        addComponent(mainPanel, labelTotaldaNota, 2, linha);
        
        textTotaldaNota = createTextArea();
        addComponent(mainPanel, textTotaldaNota, 3, linha);
        
        labelvFrete = new JLabel("Frete Unitário :");
        addComponent(mainPanel, labelvFrete, 4, linha);
        
        textvFrete = createTextArea();
        addComponent(mainPanel, textvFrete, 5, linha);
        
        
        //Linha 8
        linha = linha + 1;
        addComponent(mainPanel, dragDropArea, 0, linha, 5, 1, 1.0, 0.0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
        
        // Adicione o botão no painel principal
        JButton detailsButton = new JButton("Ver Detalhes");
        addComponent(mainPanel, detailsButton, 5, linha);

        // Adicione um ActionListener ao botão
        detailsButton.addActionListener(e -> {
            // Obtenha o produto selecionado na JList
            ProductDetails selectedProduct = productList.getSelectedValue();

            if (selectedProduct != null) {
                // Abra o pop-up com informações detalhadas do produto
                showProductPopup(selectedProduct);
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum produto selecionado!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
      
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
    
    DecimalFormat df = new DecimalFormat();
    
    // Atualiza o Produto ao clicar
    private void showProductDetails(ProductDetails produto) {
        if (produto != null) {
            textNome.setText(produto.getNome());
            textMva.setToolTipText(produto.getExplicacao()); // Define o texto do tooltip
            //System.out.println(produto.getNome());
            
            df.applyPattern("R$ #,##0.00");
            textPrecoUnit.setText(df.format(produto.getPrecoUnitario()));
            textTotal.setText(df.format(produto.getTotalComImpostos()));
            textvIPI.setText(df.format(produto.getVIPI()));
            textIcmsst.setText(df.format(produto.getVicmsst()));
            textTotalProd.setText(df.format(produto.getPrecoUnitario() * produto.getQCom()));
            textTotaldaNota.setText(df.format(produto.getTotalComImpostos()* produto.getQCom()));
            textvFrete.setText(df.format(produto.getVFrete()));
            
            df.applyPattern("##0.00");
            textpIPI.setText(df.format(produto.getPIPI()));
            textStsist.setText(df.format(produto.getStsist()));
            textSt.setText(df.format(produto.getSt()));
            textBaseicmsst.setText(df.format(produto.getBaseicmsst()));
            
            textCodigo.setText(produto.getCodigo());
            textCodigoEAN.setText(produto.getCodigoEAN());
            textNCM.setText(produto.getNcm());
            textCsta.setText(produto.getCsta());
            textCstb.setText(produto.getCstb());
            textCfop.setText(produto.getCfop());
            textMva.setText(String.valueOf(produto.getMva()));

            
            
            textUcom.setText(String.valueOf(produto.getQCom() + " x "+ produto.getUCom()));
            //System.out.println(produto.getUCom());

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
        
    private void processXmlFile(File file) {
        XmlParser xmlReadExample = new XmlParser();
        //ProductProcessor xmlReadExample = new ProductProcessor();
        try {
            // Atualiza a lista de produtos
            List<ProductDetails> produtos = xmlReadExample.parseXml(file);
            //List<ProductDetails> produtos = xmlReadExample.loadProdutos(file);

            // Verifique se productList não é nula antes de usá-la
            if (productList == null) {
                productList = new JList<>();
            }

            // Atualiza a JList
            productList.setListData(produtos.toArray(new ProductDetails[0]));

            JOptionPane.showMessageDialog(this, "Arquivo XML processado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao processar o XML: " + ex.getMessage());
        }
    }
    
    
    // Método para exibir o pop-up
    private void showProductPopup(ProductDetails product) {
        // Crie um novo JFrame para o pop-up
        JFrame popup = new JFrame("Detalhes do Produto");
        popup.setSize(600, 400);
        popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popup.setLocationRelativeTo(this);

        // Use JEditorPane para exibir o HTML retornado por getExplicacao()
        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html"); // Define o tipo de conteúdo como HTML
        editorPane.setText(product.getExplicacao()); // Insere o conteúdo HTML
        editorPane.setEditable(false); // Torna o editor apenas leitura

        // Adiciona um JScrollPane para o editor, caso o conteúdo seja maior que a janela
        JScrollPane scrollPane = new JScrollPane(editorPane);

        // Adiciona o JScrollPane ao JFrame
        popup.add(scrollPane);
        popup.setVisible(true);
    }
  

    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        ProductViewer viewer = new ProductViewer();
        viewer.setVisible(true);
    });
    }
}