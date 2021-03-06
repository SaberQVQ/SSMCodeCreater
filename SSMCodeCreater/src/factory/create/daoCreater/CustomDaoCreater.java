package factory.create.daoCreater;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import factory.pathSetting.PathSetting;
import factory.stringCaseUtil.StringCaseUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 用户自定义Dao接口 生成
 * 
 * del
 * 
 */
public class CustomDaoCreater extends DaoCreater{

	public CustomDaoCreater() {
		// TODO Auto-generated constructor stub
		super.initReader();
		setPathKey("dao");
		setTemplatePath(PathSetting.customDaoTemplate);
	}
	
	@Override
    protected void loadTemplateAndWriteFile(String className, String fileType,
                                            Map<String, Object> data) {
		try {
			Template entityTemplate = cfg.getTemplate(templatePath);
			FileOutputStream entityFos = new FileOutputStream(writePath
					+ getPackagePathWithPathKey() + StringCaseUtil.lowcaseFirstChar(className)
					+ "/Custom" + className + fileType);
			entityTemplate.process(data, new OutputStreamWriter(entityFos,
					"utf-8")); //
			entityFos.flush();
			entityFos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
