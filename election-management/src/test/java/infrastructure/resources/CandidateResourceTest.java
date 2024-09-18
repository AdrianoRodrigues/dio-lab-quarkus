package infrastructure.resources;

import api.CandidateApi;
import api.dto.in.CreateCandidate;
import api.dto.in.UpdateCandidate;
import domain.Candidate;
import io.quarkus.test.InjectMock;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.instancio.Instancio;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
@TestHTTPEndpoint(CandidateResource.class)
class CandidateResourceTest {
    @InjectMock
    CandidateApi candidateApi;

    @Test
    void create() {
        var input = Instancio.create(CreateCandidate.class);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(input)
                .when()
                .post()
                .then()
                .statusCode(RestResponse.StatusCode.CREATED);

        verify(candidateApi).create(input);
        verifyNoMoreInteractions(candidateApi);
    }

//    @Test
//    void update() {
//        String id = UUID.randomUUID().toString();
//        UpdateCandidate input = Instancio.create(UpdateCandidate.class);
//
//        Candidate candidate = input.toDomain(id);
//        //ArgumentCaptor<Candidate> captor = ArgumentCaptor.forClass(Candidate.class);
//
//        when(candidateApi.update(id, input)).thenReturn(api.dto.out.Candidate.fromDomain(candidate));
//
//        var response = candidateApi.update(id, input);
//
//        verify(candidateApi).update(id, input);
//        verifyNoMoreInteractions(candidateApi);
//
//        assertEquals(api.dto.out.Candidate.fromDomain(candidate), response);
//    }

    @Test
    void list() {
        var output = Instancio.stream(api.dto.out.Candidate.class).limit(4).toList();

        when(candidateApi.list()).thenReturn(output);

        var response = given().when().get().then().statusCode(RestResponse.StatusCode.OK)
                .extract().as(api.dto.out.Candidate[].class);

        verify(candidateApi).list();
        verifyNoMoreInteractions(candidateApi);

        assertEquals(output, Arrays.stream(response).toList());
    }
}