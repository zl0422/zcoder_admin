package com.zcoder.admin.sys.service;

import com.google.common.collect.Lists;
import com.zcoder.admin.core.common.CrudService;
import com.zcoder.admin.sys.dao.SysMenuDao;
import com.zcoder.admin.sys.domain.SysMenu;
import com.zcoder.admin.sys.domain.SysMenu.MenuTree;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
@Slf4j
public class SysMenuService extends CrudService<SysMenuDao,SysMenu> {

	public List<MenuTree> loadMenuTree(){
		List<SysMenu> menus = dao.findAll();
		return toMenuTree(menus);
	}
	
	private List<MenuTree> toMenuTree(List<SysMenu> menus){
		List<MenuTree> result = Lists.newArrayList();
		for (SysMenu menu : menus) {
			MenuTree tree = menu.new MenuTree();
			tree.setId(menu.getId());
			tree.setPId(menu.getParentId());
			tree.setName(menu.getName());
			result.add(tree);
		}
		return result;
	}
	
}
