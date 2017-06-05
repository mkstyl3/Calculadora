package dsa.eetac.upc.edu.calculadora.Controller;

import dsa.eetac.upc.edu.calculadora.Model.Operation;

/**
 * Created by root on 25/05/17.
 */

public class Brain {
    private static Brain instance = null;

    public static Brain getInstance() {
        if (instance == null) instance = new Brain();
        return instance;
    }

    public String getResultInString(Operation operation) {
        Integer result = 0;
        final String e = "ERR";
        switch (operation.getType()) {
            case 0:
                result = operation.getFirst()+operation.getSecond();
                break;
            case 1:
                result = operation.getFirst()-operation.getSecond();
                break;
            case 2:
                result = operation.getFirst()*operation.getSecond();
                break;
            case 3:
                if(operation.getSecond()!=0)
                result = operation.getFirst()/operation.getSecond();
                break;
        }
        if(operation.getSecond()==0)
            return e;
        else
            return result.toString();
    }
}
