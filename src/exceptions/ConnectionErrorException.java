/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * Excepción que se produce cuando el lado del servidor no puede conectarse a la
 * base de datos
 *
 * @author Bayron
 */
public class ConnectionErrorException extends Exception {

    public ConnectionErrorException() {
        super();
    }

    public ConnectionErrorException(String msg) {
        super(msg);
    }
}
