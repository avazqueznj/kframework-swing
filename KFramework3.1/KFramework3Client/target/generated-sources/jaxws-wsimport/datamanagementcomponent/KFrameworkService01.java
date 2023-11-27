
package datamanagementcomponent;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.3-b02-
 * Generated source version: 2.1
 * 
 */
@WebService(name = "KFrameworkService01", targetNamespace = "http://DataManagementComponent/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface KFrameworkService01 {


    /**
     * 
     * @param receivedCmd
     * @return
     *     returns datamanagementcomponent.ResultSetClass
     */
    @WebMethod(operationName = "SQLRequest")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "SQLRequest", targetNamespace = "http://DataManagementComponent/", className = "datamanagementcomponent.SQLRequest")
    @ResponseWrapper(localName = "SQLRequestResponse", targetNamespace = "http://DataManagementComponent/", className = "datamanagementcomponent.SQLRequestResponse")
    public ResultSetClass sqlRequest(
        @WebParam(name = "ReceivedCmd", targetNamespace = "")
        SQLCmdClass receivedCmd);

    /**
     * 
     * @param messageReceived
     * @return
     *     returns datamanagementcomponent.ObjectMessageClass
     */
    @WebMethod(operationName = "ObjectRequest")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "ObjectRequest", targetNamespace = "http://DataManagementComponent/", className = "datamanagementcomponent.ObjectRequest")
    @ResponseWrapper(localName = "ObjectRequestResponse", targetNamespace = "http://DataManagementComponent/", className = "datamanagementcomponent.ObjectRequestResponse")
    public ObjectMessageClass objectRequest(
        @WebParam(name = "messageReceived", targetNamespace = "")
        ObjectMessageClass messageReceived);

}
