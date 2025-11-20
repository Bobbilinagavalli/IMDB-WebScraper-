package writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import model.Movie;

public class ExcelWriter {

    public void writeMoviesToExcel(List<Movie> movies, String fileName) throws IOException {
    	
    	Workbook workbook = new XSSFWorkbook();
    	Sheet sheet = workbook.createSheet("IMDB Top 25 Movies");
    	Row header = sheet.createRow(0);
    	//header.createCell(0).setCellValue("S.No");
    	header.createCell(0).setCellValue("Rank");
    	header.createCell(1).setCellValue("Movie Name");
    	header.createCell(2).setCellValue("Year of release");
    	header.createCell(3).setCellValue("Movie rating");
    	
    	int rowNum = 1;
    	int serial = 1;
    	
    	 for(Movie movie:movies) {
         
    		 Row row = sheet.createRow(rowNum++);
    		 
    		 //row.createCell(0).setCellValue(serial++);
    		 row.createCell(0).setCellValue(movie.getRank());
    		 row.createCell(1).setCellValue(movie.getTitle());
    		 row.createCell(2).setCellValue(movie.getYear());
    		 row.createCell(3).setCellValue(movie.getRating());
    		 
    		 
         }
    	 FileOutputStream fos = new FileOutputStream(getAvailableFileName(fileName));
		 workbook.write(fos);
		
    	 workbook.close();
		 fos.close();
    }
    private String getAvailableFileName(String fileName) {
        File file = new File(fileName);

        if (!file.exists()) {
            return fileName;
        }

        String baseName = fileName.substring(0, fileName.lastIndexOf("."));
        String extension = fileName.substring(fileName.lastIndexOf("."));

        int count = 1;
        String newName;

        // keep increasing count until a free filename is found
        do {
            newName = baseName + "_" + count + extension;
            count++;
        } while (new File(newName).exists());

        return newName;
    }
}
