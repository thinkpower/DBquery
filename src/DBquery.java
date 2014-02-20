/**
 * 
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 功能概述：
 * <br>
 * 创建时间：2013-9-22下午6:23:38
 * <br>
 * 修改记录：
 * <br>
 * @author xiaozu
 * <br>
 */
public class DBquery {

	/**
	 * 方法功能说明
	 * @param args
	 */
	public static void main(String[] args) {
		String ip = args[1];
		if(ip.equalsIgnoreCase("help")){
			help();
			return ;
		}

		String service=args[2];
		String user = args[3];
		String password = args[4];
		String sql = args[5];
		
		
		
//		System.out.println("jdbc:oracle:thin:@"+ip+":1521:"+service+user+password+sql);
		
//		String ip = null;
//		String service= null ;
//		String user = null;
//		String password = null;
//		String sql = null;
//		
//		ip = "192.168.99.213";
//		service = "ikangrac";
//		user = "www";
//		password = "www";
//		sql = "select OWNER from dba_tables where table_name ='AUDIT_BAK'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@"+ip+":1521:"+service, user, password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData metadata =  rs.getMetaData();
			int count = metadata.getColumnCount();
			StringBuffer buffer = new StringBuffer();
			while(rs.next()){
				buffer.append("\n");
				buffer.append(row(rs,count));
			}
			rs.close();
			stmt.close();
			conn.close();
			System.out.println("1");
			System.out.println(buffer.substring(1));
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("-1");
			e.printStackTrace();
		} 
	}
	
	public static String row(ResultSet rs,int count){
		StringBuffer buffer = new StringBuffer();
		try {
			
			for(int i=1;i<=count; i++){
				buffer.append("\t");
				buffer.append(rs.getString(i));
//				buffer.append(rs.getObject(i));
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return buffer.substring(1);
	}
	
	public static void help(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n");
		buffer.append("====================================================\n");
		buffer.append("\t\tOralce数据库SQL脚本执行终端\n");
		buffer.append("\t\t版本1.1 xiaozu版权所有\n");
		buffer.append("====================================================\n");
		buffer.append("命令格式：java -jar Path-To-dbquery.jar DBquery 数据库IP Oracle实例名  用户名  密码 \"SQL语句\" \n");
		buffer.append("参数说明：\n");
		buffer.append("\t\tjava -jar Path-To-dbquery.jar Java执行Jar包命令的固定格式\n");
		buffer.append("\t\tDBquery：\t主功能命令\n");
		buffer.append("\t\t数据库IP：\t数据库IP或域名\n");
		buffer.append("\t\tOracle实例名：\t 对应IP上的数据库实例名\n");
		buffer.append("\t\t密码：\t 密码不应包含双引号等命令行转义字符\n");
		buffer.append("\t\tSQL语句：\t SQL语句应使用双引号括起来，且不应包含命令行转义字符【下个版本可从文件里读取复杂SQL，请期待……】\n");
		buffer.append("命令返回值格式：\n");
		buffer.append("\t\t第一行：数字状态 1执行成功 2执行失败\n");
		buffer.append("\t\t从第二行开始：SQL执行结果或失败原因\n");
		buffer.append("调用示例：\n java -jar /opt/tools/dbquery.jar  DBquery 192.168.99.213 ikangrac www www \"select sysdate from dual\"\n");
		buffer.append("返回：\n1\n2013-09-24 16:19:02.0");
		System.out.println(buffer);
	}
	
}
