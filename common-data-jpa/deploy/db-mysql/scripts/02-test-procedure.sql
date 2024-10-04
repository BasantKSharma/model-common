DELIMITER //

DROP PROCEDURE IF EXISTS p_insert_test_table;

CREATE PROCEDURE p_insert_test_table (
    IN p_name VARCHAR(64)
)
BEGIN
    IF NOT EXISTS (SELECT * FROM test_table WHERE name = p_name) THEN
        INSERT INTO test_table (name)
        VALUES (p_name);
    END IF;
END //

DELIMITER ;
