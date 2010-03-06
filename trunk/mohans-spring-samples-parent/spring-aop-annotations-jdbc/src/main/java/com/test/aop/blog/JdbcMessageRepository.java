package com.test.aop.blog;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcMessageRepository implements MessageRepository {

	private SimpleJdbcTemplate jdbcTemplate;

	@Autowired
	public void createTemplate(DataSource dataSource) {
		this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	@PostConstruct
	public void setUpDatabase() {
		jdbcTemplate.update("create table messages (language varchar(20), message varchar(100))");
		jdbcTemplate.update("insert into messages (language, message) values ('English', 'Welcome')");
		jdbcTemplate.update("insert into messages (language, message) values ('Deutsch', 'Willkommen')");
	}

	@PreDestroy
	public void tearDownDatabase() {
		jdbcTemplate.update("drop table messages");
	}

	public String getMessage(String language) {
		return jdbcTemplate.queryForObject("select message from messages where language = ?", String.class, language);
	}

}
