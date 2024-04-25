package menu.dao;

import menu.dto.Menu;
import org.springframework.stereotype.Repository;
import user.dto.User;

import java.util.List;

@Repository("MenuDao")
public interface MenuDao {
    public List<Menu> menuList();

    public User Update(User dto);

    public void menuUpdate(User dto);
}
