package com.advantage.accountsoap.util.fs;


import com.advantage.accountsoap.util.ArgumentValidationHelper;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;

/**
 * A helper class for file system issues.
 * <br/>
 *
 * @author eli.dali@hpe.com
 */
public abstract class FileSystemHelper {

    private FileSystemHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Check if a file in the given path exists.
     * <br/>
     * This method checks for the existence of an actual file - if there is a directory in
     * the given path, it will not be considered as a file.
     *
     * @param filePath the path of the file to check for it's existence.
     * @return <b>true</b> if a file in the given path exists, or <b>false</b> otherwise.
     * @throws IllegalArgumentException if the given file path argument references
     *                                  <b>null</b>, or if it <b>is</b> a blank string.
     */
    public static boolean isFileExist(final String filePath) {

        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(filePath,
                "file path");
        final File file = new File(filePath);
        return file.exists() && file.isFile() && file.isDirectory() == false;
    }

    /**
     * Check if a directory in the given path exists.
     * <br/>
     * This method checks for the existence of an actual directory - if there is a file in
     * the given path, it will not be considered as a directory.
     *
     * @param directoryPath the path of the directory to check for it's existence.
     * @return <b>true</b> if a directory in the given path exists, or <b>false</b>
     * otherwise.
     */
    public static boolean isDirectoryExist(final String directoryPath) {

        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(directoryPath,
                "directory path");
        final File directory = new File(directoryPath);
        return directory.exists() && directory.isDirectory() && directory.isFile() == false;
    }

    /**
     * Extract the file extension from the given string of file name, or file path.
     * <br/>
     * For example:
     * <ul>
     * <li>
     * if the given file name is &quot;test.txt&quot;, this method will return the
     * string &quot;txt&quot;.
     * </li>
     * <li>
     * if the given file name is &quot;C:/temp/test.txt&quot;, this method will
     * return the string &quot;txt&quot;.
     * </li>
     * <li>
     * if the given file name is &quot;test&quot;, this method will return an empty
     * string (&quot;&quot;).
     * </li>
     * </ul>
     *
     * @param fileNameOrFilePath a string with a name of a file, or a full path of a file.
     * @return file extension, or an empty string, if there is no file extension.
     * @throws IllegalArgumentException if the given argument for file name (or file path),
     *                                  references <b>null</b>, or if it <b>is</b> a blank string.
     */
    public static String extractFileExtension(final String fileNameOrFilePath) {

        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(fileNameOrFilePath,
                "file name");
        final int lastIndexOfDot = fileNameOrFilePath.lastIndexOf(".");
        final String extension;

        if (lastIndexOfDot == -1) {

            extension = StringUtils.EMPTY;
        } else {

            final int indexOfExtension = lastIndexOfDot + 1;
            extension = fileNameOrFilePath.substring(indexOfExtension);
        }

        return extension;
    }

    /**
     * Get the files of the directory in the given path. Optionally, this method can filter
     * the files, so that only files with an extension that matches one of the given
     * file extensions (if passed), will be returned.
     *
     * @param directoryPath the path of the directory to get the files of.
     * @param extensions    an optional argument - if extensions are passed to the method, only
     *                      files with a matching extension will be returned.
     * @return the files of the directory in the given path.
     * @throws IllegalArgumentException if the given directory path argument references
     *                                  <b>null</b>, or if it <b>is</b> a blank string.
     */
    public static File[] getDirectoryFiles(final String directoryPath,
                                           final String... extensions) {

        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(directoryPath,
                "directory path");
        final File directory = new File(directoryPath);
        final File[] files;

        if (extensions == null || extensions.length == 0) {

            files = directory.listFiles();
        } else {

            final FilenameFilter filenameFilter = new ExtensionsFilenameFilter(extensions);
            files = directory.listFiles(filenameFilter);
        }

        return files;
    }

    /**
     * Get a file separator string. This method will try to get the file separator that is
     * configured in the Java system properties. If there is no such a property, it will
     * return, by default, the string <b>&quot;/&quot;</b>.
     *
     * @return a file separator string.
     */
    public static String getFileSeparator() {

        return System.getProperty("file.separator", "/");
    }

    /**
     * Make the directory that has the given path, if it does not exist.
     *
     * @param directoryPath the path of the directory to make.
     * @return <b>true</>, if the directory has been created, or <b>false</b>, if it has not.
     * If the directory existed before the call to this method, <b>false</b> will be
     * returned.
     */
    public static boolean makeDirectory(final String directoryPath) {

        final boolean directoryCreated;

        if (FileSystemHelper.isDirectoryExist(directoryPath)) {

            directoryCreated = false;
        } else {

            final File directory = new File(directoryPath);
            directoryCreated = directory.mkdirs();
        }

        return directoryCreated;
    }

    /**
     * Check if the two given file system paths, are the same. The paths will be considered
     * as matching, even if they don't use the same file separator string, and even if there
     * is no match in the letters case.
     * <br/>
     * For example:
     * <ul>
     * <li>
     * if path 1 is <b>&quot;C:\Temp/test.txt&quot;</b>, and path 2 is
     * <b>&quot;c:/temp/test.TXT&quot;</b>, the method will return <b>true</b>.
     * </li>
     * <li>
     * if path 1 is <b>&quot;C:/Temp&quot;</b>, and path 2 is
     * <b>&quot;c:\temp&quot;</b>, the method will return <b>true</b>.
     * </li>
     * <li>
     * if path 1 is <b>&quot;D:/Temp&quot;</b>, and path 2 is
     * <b>&quot;c:\temp&quot;</b>, the method will return <b>false</b>.
     * </li>
     * </ul>
     *
     * @param path1 a file system path to match.
     * @param path2 a file system path to match.
     * @return <b>true</b>, if path 1 and path 2 are the same path, or <b>false</b>
     * otherwise.
     * @throws IllegalArgumentException if any one of the arguments references <b>null</b>,
     *                                  or <b>is</b> a blank string.
     */
    public static boolean matchingFileSystemPaths(final String path1, final String path2) {

        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(path1, "path 1");
        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(path2, "path 2");
        final String trimmedPath1 = path1.trim();
        final String trimmedPath2 = path2.trim();
        final String fixedPath1 = trimmedPath1.replace('\\', '/');
        final String fixedPath2 = trimmedPath2.replace('\\', '/');
        return fixedPath1.equalsIgnoreCase(fixedPath2);
    }

    /**
     * Utility which reads a CSV (Comma Separated Values) file and converts it to
     * {@link ArrayList} of {@link String} using {@code split} operation.
     * <br/>
     *
     * @param csvFilePath e.g. "/Users/regevb/Downloads/countries_20150630.csv"
     * @return
     */
    public static ArrayList<String> readFileCsv(final String csvFilePath, boolean bufferReadingType, InputStream csvInputStream) {

        BufferedReader csvBuffer = null;
        ArrayList<String> linesResult = new ArrayList<String>();

        try {
            String csvLine;
            if(bufferReadingType){
                csvBuffer = new BufferedReader(new InputStreamReader(csvInputStream));
            }else{
                csvBuffer = new BufferedReader(new FileReader(csvFilePath));
            }


            // How to read file in java line by line?
            while ((csvLine = csvBuffer.readLine()) != null) {
                //System.out.println("Raw CSV data: " + csvLine);
                linesResult.add(csvLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (csvBuffer != null) csvBuffer.close();
            } catch (IOException csvException) {
                csvException.printStackTrace();
            }
        }


        return linesResult;
    }
}