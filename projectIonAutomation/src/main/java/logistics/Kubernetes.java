package logistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import utilities.ConfigDataProvider;

public class Kubernetes {
	/**
	 * JSch Example Tutorial Java SSH Connection Program
	 */
	ConfigDataProvider config = new ConfigDataProvider();
	String result = "";
	Session session = null;
	ChannelExec openChannel = null;
	//private List<String> runningPods = new ArrayList<String>();
	
	public List<String> getRunningPods() {
		return getKubernetesRunningPods();
	}

	private ChannelSftp sftpChannel;
	
	public Kubernetes() {
		establishSshConnection(config.getDataFromConfig("kubernetesHostname"), config.getDataFromConfig("kubernetesUsername"), config.getDataFromConfig("authenticationPrivateKeyPath"));		
		System.out.println("SSH connection established.");
		//getKubernetesRunningPods();
		//closeSshConnection();
	}

	public void establishSshConnection(String host, String user, String keyLocation) {
		
		try {
			JSch jsch = new JSch();
			if(new File(keyLocation).exists()) {
				session = jsch.getSession(user, host);
				jsch.addIdentity(keyLocation);
				
				
				java.util.Properties config = new java.util.Properties();
				config.put("StrictHostKeyChecking", "no");
				session.setConfig(config);
				session.connect();
				
				sftpChannel = (ChannelSftp) session.openChannel("sftp");
				sftpChannel.connect();
			}
			else {
				System.out.println("The private key was not found");
				//JOptionPane.showMessageDialog(null, "Your \"Private_Key.ppk\" was not detected. \nPlease ensure it is within the same folder as this application.","Attention" ,1);
				System.exit(1);
			}
		}
		catch (JSchException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Wrong Username or private key (.ppk).\nPlease note the username is case sensitive.",
					"Authentication Failed", 1);
			try {
				Files.deleteIfExists(Paths.get(System.getProperty("user.dir") + "\\" + "IonLogs_Exe_Errors.txt"));
			} catch (IOException ee) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Files.write(Paths.get(System.getProperty("user.dir") + "\\"+ "IonLogs_Exe_Errors.txt"), e.getMessage().getBytes());
			} catch (IOException eb) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(0);
		}
	}
	
	public void uploadFile(String filePath) {
	    try {
	    	sftpChannel.put(filePath, "/home/beka/");
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	public void deleteFile(String filePath) {
		try {
			sftpChannel.rm(filePath);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String executeCommand(String command) {
		String commandResult = "";
		try {
			openChannel = (ChannelExec) session.openChannel("exec");
			openChannel.setCommand(command);
			openChannel.connect();

			InputStream in = openChannel.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String buf = null;
			while ((buf = reader.readLine()) != null) {
				commandResult += buf + "\n";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(commandResult);
		return commandResult;

	}

	public void closeSshConnection() {
		if (openChannel != null && !openChannel.isClosed()) {
			openChannel.disconnect();
		}
		if (session != null && session.isConnected()) {
			session.disconnect();
		}
	}

	public List<String> getKubernetesRunningPods() {
		List<String> runningPods = new ArrayList<String>();

		try {
			openChannel = (ChannelExec) session.openChannel("exec");
			openChannel.setCommand("kubectl get pods");
			openChannel.connect();

			InputStream in = openChannel.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			int indexOfStatus = 0;
			int indexOfRestarts = 0;
			String buf = null;
			while ((buf = reader.readLine()) != null) {
				// Find index of pods "status" for this environment
				if (buf.contains("STATUS")) {
					indexOfStatus = buf.indexOf("STATUS");
					indexOfRestarts = buf.indexOf("RESTARTS");
				} else {
					//Get each pod and its status
					runningPods.add(buf.substring(0, buf.indexOf(" "))  + "      (" + buf.substring(indexOfStatus, indexOfRestarts).substring(0, buf.substring(indexOfStatus, indexOfRestarts).indexOf(" ")) + ")");
				}
				
/*				if (buf.contains("Running")) {
					System.out.println("Index of \"Running\" = " + buf.indexOf("Running"));
					runningPods.add(buf.substring(0, buf.indexOf(" ")));
				}
				System.out.println(buf.substring(buf.indexOf("Running"), endIndex));
*/			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return runningPods;
	}
	
	public void getLogsWithKeyword(String pod, int mins, String keyword, boolean caseSensitive) {
		try {
			openChannel = (ChannelExec) session.openChannel("exec");
			if (caseSensitive)
			{
				openChannel.setCommand("kubectl logs --since=" + mins + "m " + pod + " | grep " + keyword);
			} else {
				openChannel.setCommand("kubectl logs --since=" + mins + "m " + pod + " | grep -i " + keyword);
			}
			openChannel.connect();

			InputStream in = openChannel.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String buf = "";
			String line = "\n--------------------------------------- " + pod + " ----------------------------------------\n";
				
			if (!result.endsWith(line)) {
				result += line;
				System.out.println(line);
			}

			do {
				if (buf  == null) {
					continue;
				} else {
					if (buf.length() > 0 ) {
						buf = buf.replace("\\n", "\n  ").replace("\\tat", "        ");
						System.out.println(buf);
					}
					result += buf + "\n";
					//result += buf + "\n";
					//System.out.println(buf);
				}
			} while ((buf = reader.readLine()) != null);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Files.write(Paths.get(System.getProperty("user.dir") + "\\"+ "IonLogs.txt"), result.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getPodsWarningsErrors(String pod, int mins) {
		try {
			openChannel = (ChannelExec) session.openChannel("exec");
			openChannel.setCommand("kubectl logs --since=" + mins + "m " + pod);
			openChannel.connect();

			InputStream in = openChannel.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String buf = "";

			String line = "\n--------------------------------------- " + pod + " ----------------------------------------\n";
				
			if (!result.endsWith(line)) {
				result += line;
				System.out.println(line);
			}

			do {
				if (buf.contains("DEBUG") || buf.contains("TRACE") || buf.contains("INFO") || buf  == null) {
					continue;
				} else {
					if (buf.length() > 0 ) {
						buf = buf.replace("\\n", "\n  ").replace("\\tat", "        ");
						System.out.println(buf);
					}
					result += buf + "\n";
				}
			} while ((buf = reader.readLine()) != null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(result);
		try {
			Files.write(Paths.get(System.getProperty("user.dir") + "\\"+ "IonLogs.txt"), result.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getPodsFullLogs(String pod, int mins) {
		try {
			openChannel = (ChannelExec) session.openChannel("exec");
			openChannel.setCommand("kubectl logs --since=" + mins + "m " + pod);
			openChannel.connect();

			InputStream in = openChannel.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String buf = "";
			String line = "\n--------------------------------------- " + pod + " ----------------------------------------\n";
				
			if (!result.endsWith(line)) {
				result += line;
				System.out.println(line);
			}

			do {
				if (buf  == null) {
					continue;
				} else {
					if (buf.length() > 0 ) {
						buf = buf.replace("\\n", "\n  ").replace("\\tat", "        ");
						System.out.println(buf);
					}
					result += buf + "\n";
					//result += buf + "\n";
					//System.out.println(buf);
				}
			} while ((buf = reader.readLine()) != null);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Files.write(Paths.get(System.getProperty("user.dir") + "\\"+ "IonLogs.txt"), result.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public void

	public void exec(String host, String user, String keyLocation, String command) {
		String result = "";
		Session session = null;
		ChannelExec openChannel = null;
		try {
			JSch jsch = new JSch();

			session = jsch.getSession(user, host);
			jsch.addIdentity(keyLocation);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();

			openChannel = (ChannelExec) session.openChannel("exec");
			openChannel.setCommand(command);
			openChannel.connect();

			InputStream in = openChannel.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String buf = null;
			while ((buf = reader.readLine()) != null) {
				result += "\n" + buf;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		} finally {
			if (openChannel != null && !openChannel.isClosed()) {
				openChannel.disconnect();
			}
			if (session != null && session.isConnected()) {
				session.disconnect();
			}
		}
		System.out.println(result);
	}
	
	
}