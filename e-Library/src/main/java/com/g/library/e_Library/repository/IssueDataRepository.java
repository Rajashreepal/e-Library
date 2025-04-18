package com.g.library.e_Library.repository;

import com.g.library.e_Library.entity.IssueData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IssueDataRepository extends JpaRepository<IssueData, UUID> {

    @Query("select i from IssueData i where i.member.id = ?1")
    List<IssueData> findByMemberId(UUID id);

}