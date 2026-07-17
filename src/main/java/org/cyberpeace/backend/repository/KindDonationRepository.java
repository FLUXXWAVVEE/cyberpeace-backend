package org.cyberpeace.backend.repository;

import org.cyberpeace.backend.entity.KindDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KindDonationRepository extends JpaRepository<KindDonation, Long> {
}
