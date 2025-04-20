package com.nlh.projects.repository.impl;


import com.nlh.projects.repository.BudgetProjectRepository;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class BudgetProjectRepositoryImpl implements BudgetProjectRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbc;

    public BudgetProjectRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbc) {
        this.namedParameterJdbc = namedParameterJdbc;
    }


    @Override
    public Boolean existsByProjectId(Long projectId) {
        var params = Map.of("project_id", projectId);

        return this.namedParameterJdbc.query("""
                                select count(*) as project
                                from projects
                                where id = :project_id;
                        """,
                params,
                getProjectExtract()
        );
    }

    private ResultSetExtractor<Boolean> getProjectExtract() {
        return rs -> {
            if (rs.next()){
                return rs.getInt("project") > 0 ? true:false;
            }
            return false;
        };
    }
}
