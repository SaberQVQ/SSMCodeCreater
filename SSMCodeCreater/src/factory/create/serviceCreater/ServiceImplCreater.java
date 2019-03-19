package factory.create.serviceCreater;

import factory.create.BaseCreater;
import factory.entity.Entity;
import factory.pathSetting.PathSetting;
import factory.stringCaseUtil.StringCaseUtil;

/**
 * ServiceImpl 接口生成
 */
public class ServiceImplCreater extends BaseCreater{

	public ServiceImplCreater() {
		super.initReader();
		setPathKey("serviceImpl");
		setTemplatePath(PathSetting.ServiceImplTemplate);
	}

	@Override
	public void executeCreateTask(Entity entity) {
		String className = StringCaseUtil.upcaseFirstCharRemoveMark(entity
				.getEntityName()) + "ServiceImpl";
		createFile(className);
		fillData(className, entity);
	}
	
}
