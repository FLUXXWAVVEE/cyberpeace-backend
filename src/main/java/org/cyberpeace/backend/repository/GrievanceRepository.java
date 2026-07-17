package org.cyberpeace.backend.repository;

import org.cyberpeace.backend.entity.Grievance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface GrievanceRepository extends JpaRepository<Grievance, Long> {
    Optional<Grievance> findByReferenceId(String referenceId);
}
