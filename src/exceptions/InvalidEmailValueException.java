/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * Excepción que se produce cuando el email es invalido.
 *
 * @author Bayron
 */
public class InvalidEmailValueException extends Exception {

    public InvalidEmailValueException() {
        super();
    }

    public InvalidEmailValueException(String msg) {
        super(msg);
    }
}
