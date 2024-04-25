package menu.service;

import menu.dto.Menu;
import user.dto.User;

import java.util.List;

public interface MenuService {
    public List<Menu> menuList();

    public User update(User dto);


    public void menuUpdate(User dto);
}
