package commons;

import java.io.File;
import org.testng.Assert;

public class DownloadFileFunction extends AbstractTest {

  public static void VerifyDownloadedSuccessfully(String fileExtension) {
    String file = fileExtension;
    /* Delete all files in folder */
    deleteAllFileInFolder();
    /* Verify Verify there's a file has been downloaded with any extension */
    waitForDownloadFileContainsNameCompleted(file);
    /* Count number of files on download folder before deleting */
    int countFileBeforeDelete = countFilesInDirectory();
    System.out.println("AFTER DOWNLOAD: " + countFileBeforeDelete);
    /* Verify number of file equals to 1 */
    Assert.assertEquals(countFileBeforeDelete, 1);
    deleteFileContainName(file);
    /* Count number of files on download folder after deleting */
    int countFileAfterDelete = countFilesInDirectory();
    System.out.println("AFTER DELETE: " + countFileAfterDelete);
    /* Verify number of file equals to 0 */
    Assert.assertEquals(countFileAfterDelete, 0);
  }

  public static void deleteAllFileInFolder() {
    try {
      String pathFolderDownload = getPathContainDownload();
      File file = new File(pathFolderDownload);
      File[] listOfFiles = file.listFiles();
      for (int i = 0; i < listOfFiles.length; i++) {
        if (listOfFiles[i].isFile()) {
          new File(listOfFiles[i].toString()).delete();
        }
      }
    } catch (Exception e) {
      System.out.print(e.getMessage());
    }
  }

  public static String getPathContainDownload() {
    String path = "";
    String machine_name;
    machine_name = System.getProperty("user.home");
    path = String.format("%s\\Downloads\\", machine_name);
    return path;
  }

  public void waitForDownloadFileFullnameCompleted(String fileName) {
    int i = 0;
    while (i < 30) {
      boolean exist = isFileExists(fileName);
      if (exist == true) {
        i = 30;
      }
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      i = i + 1;
    }
  }

  public boolean isFileExists(String file) {
    try {
      String pathFolderDownload = getPathContainDownload();
      File files = new File(pathFolderDownload + file);
      boolean exists = files.exists();
      return exists;
    } catch (Exception e) {
      System.out.print(e.getMessage());
      return false;
    }
  }

  public static int countFilesInDirectory() {
    String pathFolderDownload = getPathContainDownload();
    File file = new File(pathFolderDownload);
    int i = 0;
    for (File listOfFiles : file.listFiles()) {
      if (listOfFiles.isFile()) {
        i++;
      }
    }
    return i;
  }

  public void deleteFileFullName(String fileName) {
    if (isFileExists(fileName)) {
      deleteFullName(fileName);
    }
  }

  public void deleteFullName(String fileName) {
    try {
      if (isFileExists(fileName)) {
        String pathFolderDownload = getPathContainDownload();
        File files = new File(pathFolderDownload + fileName);
        files.delete();
      }
    } catch (Exception e) {
      System.out.print(e.getMessage());
    }
  }

  public static void waitForDownloadFileContainsNameCompleted(String fileName) {
    int i = 0;
    while (i < 30) {
      boolean exist = isFileContain(fileName);
      if (exist == true) {
        i = 30;
      }
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      i = i + 1;
    }
  }

  public static boolean isFileContain(String fileName) {
    try {
      boolean flag = false;
      String pathFolderDownload = getPathContainDownload();
      File dir = new File(pathFolderDownload);
      File[] files = dir.listFiles();
      if (files == null || files.length == 0) {
        flag = false;
      }
      for (int i = 1; i < files.length; i++) {
        if (files[i].getName().contains(fileName)) {
          flag = true;
        }
      }
      return flag;
    } catch (Exception e) {
      System.out.print(e.getMessage());
      return false;
    }
  }

  public static void deleteFileContainName(String fileName) {
    deleteContainName(fileName);
  }

  public static void deleteContainName(String fileName) {
    try {
      String files;
      String pathFolderDownload = getPathContainDownload();
      File file = new File(pathFolderDownload);
      File[] listOfFiles = file.listFiles();
      for (int i = 0; i < listOfFiles.length; i++) {
        if (listOfFiles[i].isFile()) {
          files = listOfFiles[i].getName();
          if (files.contains(fileName)) {
            new File(listOfFiles[i].toString()).delete();
          }
        }
      }
    } catch (Exception e) {
      System.out.print(e.getMessage());
    }
  }
}
