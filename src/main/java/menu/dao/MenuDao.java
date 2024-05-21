package menu.dao;

import board.dto.Category;
import menu.dto.Menu;
import org.springframework.stereotype.Repository;
import user.dto.User;
import util.UserPaging;

import java.util.List;

@Repository("MenuDao")
public interface MenuDao {
    public List<Menu> menuList();

    public User Update(User dto);

    public void menuUpdate(User dto);

    public List<Category> categoryList();

    public Category updateBorad(Category category);

    public void categoryUpdate(Category category);

	public int getPaging(UserPaging paging);

	public List<Menu> menuListByPaging(UserPaging paging);
}
