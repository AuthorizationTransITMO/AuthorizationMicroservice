package ru.ifmo.rain.issuer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifmo.rain.issuer.domain.HashContainer;

public interface HashContainerRepository extends JpaRepository <HashContainer, Long> {
}
