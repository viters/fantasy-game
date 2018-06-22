package client;

import com.google.common.hash.Hashing;
import com.ls.soa.game.fantasy.service.soap.*;

import java.nio.charset.StandardCharsets;

public class App {
    public static void main(String[] args) throws UserNotFoundException_Exception, IncorrectPasswordException_Exception, com.ls.soa.game.fantasy.service.soap.InsufficientPermissionsException_Exception, InvalidTokenException_Exception, com.ls.soa.game.fantasy.service.soap.CategoryDictionaryNotFoundException_Exception, ElementNotFoundException_Exception {
        AuthServiceImplService authServiceImplService = new AuthServiceImplService();
        AuthService authService = authServiceImplService.getAuthServiceImplPort();

        String login = "admin";
        String password = Hashing.sha256()
                .hashString("admin", StandardCharsets.UTF_8)
                .toString();

        String token = authService.login(login, password);

//        createCategoryDictionary(token);
//        updateFirstElement(token);
    }

    private static void createCategoryDictionary(String token) throws InsufficientPermissionsException_Exception, InvalidTokenException_Exception {
        CategoryDictionaryServiceImplService categoryDictionaryServiceImplService =
                new CategoryDictionaryServiceImplService();
        CategoryDictionaryService categoryDictionaryService =
                categoryDictionaryServiceImplService.getCategoryDictionaryServiceImplPort();

        CategoryDictionaryDTO categoryDictionaryDTO = new CategoryDictionaryDTO();
        categoryDictionaryDTO.setCategoryName("SOAPKategoria");
        categoryDictionaryDTO.setCategoryParam1Name("Prędkość");
        categoryDictionaryDTO.setElementName("WSDL");
        categoryDictionaryDTO.setElementParam1Name("Serwis");
        categoryDictionaryDTO.setElementParam2Name("Ilość metod");
        categoryDictionaryDTO.setElementParam3Name("Schemat");
        categoryDictionaryDTO.setElementParam4Name("Wersja");
        categoryDictionaryService.create(token, categoryDictionaryDTO);
    }

    private static void updateFirstElement(String token) throws CategoryDictionaryNotFoundException_Exception, ElementNotFoundException_Exception, InsufficientPermissionsException_Exception, InvalidTokenException_Exception, UserNotFoundException_Exception {
        ElementServiceImplService elementServiceImplService = new ElementServiceImplService();
        ElementService elementService = elementServiceImplService.getElementServiceImplPort();

        ElementDTO elementDTO = new ElementDTO();
        elementDTO.setId(1);
        elementDTO.setParam1("soap_tu_był");
        elementService.update(token, elementDTO);
    }
}
