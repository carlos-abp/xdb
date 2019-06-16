package funcoes.para_xdb;

import funcoes.para_arquivos.ManipularArquivo;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author carlos-abp
 */
public class ManipularXDB
{

    private String nomeDoXDB;
    private File caminhoDaPasta;
    private File caminhoCompleto;

    public ManipularXDB()
    {
        
    }

    public ManipularXDB(File caminho, String nomeDoXDB)
    {
        this.nomeDoXDB = nomeDoXDB;
        this.caminhoDaPasta = caminho;
    }
//----------------------------------------------------------------------------------------------------------

    /**
     *
     * cria-se uma base de dados XDB
     *@return criado ou nao criado
     */
    public Boolean criarBaseDeDados()
    {
        if(nomeDoXDB == null || nomeDoXDB.isEmpty())
        {
        	 System.out.println("Erro 12549 nome do banco nao atribuido.");
        	 return false;
        }
        if(caminhoDaPasta == null || caminhoDaPasta.toString().isEmpty())
        {
        	 System.out.println("Erro 12549 diretorio do banco nao atribuido.");
        	 return false;
        }
        else 
        {
        	
        	caminhoCompleto = new File(caminhoDaPasta + "/"+nomeDoXDB); 
        	
        	System.out.println(caminhoCompleto.getAbsolutePath());
        	
	        if (!caminhoCompleto.exists()) {
	            ManipularArquivo.criarPasta(caminhoCompleto.getAbsolutePath());
	            ManipularArquivo.criarArquivo(caminhoCompleto.getAbsolutePath()+"/", nomeDoXDB + ".xdb");
	            ManipularArquivo.criarArquivo(caminhoCompleto.getAbsolutePath()+"/", ".configuracao");
	        }
	        else {
	            System.out.println("Base de dados XDB ja existente");
	        }
	        return true;
        }
    }

    
    public Boolean excluirBaseDeDados()
    {
    	return ManipularArquivo.excluirArquivos(caminhoDaPasta.getPath(), nomeDoXDB);   
    }

    
    public void renomearBaseDeDados(String novoNomeDoXDB)
    {
    	
    	if(!novoNomeDoXDB.isEmpty() || novoNomeDoXDB != null) 
    	{
    		new File(caminhoDaPasta+"/"+nomeDoXDB).renameTo(new File(caminhoDaPasta+"/"+novoNomeDoXDB));
        	
        	caminhoCompleto = new File(caminhoDaPasta+"/"+novoNomeDoXDB);
        	
        	new File(caminhoCompleto+"/"+nomeDoXDB+".xdb").renameTo(new File(caminhoCompleto+"/"+novoNomeDoXDB+".xdb"));
        	
    	}      
    
    }

//_______________________________________________________________________________________________________
    public void setNomeDoXDB(String novoNomeDoXDB)
    {
        this.nomeDoXDB = novoNomeDoXDB;
    }

    public String getNomeDoXDB()
    {
        return nomeDoXDB;
    }

    public void setCaminhoDoXDB(File novoCaminhoDoXDB)
    {
        this.caminhoDaPasta = novoCaminhoDoXDB;
    }

    public File getCaminhoDoXDB()
    {
        return caminhoDaPasta;
    }
}