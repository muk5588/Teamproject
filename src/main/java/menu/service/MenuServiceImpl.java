package menu.service;

import board.dto.Category;
import menu.dto.Menu;
import menu.dao.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.dto.User;
import util.UserPaging;

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
    public User update(User dto) {
        return menuDao.Update(dto);
    }

    @Override
    public void menuUpdate(User dto) {
        menuDao.menuUpdate(dto);
    }

    @Override
    public List<Category> categoryList() {
        return menuDao.categoryList();
    }

    @Override
    public Category updateBorad(Category category) {
        return menuDao.updateBorad(category);
    }

    @Override
    public void categoryUpdate(Category category) {
        menuDao.categoryUpdate(category);
    }

	@Override
	public UserPaging getPagingMenuList(UserPaging paging, int curPage) {
		int totalCount = menuDao.getPaging(paging);
		UserPaging pagingres = new UserPaging(totalCount, curPage);
		return pagingres;
	}

	@Override
	public List<Menu> menuListByPaging(UserPaging paging) {
		return menuDao.menuListByPaging(paging);
	}


}
