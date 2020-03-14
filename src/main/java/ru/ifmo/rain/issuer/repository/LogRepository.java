package ru.ifmo.rain.issuer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifmo.rain.issuer.domain.Log;

public interface LogRepository extends JpaRepository<Log, Long> {
}
