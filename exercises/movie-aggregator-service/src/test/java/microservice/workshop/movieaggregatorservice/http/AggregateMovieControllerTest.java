package microservice.workshop.movieaggregatorservice.http;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AggregateMovieControllerTest {

    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    public void testExists() throws Exception {
        mockMvc.perform(get("/movie/1"))
        .andExpect(status().is(HttpStatus.OK.value()))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.title", is("The Godfather")))
        .andExpect(jsonPath("$.awards", hasSize(3)))
        .andExpect(jsonPath("$.castMembers", hasSize(6)));
    }

    @Test
    public void testNotExists() throws Exception {
        mockMvc.perform(get("/movie/21"))
        .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }
}
