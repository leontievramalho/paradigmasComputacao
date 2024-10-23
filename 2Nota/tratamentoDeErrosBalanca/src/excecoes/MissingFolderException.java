package excecoes;

public class MissingFolderException extends Exception{
    public MissingFolderException(String mensagem){
        super(mensagem);
    }
}
