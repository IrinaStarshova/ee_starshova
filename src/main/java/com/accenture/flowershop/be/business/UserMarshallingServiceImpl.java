package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.entity.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class UserMarshallingServiceImpl implements UserMarshallingService {

    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    @Value("${filePath}")
    private String filePath;

    public void convertFromUserToXML(User user, String filename)
            throws IOException {
        FileOutputStream os = new FileOutputStream(filePath + "/" + filename + ".xml");
        getMarshaller().marshal(user, new StreamResult(os));
        os.close();
    }

    public Object convertXMLStringToObject(String string) throws IOException {
        return getUnmarshaller().unmarshal(new StreamSource(new StringReader(string)));
    }

    public String convertObjectToXMLString(Object obj) throws IOException {
        StringWriter sw = new StringWriter();
        getMarshaller().marshal(obj, new StreamResult(sw));
        return sw.toString();
    }

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public Marshaller getMarshaller() {
        return marshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }
}
