package Scripts;

import java.io.*;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;  
import java.util.Properties;

import java.util.concurrent.TimeUnit;
import java.text.ParseException;

//import TestXML.SOAP.XML;

import java.nio.file.*;
public class Report 
{
	static int StepNumber = 0;
	static int PassCount = 0;
	static int FailCount = 0;
	static String ReportfilePath;
	public String FrameworkFolder = System.getProperty("user.dir");
	String strDate, EndDate;
	SimpleDateFormat formatter;
	public static String ConsolidatedStatus = "Pass";
    public void CreateBaseHTMLFile() throws FileNotFoundException, IOException
    {
    	Date date = new Date(); 
    	String SysTime = date.getYear() + String.valueOf(date.getMonth()) + String.valueOf(date.getDay()) + "_" + String.valueOf(date.getHours()) + String.valueOf(date.getMinutes()) + String.valueOf(date.getSeconds());
    	ReportfilePath = FrameworkFolder+"\\Results\\Report"+SysTime+".html";
    	Writer writer = null;
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");  
        strDate = formatter.format(date);
    	InetAddress inetAddress = InetAddress.getLocalHost();
    	File file = new File(ReportfilePath);
        if(file.exists())
        {
        	file.delete();
        	file.createNewFile();
        }
        else
        	file.createNewFile();
        Properties p=new Properties();  
		FileReader reader=new FileReader("properties.properties");  
		p.load(reader);
    	try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ReportfilePath), "utf-8"));
            writer.write("<html><head><script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>");
            writer.write("<script type='text/javascript'>");
            writer.write("google.charts.load('current', {'packages':['corechart']});");
            writer.write("google.charts.setOnLoadCallback(drawChart);");
            writer.write("function drawChart() {");
            writer.write("var data = google.visualization.arrayToDataTable([");
            writer.write("['Task', 'Hours per Day'],");
            writer.write("['Pass', ##PassCount],");
            writer.write("['Fail', ##FailCount],");
            writer.write("]);");
            writer.write("var options = {'title':'Automation Execution Report', slices: {0: {color: '#008000'}, 1:{color: '#FF0000'}}, 'width':300, 'height':200};");
            writer.write("var chart = new google.visualization.PieChart(document.getElementById('piechart'));");
            writer.write("chart.draw(data, options);");
            writer.write("}");
            writer.write("</script>");
            writer.write("</head><body  bgcolor='#E6F0B8'><br>");
            writer.write("<br><img src='..\\data\\WilliamSonama.jpg' alt='WilliamSonamaImg' width='200' height='100'><img src='..\\data\\Prolifics.jpg' alt='Prolifics' align='right' width='200' height='100'><br><br><table border = 1> <col width='500'><col width='500'><col width='500'><col width='500'><tr><td bgcolor=#4A9586 align='center'><b>Project Name</b></td><td align='center'>"+p.get("ProjectName")+"</td>");
            writer.write("\n");
            writer.write("<td bgcolor=#4A9586 align='center'><b></b></td><td align='center'></td></tr>");
            writer.write("\n");
            writer.write("<tr><td bgcolor=#4A9586 align='center'><b>User Name</b></td><td align='center'>"+System.getProperty("user.name")+"</td>");
            writer.write("\n");
            writer.write("<td bgcolor=#4A9586 align='center'><b>Environment</b></td><td align='center'>"+p.get("Environment")+"</td></tr>");
            writer.write("\n");
            writer.write("<tr><td bgcolor=#4A9586 align='center'><b>Host Name</b></td><td align='center'>"+inetAddress.getHostName()+"</td>");
            writer.write("\n");
            writer.write("<td bgcolor=#4A9586 align='center'><b>Date Time</b></td><td align='center'>"+strDate+"</td></tr>");
            writer.write("\n");
            writer.write("<tr><td bgcolor=#4A9586 align='center'><b>Execution Time</b></td><td align='center'>##Execution Time</td>");
            writer.write("\n");
            writer.write("<td bgcolor=#4A9586 align='center'><b>Status</b></td><td align='center'>##Status</td></tr>");
            writer.write("\n");
            writer.write("</tr></table><br><table border = 1 align='center'><tr align='center' bgcolor=#4A9586><td colspan=5><b>Automation Execution Report</b></td></tr><tr><td><font color='GREEN'>Pass</font></td><td width=50>##PassNumber %</td>");
            writer.write("<td rowspan=4><center><div id='piechart'></div></center></td></tr>");
            writer.write("<tr><td><font color='RED'>Fail</font></td><td width=50>##FailNumber %</td></tr><tr><td>On Hold</td><td></td></table><br>");
            writer.write("\n");
            writer.write("<table border = 1 align = 'center'> <col width='100'>");
            writer.write("\n");
            writer.write("<col width='400'><col width='600'><col width='200'><col width='200'><col width='200'><col width='200'><col width='200'><tr><th bgcolor=#4A9586>S.No</th><th bgcolor=#4A9586>API Name</th><th bgcolor=#4A9586>Request</th><th bgcolor=#4A9586>Response</th><th bgcolor=#4A9586>Execution Status</th><th bgcolor=#4A9586>Detailed Status</th>");
            writer.write("\n");
            writer.write("</tr>");
            writer.write("\n");
            writer.write("</table></body></html>");
            writer.close();
        	 } 
        catch (IOException ex) {
            // Report
        }
    }
    public void LogStep(String APIName, String StepStatus) throws FileNotFoundException, IOException
    {
    	 String fileoutput = FrameworkFolder + "\\target\\TempFile.html";
         File fin = new File(ReportfilePath);
         StepNumber = StepNumber + 1;
         if(StepStatus.contains("Pass"))
    	 {
        	 PassCount = PassCount + 1;
    	 }
         if(StepStatus.contains("Fail"))
    	 {
        	 FailCount = FailCount + 1;
    	 }
         FileInputStream fis = new FileInputStream(fin);
         BufferedReader in = new BufferedReader(new InputStreamReader(fis));
         FileWriter fstream = new FileWriter(fileoutput, true);
         BufferedWriter out = new BufferedWriter(fstream);
         String RequestFilePath = null, ResponseFilePath = null;
         if(Files.exists(Paths.get(FrameworkFolder+"\\Results\\ResponseFiles\\"+APIName+".xml"))) 
         { 
        	 if(Files.exists(Paths.get(FrameworkFolder+"\\Results\\RequestFiles\\"+APIName+".xml")))
        		 RequestFilePath = "RequestFiles\\"+APIName+".xml";
        	 else
        		 RequestFilePath = "";
    		 ResponseFilePath 	= "ResponseFiles\\"+APIName+".xml";
        }
         String aLine = null;
         while ((aLine = in.readLine()) != null) {
        	 if(StepStatus.contains("Pass"))
        	 {
        		 if(RequestFilePath.length() > 1)
        			 aLine = aLine.replace("</table></body></html>", "<tr><th>"+ StepNumber + "</th><th><a href='"+RequestFilePath+"'>Request</a></th><th><a href='"+ResponseFilePath+"'>Response</a></th><th><font color ='GREEN'>"+StepStatus+"</font></th><th><a href='HTMLReqResFiles\\"+APIName+".html'>Detailed Status</a></th></tr></table></body></html>");
        		 else
        			 aLine = aLine.replace("</table></body></html>", "<tr><th>"+ StepNumber + "</th><th><a href='"+RequestFilePath+"'>Request</a></th><th><a href='"+ResponseFilePath+"'>Response</a></th><th><font color ='GREEN'>"+StepStatus+"</font></th><th><a href='HTMLReqResFiles\\"+APIName+".html'>Detailed Status</a></th></tr></table></body></html>");
        	 }
        	 if(StepStatus.contains("Fail"))
        	 {
        		 if(RequestFilePath.length() > 1)
        		 	aLine = aLine.replace("</table></body></html>", "<tr><th>"+ StepNumber + "</th><th align='left'><font>"+APIName+"</font></th><th><a href='"+RequestFilePath+"'>Request</a></th><th><a href='"+ResponseFilePath+"'>Response</a></th><th><font color ='RED'>"+StepStatus+"</font></th><th><a href='HTMLReqResFiles\\"+APIName+".html'>Detailed Status</a></th></tr><a id='#Step 1'></a></table></body></html>");
        		 else
        			 aLine = aLine.replace("</table></body></html>", "<tr><th>"+ StepNumber + "</th><th align='left'><font>"+APIName+"</font></th><th><a href='"+RequestFilePath+"'>Request</a></th><th><a href='"+ResponseFilePath+"'>Response</a></th><th><font color ='RED'>"+StepStatus+"</font></th><th><a href='HTMLReqResFiles\\"+APIName+".html'>Detailed Status</a></th></tr><a id='#Step 1'></a></table></body></html>"); 
        			 ConsolidatedStatus = "Fail";
        	 }
        	 out.write(aLine);
 		 	 out.newLine();
         }
         in.close();
         out.close();
         File file = new File(ReportfilePath);
         file.delete();
         File Renamefile = new File(FrameworkFolder + "\\target\\TempFile.html");
         Renamefile.renameTo(file);
         fin = new File(FrameworkFolder + "\\Results\\HTMLReqResFiles\\"+APIName+".html");
         fis = new FileInputStream(fin);
         in = new BufferedReader(new InputStreamReader(fis));
         fstream = new FileWriter(fileoutput, true);
         out = new BufferedWriter(fstream);
         aLine = null;
         while ((aLine = in.readLine()) != null) {
        	 if(StepStatus.contains("Pass"))
        		 aLine = aLine.replace("</table><br><br>", "<tr><th colspan='2' bgcolor=#4A9586 align='left'>Request File Path</th><th align='left'><a href='..\\"+RequestFilePath+"'>Request</a></th></tr><tr><th bgcolor=#4A9586 colspan='2' align='left'>Response File</th><th align='left'><a href='..\\"+ResponseFilePath+"'>Response</a></th></table><br><br>");
        	 out.write(aLine);
 		 	 out.newLine();
         }
         in.close();
         out.close();
         file = new File(FrameworkFolder + "\\Results\\HTMLReqResFiles\\"+APIName+".html");
         file.delete();
         Renamefile = new File(FrameworkFolder + "\\target\\TempFile.html");
         Renamefile.renameTo(file);
    }
    public void CreateLogFile(String RequestType, String ScriptName, String StepStatus) throws IOException
    {
    	String fileoutput = FrameworkFolder + "\\target\\TempFile.html";
    	String fin = FrameworkFolder+ "\\Results\\HTMLReqResFiles\\"+ScriptName+".html";
    	FileInputStream fis = new FileInputStream(fin);
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));
        FileWriter fstream = new FileWriter(fileoutput, true);
        BufferedWriter out = new BufferedWriter(fstream);
    	Writer writer = null;
    	String ReqString = null, ResString = null;
    	//XML xml = new XML(); 
    	String str;
    	 String aLine = null;
         while ((aLine = in.readLine()) != null) 
         {
        	 aLine = aLine.replace("</table></body></html>", "<tr><th>"+ StepNumber + "</th><th><font align='left'>"+ScriptName+"</font></th><th>"+RequestType+"</th><th><font color ='GREEN'>"+StepStatus+"</font></th><th><a href='HTMLReqResFiles\\"+ScriptName+".html'>Detailed Status</a></th></tr></table></body></html>");
        	 out.write(aLine);
        	 out.newLine();
         }
         in.close();
         out.close();
         File file = new File(ReportfilePath);
         file.delete();
         File Renamefile = new File(FrameworkFolder + "\\target\\TempFile.html");
         Renamefile.renameTo(file);
    	 try {
            	writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));            	
            	ReqString = ReqString.replace("<","&lt;");
            	ReqString = ReqString.replace(">","&gt;");
            	ReqString = ReqString.replace("{","{\n");
            	ReqString = ReqString.replace("}","\n}");
            	ResString = ResString.replace(">","&gt;");
            	ResString = ResString.replace("<","&lt;");
            	if(RequestType.contains("POST"))
            		ReqString = ReqString.replace(",",",\n");
            	ResString = ResString.replace("&gt;&lt;","&gt;\n&lt;");
            	if(RequestType.contains("POST"))
            		str = "<table border=1><tr><th bgcolor=#4A9586>Request</th><th  align='left'><pre>"+ReqString+"</pre></th><th bgcolor=#4A9586>Response</th><th align='left'><pre>"+ResString+"</pre></th></tr></table></body></html>";
            	else
            		str = "<table border=1><tr><th bgcolor=#4A9586>Response</th><th align='left'><pre>"+ResString+"</pre></th><th bgcolor=#4A9586>Response</th><th align='left'><pre>"+ResString+"</pre></th></tr></table></body></html>";
            	writer.close();
        	 } 
        catch (IOException ex) {
        }
    }
    public void CreateBaseLogFile(String RequestType,String POST_URL, String ScriptName)
    {
    	String filePath = FrameworkFolder+ "\\Results\\HTMLReqResFiles\\"+ScriptName+".html";
    	
    	Writer writer = null;
    	try {
            	writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "utf-8"));
            	writer.write("<html><h1 align='center'>"+ScriptName+ "</h1><body><table border=1><tr><td bgcolor=#4A9586 colspan='2'><b>Request Type:</b></td><td colspan='2'><b> "+RequestType+"</b></td></tr></table><br><br><table border=1><tr><th bgcolor=#4A9586>Expected Result</th><th bgcolor=#4A9586>Actual Result</th><th bgcolor=#4A9586>Status</th></table></body></html>");
            	writer.write("</body></html>");
            	writer.close();
        	 } 
        catch (IOException ex) {
        }
    }
    public void ReportStep(String APIName, String ExpectedResult, String ActualResult, String Status) throws FileNotFoundException, IOException
    {
    	String fileoutput = FrameworkFolder + "\\Results\\HTMLReqResFiles\\TempFile.html";
    	File fin = new File(FrameworkFolder + "\\Results\\HTMLReqResFiles\\"+APIName+".html");
    	FileInputStream fis = new FileInputStream(fin);
    	BufferedReader in = new BufferedReader(new InputStreamReader(fis));
    	FileWriter fstream = new FileWriter(fileoutput, true);
    	BufferedWriter out = new BufferedWriter(fstream);
        String aLine = null;
        while ((aLine = in.readLine()) != null) {
        	if(Status.contains("Pass"))
        		aLine = aLine.replace("</table></body></html>", "<tr><th align='left'>"+ExpectedResult+"</th><th align='left'>"+ActualResult+"</th><th align='center'><font color ='GREEN'>"+Status+"</th></tr></table></body></html>");
        	else
        		aLine = aLine.replace("</table></body></html>", "<tr><th align='left'>"+ExpectedResult+"</th><th align='left'>"+ActualResult+"</th><th align='center'><font color ='RED'>"+Status+"</th></tr></table></body></html>");
        	out.write(aLine);
        	out.newLine();
        }
        in.close();
        out.close();
        File file = new File(FrameworkFolder + "\\Results\\HTMLReqResFiles\\"+APIName+".html");
        file.delete();
        File Renamefile = new File(FrameworkFolder + "\\Results\\HTMLReqResFiles\\TempFile.html");
        Renamefile.renameTo(file);
    }
    public void ReplaceExecutionTimeNStatus() throws IOException, ParseException, InterruptedException
    {
    	System.out.println("Inside Replace Execution Time N Status function");
    	
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");  
        //strDate = formatter.format(date);
    	Date Enddate = new Date();
        EndDate = formatter.format(Enddate);  
        System.out.println("End DAte now "+Enddate);
    	System.out.println("strDate DAte now "+strDate);
        long diff = formatter.parse(EndDate).getTime() - formatter.parse(strDate).getTime();
        System.out.println(diff);
        long days = TimeUnit.MILLISECONDS.toDays(diff);
        long remainingHoursInMillis = diff - TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(remainingHoursInMillis);
        long remainingMinutesInMillis = remainingHoursInMillis - TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(remainingMinutesInMillis);
        long remainingSecondsInMillis = remainingMinutesInMillis - TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(remainingSecondsInMillis);
        String TimeToExecute = hours +  " hrs: " + minutes + " mins: " + seconds + " secs";
        System.out.println("Inside Replace Execution Time N Status function");
    	 String fileoutput = FrameworkFolder + "\\target\\TempFile.html";
         File fin = new File(ReportfilePath);
         StepNumber = StepNumber + 1;
         FileInputStream fis = new FileInputStream(fin);
         BufferedReader in = new BufferedReader(new InputStreamReader(fis));
         FileWriter fstream = new FileWriter(fileoutput, true);
         BufferedWriter out = new BufferedWriter(fstream);
         String aLine = null;
         System.out.println("Inside Replace Execution Time N Status function");
         while ((aLine = in.readLine()) != null) {
        	 if(ConsolidatedStatus.contains("Pass"))
        		 aLine = aLine.replace("##Status", "<font color ='GREEN'><b>"+ConsolidatedStatus+"</b></font>");
        	 if(ConsolidatedStatus.contains("Fail"))
        		 aLine = aLine.replace("##Status", "<font color ='RED'><b>"+ConsolidatedStatus+"</b></font>");
        	 aLine = aLine.replace("##Execution Time", TimeToExecute);
        	 int TotalNumber = PassCount + FailCount;
        	 int PassPercent = (PassCount * 100)/TotalNumber;
        	 int FailPercent = (FailCount * 100)/TotalNumber;
        	 aLine = aLine.replace("##PassCount", String.valueOf(PassPercent));
        	 aLine = aLine.replace("##FailCount", String.valueOf(FailPercent));
        	 aLine = aLine.replace("##PassNumber", String.valueOf(PassPercent));
        	 aLine = aLine.replace("##FailNumber", String.valueOf(FailPercent));
        	 
        	 //aLine = aLine.replace("##TotalNumber", String.valueOf(TotalNumber));
        	 out.write(aLine);
 		 	 out.newLine();
         }
         in.close();
         out.close();
         File file = new File(ReportfilePath);
         file.delete();
         File Renamefile = new File(FrameworkFolder + "\\target\\TempFile.html");
         Renamefile.renameTo(file);
    }
}
