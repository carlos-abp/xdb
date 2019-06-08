package funcoes.para_arquivos;


import java.io.File;
import java.io.IOException;


public class ManipularArquivo
{
	public static void criarArquivo(String caminhoPai, String nomeDoArquivo)
	{
		File arq = new File(caminhoPai + nomeDoArquivo);
		try
		{
			if (!arq.exists())
			{
				new File(caminhoPai).mkdirs();
			}
			arq.createNewFile();
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public static void apagarArquivo(String caminhoPai, String nomeDoArquivo)
	{
		File arq = new File(caminhoPai + nomeDoArquivo);
		arq.deleteOnExit();

	}
}
