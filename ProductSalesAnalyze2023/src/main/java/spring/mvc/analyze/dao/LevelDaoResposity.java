package spring.mvc.analyze.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.mvc.analyze.entity.Level;

@Repository
public class LevelDaoResposity implements LevelDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public Optional<Level> findLevelById(Integer levelId) {
		String sql = "select l.levelId, l.levelName from level l where l.levelId=?";
		try {
			Level level = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Level.class), levelId);
			return Optional.ofNullable(level);
		} catch (Exception e) {
			e.printStackTrace(); // 可以看console的錯誤
			return Optional.empty();
		}
	}

	@Override
	public List<Level> findAllLevels() {
		String sql = "select levelId, levelName from level;";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Level.class));
	}

}
