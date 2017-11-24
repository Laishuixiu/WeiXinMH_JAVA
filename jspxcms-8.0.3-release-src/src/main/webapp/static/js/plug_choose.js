Cms.f7 = {};
$.ajaxSettings.traditional=true;

/**
 * 菜单单选
 */
Cms.f7.wxmenu = function(name, focus, options) {
	options = options || {};
	var url = CTX + CMSCP + "/plug/wxmenu/choose_wxmenu_tree.do";
	var settings = {title:"wxmenu Select",width:350,height:450};
	options.settings = $.extend(settings, options.settings);
	var names = ["Number","Name"];
	var f7 = Cms.F7Single(url,name,names,focus,options);
};

/**
 * 组织多选
 */
Cms.f7.orgMulti = function(name, options) {
	options = options || {};
	var url = CTX + CMSCP + "/core/org/choose_org_tree_multi.do";
	var settings = {title:"Orgs Select",width:550,height:450};
	options.settings = $.extend(settings, options.settings);
	var f7 = Cms.F7Multi(url, name, options);
}




