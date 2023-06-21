package test;

import model.Credential;
import model.CredentialDAO;
import org.junit.Assert;
import org.junit.Test;

public class CredentialDAOTest {
    private Credential login(String username, String password) {
        Credential formSubmitted = new Credential();
        formSubmitted.setUsername(username);
        formSubmitted.setPassword(password);
        Credential credential = CredentialDAO.login(formSubmitted);
        return credential;
        //Assert.assertEquals(credential.getUsername(), formSubmitted.getUsername());
        //Assert.assertEquals(credential.getPassword(), formSubmitted.getPassword());
    }

    @Test
    public void testLoginAssertSuccess() {
        Credential credential = login("ADMIN", "ADMIN");
        Assert.assertNotNull(credential);
        Assert.assertEquals(credential.getUsername(), "ADMIN");
        Assert.assertEquals(credential.getPassword(), "ADMIN");
    }

    @Test
    public void testLoginAssertFailure() {
        Credential credential = login("123", "123");
        Assert.assertNull(credential);
    }
}
