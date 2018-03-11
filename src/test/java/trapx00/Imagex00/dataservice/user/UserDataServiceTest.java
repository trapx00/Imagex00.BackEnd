package trapx00.Imagex00.dataservice.user;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import trapx00.Imagex00.entity.user.User;
import trapx00.Imagex00.vo.ResultMessage;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDataServiceTest {
    @Autowired
    private UserDataService userDataService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void isTheUserExists() {
        assertEquals(true, userDataService.isTheUserExists("123"));
    }

    @Test
    public void saveUser() {
        User user = new User("123", "123");
        assertEquals(ResultMessage.Success, userDataService.saveUser(user));
    }
}