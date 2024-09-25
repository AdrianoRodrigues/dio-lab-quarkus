package infrastructure.repositories;

import domain.Candidate;
import domain.Election;
import domain.ElectionRepository;
import io.quarkus.cache.CacheResult;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.keys.KeyCommands;
import io.quarkus.redis.datasource.sortedset.SortedSetCommands;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

import java.util.List;

@ApplicationScoped
public class RedisElectionRepository implements ElectionRepository {
    private static final Logger LOGGER = Logger.getLogger(RedisElectionRepository.class);
    private final SortedSetCommands<String, String> commands;
    private final KeyCommands<String> keyCommands;

    private static final String ELECTION_KEY_PATTERN = "election:";

    public RedisElectionRepository(RedisDataSource redisDataSource) {
        this.commands = redisDataSource.sortedSet(String.class, String.class);
        this.keyCommands = redisDataSource.key(String.class);
    }

    @Override
    @CacheResult(cacheName = "memoization")
    public Election findById(String id) {
        LOGGER.info("Retrieving election " + id + " from redis");

        List<Candidate> candidates = commands.zrange(ELECTION_KEY_PATTERN + id, 0, -1)
                .stream().map(Candidate::new).toList();

        return new Election(id, candidates);
    }

    @Override
    public List<Election> findAll() {
        LOGGER.info("Retrieving all elections from redis");

        return keyCommands.keys(ELECTION_KEY_PATTERN + "*")
                .stream()
                .map(id -> findById(id.replace(ELECTION_KEY_PATTERN, "")))
                .toList();
    }

    @Override
    public void vote(String electionId, Candidate candidate) {
        LOGGER.info("Voting for " + candidate.id());
        commands.zincrby(ELECTION_KEY_PATTERN + electionId, 1, candidate.id());
    }
}
