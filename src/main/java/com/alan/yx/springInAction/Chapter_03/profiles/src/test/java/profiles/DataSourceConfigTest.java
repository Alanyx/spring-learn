package com.alan.yx.springInAction.Chapter_03.profiles.src.test.java.profiles;

import com.alan.yx.springInAction.Chapter_03.profiles.src.main.java.com.myapp.method_profile.DataSourceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 使用 @ActiveProfiles("dev") 在集成测试时，激活不同的 profile
 */
public class DataSourceConfigTest {

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes = DataSourceConfig.class)
    @ActiveProfiles("dev")
    public static class DevDataSourceTest {
        @Autowired
        private DataSource dataSource;

        @Test
        public void shouldBeEmbeddedDatasource() {
            assertNotNull(dataSource);
            JdbcTemplate jdbc = new JdbcTemplate(dataSource);
            List<String> results = jdbc.query("SELECT id, name FROM Things", new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getLong("id") + ":" + rs.getString("name");
                }
            });

            assertEquals(1, results.size());
            assertEquals("1:A", results.get(0));
        }
    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes = DataSourceConfig.class)
    @ActiveProfiles("prod")
    public static class ProductionDataSourceTest {
        @Autowired
        private DataSource dataSource;

        @Test
        public void shouldBeEmbeddedDatasource() {
            // should be null, because there isn't a datasource configured in JNDI
            assertNull(dataSource);
        }
    }

    @RunWith(SpringJUnit4ClassRunner.class)
    // 这里正常使用相对路径，只是演示
    @ContextConfiguration("classpath:com/alan/yx/springInAction/Chapter_03/profiles/src/main/resources/datasource-config.xml")
    @ActiveProfiles("dev")
    public static class DevDataSourceTest_XMLConfig {
        @Autowired
        private DataSource dataSource;

        @Test
        public void shouldBeEmbeddedDatasource() {
            assertNotNull(dataSource);
            JdbcTemplate jdbc = new JdbcTemplate(dataSource);
            List<String> results = jdbc.query("SELECT id, name FROM Things", new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getLong("id") + ":" + rs.getString("name");
                }
            });

            assertEquals(1, results.size());
            assertEquals("1:A", results.get(0));
        }
    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration("classpath:com/alan/yx/springInAction/Chapter_03/profiles/src/main/resources/datasource-config.xml")
    @ActiveProfiles("prod")
    public static class ProductionDataSourceTest_XMLConfig {
        @Autowired(required = false)
        private DataSource dataSource;

        @Test
        public void shouldBeEmbeddedDatasource() {
            // should be null, because there isn't a datasource configured in JNDI
            assertNull(dataSource);
        }
    }

}
