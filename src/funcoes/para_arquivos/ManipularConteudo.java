package funcoes.para_arquivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author carlos-abp classe responsavel pela manipulacao de conteudo intriseco ao arq.
 */
public class ManipularConteudo
{

    /**
     *
     * @param texto texto a introduzir no arquivo.
     * @param arq arquiva para a inscrisao.
     * @param adicionar se deseja acresentar ao texto original ou nao.
     */
	public static void escreverNoArquivo(String texto, File arq, Boolean adicionar)
    {
    	//Plano para nao duplicar dados a ser inserido no arquivo
    	if(VerificarOuAtualizar.arquivoExiste(arq.getAbsolutePath())) 
    	{
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arq, adicionar))) {
	            bw.append(String.format("%s%n", texto));
	        }
	        catch (IOException e) {
	            System.out.println(e.getMessage());
	        }
    	}
    	
    }

 
    /**
     * metodo que realiza a introducao de texto em uma linha bem expecifica
     *
     * @param texto texto a escrever na linha. 
     * @param arq arquiva para a inscrisao.
     * @param posicao a linha que deseja reeditar.  0 *nao feito
     */
    public static void escreverNoArquivo(String texto, File arq, int posicao)
    {
       
        Map<Integer, String> textoKM = new TreeMap<>(lerNoArquivo(arq));
        
                
        
        //para linha especifica
        
        if(posicao > 0)
        {
	        if(textoKM.size()  < posicao) 
	        {
	        	//intera o map onde as linhas do arquivo nem existem
	        	for(int i = textoKM.size()+1 ; i < posicao;i++) {
	        		
	        			textoKM.put(i,"");
	        	}
	        	
	        	textoKM.put(posicao, texto);
	        	ManipularConteudo.limparOArquivo(arq);
	        	
	        	for (Integer a : textoKM.keySet()) {
	        	   
	               ManipularConteudo.escreverNoArquivo(textoKM.get(a),arq, true);
	            }
	        }
	        
	        //escreve no arquivo se todas as linha antes da posicao existe
	        
	        if(textoKM.keySet().size() > posicao) 
	        {	
	        		textoKM.remove(posicao-1);
	        		textoKM.put(posicao-1, texto);
	        		ManipularConteudo.limparOArquivo(arq);
	        	
	               for (Integer a : textoKM.keySet()) {
	                   ManipularConteudo.escreverNoArquivo(textoKM.get(a),arq, true);
	               }
	            
	        }
        
        }
        //ele apenas adiciona no arquivo na proxima linha
        else {
        	ManipularConteudo.escreverNoArquivo(texto, arq, true);
        }
        
    
  }
            

        
        
    /**
     *
     * @param  arq Diretorio completo do arquivo para a inscrisao.
     * @param linha Linha que deseja ler
     * @return A leitura da linha desejada || null se der um erro
     */
    public static String lerNoArquivo(File arq, int linha)
    {
        if (VerificarOuAtualizar.arquivoExiste(arq.getAbsolutePath())) {

            try (BufferedReader br = new BufferedReader(new FileReader(arq))) {
                String texto = br.readLine();
                int contador = 1;

                while (texto != null) {
                    if (contador == linha) {
                        return texto;
                    }
                    else {
                        texto = br.readLine();
                        contador++;
                    }
                }
             
            }
            catch (IOException e) {
                System.out.println("Erro 12507: " + e.getMessage());
                return null;
            }
        }
        return null;
    }

    /**
     *
     * @param arq Diretorio completo do arquivo para a inscrisao.
     * @return linha todas mapeada devido o retono ser map e provem uma manipulacao mais facil do texto  || null se der erro
     */
    public static Map<Integer, String> lerNoArquivo(File arq)
    {
        if (VerificarOuAtualizar.arquivoExiste(arq.getAbsolutePath())) 
        {

            Map<Integer, String> textoKM = new TreeMap<>();

            try (BufferedReader br = new BufferedReader(new FileReader(arq))) {

                String texto = br.readLine();

                int contador = 0;

                while (texto != null) {
                	
                    textoKM.put(contador, texto);
                    contador++;
                    texto = br.readLine();

                }
                return textoKM;
            }
            catch (IOException e) {
                System.out.println("Erro 12520: " + e.getMessage());
            }
        }
        else {
            // codigo de erro personalizar
        }
        return null;
    }
    /**
    *
    * @param Arq Diretorio completo do arquivo.
    */
   public static void limparOArquivo(File Arq)
   {
   	if(VerificarOuAtualizar.arquivoExiste(Arq.getAbsolutePath())) {
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Arq))) {
	            bw.append("");
	        }
	        catch (IOException e) {
	            System.out.println(e.getMessage());
	        }
   	}
   }

}