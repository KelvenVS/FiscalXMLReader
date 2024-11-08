package fp.flamapar.xmlread;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pessoa {
    @XmlElement
    private String nome;
    @XmlElement
    private int idade;

    // Getter e Setter para 'nome'
    public String getName() {
        return nome;
    }
    public void setName(String nome) {
        this.nome = nome;
    }

    // Getter e Setter para 'idade'
    public int getAge() {
        return idade;
    }
    public void setAge(int idade) {
        this.idade = idade;
    }
}
