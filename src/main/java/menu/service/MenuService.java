package menu.service;

import menu.dto.Menu;
import user.dto.UserDTO;

import java.util.List;

public interface MenuService {
    public List<Menu> menuList();

    void menuUpdate(UserDTO dto);
}
