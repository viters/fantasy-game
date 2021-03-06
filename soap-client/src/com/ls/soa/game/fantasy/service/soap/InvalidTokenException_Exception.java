
package com.ls.soa.game.fantasy.service.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 */
@WebFault(name = "InvalidTokenException", targetNamespace = "http://soap.service.fantasy.game.soa.ls.com/")
public class InvalidTokenException_Exception
        extends Exception {

    /**
     * Java type that goes as soapenv:Fault detail element.
     */
    private InvalidTokenException faultInfo;

    /**
     * @param faultInfo
     * @param message
     */
    public InvalidTokenException_Exception(String message, InvalidTokenException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * @param faultInfo
     * @param cause
     * @param message
     */
    public InvalidTokenException_Exception(String message, InvalidTokenException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * @return returns fault bean: com.ls.soa.game.fantasy.service.soap.InvalidTokenException
     */
    public InvalidTokenException getFaultInfo() {
        return faultInfo;
    }

}
