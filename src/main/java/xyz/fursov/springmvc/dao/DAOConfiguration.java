package xyz.fursov.springmvc.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.fursov.springmvc.dao.list.ListDAOFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DAOConfiguration {
    @Bean
    public Map<String, DAOFactory> daoFactoryMap(ListDAOFactory list) {
        Map<String, DAOFactory> map = new HashMap<>();
        map.put("list", list);
        return map;
    }
}
