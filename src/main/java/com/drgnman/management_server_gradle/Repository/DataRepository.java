package com.drgnman.management_server_gradle.Repository;

import com.drgnman.management_server_gradle.Entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<Data, Integer> {
}
