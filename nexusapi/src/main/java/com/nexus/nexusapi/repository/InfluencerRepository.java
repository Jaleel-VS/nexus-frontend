package com.nexus.nexusapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexus.nexusapi.model.Influencer;

@Repository
public interface InfluencerRepository extends JpaRepository<Influencer, Long> {
}