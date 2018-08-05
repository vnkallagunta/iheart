package com.iheart.challenge.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.iheart.challenge.entity.AdvertiserEntity;

@Component
public interface AdvertiserRepository extends JpaRepository<AdvertiserEntity, String>{
	public AdvertiserEntity save(final AdvertiserEntity entity);
}
