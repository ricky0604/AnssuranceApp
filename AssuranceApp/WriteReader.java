/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javatestinf2015;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Compaq_Propriétaire
 */
public class WriteReader {
    
    public static void writeFileIntoString(String data, String filePath, String fileEncoding) throws FileNotFoundException, IOException {
        IOUtils.write(data, new FileOutputStream(filePath), fileEncoding);
    }
    
}
