package net.weg.gestao_produtos.Exceptions;

public class EmptyNameOrNullException extends RuntimeException{

    public EmptyNameOrNullException() {
        super("O nome que você está tentando cadastrar não pode ser vazio ou nulo.");
    }
}
