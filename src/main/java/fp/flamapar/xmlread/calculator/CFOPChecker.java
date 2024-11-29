package fp.flamapar.xmlread.calculator;
import java.util.HashSet;
import java.util.Set;

public class CFOPChecker {
    // Conjunto de CFOPs que indicam Substituição Tributária
    private static final Set<String> CFOP_ST = new HashSet<>();

    static {
        // Adiciona os CFOPs relacionados à ST ao conjunto
        CFOP_ST.add("1401");
        CFOP_ST.add("1403");
        CFOP_ST.add("1406");
        CFOP_ST.add("1407");
        CFOP_ST.add("1408");
        CFOP_ST.add("1409");
        CFOP_ST.add("1410");
        CFOP_ST.add("1411");
        CFOP_ST.add("1414");
        CFOP_ST.add("1415");
        CFOP_ST.add("2401");
        CFOP_ST.add("2403");
        CFOP_ST.add("2406");
        CFOP_ST.add("2407");
        CFOP_ST.add("2408");
        CFOP_ST.add("2409");
        CFOP_ST.add("2410");
        CFOP_ST.add("2411");
        CFOP_ST.add("2414");
        CFOP_ST.add("2415");
        CFOP_ST.add("5401");
        CFOP_ST.add("5402");
        CFOP_ST.add("5403");
        CFOP_ST.add("5405");
        CFOP_ST.add("5408");
        CFOP_ST.add("5409");
        CFOP_ST.add("5410");
        CFOP_ST.add("5411");
        CFOP_ST.add("5412");
        CFOP_ST.add("5413");
        CFOP_ST.add("5414");
        CFOP_ST.add("5415");
        CFOP_ST.add("6401");
        CFOP_ST.add("6402");
        CFOP_ST.add("6403");
        CFOP_ST.add("6404");
        CFOP_ST.add("6408");
        CFOP_ST.add("6409");
        CFOP_ST.add("6410");
        CFOP_ST.add("6411");
        CFOP_ST.add("6412");
        CFOP_ST.add("6413");
        CFOP_ST.add("6414");
        CFOP_ST.add("6415");
    }

// Método para verificar se o CFOP indica Substituição Tributária
public static boolean isSubstituicaoTributaria(String cfop) {
    return CFOP_ST.contains(cfop);
}


}

