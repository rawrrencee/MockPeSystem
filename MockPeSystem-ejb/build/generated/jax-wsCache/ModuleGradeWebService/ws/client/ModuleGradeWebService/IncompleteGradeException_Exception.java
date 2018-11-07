
package ws.client.ModuleGradeWebService;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.11-b150120.1832
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "IncompleteGradeException", targetNamespace = "http://ws.session.ejb/")
public class IncompleteGradeException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private IncompleteGradeException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public IncompleteGradeException_Exception(String message, IncompleteGradeException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public IncompleteGradeException_Exception(String message, IncompleteGradeException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: ws.client.ModuleGradeWebService.IncompleteGradeException
     */
    public IncompleteGradeException getFaultInfo() {
        return faultInfo;
    }

}
