package menu.dao;

import menu.dto.Menu;
import org.springframework.stereotype.Repository;
import user.dto.UserDTO;

import java.util.List;

@Repository("MenuDao")
public interface MenuDao {
    public List<Menu> menuList();

    public UserDTO Update(UserDTO dto);

    public void menuUpdate(UserDTO dto);
}
