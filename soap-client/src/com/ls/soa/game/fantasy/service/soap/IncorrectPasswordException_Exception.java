
package com.ls.soa.game.fantasy.service.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 */
@WebFault(name = "IncorrectPasswordException", targetNamespace = "http://soap.service.fantasy.game.soa.ls.com/")
public class IncorrectPasswordException_Exception
        extends Exception {

    /**
     * Java type that goes as soapenv:Fault detail element.
     */
    private IncorrectPasswordException faultInfo;

    /**
     * @param faultInfo
     * @param message
     */
    public IncorrectPasswordException_Exception(String message, IncorrectPasswordException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * @param faultInfo
     * @param cause
     * @param message
     */
    public IncorrectPasswordException_Exception(String message, IncorrectPasswordException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * @return returns fault bean: com.ls.soa.game.fantasy.service.soap.IncorrectPasswordException
     */
    public IncorrectPasswordException getFaultInfo() {
        return faultInfo;
    }

}
