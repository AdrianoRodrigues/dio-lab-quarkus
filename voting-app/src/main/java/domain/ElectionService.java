package domain;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@ApplicationScoped
public class ElectionService {

    private final ElectionRepository repository;

    public ElectionService(ElectionRepository repository) {
        this.repository = repository;
    }

    public List<Election> findAll() {
        return repository.findAll();
    }

    public void vote(String electionId, String candidateId) {
        Election election = repository.findById(electionId);

        election.candidates().stream()
                .filter(candidate -> candidate.id().equals(candidateId))
                .findFirst()
                .ifPresent(candidate -> repository.vote(electionId, candidate));
    }
}
