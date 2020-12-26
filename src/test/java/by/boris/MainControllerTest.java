package by.boris;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("biba")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql", "/comments-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/comments-list-after.sql", "/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Checking the main page")
    public void mainPageTest() throws Exception {
        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='navbarSupportedContent']/div").string("biba"));
    }

    @Test
    @DisplayName("Check if the list of comments is displayed correctly")
    public void commentsListTest() throws Exception {
        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='comments-list']/div").nodeCount(4));
    }

    @Test
    @DisplayName("Checking the filter")
    public void filterCommentsTest() throws Exception {
        this.mockMvc.perform(get("/main").param("filter", "China"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='comments-list']/div").nodeCount(2))
                .andExpect(xpath("//div[@id='comments-list']/div[@data-id=3]").exists())
                .andExpect(xpath("//div[@id='comments-list']/div[@data-id=4]").exists());
    }

    @Test
    @DisplayName("Check adding comments")
    public void addCommentToListTest() throws Exception {
        MockHttpServletRequestBuilder multipart = MockMvcRequestBuilders.multipart("/main")
                .param("user", "1")
                .param("text", "text about Greece")
                .param("country", "Greece")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='comments-list']/div").nodeCount(5))
                .andExpect(xpath("//div[@id='comments-list']/div[@data-id=7]").exists())
                .andExpect(xpath("//div[@id='comments-list']/div[@data-id=7]/div/b/i")
                        .string("Greece"))
                .andExpect(xpath("//div[@id='comments-list']/div[@data-id=7]/div/blockquote/p/i")
                        .string("text about Greece"));
    }
}
