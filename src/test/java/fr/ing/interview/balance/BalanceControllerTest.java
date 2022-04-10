package fr.ing.interview.balance;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BalanceControllerTest {

    @MockBean
    private BalanceService consultingService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void valid_AccountNumber_return_200() throws Exception {
        // Setup
        String invalidAccountNumber = "01234567891";
        when(consultingService.retrieveBalance(invalidAccountNumber)).thenReturn(new BigDecimal("100"));

        //Test & Assert
        mockMvc.perform(get("/balance/" + invalidAccountNumber)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void invalid_AccountNumber_return_400() throws Exception {
        // Setup
        String invalidAccountNumber = "0123";
        when(consultingService.retrieveBalance(invalidAccountNumber)).thenReturn(new BigDecimal("100"));

        //Test & Assert
        mockMvc.perform(get("/balance/" + invalidAccountNumber)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    }

}
