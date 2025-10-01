package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name="allData")
    public static String[][] allDataProvider(){

        String testDataFilePath = System.getProperty("user.dir") + "//TestData//TestData.xlsx";
        System.out.println(testDataFilePath);
        int totalRowCount = ReadExcelData.getRowCount(testDataFilePath, "UserData");
        System.out.println(totalRowCount);
        int totalColumnCount = ReadExcelData.getColCount(testDataFilePath, "UserData");
        System.out.println(totalColumnCount);
        String userData[][] = new String[totalRowCount-1][totalColumnCount];

        for(int row = 1 ; row < totalRowCount; row++){
            for (int col = 0; col < totalColumnCount; col++){
                userData[row-1][col] = ReadExcelData.getCellValue(testDataFilePath, "UserData", row, col);
            }
        }


        return userData;
    }

    @DataProvider(name="userNamesData")
    public static String[] getUserName() {
        String testDataFilePath = System.getProperty("user.dir") + "//TestData//TestData.xlsx";
        int totalRowCount = ReadExcelData.getRowCount(testDataFilePath, "UserData");
       // int totalColumnCount = ReadExcelData.getColCount(testDataFilePath, "UserData");

        String userNames[] = new String[totalRowCount-1];

        for(int row = 1; row < totalRowCount; row++){
                userNames[row-1] = ReadExcelData.getCellValue(testDataFilePath, "UserData", row, 1);

        }
        return userNames;
    }


}
