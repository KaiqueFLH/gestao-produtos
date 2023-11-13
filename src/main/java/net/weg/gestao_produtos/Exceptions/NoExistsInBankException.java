package net.weg.gestao_produtos.Exceptions;

public class NoExistsInBankException extends Exception{
    public NoExistsInBankException(String item) {
        super("Não existe um(a)" + item + " com esse ID no banco de dados.");
    }

    public NoExistsInBankException(Integer id) {
        super("Este item com o ID " + id + " não existe! Tente novamente com outro ID.");
    }

}
