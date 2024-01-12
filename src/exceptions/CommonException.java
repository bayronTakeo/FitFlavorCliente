/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * Excepciones comunes.
 *
 * @author bayro
 */
public class CommonException extends Exception {

    public CommonException(String type) {
        super(getMessageForType(type));
    }

    private static String getMessageForType(String type) {
        switch (type) {
            case "empty":
                return "This field can´t be blank";
            case "spaces":
                return "This field can´t contains blank spaces";
            case "symbols":
                return "This field cannot contain special characters";
            case "numbers":
                return "This field cannot contain numbers";
            case "letters":
                return "This field cannot contain letters";
            case "data":
                return "Some data is wrong";
            default:
                return "Something has gone wrong";
        }
    }
}
