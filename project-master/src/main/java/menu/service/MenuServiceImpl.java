package menu.service;

import menu.dto.Menu;
import menu.dao.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.dto.UserDTO;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;
    @Override
    public List<Menu> menuList() {
        return menuDao.menuList();
    }

    @Override
    public UserDTO update(UserDTO dto) {
        return menuDao.Update(dto);
    }

    @Override
    public void menuUpdate(UserDTO dto) {
        menuDao.menuUpdate(dto);
    }


}
