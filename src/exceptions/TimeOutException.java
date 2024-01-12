/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * Excepción que se produce cuando se agota el tiempo de espera.
 *
 * @author Bayron
 */
public class TimeOutException extends Exception {

    public TimeOutException() {
        super();
    }

    public TimeOutException(String msg) {
        super(msg);
    }
}
