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
 * ���ܸ�����
 * <br>
 * ����ʱ�䣺2013-9-22����6:23:38
 * <br>
 * �޸ļ�¼��
 * <br>
 * @author xiaozu
 * <br>
 */
public class DBquery {

	/**
	 * ��������˵��
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
		buffer.append("\t\tOralce���ݿ�SQL�ű�ִ���ն�\n");
		buffer.append("\t\t�汾1.1 xiaozu��Ȩ����\n");
		buffer.append("====================================================\n");
		buffer.append("�����ʽ��java -jar Path-To-dbquery.jar DBquery ���ݿ�IP Oracleʵ����  �û���  ���� \"SQL���\" \n");
		buffer.append("����˵����\n");
		buffer.append("\t\tjava -jar Path-To-dbquery.jar Javaִ��Jar������Ĺ̶���ʽ\n");
		buffer.append("\t\tDBquery��\t����������\n");
		buffer.append("\t\t���ݿ�IP��\t���ݿ�IP������\n");
		buffer.append("\t\tOracleʵ������\t ��ӦIP�ϵ����ݿ�ʵ����\n");
		buffer.append("\t\t���룺\t ���벻Ӧ����˫���ŵ�������ת���ַ�\n");
		buffer.append("\t\tSQL��䣺\t SQL���Ӧʹ��˫�������������Ҳ�Ӧ����������ת���ַ����¸��汾�ɴ��ļ����ȡ����SQL�����ڴ�������\n");
		buffer.append("�����ֵ��ʽ��\n");
		buffer.append("\t\t��һ�У�����״̬ 1ִ�гɹ� 2ִ��ʧ��\n");
		buffer.append("\t\t�ӵڶ��п�ʼ��SQLִ�н����ʧ��ԭ��\n");
		buffer.append("����ʾ����\n java -jar /opt/tools/dbquery.jar  DBquery 192.168.99.213 ikangrac www www \"select sysdate from dual\"\n");
		buffer.append("���أ�\n1\n2013-09-24 16:19:02.0");
		System.out.println(buffer);
	}
	
}
