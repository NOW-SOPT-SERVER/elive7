package opt.sopt.practice.auth.rds.repository;

import java.util.Optional;
import opt.sopt.practice.auth.rds.domain.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, Long> {
    Optional<Token> findByRefreshToken(final String refreshToken);

    Optional<Token> findById(final Long id);
}
