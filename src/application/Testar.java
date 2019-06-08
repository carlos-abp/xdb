package application;


import java.io.File;



import funcoes.para_conteudo.ManipularConteudo;


public class Testar
{

	public static void main(String[ ] args)
	{
		// Manipular.criarArquivo("/home/carlos-abp/teste/","teste.txt");

		// Manipular.apagarArquivo("/home/carlos-abp/teste/","teste.txt");
		File arq = new File("/home/carlos-abp/teste/teste.txt");

		//ManipularConteudo.escreverNoArquivo("Carla,27", arq, true);
		
		ManipularConteudo.limparOArquivo(arq);
		

	}

}
