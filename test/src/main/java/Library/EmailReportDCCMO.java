package Library;


	
	import java.io.File;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.OutputStream;
	import java.nio.file.Files;
	import java.nio.file.Path;
	import java.nio.file.Paths;
	import java.text.SimpleDateFormat;
	import java.util.Date;
	import java.util.List;


	public class EmailReportDCCMO {

		public void afterSuite() throws IOException {
			int TestCaseCount = 0, PassCount = 0, FailCount = 0;
			
			
			String pattern = "dd-MM-yy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(new Date());
			System.out.println("In After Suite");

			File folder = new File(System.getProperty("user.dir") + "\\target\\");

			// create object of Path
			Path path = Paths.get(System.getProperty("user.dir") + "\\target\\HTMLEmailableReport.html");

			// deleteIfExists File
			try {
				Files.deleteIfExists(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int TotalFailed = 0;
			int TotalPassed = 0;
			File[] listOfFiles = folder.listFiles();
			String LogString = "";
			String PreviousLine = "";
			System.out.println(listOfFiles.length);
			for (int j = 0; j < listOfFiles.length; j++)

			{
				int ScenarioCount = 0;
				int NumberOfFailedScenarios = 0;
				int NumberOfPassedScenarios = 0;
				System.out.println(listOfFiles[j].getName());
				if (listOfFiles[j].isFile() && !listOfFiles[j].getName().contains(".csv")) {
					if(!listOfFiles[j].getName().contains("index.html") && !listOfFiles[j].getName().contains(".properties"))
					{
					Path sourcePath = Paths.get(System.getProperty("user.dir") + "\\target\\" + listOfFiles[j].getName());
					Path destinationPath = Paths
							.get(System.getProperty("user.dir") + "\\target\\" + listOfFiles[j].getName());
					List<String> allLines = Files.readAllLines(
					Paths.get(System.getProperty("user.dir") + "\\target\\" + listOfFiles[j].getName()));
					boolean LogStarted = false;
					int Result = 0;
					String ScriptStatus = "";
					boolean ScenarioStatus = true, ScenarioFailed = false;
				
					String FailedSteps = "", LogStep = "";
					boolean StartLog = false, LoggStep = false;
					String CurrentLine = "";
					for (String line : allLines) {
						System.out.println(line);
						if(line.contains("2%") && line.contains("table_cell"))
						{
							StartLog = true;
						}
						if(line.contains("Pass") || line.contains("Fail"))
						{
							StartLog = false;
							CurrentLine = line;
							if(line.contains("Fail"))
								LoggStep = true;
						}
						if(StartLog == true)
							LogStep = LogStep + line;  
						if(LoggStep == true && StartLog == false)
						{
							int first = LogStep.indexOf("36%");
							int second = LogStep.indexOf("36%", first + 1);
							if(second != -1)
							{
								System.out.println(line);
								int third = LogStep.indexOf("12px", second + 1);
								System.out.println(FailedSteps);
								int errorindex = LogStep.lastIndexOf("</td><td style=\"color: red; font-weight: bold; text-align:center;\" width=\"12px\" class=\"table_cell\">", LogStep.length());
								int errorStartIndex = LogStep.lastIndexOf("<td width=\"36%\" class=\"table_cell\">", LogStep.length());
								if(errorindex != -1)
									FailedSteps = FailedSteps + LogStep.substring(errorStartIndex+35, errorindex) + "<br>";
							}
							LogStep = "";
							LoggStep = false;
						}
						if (line.contains("Result"))
							LogStarted = true;
						if (LogStarted == true)
							Result++;
						if (line.contains("table_cell_header"))
						{
							ScenarioCount++;
							ScenarioStatus = true; 
							ScenarioFailed = false;
							StartLog = false;
							LogStep = "";
							LoggStep = false;
						}

						if (LogStarted == true && Result == 5) {
							System.out.println(listOfFiles[j].getName() + " " + line);
							System.out.println(ScriptStatus);
							ScriptStatus = line.trim();
						}
						if(line.contains("Fail") && ScenarioFailed == false)
						{
							NumberOfFailedScenarios++;
							ScenarioFailed = true;
						}
						PreviousLine = line;
					}
					TestCaseCount = TestCaseCount + ScenarioCount;
					if(NumberOfFailedScenarios > 0) 
						NumberOfFailedScenarios--;
					NumberOfPassedScenarios = ScenarioCount - NumberOfFailedScenarios;
					System.out.println(NumberOfFailedScenarios);
					LogString = LogString + "<tr><td style=\"font-family:Century Gothic;\">"+ listOfFiles[j].getName().substring(0, listOfFiles[j].getName().length() - 5) + "</td>";
					if (ScriptStatus.contains("Pass") ) {
						System.out.println(ScriptStatus.trim());
						LogString = LogString + "<td align=center bgcolor=\"#b3ffb3\">" + ScriptStatus + "</td>";
					}
					else if (ScriptStatus.contains("Fail")) {
						System.out.println(ScriptStatus.trim());
						LogString = LogString + "<td align=center bgcolor=\"#ffcccc\">" + ScriptStatus + "</td>";
					}
				LogString = LogString + "<td align=center>" + String.valueOf(ScenarioCount) + "</td>"
						+ "<td align=center bgcolor=\"#b3ffb3\">"+String.valueOf(NumberOfPassedScenarios)+"</td>" 
						+ "<td align=center bgcolor=\"#ffcccc\">"+String.valueOf(NumberOfFailedScenarios)+"</td>"+ "<td align=left >"+FailedSteps+" </td>" + "</tr>";
				TotalFailed = TotalFailed + NumberOfFailedScenarios;
				TotalPassed = TotalPassed + NumberOfPassedScenarios;
					}
			}

		}

		// HTML Email building

		String HTMLContent = "<html ><head><title style=\"font-family:Century Gothic;\">API Automation - Test Results</title></head>";HTMLContent=HTMLContent+"<body><br><br>";HTMLContent=HTMLContent+"<p style=\"font-family:Century Gothic;\">Hi Team <br> Please find the WCC DC CMO Batch Automation execution report </p>";HTMLContent=HTMLContent+"<h3 style=\"font-family:Century Gothic;\">Summary Report for Build <b>#${BUILD_NUMBER}</b></h3>";
		// summary table start
		HTMLContent=HTMLContent+"<font face=\"Century Gothic\"><table border=1>";
		// first headder row
		HTMLContent=HTMLContent+"<tr align=center bgcolor=\"#e6faff\"><td ><b>Total Number of TC's</b></td><td><b>Number of Pass TC's</b></td><td><b>Number of Fail TC's</b></td></tr>";
		// second row
		HTMLContent=HTMLContent+"<tr><td align=center>"+TestCaseCount+"</td>"
				+ "<td align=center bgcolor=\"#b3ffb3\">"+String.valueOf(TotalPassed)+"</td>"
						+ "<td align=center bgcolor=\"#ffcccc\">"+String.valueOf(TotalFailed)+"</td></tr>";
		// summary table end
		HTMLContent=HTMLContent+"</table></font>";
		// Execution Report heading
		HTMLContent=HTMLContent+"<h3 style = \"font-family:Century Gothic;\">WCC Test Automation Execution Report</h3>";
		// Execution Report table start
		HTMLContent=HTMLContent+"<font face=\"Century Gothic\"><table border=1>";
		// first headder row
		HTMLContent=HTMLContent+"<tr align=center bgcolor=\"#e6faff\"><td><b>Functionality</b></td><td><b>Status</b></td><td width = '15%'><b>Total No. of TC's</b></td><td width = '15%'><b>No. of TC's Passed</b></td><td width = '15%'><b>No. of TC's Failed</b></td><td><b>Failure Reason</b></td></tr>";
		if(LogString.contains("</td>"))
			LogString  = LogString.replace("</td>", "");
		if(LogString.contains("<td style=\"color: green; font-weight<br>"))
			LogString = LogString.replace("<td style=\"color: green; font-weight<br>", "");
		if(LogString.contains("class=\"table_cell\">"))
			LogString = LogString.replace("class=\"table_cell\">", "");
		HTMLContent=HTMLContent+LogString+"</table></font>";
		// thanks message
		HTMLContent=HTMLContent+"<br><br><p style = \"font-family:Century Gothic;\">Thanks,<br>Prolifics QE Team</p>";
		// end of html tag
		HTMLContent=HTMLContent+"</body></html>";

		System.out.println(HTMLContent);
		OutputStream outputStream = new FileOutputStream(
				System.getProperty("user.dir") + "//target//HTMLEmailableReport.html");
		byte b[] = HTMLContent.getBytes();outputStream.write(b);outputStream.close();
	}
}
