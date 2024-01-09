/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * Excepción que se produce cuando se alcanza el numero maximo de conexiones.
 *
 * @author Bayron
 */
public class MaxConnectionException extends Exception {

    public MaxConnectionException() {
        super();
    }

    public MaxConnectionException(String msg) {
        super(msg);
    }
}
