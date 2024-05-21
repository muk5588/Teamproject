package menu.service;

import board.dto.Category;
import menu.dto.Menu;
import user.dto.User;
import util.UserPaging;

import java.util.List;

public interface MenuService {
    public List<Menu> menuList();

    public User update(User dto);

    public void menuUpdate(User dto);

    public List<Category> categoryList();

    public Category updateBorad(Category category);

    public void categoryUpdate(Category category);

    /**
     * 페이징 처리
     * @param paging - 페이징 객체(UserPaging)
     * @param curPage - 현재 페이지
     * @return - 페이징 객체
     */
	public UserPaging getPagingMenuList(UserPaging paging, int curPage);

	public List<Menu> menuListByPaging(UserPaging paging);
}
