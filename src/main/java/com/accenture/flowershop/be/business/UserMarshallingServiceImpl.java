package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.entity.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.Marshaller;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;

public class UserMarshallingServiceImpl implements UserMarshallingService {

    private Marshaller marshaller;

    @Value("${filePath}")
    private String filePath;

    public void convertFromUserToXML(User user, String filename)
            throws IOException {
        FileOutputStream os = new FileOutputStream(filePath + "/" + filename + ".xml");
        getMarshaller().marshal(user, new StreamResult(os));
        os.close();
    }
    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public Marshaller getMarshaller() {
        return marshaller;
    }
}
