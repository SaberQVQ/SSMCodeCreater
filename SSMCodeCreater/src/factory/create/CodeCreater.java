package factory.create;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import factory.create.controllerCreater.ControllerCreater;
import factory.create.daoCreater.DaoCreater;
import factory.create.dtoCreater.EntityDtoCreater;
import factory.create.dtoCreater.EntitySearchDtoCreater;
import factory.create.entityCreater.EntityCreater;
import factory.create.mapperXMLCreater.MapperXMLCreater;
import factory.create.serviceCreater.ServiceCreater;
import factory.create.serviceCreater.ServiceImplCreater;
import factory.database.SQLCreate;
import factory.database.dao.CommonDAO;
import factory.entity.Entity;
import factory.fileReader.IFileReader;
import factory.fileReader.excelReader.ExcelReader;
import factory.fileReader.wordReader.WordReader;
import factory.pathSetting.PathSetting;
import freemarker.template.TemplateException;

/**
 * 全局控制类 指派任务
 */
public class CodeCreater {
	
	/*初始化log4j配置*/
	private static void InitLog4jConfig() {
		Properties props = null;
		FileInputStream fis = null;
		try {
			// 从配置文件dbinfo.properties中读取配置信息
			props = new Properties();
			fis = new FileInputStream("resource/log4j.properties");
			props.load(fis);
			PropertyConfigurator.configure(props);//装入log4j配置信息
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			fis = null;
		}
	}
	
    private static final Logger logger = Logger.getLogger(CodeCreater.class);
	//private static List<Integer> resultList = new ArrayList<Integer>();
	
	public static void StartCreate(String readPath,String writePath) throws IOException, TemplateException{
		InitLog4jConfig();
		
		PathSetting.readPath = readPath;
		PathSetting.writePath = writePath;
		
		logger.info("---------------start code creater---------------");
		
		String[] filePathArr = readPath.split("\\.");
		String fileType = filePathArr[filePathArr.length - 1];
		IFileReader reader;
		List<Entity> entityList = null;
		
		if (fileType.equals("doc")) {
			reader = new WordReader();
			entityList = reader.readFile(readPath);
		} else if (fileType.equals("xls") || fileType.equals("xlsx")){
			reader = new ExcelReader();
			entityList = reader.readFile(readPath);
		}
		
		try {
			for (int i = 0; i < entityList.size(); i++) {
				Entity entity = entityList.get(i);
//				createDatabase(entity);
				createTask(entity);
			}
		} catch (Exception e) {
			System.out.println("");
			e.printStackTrace();
		}
	}
	
	public static void createDatabase(Entity entity) throws IOException {
		System.out.println("执行数据库操作");
		CommonDAO cd = new CommonDAO();
		//新增数据库
		if (entity.getEntitySteate() == 0) {
			String sql = SQLCreate.tableCreateSQL(entity);
			//System.out.println(sql);
			cd.executeSql(sql);
			//resultList.add(cd.executeSql(sql));
		} else {//修改数据库字段
			List<String> sqlList = SQLCreate.tableAlterSQL(entity);
			for (int i = 0; i < sqlList.size(); i++) {
				System.out.println(sqlList.get(i));
				cd.executeSql(sqlList.get(i));
			}
		}
	}
	
	public static void createTask(Entity entity){
		System.out.println("为" + entity.getEntityName() + "加载模板\n");
		new EntityCreater().executeCreateTask(entity);
		new EntityDtoCreater().executeCreateTask(entity);
		new EntitySearchDtoCreater().executeCreateTask(entity);
		new DaoCreater().executeCreateTask(entity);
		new MapperXMLCreater().executeCreateTask(entity);
		new ServiceCreater().executeCreateTask(entity);
		new ServiceImplCreater().executeCreateTask(entity);
		new ControllerCreater().executeCreateTask(entity);		
		
		System.out.println(entity.getEntityName() + "创建java类完成\n");
	}
}
