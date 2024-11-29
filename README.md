<div align="center">
<h1 style="font-weight: bold;">XML Reader Java 💻</h1>

![Java](https://img.shields.io/badge/Java-21-red?style=for-the-badge&logo=java)
![Maven](https://img.shields.io/badge/Maven-v4.0.0-blue?style=for-the-badge&logo=apachemaven)
![Lombok](https://img.shields.io/badge/Lombok-v1.18.30-green?style=for-the-badge)

</div>

<p align="center">

  • [Introdução](#introdução) • [Descrição](#descrição) • [Funcionalidades](#funcionalidades) • [Estrutura do Projeto](#estrutura-do-projeto) • [Dependências](#dependências) • [Instalação](#instalação) • [Como Usar](#como-usar) • [Exemplo de Uso](#exemplo-de-uso) • [Sobre o Autor](#Sobre-o-Autor) • [Licença](#licença)

</p>

## Introdução

O **XML Reader Java** é uma aplicação para leitura e processamento de arquivos XML no formato da Nota Fiscal Eletrônica (NF-e) brasileira. Ele utiliza a biblioteca **JAXB** para desserializar os dados em classes Java e oferece uma interface gráfica desenvolvida com **Swing** para visualização detalhada dos produtos extraídos do XML.

Com suporte a diferentes operações, como o cálculo de ICMS-ST e a validação de CFOPs, esta ferramenta é ideal para profissionais da área tributária e desenvolvedores que trabalham com dados fiscais.

## Descrição

O projeto carrega arquivos XML de notas fiscais, extrai os detalhes dos produtos e calcula informações tributárias, como ICMS-ST e IPI, utilizando regras definidas. A interface gráfica permite que o usuário visualize e compare os dados extraídos, com destaque para tributos e cálculos avançados.

### Tecnologias Utilizadas

- **Java 21**
- **Maven** para gerenciamento de dependências.
- **JAXB** para mapeamento de XML para objetos Java.
- **Lombok** para simplificar a criação de classes.
- **Swing** para a interface gráfica.

## Funcionalidades

- **Leitura de XMLs de NF-e**:
  - Extração de informações detalhadas de produtos e tributos.
- **Interface Gráfica Interativa**:
  - Visualização de produtos em uma lista interativa.
  - Detalhes exibidos com destaque para valores tributários.
- **Cálculo Tributário**:
  - ICMS-ST (Substituição Tributária) com múltiplos cenários.
  - Cálculo de IPI.
- **Suporte ao arrastar e soltar**:
  - Carregue arquivos XML diretamente na interface gráfica.

## Estrutura do Projeto

```plaintext
src/
└── main/
    └── java/
        └── br/kelvenserejo/xmlread/
            ├── ProductViewer.java
            ├── XmlParser.java
            ├── calculator/
            │   ├── CalculatorICMSST.java
            │   └── CFOPChecker.java
            ├── model/
            │   ├── ProductDetails.java
            │   ├── ProductDetailsViewer.java
            │   ├── icms/
            │   │   ├── base/
            │   │   │   └── ICMSBase.java
            │   │   └── impl/
            │   │       ├── ICMS00.java
            │   │       ├── ICMS10.java
            │   │       └── ...
            │   ├── nota/
            │   │   ├── Det.java
            │   │   ├── NfeProc.java
            │   │   └── ...
            │   └── produto/
            │       ├── Prod.java
            │       └── IPITrib.java
    └── resources/
└── pom.xml
```

## Dependências

Este projeto utiliza as seguintes dependências:

- **JAXB API**: `jakarta.xml.bind-api` versão `4.0.2`
- **JAXB Runtime**: `org.glassfish.jaxb` versão `4.0.5`
- **Lombok**: `org.projectlombok` versão `1.18.30`
- **Maven Core**: `org.apache.maven.maven-core` versão `3.1.0`

Instale todas as dependências executando o comando Maven:

```bash
mvn clean install
```

## Instalação

1. Clone o repositório do projeto:
   ```bash
   git clone https://github.com/KelvenVS/xmlread-java.git
   ```
2. Navegue até o diretório do projeto:
   ```bash
   cd xml-reader-java
   ```
3. Compile o projeto com Maven:
   ```bash
   mvn clean package
   ```
4. Execute o jar gerado:
   ```bash
   java -jar target/xml-reader-java-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```

## Como Usar

1. **Carregar XML**:
   - Abra o aplicativo.
   - Arraste e solte um arquivo XML válido na área de "Drag and Drop".
2. **Visualizar Produtos**:
   - Clique nos produtos listados para ver detalhes, incluindo informações tributárias.
3. **Cálculos Avançados**:
   - O programa calcula automaticamente o ICMS-ST e o IPI, destacando valores relevantes.

## Exemplo de Uso

Após executar o programa:

1. Adicione arquivos XML no formato NF-e à interface gráfica.
2. O aplicativo exibirá a lista de produtos contidos no XML.
3. Clique nos produtos para ver detalhes, incluindo cálculos tributários.

## Sobre o Autor

*Este projeto foi desenvolvido por **Kelven Vilela Serejo**, utilizando Java e Maven como ferramentas principais, com o objetivo de facilitar o processamento de arquivos XML de notas fiscais eletrônicas.*

## Aviso

*Este projeto foi desenvolvido exclusivamente para fins de estudo e aprendizado. Não há garantia de funcionamento em ambientes de produção ou suporte técnico. Use por sua conta e risco.*

## Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](./LICENSE.md) para mais detalhes.
