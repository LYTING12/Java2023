package spring.mvc.analyze.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import spring.mvc.analyze.entity.Product;
import spring.mvc.analyze.entity.User;

@Repository
public class UserDaoResposity implements UserDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ServiceDaoResposity serviceDaoResposity;
	
	@Autowired
	private LevelDaoResposity levelDaoResposity;
	
	RowMapper<User> rowMapper = (ResultSet rs, int rowNum) -> {
		User user = new User();
		user.setUserId(rs.getInt("userId"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setLevelId(rs.getInt("levelId"));
		user.setLevel(levelDaoResposity.findLevelById(rs.getInt("levelId")).get());
		user.setMenu(serviceDaoResposity.findSevicesByUserId(rs.getInt("userId")));
		return user;
	};
	
	@Override
	public List<User> findAllUsers() {
		String sql = "select userId, username, password, levelId from user";
		//return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public Optional<User> findUserById(Integer userId) {
		String sql = "select userId, username, password, levelId from user where userId=?";
		try {
			User user = jdbcTemplate.queryForObject(sql,rowMapper, userId);
			return Optional.ofNullable(user);
		} catch (Exception e) {
			e.printStackTrace(); // 可以看console的錯誤
			return Optional.empty();
		}
	}

	@Override
	public Optional<User> findUserByUsername(String username) {
		String sql = "select userId, username, password, levelId from user where username=?";
		try {
			User user = jdbcTemplate.queryForObject(sql,rowMapper, username);
			return Optional.ofNullable(user);
		} catch (Exception e) {
			e.printStackTrace(); // 可以看console的錯誤
			return Optional.empty();
		}
	}

	@Override
	public int addUser(User user) {
		String sql = "insert into user(username, password, levelId) value (?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowcount = jdbcTemplate.update(conn -> {
        	PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        	pstmt.setString(1, user.getUsername());
        	pstmt.setString(2, user.getPassword());
        	pstmt.setInt(3,  user.getLevelId());
        	return pstmt;
        }, keyHolder);
        if(rowcount > 0) {
        	int userId = keyHolder.getKey().intValue(); // 得到 id
        	user.setUserId(userId);
        }
		return rowcount;
	}

	@Override
	public int updateUserLevelIdAndName(Integer userId, Integer levelId, String name) {
		String sql = "update user set levelId = ?, username = ? where userId = ?";
		int rowcount = jdbcTemplate.update(sql, levelId, name , userId);
		return rowcount ;
	}

	@Override
	public int removeProductById(Integer userId) {
		String sql = "delete from user where userId = ?";
		return jdbcTemplate.update(sql, userId);
	}

	
	
}
