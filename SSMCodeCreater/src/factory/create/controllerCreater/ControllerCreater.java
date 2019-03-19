package factory.create.controllerCreater;

import factory.create.BaseCreater;
import factory.entity.Entity;
import factory.pathSetting.PathSetting;
import factory.stringCaseUtil.StringCaseUtil;

/**
 * Controller类 生成
 * 
 */
public class ControllerCreater extends BaseCreater{

	public ControllerCreater() {
		super.initReader();
		setPathKey("controller");
		setTemplatePath(PathSetting.controllerTemplate);
	}

	@Override
	public void executeCreateTask(Entity entity) {
		String className = StringCaseUtil.upcaseFirstCharRemoveMark(entity.getEntityName()) + "Controller";
		createFile(className);
		fillData(className, entity);
	}

}
