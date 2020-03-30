package com.advantage.accountsoap.util.fs;

import com.advantage.accountsoap.util.ArgumentValidationHelper;

import java.io.File;
import java.io.FilenameFilter;

/**
 * A file name filter implementation, for filtering files by extensions - useful for filtering
 * directory files of specific types.
 * <br/>
 * <b>The filtering is not case sensitive.</b>
 * <br/>
 *
 * @author eli.dali@hpe.com
 */
public class ExtensionsFilenameFilter implements FilenameFilter {

    private final String[] fileExtensions;

    /**
     * Construct a new instance, for filtering the extensions in the given file extensions
     * array.
     *
     * @param fileExtensions the extensions of the files to filter.
     * @throws IllegalArgumentException if the given file extensions argument references
     *                                  <b>null></b>, or if it <b>is</b> a zero length array.
     */
    public ExtensionsFilenameFilter(final String... fileExtensions) {

        ArgumentValidationHelper.validateArrayArgumentIsNotNullAndNotZeroLength(fileExtensions,
                "file extensions");
        this.fileExtensions = fileExtensions;
    }

    /**
     * Accept the file from the given directory, with the given file name.
     * <br/>
     * This method will check if the given file name, has an extension that matches one of the
     * extensions that are encapsulated in this filter instance.
     *
     * @param directory the directory in which the file was found.
     * @param fileName  the name of the file.
     * @return <b>true</b>, if the file from the given directory, with the given file name,
     * has an extension that matches the one of extensions that are associated with this
     * instance, or <b>false</b>, otherwise.
     * @throws IllegalArgumentException if any one of the given arguments references
     *                                  <b>null</b>, or if the given file name argument <b>is</b> a blank string.
     */
    @Override
    public boolean accept(final File directory, final String fileName) {

        ArgumentValidationHelper.validateArgumentIsNotNull(directory, "directory");
        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(fileName,
                "file name");
        boolean accept = false;

        for (int i = 0; i < fileExtensions.length && accept == false; i++) {

            final String extension = fileExtensions[i];
            final String fileExtension = FileSystemHelper.extractFileExtension(fileName);
            accept = extension.equalsIgnoreCase(fileExtension);
        }

        return accept;
    }
}