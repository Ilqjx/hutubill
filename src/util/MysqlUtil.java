package util;

import java.io.IOException;

public class MysqlUtil {

	public static void backup(String mysqlPath,String backupfile) throws IOException{
		String commandFormat = "%s/bin/mysqldump.exe -u%s -p%s -hlocalhost -P%d %s -r %s";
		String command = String.format(commandFormat,mysqlPath,DBUtil.loginName,DBUtil.password,DBUtil.port,DBUtil.database,backupfile);
		Runtime.getRuntime().exec(command);
	}
	
	public static void recover(String mysqlPath,String recoverfile) throws IOException{
		String commandFormat = "%s/bin/mysql.exe -u%s -p%s %s < %s";
		String command = String.format(commandFormat,mysqlPath,DBUtil.loginName,DBUtil.password,DBUtil.database,recoverfile);
		String[] cmd = {"cmd","/c",command};
		Runtime.getRuntime().exec(cmd);
	}
	
	/*
	public static void recover(String mysqlPath,String recoverfile) throws IOException{
		try{
			String commandFormat = "\"%s/bin/mysql.exe\" -u%s -p%s %s";
			String command = String.format(commandFormat,mysqlPath,DBUtil.loginName,DBUtil.password,DBUtil.database);
			
			Process p = Runtime.getRuntime().exec(command);
			
			OutputStream out = p.getOutputStream();
			
			StringBuffer sb = new StringBuffer("");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(recoverfile),Charset.forName("utf8")));
			//缓存流 字符流 字节流
			String inStr;
			String outStr;
			while((inStr = br.readLine()) != null){
				sb.append(inStr+"\r\n");
			}
			outStr = sb.toString();
			OutputStreamWriter writer = new OutputStreamWriter(out,"utf8");//字符流
			writer.write(outStr);
			writer.flush();
			out.close();
			br.close();
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	*/
}