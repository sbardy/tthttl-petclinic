package tthttl.vetservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tthttl.vetservice.model.Specialty;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
}
