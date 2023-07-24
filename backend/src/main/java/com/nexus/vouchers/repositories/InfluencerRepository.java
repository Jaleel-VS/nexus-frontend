package com.nexus.vouchers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nexus.vouchers.models.Influencer;
import org.springframework.stereotype.Repository;

@Repository
public interface InfluencerRepository extends JpaRepository<Influencer, Long> {
    
}
