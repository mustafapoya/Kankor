package net.golbarg.kankor.controller;

import java.awt.*;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Observable;
import org.kordamp.ikonli.javafx.FontIcon;

//this class download a file from url
public class DownloadController extends Observable implements Runnable {

    // Max size of download buffer
    private static final int MAX_BUFFER_SIZE = 1024;
    // These are the status names
    public static final String STRING_STATUES[] = { "Downloading", "Paused", "Complete", "Cancelled", "Error" };
    public static final FontIcon IMG_STATUS[] = { new FontIcon("bi-alarm"), new FontIcon("bi-alarm"),
            new FontIcon("bi-alarm"), new FontIcon("bi-alarm"), new FontIcon("bi-alarm") };

    // These are the status codes.
    public static final int DOWNLOADING = 0;
    public static final int PAUSED = 1;
    public static final int COMPLETE = 2;
    public static final int CANCELLED = 3;
    public static final int ERROR = 4;

    // download URL
    private URL url;
    // size of download in bytes
    private int size;
    // number of bytes downloaded
    private int downloaded;
    // current status of download
    private int status;

    // constructor for download.
    public DownloadController(URL url) {
        this.url = url;
        size = -1;
        downloaded = 0;
        status = DOWNLOADING;

        // Begin the download
        download();
    }

    // Get this download's URL
    public String getUrl() {
        return url.toString();
    }

    // Get the download's size
    public int getSize() {
        return size;
    }

    // Get the download's progress.
    public float getProgress() {
        // from 0% to 100%
//		 return ((float) downloaded / size) * 100;
        // from 0 to 1
        return ((float) downloaded / size);
    }

    // get this downlod's status
    public int getStatus() {
        return status;
    }

    public FontIcon getStatusImage() {
        return IMG_STATUS[status];
    }

    // pause this download
    public void pause() {
        status = PAUSED;
        stateChanged();
    }

    // Resume the Download.
    public void resume() {
        status = DOWNLOADING;
        stateChanged();
        download();
    }

    // Cancel this download
    public void cancel() {
        status = CANCELLED;
        stateChanged();
    }

    // Make this download as having an error
    public void error() {
        status = ERROR;
        stateChanged();
    }

    // Start or resume downloading.
    private void download() {
        Thread thread = new Thread(this);
        thread.start();
    }

    // Get file name portion of url
    public String getFileName(URL url) {
        String fileName = url.getFile();
        return fileName.substring(fileName.lastIndexOf('/') + 1);
    }

    public String getFileName() {
        String fileName = url.getFile();
        return fileName.substring(fileName.lastIndexOf('/') + 1);
    }
    // Download File
    @Override
    public void run() {
        RandomAccessFile file = null;
        InputStream stream = null;
        try {
            // Open connection to URL.
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Specify what portion of file to download.
            connection.setRequestProperty("Range", "bytes=" + downloaded + "-");

            // Connect to server.
            connection.connect();

            // Make sure response code is in the 200 range.
            if (connection.getResponseCode() / 100 != 2) {
                error();
            }

            // Check for valid content length.
            int contentLength = connection.getContentLength();
            if (contentLength < 1) {
                error();
            }

            /*
             * Set the size for this download if it hasn't been already set.
             */
            if (size == -1) {
                size = contentLength;
                stateChanged();
            }

            // Open file and seek to the end of it.
            file = new RandomAccessFile("update/" + getFileName(url), "rw");
            file.seek(downloaded);
            stream = connection.getInputStream();
            while (status == DOWNLOADING) {
                /*
                 * Size buffer according to how much of the file is left to
                 * download.
                 */
                byte buffer[];
                if (size - downloaded > MAX_BUFFER_SIZE) {
                    buffer = new byte[MAX_BUFFER_SIZE];
                } else {
                    buffer = new byte[size - downloaded];
                }

                // Read from server into buffer.
                int read = stream.read(buffer);
                if (read == -1) {
                    break;
                }
                // Write buffer to file.
                file.write(buffer, 0, read);
                downloaded += read;
                stateChanged();
            }

            /*
             * Change status to complete if this point was reached because
             * downloading has finished.
             */
            if (status == DOWNLOADING) {
                status = COMPLETE;
                stateChanged();
            }
        } catch (Exception e) {
            error();
        } finally {
            // Close file.
            if (file != null) {
                try {
                    file.close();
                } catch (Exception e) {
                }
            }
            // Close connection to server.
            if (stream != null) {
                try {
                    stream.close();
                } catch (Exception e) {
                }
            }
        }
    }
    //for table
    public int getDownloaded() {
        return downloaded;
    }
    public static FontIcon getImgStatus(int index) {
        return IMG_STATUS[index];
    }

    // notify observers that this download's status has changed
    public void stateChanged() {
        setChanged();
        notifyObservers();
    }

//	public static void main(String[] args) {
//		try {
//			Download d = new Download(new URL("http://localhost/microcis/update/asa913-k8.bin"));
//			Thread t = new Thread(d);
//			t.start();
//			while(t.isAlive()){
//				System.out.println(d.getProgress());
//			}
//			t.join();
//			System.out.println("downloaded file");
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
}
