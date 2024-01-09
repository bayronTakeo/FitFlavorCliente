/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * Excepción que se produce cuando codigo postal es invalido
 * @author Bayron
 */
public class InvalidCodeException extends Exception{
    public InvalidCodeException() { 
        super();
    }
    public InvalidCodeException(String msg) {
        super(msg);
    }
}
