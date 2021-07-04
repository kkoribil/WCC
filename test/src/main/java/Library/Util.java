package Library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util
{	
	public String ConvertXMLFileToString(String PathOfXMLFile) throws IOException
	{
		File xmlFile = new File(PathOfXMLFile); 
		Reader fileReader = new FileReader(xmlFile); 
		BufferedReader bufReader = new BufferedReader(fileReader); 
		StringBuilder sb = new StringBuilder(); 
		String line = bufReader.readLine(); 
		while( line != null){ 
			sb.append(line).append("\n"); 
		line = bufReader.readLine(); } 
		String xml2String = sb.toString(); 
		System.out.println("XML to String using BufferedReader : "); 
		return(xml2String);
	}
	 public String addNumberOfDaysToToday(int NumberOfDays)
	  {
		  Date date = new Date();
		  String DateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
		  DateString = DateString.replace("-", "");
		  Calendar c = Calendar.getInstance();
		  c.setTime(new Date());
		  c.add(Calendar.DAY_OF_MONTH, NumberOfDays); 
		  Date currentDatePlusOne = c.getTime();
		  return(new SimpleDateFormat("MM/dd/yyyy").format(currentDatePlusOne));
	}
	public void WriteTextToFile(String str, String FilePath) throws IOException
	 {
		 File newTextFile = new File(FilePath);
		 FileWriter fw = new FileWriter(newTextFile);
		 fw.write(str);
		 fw.close();
	 }
	/* public boolean validateXMLSchema(String xmlFilePath, BaseTest basetest, String SCHEMA_FILE)
	 {
		    File xsdFile = new File(SCHEMA_FILE);
		    File xmlFile = new File(xmlFilePath);
	        try {
	            Path xmlPath = Paths.get(xmlFilePath);
	            Reader reader = Files.newBufferedReader(xmlPath);
	            String schemaLang = XMLConstants.W3C_XML_SCHEMA_NS_URI;
	            SchemaFactory factory = SchemaFactory.newInstance(schemaLang);
	            Schema schema = factory.newSchema(xsdFile);
	            Validator validator = schema.newValidator();
	            SAXSource source = new SAXSource(new InputSource(reader));
	            validator.validate(source);
	            basetest.test.log(Status.PASS, xmlFile.getName() + " XML File is validation was successfully");
	            System.out.println("The document was validated OK");
	            return(true);
	        } catch (SAXException ex) {
	            Logger lgr = Logger.getLogger(TestJava.class.getName());
	            lgr.log(Level.SEVERE, "The document failed to validate");
	            lgr.log(Level.SEVERE, ex.getMessage(), ex);
	            basetest.test.log(Status.FAIL, ex.getMessage() + " is displayed");
	            return(false);
	        } catch (IOException ex) {
	            Logger lgr = Logger.getLogger(TestJava.class.getName());
	            lgr.log(Level.SEVERE, ex.getMessage(), ex);
	            basetest.test.log(Status.FAIL, ex.getMessage() + " is displaye");
	            return(false);
	        }
	 }*/
	 public void deleteDownloadFolderFiles()
	 {
		 File directory = new File(System.getProperty("user.home") + "\\Downloads");
			File[] files = directory.listFiles();
			for (File file : files)
			{
			if (!file.delete())
			{
			System.out.println("Failed to delete "+file);
			}
			}
	 }
	 static void xlsx(File inputFile, File outputFile) {
         // For storing data into CSV files
        /* StringBuffer data = new StringBuffer();
         try {
                 FileOutputStream fos = new FileOutputStream(outputFile);
                 // Get the workbook object for XLSX file
                 XSSFWorkbook myExcelBook = new XSSFWorkbook(inputFile); 
         		XSSFSheet myExcelSheet = myExcelBook.getSheet(cSheetName); 
                 XSSFWorkbook myExcelBook = new XSSFWorkbook(cFileNameWithPath);
                 XSSFWorkbook wBook = new XSSFWorkbook(new FileInputStream(inputFile));
                 File file = new File(inputFile);
                 XSSFWorkbook wb = new XSSFWorkbook(file);
                 // Get first sheet from the workbook
                 XSSFSheet sheet = wBook.getSheetAt(0);
                 Row row;
                 Cell cell;
                 // Iterate through each rows from first sheet
                 Iterator<Row> rowIterator = sheet.iterator();
                 while (rowIterator.hasNext()) {
                         row = rowIterator.next();
                         // For each row, iterate through each columns
                         Iterator<Cell> cellIterator = row.cellIterator();
                         while (cellIterator.hasNext()) {
                                 cell = cellIterator.next();
                                 switch (cell.getCellType()) {
                                 case Cell.CELL_TYPE_BOOLEAN:
                                         data.append(cell.getBooleanCellValue() + ",");
                                         break;
                                 case Cell.CELL_TYPE_NUMERIC:
                                         data.append(cell.getNumericCellValue() + ",");
                                         break;
                                 case Cell.CELL_TYPE_STRING:
                                         data.append(cell.getStringCellValue() + ",");
                                         break;
                                 case Cell.CELL_TYPE_BLANK:
                                         data.append("" + ",");
                                         break;
                                 default:
                                         data.append(cell + ",");
                                 }
                         }
                 }*/
/*
                 fos.write(data.toString().getBytes());
                 fos.close();
         } catch (Exception ioe) {
                 ioe.printStackTrace();
         }
*/	 }
	 public void deleteFilesInFolder(String PathOfFolder)
	 {
			File dir1 = new File(PathOfFolder);
			for (File file: dir1.listFiles()) {
		        if (!file.isDirectory())
		            file.delete();
		    }
	 }
}