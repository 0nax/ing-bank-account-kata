package fr.ing.interview.transaction.operations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class OperationsControllerTest {

    @MockBean
    private OperationService operationService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void withdraw_succesful() throws Exception {
        // Setup
        OperationDTO operationDTO = new OperationDTO();
        operationDTO.setAccountNumber("01234567891");
        operationDTO.setAmount(10);
        doNothing().when(operationService).withdraw(operationDTO);

        // Test & Assert
        mockMvc.perform(post("/operation/withdraw")
                .content(asJsonString(operationDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void withdraw_invalid_amount() throws Exception {
        // Setup
        OperationDTO operationDTO = new OperationDTO();
        operationDTO.setAccountNumber("01234567891");
        operationDTO.setAmount(0);
        doNothing().when(operationService).withdraw(operationDTO);

        // Test & Assert
        mockMvc.perform(post("/operation/withdraw")
                .content(asJsonString(operationDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deposit_succesful() throws Exception {
        // Setup
        OperationDTO operationDTO = new OperationDTO();
        operationDTO.setAccountNumber("01234567891");
        operationDTO.setAmount(10);
        doNothing().when(operationService).deposit(operationDTO);

        // Test & Assert
        mockMvc.perform(post("/operation/withdraw")
                .content(asJsonString(operationDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
