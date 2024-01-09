/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * Excepción que se produce cuando la contraseña es invalida.
 *
 * @author Bayron
 */
public class InvalidPasswordException extends Exception {

    public InvalidPasswordException() {
        super();
    }

    public InvalidPasswordException(String msg) {
        super(msg);
    }
}
