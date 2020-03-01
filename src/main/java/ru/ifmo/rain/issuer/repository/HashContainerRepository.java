package ru.ifmo.rain.issuer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifmo.rain.issuer.domain.Cvv2Container;

public interface HashContainerRepository extends JpaRepository <Cvv2Container, Long> {
}
