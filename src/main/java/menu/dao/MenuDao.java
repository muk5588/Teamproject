package menu.dao;

import menu.dto.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("MenuDao")
public interface MenuDao {
    public List<Menu> menuList();
}
