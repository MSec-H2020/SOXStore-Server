package com.drgnman.management_server_gradle.Repository;

import com.drgnman.management_server_gradle.Entity.SOX_SERVER_INFO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoxServerInfoRepository extends JpaRepository<SOX_SERVER_INFO, Integer> {
}
