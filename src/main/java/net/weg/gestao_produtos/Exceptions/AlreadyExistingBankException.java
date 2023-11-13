package net.weg.gestao_produtos.Exceptions;

public class AlreadyExistingBankException extends Exception{

    public AlreadyExistingBankException(String item) {
        super("Já existe um(a)" + item + " que você está tentando cadastrar no banco de dados.");
    }
}
