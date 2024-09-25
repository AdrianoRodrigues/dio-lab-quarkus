package infrastructure.resources;

import api.dto.Election;
import infrastructure.rest.ElectionManagement;
import io.smallrye.mutiny.Multi;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestStreamElementType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Path("/")
public class ResultResource {

    private static final Logger log = LoggerFactory.getLogger(ResultResource.class);
    private final ElectionManagement management;

    public ResultResource(@RestClient ElectionManagement management) {
        this.management = management;
    }

    @GET
    @RestStreamElementType(MediaType.APPLICATION_JSON)
    public Multi<List<Election>> results() {
        return Multi.createFrom()
                .ticks()
                .every(Duration.of(5, ChronoUnit.SECONDS))
                .onItem()
                .transformToMultiAndMerge(n -> management.getElections().toMulti());
    }
}
