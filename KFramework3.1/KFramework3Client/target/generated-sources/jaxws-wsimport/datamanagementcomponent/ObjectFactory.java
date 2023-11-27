
package datamanagementcomponent;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the datamanagementcomponent package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ObjectRequestResponse_QNAME = new QName("http://DataManagementComponent/", "ObjectRequestResponse");
    private final static QName _ObjectRequest_QNAME = new QName("http://DataManagementComponent/", "ObjectRequest");
    private final static QName _SQLRequestResponse_QNAME = new QName("http://DataManagementComponent/", "SQLRequestResponse");
    private final static QName _SQLRequest_QNAME = new QName("http://DataManagementComponent/", "SQLRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: datamanagementcomponent
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ObjectMessageClass }
     * 
     */
    public ObjectMessageClass createObjectMessageClass() {
        return new ObjectMessageClass();
    }

    /**
     * Create an instance of {@link SQLRequest }
     * 
     */
    public SQLRequest createSQLRequest() {
        return new SQLRequest();
    }

    /**
     * Create an instance of {@link FieldClass }
     * 
     */
    public FieldClass createFieldClass() {
        return new FieldClass();
    }

    /**
     * Create an instance of {@link ObjectRequestResponse }
     * 
     */
    public ObjectRequestResponse createObjectRequestResponse() {
        return new ObjectRequestResponse();
    }

    /**
     * Create an instance of {@link ResultSetClass }
     * 
     */
    public ResultSetClass createResultSetClass() {
        return new ResultSetClass();
    }

    /**
     * Create an instance of {@link RecordClass }
     * 
     */
    public RecordClass createRecordClass() {
        return new RecordClass();
    }

    /**
     * Create an instance of {@link SQLRequestResponse }
     * 
     */
    public SQLRequestResponse createSQLRequestResponse() {
        return new SQLRequestResponse();
    }

    /**
     * Create an instance of {@link SQLCmdClass }
     * 
     */
    public SQLCmdClass createSQLCmdClass() {
        return new SQLCmdClass();
    }

    /**
     * Create an instance of {@link ObjectRequest }
     * 
     */
    public ObjectRequest createObjectRequest() {
        return new ObjectRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObjectRequestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://DataManagementComponent/", name = "ObjectRequestResponse")
    public JAXBElement<ObjectRequestResponse> createObjectRequestResponse(ObjectRequestResponse value) {
        return new JAXBElement<ObjectRequestResponse>(_ObjectRequestResponse_QNAME, ObjectRequestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObjectRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://DataManagementComponent/", name = "ObjectRequest")
    public JAXBElement<ObjectRequest> createObjectRequest(ObjectRequest value) {
        return new JAXBElement<ObjectRequest>(_ObjectRequest_QNAME, ObjectRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SQLRequestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://DataManagementComponent/", name = "SQLRequestResponse")
    public JAXBElement<SQLRequestResponse> createSQLRequestResponse(SQLRequestResponse value) {
        return new JAXBElement<SQLRequestResponse>(_SQLRequestResponse_QNAME, SQLRequestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SQLRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://DataManagementComponent/", name = "SQLRequest")
    public JAXBElement<SQLRequest> createSQLRequest(SQLRequest value) {
        return new JAXBElement<SQLRequest>(_SQLRequest_QNAME, SQLRequest.class, null, value);
    }

}
