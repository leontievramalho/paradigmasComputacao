package services;

import excecoes.EmptyListException;
import excecoes.InvalidAttributeException;
import excecoes.MissingFolderException;
import interfaces.IBalanca;
import models.Produto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ToledoMGV6 implements IBalanca<Produto> {

    @Override
    public void exportar(List<Produto> produtos, String pastaArquivoTxt) {
        try{
            if(produtos.isEmpty()){
                throw new EmptyListException("Lista de produtos vazia.");
            }
        File directory = new File(pastaArquivoTxt);
            if(!directory.isDirectory()){
                throw new MissingFolderException("Diretório não encontrado no caminho especificado");
            }
        }catch (EmptyListException ele){
            System.err.println("Erro de lista vazia: " + ele.getMessage());
        }
        catch (MissingFolderException mfe) {
            System.err.println("Erro de diretório: " + mfe.getMessage());
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pastaArquivoTxt + "/ITENSMGV.TXT"))) {
            for (Produto produto : produtos) {
                String linha = formatarProduto(produto);
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException ioe) {
            System.err.println("Erro de arquivo: " + ioe.getMessage());
        }
    }

    private String formatarProduto(Produto produto) {
        try {
            String dept = "01";
            String tipo = "0";
            String codigo = String.format("%06d", produto.getCodigo());
            String preco = String.format("%06d", (int) (produto.getValor() * 100));
            String descricao = String.format("%-50s", produto.getDescricao());
            if (produto.getCodigo() == 0) {
                throw new InvalidAttributeException("Código não pode ser 0");
            } else if (produto.getValor() <= 0) {
                throw new InvalidAttributeException("Preço deve ser maior que 0");
            }

            String linha = dept + tipo + codigo + preco + "000" + descricao +
                    "0000000000|01|                                                                      0000000000000000000000000|0000|0||";

            return linha;
        }catch (NullPointerException npe){
            System.err.println("Erro de objeto nulo: " + npe.getMessage());
            return "";
        }catch (InvalidAttributeException iae){
            System.err.println("Erro de atributo inválido: " + iae.getMessage());
            return "";
        }
    }
}
