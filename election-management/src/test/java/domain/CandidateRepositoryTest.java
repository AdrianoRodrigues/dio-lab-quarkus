package domain;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.*;

public abstract class CandidateRepositoryTest {
    public abstract CandidateRepository repositotry();

    @Test
    void save() {
        Candidate candidate = Instancio.create(Candidate.class);
        repositotry().save(candidate);

        Optional<Candidate> result = repositotry().findById(candidate.id());
        assertTrue(result.isPresent());
        assertEquals(candidate, result.get());
    }

    @Test
    void findAll() {
        List<Candidate> candidates = Instancio.stream(Candidate.class).limit(10).toList();
        repositotry().save(candidates);

        List<Candidate> result = repositotry().findAll();

        assertEquals(result.size(), candidates.size());
    }

    @Test
    void findByName() {
        Candidate candidate = Instancio.of(Candidate.class).set(field("familyName"), "Silva").create();
        Candidate otherCandidate = Instancio.of(Candidate.class).set(field("familyName"), "Poiani").create();;

        CandidateQuery query = new CandidateQuery.Builder().name("SIL").build();
        repositotry().save(List.of(candidate, otherCandidate));

        List<Candidate> result = repositotry().find(query);

        assertEquals(1, result.size());
        assertEquals(candidate, result.get(0));
    }
}