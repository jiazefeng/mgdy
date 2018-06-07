package com.mgdy.vesta.application.menu.impl;

import com.mgdy.vesta.application.menu.DTO.MenuDTO;
import com.mgdy.vesta.application.menu.inf.MenuService;
import com.mgdy.vesta.domain.model.RoleViewmodelEntity;
import com.mgdy.vesta.domain.repository.UserPropertyStaffRepository;
import com.mgdy.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason on 2017/7/9.
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private UserPropertyStaffRepository userPropertyStaffRepository;

    @Override
    public List<MenuDTO> getFirstMenuList(WebPage webPage) {
        List<RoleViewmodelEntity> menus = userPropertyStaffRepository.listMenu("", webPage);
        List<MenuDTO> roleMenuDTOs = new ArrayList<MenuDTO>();
        if (menus.size() > 0) {
            for (RoleViewmodelEntity menuDto : menus) {
                MenuDTO roleMenuDTO = new MenuDTO();
                roleMenuDTO.setRoleMenuId(menuDto.getMenuId());
                roleMenuDTO.setRoleMenuDesc(menuDto.getMenuDescription());
                roleMenuDTO.setRoleMenuName(menuDto.getMenuName());
                roleMenuDTOs.add(roleMenuDTO);
            }
        }
        return roleMenuDTOs;
    }

    @Override
    public void addFirMenu(MenuDTO roleMenuDTO) {
        RoleViewmodelEntity roleViewmodelEntity = userPropertyStaffRepository.getLastFirVeiwModel();
        RoleViewmodelEntity newMenu = new RoleViewmodelEntity();
        String roleMenuId = "";
        String roleMenuOrder = "";
        if (roleViewmodelEntity == null || roleViewmodelEntity.getMenuId() == null) {
            roleMenuId = "000100000000";
            newMenu.setMenuId(roleMenuId);
            roleMenuOrder = "1";
            newMenu.setMenuorder(roleMenuOrder);
        } else {
            roleMenuOrder = (Integer.parseInt(roleViewmodelEntity.getMenuorder()) + 1) + "";
            if (roleMenuOrder.length() == 1) {
                roleMenuId = "000" + roleMenuOrder + "00000000";
            } else if (roleMenuOrder.length() == 2) {
                roleMenuId = "00" + roleMenuOrder + "00000000";
            }
            newMenu.setMenuId(roleMenuId);
            newMenu.setMenuorder(roleMenuOrder);
        }
        newMenu.setChildFlag("N");
        newMenu.setMenuDescription(roleMenuDTO.getRoleMenuDesc());
        newMenu.setMenuName(roleMenuDTO.getRoleMenuName());
        newMenu.setMenuState("1");
        newMenu.setMenulevel("1");
        newMenu.setOperator("crj");
        newMenu.setParantmenuid("");
        newMenu.setRunscript("#");
        newMenu.setOwner("property");
        newMenu.setStatus(1);
        userPropertyStaffRepository.addViewModel(newMenu);
    }

    @Override
    public void addSecMenu(MenuDTO roleMenuDTO) {
//---------------------------------------------------------添加菜单
        RoleViewmodelEntity parMenu = userPropertyStaffRepository.getModelById(roleMenuDTO.getRoleMenuParId());
        RoleViewmodelEntity roleViewmodelEntity = userPropertyStaffRepository.getLastSecViewModel(roleMenuDTO.getRoleMenuParId());
        RoleViewmodelEntity newMenu = new RoleViewmodelEntity();
        String roleMenuId = "";
        String roleMenuOrder = "";
        if (roleViewmodelEntity == null || roleViewmodelEntity.getMenuId() == null) {
            roleMenuId = parMenu.getMenuId().substring(0, 4) + "0001" + "0000";
            roleMenuOrder = "1";
        } else {
            roleMenuOrder = (Integer.parseInt(roleViewmodelEntity.getMenuorder()) + 1) + "";
            if (roleMenuOrder.length() == 1) {
                roleMenuId = parMenu.getMenuId().substring(0, 4) + "000" + roleMenuOrder + "0000";
            } else if (roleMenuOrder.length() == 2) {
                roleMenuId = parMenu.getMenuId().substring(0, 4) + "00" + roleMenuOrder + "0000";
            }
        }
        newMenu.setMenuId(roleMenuId);
        newMenu.setMenuorder(roleMenuOrder);
        newMenu.setChildFlag("Y");
        newMenu.setMenuDescription(roleMenuDTO.getRoleMenuDesc());
        newMenu.setMenuName(roleMenuDTO.getRoleMenuName());
        newMenu.setMenuState("1");
        newMenu.setMenulevel("2");
        newMenu.setOperator("java");
        newMenu.setParantmenuid(roleMenuDTO.getRoleMenuParId());
        newMenu.setRunscript("#");
        newMenu.setOwner("property");
        newMenu.setStatus(1);
        userPropertyStaffRepository.addViewModel(newMenu);
    }

    @Override
    public List<MenuDTO> listSecMenu(String parId, WebPage webPage) {
        List<RoleViewmodelEntity> menus = userPropertyStaffRepository.listMenu(parId, webPage);
        List<MenuDTO> roleMenuDTOs = new ArrayList<MenuDTO>();
        if (menus.size() > 0) {
            for (RoleViewmodelEntity menuDto : menus) {
                MenuDTO roleMenuDTO = new MenuDTO();
                roleMenuDTO.setRoleMenuId(menuDto.getMenuId());
                roleMenuDTO.setRoleMenuDesc(menuDto.getMenuDescription());
                roleMenuDTO.setRoleMenuName(menuDto.getMenuName());
                roleMenuDTOs.add(roleMenuDTO);
            }
        }
        return roleMenuDTOs;
    }
}
