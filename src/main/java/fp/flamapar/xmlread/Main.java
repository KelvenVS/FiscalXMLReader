package fp.flamapar.xmlread;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        Pessoa person = loadPerson();
        if (person != null) {
            System.out.println("Name: " + person.getName());
            System.out.println("Age: " + person.getAge());
        } else {
            System.out.println("Failed to load person data.");
        }
    }

    public static Pessoa loadPerson() {
        try {
            // Carrega o recurso XML como URL para um arquivo relativo em um método estático
            URL resource = Main.class.getClassLoader().getResource("pessoal.xml");
            if (resource == null) {
                System.out.println("Arquivo XML não encontrado.");
                return null;
            }
            
            File file = new File(resource.getFile());

            // Configura JAXB para deserializar o XML
            JAXBContext context = JAXBContext.newInstance(Pessoa.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Pessoa) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
