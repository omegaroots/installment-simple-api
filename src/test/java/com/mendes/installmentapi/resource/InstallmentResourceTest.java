package com.mendes.installmentapi.resource;

import com.mendes.installmentapi.model.Installment;
import com.mendes.installmentapi.service.InstallmentService;
import com.mendes.installmentapi.service.ResourceNotFoundException;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

@ExtendWith({SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class InstallmentResourceTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @MockBean
    private InstallmentService service;

    private HttpHeaders headers = new HttpHeaders();

    @Test
    public void Should_Return_a_Json_Showing_an_Installment_Object()
            throws JSONException {

        Mockito.when(service.retrieveInstallment(Mockito.eq(1L))).thenReturn(new Installment(1L, "13-18", new BigDecimal("99.6"), "description"));

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/installment/1"),
                HttpMethod.GET, entity, String.class);

        String expected = """
                {"id":1,"range":"13-18","amount":99.6, "chargeDescription":"description"}
               """;

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void Should_Return_404_for_a_non_existent_installment() {

        Mockito.when(service.retrieveInstallment(Mockito.eq(1L))).thenThrow(new ResourceNotFoundException());

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/installment/1"),
                HttpMethod.GET, entity, String.class);

        String expected = """
                {"id":1,"range":"FOUR"","amount":99.6, "chargeDescription":"description"}
               """;

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void Should_Save_a_new_resource() {

        Installment installment = new Installment(1L, "7-12", new BigDecimal("99.6"), "description");

        Mockito.when(service.retrieveInstallment(Mockito.eq(1L))).thenReturn(installment);

        HttpEntity<Installment> requestEntity = new HttpEntity<>(installment, headers);

        ResponseEntity<Installment> response = restTemplate.postForEntity(
                createURLWithPort("/api/installment"),
                requestEntity, Installment.class);

        Mockito.verify(service, Mockito.times(1)).create(Mockito.eq(installment));
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
