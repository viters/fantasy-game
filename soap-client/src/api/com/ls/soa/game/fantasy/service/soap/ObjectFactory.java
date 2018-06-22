
package com.ls.soa.game.fantasy.service.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.ls.soa.game.fantasy.service.soap package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _InvalidTokenException_QNAME = new QName("http://soap.service.fantasy.game.soa.ls.com/", "InvalidTokenException");
    private final static QName _UserNotFoundException_QNAME = new QName("http://soap.service.fantasy.game.soa.ls.com/", "UserNotFoundException");
    private final static QName _CategoryDictionaryNotFoundException_QNAME = new QName("http://soap.service.fantasy.game.soa.ls.com/", "CategoryDictionaryNotFoundException");
    private final static QName _InsufficientPermissionsException_QNAME = new QName("http://soap.service.fantasy.game.soa.ls.com/", "InsufficientPermissionsException");
    private final static QName _UpdateResponse_QNAME = new QName("http://soap.service.fantasy.game.soa.ls.com/", "updateResponse");
    private final static QName _Update_QNAME = new QName("http://soap.service.fantasy.game.soa.ls.com/", "update");
    private final static QName _ElementNotFoundException_QNAME = new QName("http://soap.service.fantasy.game.soa.ls.com/", "ElementNotFoundException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ls.soa.game.fantasy.service.soap
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UserNotFoundException }
     */
    public UserNotFoundException createUserNotFoundException() {
        return new UserNotFoundException();
    }

    /**
     * Create an instance of {@link InvalidTokenException }
     */
    public InvalidTokenException createInvalidTokenException() {
        return new InvalidTokenException();
    }

    /**
     * Create an instance of {@link CategoryDictionaryNotFoundException }
     */
    public CategoryDictionaryNotFoundException createCategoryDictionaryNotFoundException() {
        return new CategoryDictionaryNotFoundException();
    }

    /**
     * Create an instance of {@link Update }
     */
    public Update createUpdate() {
        return new Update();
    }

    /**
     * Create an instance of {@link ElementNotFoundException }
     */
    public ElementNotFoundException createElementNotFoundException() {
        return new ElementNotFoundException();
    }

    /**
     * Create an instance of {@link InsufficientPermissionsException }
     */
    public InsufficientPermissionsException createInsufficientPermissionsException() {
        return new InsufficientPermissionsException();
    }

    /**
     * Create an instance of {@link UpdateResponse }
     */
    public UpdateResponse createUpdateResponse() {
        return new UpdateResponse();
    }

    /**
     * Create an instance of {@link ElementDTO }
     */
    public ElementDTO createElementDTO() {
        return new ElementDTO();
    }

    /**
     * Create an instance of {@link CategoryDTO }
     */
    public CategoryDTO createCategoryDTO() {
        return new CategoryDTO();
    }

    /**
     * Create an instance of {@link UserDTO }
     */
    public UserDTO createUserDTO() {
        return new UserDTO();
    }

    /**
     * Create an instance of {@link CategoryDictionaryDTO }
     */
    public CategoryDictionaryDTO createCategoryDictionaryDTO() {
        return new CategoryDictionaryDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidTokenException }{@code >}}
     */
    @XmlElementDecl(namespace = "http://soap.service.fantasy.game.soa.ls.com/", name = "InvalidTokenException")
    public JAXBElement<InvalidTokenException> createInvalidTokenException(InvalidTokenException value) {
        return new JAXBElement<InvalidTokenException>(_InvalidTokenException_QNAME, InvalidTokenException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserNotFoundException }{@code >}}
     */
    @XmlElementDecl(namespace = "http://soap.service.fantasy.game.soa.ls.com/", name = "UserNotFoundException")
    public JAXBElement<UserNotFoundException> createUserNotFoundException(UserNotFoundException value) {
        return new JAXBElement<UserNotFoundException>(_UserNotFoundException_QNAME, UserNotFoundException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CategoryDictionaryNotFoundException }{@code >}}
     */
    @XmlElementDecl(namespace = "http://soap.service.fantasy.game.soa.ls.com/", name = "CategoryDictionaryNotFoundException")
    public JAXBElement<CategoryDictionaryNotFoundException> createCategoryDictionaryNotFoundException(CategoryDictionaryNotFoundException value) {
        return new JAXBElement<CategoryDictionaryNotFoundException>(_CategoryDictionaryNotFoundException_QNAME, CategoryDictionaryNotFoundException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsufficientPermissionsException }{@code >}}
     */
    @XmlElementDecl(namespace = "http://soap.service.fantasy.game.soa.ls.com/", name = "InsufficientPermissionsException")
    public JAXBElement<InsufficientPermissionsException> createInsufficientPermissionsException(InsufficientPermissionsException value) {
        return new JAXBElement<InsufficientPermissionsException>(_InsufficientPermissionsException_QNAME, InsufficientPermissionsException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://soap.service.fantasy.game.soa.ls.com/", name = "updateResponse")
    public JAXBElement<UpdateResponse> createUpdateResponse(UpdateResponse value) {
        return new JAXBElement<UpdateResponse>(_UpdateResponse_QNAME, UpdateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Update }{@code >}}
     */
    @XmlElementDecl(namespace = "http://soap.service.fantasy.game.soa.ls.com/", name = "update")
    public JAXBElement<Update> createUpdate(Update value) {
        return new JAXBElement<Update>(_Update_QNAME, Update.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ElementNotFoundException }{@code >}}
     */
    @XmlElementDecl(namespace = "http://soap.service.fantasy.game.soa.ls.com/", name = "ElementNotFoundException")
    public JAXBElement<ElementNotFoundException> createElementNotFoundException(ElementNotFoundException value) {
        return new JAXBElement<ElementNotFoundException>(_ElementNotFoundException_QNAME, ElementNotFoundException.class, null, value);
    }

}
