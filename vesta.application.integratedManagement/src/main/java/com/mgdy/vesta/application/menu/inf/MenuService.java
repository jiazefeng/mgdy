package com.mgdy.vesta.application.menu.inf;

import com.mgdy.vesta.application.menu.DTO.MenuDTO;
import com.mgdy.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by Jason on 2017/7/9.
 */
public interface MenuService {
    /**
     * 获取一级菜单
     *
     * @param webPage
     * @return
     */
    List<MenuDTO> getFirstMenuList(WebPage webPage);

    /**
     * 添加一级菜单
     *
     * @param roleMenuDTO
     */
    void addFirMenu(MenuDTO roleMenuDTO);

    /**
     * 添加二级菜单
     *
     * @param roleMenuDTO
     */
    void addSecMenu(MenuDTO roleMenuDTO);

    /**
     * 获取二级菜单
     *
     * @param parId
     * @param webPage
     * @return
     */
    List<MenuDTO> listSecMenu(String parId, WebPage webPage);
}
