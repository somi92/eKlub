/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import rs.fon.eklub.core.entities.Attendance;
import rs.fon.eklub.core.entities.Category;
import rs.fon.eklub.core.entities.Employee;
import rs.fon.eklub.core.entities.EmployeeEngagement;
import rs.fon.eklub.core.entities.Group;
import rs.fon.eklub.core.entities.Member;
import rs.fon.eklub.core.entities.MembershipFee;
import rs.fon.eklub.core.entities.Payment;
import rs.fon.eklub.core.entities.Training;
import rs.fon.eklub.envelopes.ServiceResponse;
import rs.fon.eklub.mixin.AttendanceSerialization;
import rs.fon.eklub.mixin.EntitySerializationMixin;
import rs.fon.eklub.mixin.MemberSerialization;

/**
 *
 * @author milos
 */
@Component
public class Json2HttpMapper extends MappingJackson2HttpMessageConverter {

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        
        ObjectMapper mapper = getObjectMapper();
        JsonGenerator jsonGenerator = mapper.getFactory().createGenerator(outputMessage.getBody());
        
        try {
            
            if(object instanceof ServiceResponse) {
//                ServiceResponse res = (ServiceResponse) object;
//                Object payload = res.getPayload();
//                if(payload instanceof List<?>) {
//                    
//                } else {
//                    
//                }
                
                mapper.addMixInAnnotations(Attendance.class, AttendanceSerialization.class);
                mapper.addMixInAnnotations(Member.class, MemberSerialization.class);
                mapper.addMixInAnnotations(Attendance.class, EntitySerializationMixin.class);
                mapper.addMixInAnnotations(Category.class, EntitySerializationMixin.class);
                mapper.addMixInAnnotations(Employee.class, EntitySerializationMixin.class);
                mapper.addMixInAnnotations(EmployeeEngagement.class, EntitySerializationMixin.class);
                mapper.addMixInAnnotations(Group.class, EntitySerializationMixin.class);
                mapper.addMixInAnnotations(Member.class, EntitySerializationMixin.class);
                mapper.addMixInAnnotations(MembershipFee.class, EntitySerializationMixin.class);
                mapper.addMixInAnnotations(Payment.class, EntitySerializationMixin.class);
                mapper.addMixInAnnotations(Training.class, EntitySerializationMixin.class);
                mapper.setDateFormat(new SimpleDateFormat("dd.MM.yyyy."));
                mapper.writeValue(jsonGenerator, object);
            } else if(object == null) {
                jsonGenerator.writeNull();
            } else {
                mapper.writeValue(jsonGenerator, object);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
