/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * Excepción que se produce cuando ya existe el usuario en la base de datos.
 *
 * @author Bayron
 */
public class UserExistException extends Exception {

    public UserExistException() {
        super();
    }

    public UserExistException(String msg) {
        super(msg);
    }
}
