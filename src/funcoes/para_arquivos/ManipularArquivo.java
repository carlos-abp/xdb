package funcoes.para_arquivos;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author carlos-abp
 */
public class ManipularArquivo
{

    private String[] pastasEArq;

    public static void criarPasta(String caminhoPai)
    {
        File arq = new File(caminhoPai);
        arq.mkdirs();
    }

    /**
     *
     * @param caminhoPai caminho ate a pasta do arq ex: /home/carlos-abp/NetBeansProjects/XDB/config
     * @param nomeDoArquivo nome e estencao ex: texto.txt
     */
    public static void criarArquivo(String caminhoPai, String nomeDoArquivo)
    {
        File arq = new File(caminhoPai + nomeDoArquivo);
        try {
            if (!arq.exists()) {
                new File(caminhoPai).mkdirs();
            }
            arq.createNewFile();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * excluirArquivo("/home/carlos","gmail.txt");
     *
     * @param caminhoPai caminho ate a pasta do arq ex: /home/carlos-abp/NetBeansProjects/XDB/config
     * @param nomeDoArquivoOuPasta nome da pasta ou arquivo que deseja excluir por campleto
     * @return se o metodo exclui ou nao o arq,dir
     */
    public static Boolean excluirArquivos(String caminhoPai, String nomeDoArquivoOuPasta)
    {
        File arq = new File(caminhoPai + "/" + nomeDoArquivoOuPasta);
        String[] filhos = arq.list();
        Map<Integer, String> arvoresDePastas = new TreeMap<>();

        if (!arq.exists()) {
            System.out.println("Erro 12510 : arqivo ou pasta inexistente");
        }
        else {
            if (arq.isFile()) {
                arq.delete();
            }
            else {
                for (int i = 0; i < filhos.length; i++) {
                    File pastaTemporaria = new File(arq + "/" + filhos[i]);
                    if (pastaTemporaria.isFile()) {
                        pastaTemporaria.delete();
                    }
                    else {

                        String[] temporario = pastaTemporaria.list();

                        if (temporario.length == 0) {
                            pastaTemporaria.delete();
                        }
                        else {
                            arvoresDePastas.put(i, pastaTemporaria.getPath());
                        }

                    }

                }

                for (Integer a : arvoresDePastas.keySet()) {
                    try {
                        EXcluirArquivos(arvoresDePastas.get(a));

                    }
                    catch (NullPointerException e) {
                    }
                }

            }

        }

        return arq.delete();
    }

    /**
     * Metodo que excluir as pastas dentro da pasta que deseja excluir
     *
     * @param caminhoPai Passa-se o caminho completo ex:"/home/eclipse/program" || "/home/eclipse/program.exe"
     */
    private static void EXcluirArquivos(String caminhoCompleto)
    {
        File arq = new File(new File(caminhoCompleto).getParent() + "/" + new File(caminhoCompleto).getName());
        System.out.println(arq);
        String[] filhos = arq.list();
        Map<Integer, String> arvoresDePastas = new TreeMap<>();

        for (int i = 0; i < filhos.length; i++) {
            File pastaTemporaria = new File(arq + "/" + filhos[i]);

            if (pastaTemporaria.isFile()) {
                System.out.println("del: " + pastaTemporaria.getPath());
                pastaTemporaria.delete();
            }
            else {

                String[] temporario = pastaTemporaria.list();

                if (temporario.length == 0) {
                    System.out.println("del: " + pastaTemporaria.getPath());
                    pastaTemporaria.delete();
                }
                else {
                    arvoresDePastas.put(i, pastaTemporaria.getPath());
                }

            }

        }

        for (Integer a : arvoresDePastas.keySet()) {
            System.out.println(arvoresDePastas.get(a));
            try {
                System.out.println(new File(arvoresDePastas.get(a)).getParent() + new File(arvoresDePastas.get(a)).getName());
                excluirArquivos(new File(arvoresDePastas.get(a)).getParent(), new File(arvoresDePastas.get(a)).getName());

            }
            catch (NullPointerException e) {

            }
        }
        System.out.println(arq.delete());

    }

}