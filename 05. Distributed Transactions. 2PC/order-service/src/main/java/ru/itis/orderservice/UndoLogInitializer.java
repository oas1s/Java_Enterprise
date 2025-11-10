package ru.itis.orderservice;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UndoLogInitializer {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UndoLogInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void initUndoLogTable() {
        try {
            Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'undo_log'",
                Integer.class
            );

            if (count != null && count == 0) {
                String sql = """
                    CREATE TABLE IF NOT EXISTS undo_log (
                        id BIGSERIAL PRIMARY KEY,
                        branch_id BIGINT NOT NULL,
                        xid VARCHAR(100) NOT NULL,
                        context VARCHAR(128),
                        rollback_info BYTEA NOT NULL,
                        log_status INT NOT NULL,
                        log_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        log_modified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
                    );
                    CREATE INDEX idx_undo_log_xid_branch_id ON undo_log (xid, branch_id);
                    """;

                jdbcTemplate.execute(sql);

            } else {
                System.out.println(" Таблица undo_log уже существует.");
            }

        } catch (Exception e) {
            System.err.println("Ошибка при создании undo_log: " + e.getMessage());
        }
    }
}
