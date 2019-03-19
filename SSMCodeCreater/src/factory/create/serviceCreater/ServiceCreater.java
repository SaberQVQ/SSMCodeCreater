package factory.create.serviceCreater;

import factory.create.BaseCreater;
import factory.entity.Entity;
import factory.pathSetting.PathSetting;
import factory.stringCaseUtil.StringCaseUtil;

/**
 * Service 实现类生成
 * 
 */
public class ServiceCreater extends BaseCreater{

	public ServiceCreater() {
		super.initReader();
		setPathKey("service");
		setTemplatePath(PathSetting.serviceTemplate);
	}

	@Override
	public void executeCreateTask(Entity entity) {
		String className = StringCaseUtil.upcaseFirstCharRemoveMark(entity.getEntityName()) + "Service";
		createFile(className);
		fillData(className, entity);
	}
	
}
