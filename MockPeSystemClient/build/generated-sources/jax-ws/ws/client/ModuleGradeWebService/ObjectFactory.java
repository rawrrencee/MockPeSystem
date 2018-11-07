
package ws.client.ModuleGradeWebService;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws.client.ModuleGradeWebService package. 
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

    private final static QName _IncompleteGradeException_QNAME = new QName("http://ws.session.ejb/", "IncompleteGradeException");
    private final static QName _RetrieveStudentModuleGrade_QNAME = new QName("http://ws.session.ejb/", "retrieveStudentModuleGrade");
    private final static QName _RetrieveStudentModuleGradeResponse_QNAME = new QName("http://ws.session.ejb/", "retrieveStudentModuleGradeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.client.ModuleGradeWebService
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IncompleteGradeException }
     * 
     */
    public IncompleteGradeException createIncompleteGradeException() {
        return new IncompleteGradeException();
    }

    /**
     * Create an instance of {@link RetrieveStudentModuleGrade }
     * 
     */
    public RetrieveStudentModuleGrade createRetrieveStudentModuleGrade() {
        return new RetrieveStudentModuleGrade();
    }

    /**
     * Create an instance of {@link RetrieveStudentModuleGradeResponse }
     * 
     */
    public RetrieveStudentModuleGradeResponse createRetrieveStudentModuleGradeResponse() {
        return new RetrieveStudentModuleGradeResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IncompleteGradeException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "IncompleteGradeException")
    public JAXBElement<IncompleteGradeException> createIncompleteGradeException(IncompleteGradeException value) {
        return new JAXBElement<IncompleteGradeException>(_IncompleteGradeException_QNAME, IncompleteGradeException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveStudentModuleGrade }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveStudentModuleGrade")
    public JAXBElement<RetrieveStudentModuleGrade> createRetrieveStudentModuleGrade(RetrieveStudentModuleGrade value) {
        return new JAXBElement<RetrieveStudentModuleGrade>(_RetrieveStudentModuleGrade_QNAME, RetrieveStudentModuleGrade.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveStudentModuleGradeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveStudentModuleGradeResponse")
    public JAXBElement<RetrieveStudentModuleGradeResponse> createRetrieveStudentModuleGradeResponse(RetrieveStudentModuleGradeResponse value) {
        return new JAXBElement<RetrieveStudentModuleGradeResponse>(_RetrieveStudentModuleGradeResponse_QNAME, RetrieveStudentModuleGradeResponse.class, null, value);
    }

}
